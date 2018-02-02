package io.opensphere.core.collada;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.annotation.concurrent.NotThreadSafe;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

import io.opensphere.core.collada.jaxb.ColladaModel;
import io.opensphere.core.collada.jaxb.Effect;
import io.opensphere.core.collada.jaxb.Geometry;
import io.opensphere.core.collada.jaxb.Image;
import io.opensphere.core.collada.jaxb.InstanceGeometry;
import io.opensphere.core.collada.jaxb.InstanceMaterial;
import io.opensphere.core.collada.jaxb.InstanceNode;
import io.opensphere.core.collada.jaxb.Lines;
import io.opensphere.core.collada.jaxb.Material;
import io.opensphere.core.collada.jaxb.NewParam;
import io.opensphere.core.collada.jaxb.Node;
import io.opensphere.core.collada.jaxb.Scene;
import io.opensphere.core.collada.jaxb.Triangles;
import io.opensphere.core.collada.jaxb.VisualScene;
import io.opensphere.core.geometry.AbstractGeometry;
import io.opensphere.core.geometry.PolygonMeshGeometry;
import io.opensphere.core.geometry.constraint.Constraints;
import io.opensphere.core.geometry.renderproperties.PolygonMeshRenderProperties;
import io.opensphere.core.geometry.renderproperties.PolylineRenderProperties;
import io.opensphere.core.image.ImageProvider;
import io.opensphere.core.math.Matrix4d;
import io.opensphere.core.util.XMLUtilities;
import io.opensphere.core.util.collections.CollectionUtilities;
import io.opensphere.core.util.collections.New;
import io.opensphere.core.util.lang.Pair;

/**
 * Parses COLLADA models and generates {@link PolygonMeshGeometry}s from them.
 */
@SuppressWarnings("PMD.GodClass")
@NotThreadSafe
public class ColladaParser
{
    /** Logger reference. */
    private static final Logger LOGGER = Logger.getLogger(ColladaParser.class);

    /** Map of image ids to images. */
    private final Map<String, io.opensphere.core.collada.jaxb.Image> myImageLibrary = New.map();

    /** Map of effect ids to effects. */
    private final Map<String, io.opensphere.core.collada.jaxb.Effect> myEffectLibrary = New.map();

    /** Map of material names to materials. */
    private final Map<String, io.opensphere.core.collada.jaxb.Material> myNameMaterialLibrary = New.map();

    /** Map of material IDs to materials. */
    private final Map<String, io.opensphere.core.collada.jaxb.Material> myIdMaterialLibrary = New.map();

    /** Map of geometry ids to geometries. */
    private final Map<String, io.opensphere.core.collada.jaxb.Geometry> myGeomLibrary = New.map();

    /** Map of node ids to nodes. */
    private final Map<String, io.opensphere.core.collada.jaxb.Node> myNodeLibrary = New.map();

    /** Keep track of unused tags. */
    private final Set<String> myUnusedTags = New.set();

    /** Parser for triangles. */
    private final ColladaMeshTrianglesParser myTriangleParser;

    /** Parser for lines. */
    private final ColladaMeshLinesParser myLineParser;

    /**
     * Constructor.
     *
     * @param meshProps Render properties for polygon mesh geometries generated
     *            by this parser.
     * @param lineProps Render properties for polyline geometries generated by
     *            this parser.
     * @param constraints The constraints to be used in the result geometries.
     */
    public ColladaParser(PolylineRenderProperties lineProps, PolygonMeshRenderProperties meshProps, Constraints constraints)
    {
        this(lineProps, meshProps, constraints, null);
    }

    /**
     * Constructor.
     *
     * @param meshProps Render properties for polygon mesh geometries generated
     *            by this parser.
     * @param lineProps Render properties for polyline geometries generated by
     *            this parser.
     * @param constraints The constraints to be used in the result geometries.
     * @param imageProvider the image provider
     */
    public ColladaParser(PolylineRenderProperties lineProps, PolygonMeshRenderProperties meshProps, Constraints constraints,
            ImageProvider<Pair<Image, UUID>> imageProvider)
    {
        myTriangleParser = meshProps != null ? new ColladaMeshTrianglesParser(meshProps, constraints, imageProvider) : null;
        myLineParser = lineProps != null ? new ColladaMeshLinesParser(lineProps, constraints) : null;
    }

    /**
     * Parses the COLLADA models from an input stream and generates.
     *
     * @param is The stream containing the marshalled models.
     * @param results Collection to contain the result geometries.
     * @return the COLLADA model
     * @throws JAXBException If there is an error parsing the input.
     *             {@link PolygonMeshGeometry}s.
     */
    public ColladaModel parseModels(InputStream is, Collection<? super AbstractGeometry> results) throws JAXBException
    {
        ColladaModel model = XMLUtilities.readXMLObject(new StreamSource(is), ColladaModel.class);

        populateLibrary(model);

        Scene scene = model.getScene();
        String visualSceneUrl = scene.getVisualScene().getUrl();

        for (VisualScene visualScene : model.getVisualScenes())
        {
            if (visualSceneUrl.equals("#" + visualScene.getId()))
            {
                List<Node> nodes = visualScene.getNodes();
                for (Node node : nodes)
                {
                    transformNode(node, null, results);
                }
            }
        }

        if (!myUnusedTags.isEmpty())
        {
            LOGGER.warn("Unused tags: " + myUnusedTags);
        }

        return model;
    }

    /**
     * Populates the library maps with components from the model.
     *
     * @param model the model
     */
    private void populateLibrary(ColladaModel model)
    {
        if (model.getLibraryImages() != null && model.getLibraryImages().getImages() != null)
        {
            for (io.opensphere.core.collada.jaxb.Image image : model.getLibraryImages().getImages())
            {
                myImageLibrary.put(image.getId(), image);
            }
//            myUnusedTags.addAll(myImageLibrary.keySet());
        }

        if (model.getLibraryEffects() != null)
        {
            for (io.opensphere.core.collada.jaxb.Effect effect : model.getLibraryEffects().getEffects())
            {
                myEffectLibrary.put("#" + effect.getId(), effect);
            }
//            myUnusedTags.addAll(myEffectLibrary.keySet());
        }

        if (model.getLibraryMaterials() != null)
        {
            for (io.opensphere.core.collada.jaxb.Material material : model.getLibraryMaterials().getMaterials())
            {
                myNameMaterialLibrary.put(material.getName(), material);
                myIdMaterialLibrary.put("#" + material.getId(), material);
            }
//            myUnusedTags.addAll(myNameMaterialLibrary.keySet());
        }

        for (io.opensphere.core.collada.jaxb.Geometry geometry : model.getLibraryGeometries())
        {
            myGeomLibrary.put("#" + geometry.getId(), geometry);
        }
        myUnusedTags.addAll(myGeomLibrary.keySet());

        for (Node node : model.getLibraryNodes())
        {
            addNodeToLibrary(node);
        }
        myUnusedTags.addAll(myNodeLibrary.keySet());
    }

    /**
     * Adds the node to library.
     *
     * @param node The node.
     */
    private void addNodeToLibrary(Node node)
    {
        if (node.getId() != null)
        {
            myNodeLibrary.put("#" + node.getId(), node);
        }
        node.getNodes().forEach(n -> addNodeToLibrary(n));
    }

    /**
     * Generate geometries from a COLLADA node.
     *
     * @param node The node.
     * @param inMatrix The in matrix.
     * @param results The results.
     */
    private void transformNode(Node node, Matrix4d inMatrix, Collection<? super AbstractGeometry> results)
    {
        Matrix4d nodeMatrix;
        if (node.getMatrix() == null)
        {
            nodeMatrix = null;
        }
        else
        {
            nodeMatrix = new Matrix4d();
            nodeMatrix.set(node.getMatrix(), true);
        }
        Matrix4d matrix = nodeMatrix == null ? inMatrix : inMatrix == null ? nodeMatrix : inMatrix.mult(nodeMatrix);

        transformInstanceGeometries(node, matrix, results);

        for (Node child : node.getNodes())
        {
            myUnusedTags.remove("#" + child.getId());
            transformNode(child, matrix, results);
        }

        for (InstanceNode ref : node.getInstanceNodes())
        {
            myUnusedTags.remove(ref.getUrl());
            Node deref = myNodeLibrary.get(ref.getUrl());
            if (deref == null)
            {
                LOGGER.warn("Node instance with url " + ref.getUrl() + " not found");
            }
            else
            {
                transformNode(deref, matrix, results);
            }
        }
    }

    /**
     * Transform instance geometries.
     *
     * @param node The node.
     * @param matrix The matrix.
     * @param results The results.
     */
    private void transformInstanceGeometries(Node node, Matrix4d matrix, Collection<? super AbstractGeometry> results)
    {
        List<InstanceGeometry> instanceGeometries = node.getInstanceGeometries();
        for (InstanceGeometry geomInstance : instanceGeometries)
        {
            String geomUrl = geomInstance.getUrl();
            myUnusedTags.remove(geomUrl);
            Geometry geom = myGeomLibrary.get(geomUrl);
            if (geom != null)
            {
                GeometryInfo geomInfo = new GeometryInfo(geomInstance, geom);
                if (CollectionUtilities.hasContent(geom.getMesh().getTriangles()))
                {
                    for (Triangles triangle : geom.getMesh().getTriangles())
                    {
                        Material material = getMaterial(triangle.getMaterial(), geomInstance);
                        Effect effect = getEffect(material);
                        Image image = getImage(effect);
                        geomInfo.addShape(new ShapeInfo<>(triangle, material, effect, image));
                    }
                }
                if (CollectionUtilities.hasContent(geom.getMesh().getLines()))
                {
                    for (Lines line : geom.getMesh().getLines())
                    {
                        Material material = getMaterial(line.getMaterial(), geomInstance);
                        Effect effect = getEffect(material);
                        Image image = getImage(effect);
                        geomInfo.addShape(new ShapeInfo<>(line, material, effect, image));
                    }
                }

                transformGeometry(geomInfo, matrix, results);
            }
            else
            {
                LOGGER.warn("Geometry with id " + geomUrl + " not found.");
            }
        }
    }

    /**
     * Transform the geometry.
     *
     * @param geomInfo The geometry info.
     * @param matrix The matrix.
     * @param results The results.
     */
    private void transformGeometry(GeometryInfo geomInfo, Matrix4d matrix, Collection<? super AbstractGeometry> results)
    {
        Geometry geom = geomInfo.getGeometry();
        if (geom.getMesh() == null)
        {
            LOGGER.warn("No mesh for geometry " + geom.getId());
        }
        else
        {
            boolean foundSomething = false;
            if (myTriangleParser != null)
            {
                foundSomething |= myTriangleParser.parseGeometryTriangles(geomInfo, matrix, results);
            }
            if (myLineParser != null)
            {
                foundSomething |= myLineParser.parseGeometryLines(geomInfo, matrix, results);
            }

            if (!foundSomething && LOGGER.isDebugEnabled())
            {
                LOGGER.debug("No lines or triangles found in mesh for geometry " + geom.getId());
            }
        }
    }

    /**
     * Gets the material for the triangle.
     *
     * @param materialName the material name
     * @param instanceGeometry the instance geometry
     * @return the material
     */
    private Material getMaterial(String materialName, InstanceGeometry instanceGeometry)
    {
        Material material = myNameMaterialLibrary.get(materialName);
        if (material == null)
        {
            List<InstanceMaterial> instanceMaterials = instanceGeometry.getBindMaterial().getTechniqueCommon()
                    .getInstanceMaterials();
            InstanceMaterial instanceMaterial = instanceMaterials.stream().filter(m -> materialName.equals(m.getSymbol()))
                    .findAny().orElse(null);
            if (instanceMaterial != null)
            {
                material = myIdMaterialLibrary.get(instanceMaterial.getTarget());
            }
        }
        return material;
    }

    /**
     * Gets the effect for the material.
     *
     * @param material the material
     * @return the effect
     */
    private Effect getEffect(Material material)
    {
        Effect effect = null;
        if (material != null)
        {
            String effectId = material.getInstanceEffect().getUrl();
            effect = myEffectLibrary.get(effectId);
        }
        return effect;
    }

    /**
     * Gets the image for the effect.
     *
     * @param effect the effect
     * @return the image
     */
    private Image getImage(Effect effect)
    {
        Image image = null;
        if (effect != null && effect.getProfileCommon().getNewParams() != null)
        {
            Optional<NewParam> surfaceParam = effect.getProfileCommon().getNewParams().stream()
                    .filter(p -> p.getSurface() != null).findAny();
            if (surfaceParam.isPresent())
            {
                String imageId = surfaceParam.get().getSurface().getInitFrom();
                image = myImageLibrary.get(imageId);
            }
        }
        return image;
    }
}