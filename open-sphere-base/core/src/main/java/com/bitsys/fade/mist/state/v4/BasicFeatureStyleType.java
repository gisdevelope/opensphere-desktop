//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.02.22 at 10:22:41 AM MST 
//


package com.bitsys.fade.mist.state.v4;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for BasicFeatureStyleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BasicFeatureStyleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="altitudeColumn" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="altColUnits" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="altitudeRef" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="labelColor" type="{http://www.bit-sys.com/mist/state/v4}HexColorType" minOccurs="0"/>
 *         &lt;element name="labelColumn" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="labelColumns" type="{http://www.bit-sys.com/mist/state/v4}LabelColumnsType" minOccurs="0"/>
 *         &lt;element name="labelSize" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="lift" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="pointColor" type="{http://www.bit-sys.com/mist/state/v4}HexColorType" minOccurs="0"/>
 *         &lt;element name="pointOpacity" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="pointSize" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="useAltitude" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BasicFeatureStyleType", propOrder = {

})
public class BasicFeatureStyleType {

    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String altitudeColumn;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String altColUnits;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String altitudeRef;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String labelColor;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String labelColumn;
    protected LabelColumnsType labelColumns;
    protected BigInteger labelSize;
    protected BigDecimal lift;
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String pointColor;
    protected Integer pointOpacity;
    protected BigDecimal pointSize;
    protected Boolean useAltitude;

    /**
     * Gets the value of the altitudeColumn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAltitudeColumn() {
        return altitudeColumn;
    }

    /**
     * Sets the value of the altitudeColumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAltitudeColumn(String value) {
        this.altitudeColumn = value;
    }

    public boolean isSetAltitudeColumn() {
        return (this.altitudeColumn!= null);
    }

    /**
     * Gets the value of the altColUnits property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAltColUnits() {
        return altColUnits;
    }

    /**
     * Sets the value of the altColUnits property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAltColUnits(String value) {
        this.altColUnits = value;
    }

    public boolean isSetAltColUnits() {
        return (this.altColUnits!= null);
    }

    /**
     * Gets the value of the altitudeRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAltitudeRef() {
        return altitudeRef;
    }

    /**
     * Sets the value of the altitudeRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAltitudeRef(String value) {
        this.altitudeRef = value;
    }

    public boolean isSetAltitudeRef() {
        return (this.altitudeRef!= null);
    }

    /**
     * Gets the value of the labelColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelColor() {
        return labelColor;
    }

    /**
     * Sets the value of the labelColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelColor(String value) {
        this.labelColor = value;
    }

    public boolean isSetLabelColor() {
        return (this.labelColor!= null);
    }

    /**
     * Gets the value of the labelColumn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelColumn() {
        return labelColumn;
    }

    /**
     * Sets the value of the labelColumn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelColumn(String value) {
        this.labelColumn = value;
    }

    public boolean isSetLabelColumn() {
        return (this.labelColumn!= null);
    }

    /**
     * Gets the value of the labelColumns property.
     * 
     * @return
     *     possible object is
     *     {@link LabelColumnsType }
     *     
     */
    public LabelColumnsType getLabelColumns() {
        return labelColumns;
    }

    /**
     * Sets the value of the labelColumns property.
     * 
     * @param value
     *     allowed object is
     *     {@link LabelColumnsType }
     *     
     */
    public void setLabelColumns(LabelColumnsType value) {
        this.labelColumns = value;
    }

    public boolean isSetLabelColumns() {
        return (this.labelColumns!= null);
    }

    /**
     * Gets the value of the labelSize property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLabelSize() {
        return labelSize;
    }

    /**
     * Sets the value of the labelSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLabelSize(BigInteger value) {
        this.labelSize = value;
    }

    public boolean isSetLabelSize() {
        return (this.labelSize!= null);
    }

    /**
     * Gets the value of the lift property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLift() {
        return lift;
    }

    /**
     * Sets the value of the lift property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLift(BigDecimal value) {
        this.lift = value;
    }

    public boolean isSetLift() {
        return (this.lift!= null);
    }

    /**
     * Gets the value of the pointColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPointColor() {
        return pointColor;
    }

    /**
     * Sets the value of the pointColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointColor(String value) {
        this.pointColor = value;
    }

    public boolean isSetPointColor() {
        return (this.pointColor!= null);
    }

    /**
     * Gets the value of the pointOpacity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPointOpacity() {
        return pointOpacity;
    }

    /**
     * Sets the value of the pointOpacity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPointOpacity(Integer value) {
        this.pointOpacity = value;
    }

    public boolean isSetPointOpacity() {
        return (this.pointOpacity!= null);
    }

    /**
     * Gets the value of the pointSize property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPointSize() {
        return pointSize;
    }

    /**
     * Sets the value of the pointSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPointSize(BigDecimal value) {
        this.pointSize = value;
    }

    public boolean isSetPointSize() {
        return (this.pointSize!= null);
    }

    /**
     * Gets the value of the useAltitude property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseAltitude() {
        return useAltitude;
    }

    /**
     * Sets the value of the useAltitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseAltitude(Boolean value) {
        this.useAltitude = value;
    }

    public boolean isSetUseAltitude() {
        return (this.useAltitude!= null);
    }

}