package io.opensphere.importer.config;

import io.opensphere.core.common.configuration.date.DateFormat;
import io.opensphere.core.util.lang.enums.EnumUtilities;

/**
 * The Enum ColumnType.
 */
public enum ColumnType
{
    /** The altitude. */
    ALT("Altitude"),

    /** The DATE. */
    DATE("Date", Category.TEMPORAL),

    /** The TIME. */
    TIME("Time", Category.TEMPORAL),

    /** The TIMESTAMP. */
    TIMESTAMP("Date and Time", Category.TEMPORAL),

    /** The DOWN_DATE. */
    DOWN_DATE("Down Date", Category.TEMPORAL),

    /** The DOWN_TIME. */
    DOWN_TIME("Down Time", Category.TEMPORAL),

    /** DOWN_TIMESTAMP. */
    DOWN_TIMESTAMP("Down Date and Time", Category.TEMPORAL),

    /** The LAT. */
    LAT("Latitude", Category.SPATIAL),

    /** The LOB. */
    LOB("LOB"),

    /** The LON. */
    LON("Longitude", Category.SPATIAL),

    /** The MGRS. */
    MGRS("MGRS", Category.SPATIAL),

    /** The ORIENTATION. */
    ORIENTATION("Orientation"),

    /** The OTHER. */
    OTHER("Other"),

    /** The POSITION. */
    POSITION("Lat. and Lon.", Category.SPATIAL),

    /** The RADIUS. */
    RADIUS("Radius (CEP)"),

    /** The SEMIMAJOR. */
    SEMIMAJOR("Semi-major"),

    /** The SEMIMINOR. */
    SEMIMINOR("Semi-minor"),

    /** WKT GEOMETRY. */
    WKT_GEOMETRY("WKT", Category.SPATIAL),

    /** Association ID. */
    ASSOCIATION_ID("Association ID")

    ;

    /** The display text. */
    private final String myDisplayText;

    /** The category. */
    private final Category myCategory;

    /**
     * Constructor.
     *
     * @param displayText The display text
     */
    ColumnType(String displayText)
    {
        this(displayText, null);
    }

    /**
     * Constructor.
     *
     * @param displayText The display text
     * @param category Optional category of the column type
     */
    ColumnType(String displayText, Category category)
    {
        myDisplayText = displayText;
        myCategory = category;
    }

    /**
     * Gets the category.
     *
     * @return the category
     */
    public Category getCategory()
    {
        return myCategory;
    }

    @Override
    public String toString()
    {
        return myDisplayText;
    }

    /**
     * Retrieve the value for the enum whose toString() matches the given label.
     * This should only be used for enums which override toString(), otherwise
     * valueOf() should be used instead.
     *
     * @param label The label whose equivalent value is desired.
     * @return The matching value if found or {@code null} if no match is
     *         available.
     */
    public static ColumnType fromString(String label)
    {
        return EnumUtilities.fromString(ColumnType.class, label);
    }

    /**
     * Converts a DateFormat.Type to a ColumnType.
     *
     * @param dateFormatType the DateFormat.Type
     * @param isDown True if it's a down time
     * @return the ColumnType
     */
    public static ColumnType fromDateFormatType(DateFormat.Type dateFormatType, boolean isDown)
    {
        ColumnType columnType;
        switch (dateFormatType)
        {
            case DATE:
                columnType = isDown ? DOWN_DATE : DATE;
                break;
            case TIME:
                columnType = isDown ? DOWN_TIME : TIME;
                break;
            case TIMESTAMP:
                columnType = isDown ? DOWN_TIMESTAMP : TIMESTAMP;
                break;
            default:
                columnType = null;
                break;
        }
        return columnType;
    }

    /**
     * Category of a column type.
     */
    public enum Category
    {
        /** Spatial category. */
        SPATIAL,

        /** Temporal category. */
        TEMPORAL;
    }
}