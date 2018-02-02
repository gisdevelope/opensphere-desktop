package io.opensphere.analysis.table.renderers;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import io.opensphere.core.model.GeographicPositionFormat;
import io.opensphere.core.model.LatLonAlt;
import io.opensphere.core.units.angle.DegreesMinutesSeconds;

/** Lon table cell renderer. */
public class LonTableCellRenderer extends DefaultTableCellRenderer
{
    /** The serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The lat format. */
    private final GeographicPositionFormat myFormat;

    /**
     * Constructor.
     *
     * @param format the format
     */
    public LonTableCellRenderer(GeographicPositionFormat format)
    {
        myFormat = format;
        if (myFormat == GeographicPositionFormat.DMSDEG)
        {
            setHorizontalAlignment(SwingConstants.RIGHT);
        }
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        return super.getTableCellRendererComponent(table, formatValue(value), isSelected, hasFocus, row, column);
    }

    /**
     * Calculate the formatted value based on the current format.
     * @param val the value to be formatted
     * @return the formatted value
     */
    private Object formatValue(Object val)
    {
        if (!(val instanceof Double))
        {
            return val;
        }
        Double dbl = (Double) val;
        if (myFormat == GeographicPositionFormat.DMSDEG)
        {
            return DegreesMinutesSeconds.getShortLabelString(dbl, 12, 0, 'E', 'W');
        }
        if (myFormat == GeographicPositionFormat.DEG_DMIN)
        {
            return LatLonAlt.lonToDdmString(dbl, 3);
        }
        return val;
    }
}