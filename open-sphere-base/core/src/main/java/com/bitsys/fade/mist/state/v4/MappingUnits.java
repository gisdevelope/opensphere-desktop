//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.02.22 at 10:22:41 AM MST 
//


package com.bitsys.fade.mist.state.v4;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MappingUnits.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MappingUnits">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value=""/>
 *     &lt;enumeration value="km"/>
 *     &lt;enumeration value="m"/>
 *     &lt;enumeration value="nmi"/>
 *     &lt;enumeration value="mi"/>
 *     &lt;enumeration value="ft"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MappingUnits")
@XmlEnum
public enum MappingUnits {

    @XmlEnumValue("")
    VALUE_1(""),
    @XmlEnumValue("km")
    VALUE_2("km"),
    @XmlEnumValue("m")
    VALUE_3("m"),
    @XmlEnumValue("nmi")
    VALUE_4("nmi"),
    @XmlEnumValue("mi")
    VALUE_5("mi"),
    @XmlEnumValue("ft")
    VALUE_6("ft");
    private final String value;

    MappingUnits(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MappingUnits fromValue(String v) {
        for (MappingUnits c: MappingUnits.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}