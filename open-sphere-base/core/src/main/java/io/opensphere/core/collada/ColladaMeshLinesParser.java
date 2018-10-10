package io.opensphere.core.collada;

import java.awt.Color;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import io.opensphere.core.collada.jaxb.Geometry;
import io.opensphere.core.collada.jaxb.Input;
import io.opensphere.core.collada.jaxb.Lines;
import io.opensphere.core.collada.jaxb.Mesh;
import io.opensphere.core.geometry.AbstractGeometry;
import io.opensphere.core.geometry.PolylineGeometry;
import io.opensphere.core.geometry.constraint.Constraints;
import io.opensphere.core.geometry.renderproperties.PolylineRenderProperties;
import io.opensphere.core.math.Matrix4d;
import io.opensphere.core.model.ModelPosition;
import io.opensphere.core.util.collections.CollectionUtilities;
import io.opensphere.core.util.collections.New;

/**
 * Parser for COLLADA mesh lines.
 */
public class ColladaMeshLinesParser
{
    /** Logger reference. */
    private static final Logger LOGGER = Logger.getLogger(ColladaMeshLinesParser.class);

    /**
     * The constraints to be used in the result geometries.
     */
    private final Constraints myConstraints;

    /** The line geometry builder. */
    private final PolylineGeometry.Builder<ModelPosition> myLineBuilder = new PolylineGeometry.Builder<>();

    /**
     * Render properties for polygon mesh geometries generated by this parser.
     */
    private final PolylineRenderProperties myPolylineProperties;

    /**
     * Constructor.
     *
     * @param lineProps The line props.
     * @param constraints The constraints.
     */
    public ColladaMeshLinesParser(PolylineRenderProperties lineProps, Constraints constraints)
    {
        myPolylineProperties = lineProps;
        myConstraints = constraints;
    }

    /**
     * Parses the lines in a COLLADA mesh and generates {@link PolylineGeometry}
     * s.
     *
     * @param geomInfo The geometry info.
     * @param matrix Optional transform matrix to be applied to the geometries'
     *            coordinates.
     * @param results The return collection.
     * @return {@code true} if any geometries were produced.
     */
    public boolean parseGeometryLines(GeometryInfo geomInfo, Matrix4d matrix, Collection<? super AbstractGeometry> results)
    {
        boolean foundSomething = false;
        Geometry geom = geomInfo.getGeometry();
        if (CollectionUtilities.hasContent(geomInfo.getShapes()))
        {
            List<ShapeInfo<?>> shapes = geomInfo.getShapes().stream().filter(s -> s.getShape() instanceof Lines)
                    .collect(Collectors.toList());
            for (ShapeInfo<?> shape : shapes)
            {
                @SuppressWarnings("unchecked")
                ShapeInfo<Lines> lines = (ShapeInfo<Lines>)shape;
                if (lines.getShape().getPrimitives() == null)
                {
                    LOGGER.warn("Lines with no primitives found for geometry " + geom.getId());
                }
                else
                {
                    results.addAll(parseGeometryLines(geomInfo.getGeometry(), lines, matrix));
                    foundSomething = true;
                }
            }
        }
        return foundSomething;
    }

    /**
     * Parses a COLLADA "lines" model and generates {@link PolylineGeometry}s.
     *
     * @param geometry The COLLADA geometry.
     * @param line The line to be parsed.
     * @param matrix Optional transform matrix to be applied to the geometries'
     *            coordinates.
     * @return The result geometry or {@code null} if one was not produced.
     */
    private Collection<PolylineGeometry> parseGeometryLines(Geometry geometry, ShapeInfo<Lines> line, Matrix4d matrix)
    {
        Lines lines = line.getShape();

        Optional<Input> vertexInput = lines.getInputs().stream().filter(i -> "VERTEX".equals(i.getSemantic())).findAny();
        if (!vertexInput.isPresent())
        {
            LOGGER.warn("No vertex input found for geometry " + geometry.getId());
            return null;
        }

        Mesh mesh = geometry.getMesh();
        String positionSourceId = ColladaUtilities.getVerticesIdToSourceIdMap(mesh, "POSITION")
                .get(vertexInput.get().getSource());

        float[] positionData = ColladaUtilities.getDataFromSources(mesh, positionSourceId);

        if (positionData == null)
        {
            LOGGER.warn("No position data found for geometry " + geometry.getId() + " with tag " + positionSourceId);
            return null;
        }

        int[] primitives = lines.getPrimitives();
        int maxOffset = lines.getInputs().stream().mapToInt(i -> i.getOffset()).max().getAsInt();
        int vertexOffset = vertexInput.get().getOffset();

        List<ModelPosition> positions = ColladaUtilities.getPositions(vertexOffset, maxOffset + 1, positionData, primitives,
                matrix);

        PolylineRenderProperties renderProps = myPolylineProperties;
        String colorString = line.getEffect().getProfileCommon().getTechnique().getColor();
        Color color = ColladaUtilities.parseColor(colorString);
        if (color != null)
        {
            renderProps = myPolylineProperties.clone();
            renderProps.setColor(color);
        }

        myLineBuilder.setDataModelId(geometry.hashCode());
        Collection<PolylineGeometry> results = New.collection();
        for (int index = 0; index < positions.size() - 1; index += 2)
        {
            myLineBuilder.setVertices(positions.subList(index, index + 2));
            results.add(new PolylineGeometry(myLineBuilder, renderProps, myConstraints));
        }

        return results;
    }
}
