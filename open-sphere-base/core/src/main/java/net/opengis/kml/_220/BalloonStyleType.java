//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.02.22 at 10:22:41 AM MST 
//


package net.opengis.kml._220;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for BalloonStyleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BalloonStyleType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/kml/2.2}AbstractSubStyleType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/kml/2.2}color" minOccurs="0"/>
 *           &lt;element ref="{http://www.opengis.net/kml/2.2}bgColor" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.opengis.net/kml/2.2}textColor" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/kml/2.2}text" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/kml/2.2}displayMode" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/kml/2.2}BalloonStyleSimpleExtensionGroup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/kml/2.2}BalloonStyleObjectExtensionGroup" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BalloonStyleType", propOrder = {
    "color",
    "bgColor",
    "textColor",
    "text",
    "displayMode",
    "balloonStyleSimpleExtensionGroup",
    "balloonStyleObjectExtensionGroup"
})
public class BalloonStyleType
    extends AbstractSubStyleType
{

    @XmlElement(type = String.class, defaultValue = "ffffffff")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] color;
    @XmlElement(type = String.class, defaultValue = "ffffffff")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] bgColor;
    @XmlElement(type = String.class, defaultValue = "ff000000")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] textColor;
    protected String text;
    @XmlElement(defaultValue = "default")
    @XmlSchemaType(name = "string")
    protected DisplayModeEnumType displayMode;
    @XmlElement(name = "BalloonStyleSimpleExtensionGroup")
    @XmlSchemaType(name = "anySimpleType")
    protected List<Object> balloonStyleSimpleExtensionGroup;
    @XmlElement(name = "BalloonStyleObjectExtensionGroup")
    protected List<AbstractObjectType> balloonStyleObjectExtensionGroup;

    /**
     * Gets the value of the color property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getColor() {
        return color;
    }

    /**
     * Sets the value of the color property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColor(byte[] value) {
        this.color = value;
    }

    public boolean isSetColor() {
        return (this.color!= null);
    }

    /**
     * Gets the value of the bgColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getBgColor() {
        return bgColor;
    }

    /**
     * Sets the value of the bgColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBgColor(byte[] value) {
        this.bgColor = value;
    }

    public boolean isSetBgColor() {
        return (this.bgColor!= null);
    }

    /**
     * Gets the value of the textColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getTextColor() {
        return textColor;
    }

    /**
     * Sets the value of the textColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextColor(byte[] value) {
        this.textColor = value;
    }

    public boolean isSetTextColor() {
        return (this.textColor!= null);
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

    public boolean isSetText() {
        return (this.text!= null);
    }

    /**
     * Gets the value of the displayMode property.
     * 
     * @return
     *     possible object is
     *     {@link DisplayModeEnumType }
     *     
     */
    public DisplayModeEnumType getDisplayMode() {
        return displayMode;
    }

    /**
     * Sets the value of the displayMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link DisplayModeEnumType }
     *     
     */
    public void setDisplayMode(DisplayModeEnumType value) {
        this.displayMode = value;
    }

    public boolean isSetDisplayMode() {
        return (this.displayMode!= null);
    }

    /**
     * Gets the value of the balloonStyleSimpleExtensionGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the balloonStyleSimpleExtensionGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBalloonStyleSimpleExtensionGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getBalloonStyleSimpleExtensionGroup() {
        if (balloonStyleSimpleExtensionGroup == null) {
            balloonStyleSimpleExtensionGroup = new ArrayList<Object>();
        }
        return this.balloonStyleSimpleExtensionGroup;
    }

    public boolean isSetBalloonStyleSimpleExtensionGroup() {
        return ((this.balloonStyleSimpleExtensionGroup!= null)&&(!this.balloonStyleSimpleExtensionGroup.isEmpty()));
    }

    public void unsetBalloonStyleSimpleExtensionGroup() {
        this.balloonStyleSimpleExtensionGroup = null;
    }

    /**
     * Gets the value of the balloonStyleObjectExtensionGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the balloonStyleObjectExtensionGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBalloonStyleObjectExtensionGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AbstractObjectType }
     * 
     * 
     */
    public List<AbstractObjectType> getBalloonStyleObjectExtensionGroup() {
        if (balloonStyleObjectExtensionGroup == null) {
            balloonStyleObjectExtensionGroup = new ArrayList<AbstractObjectType>();
        }
        return this.balloonStyleObjectExtensionGroup;
    }

    public boolean isSetBalloonStyleObjectExtensionGroup() {
        return ((this.balloonStyleObjectExtensionGroup!= null)&&(!this.balloonStyleObjectExtensionGroup.isEmpty()));
    }

    public void unsetBalloonStyleObjectExtensionGroup() {
        this.balloonStyleObjectExtensionGroup = null;
    }

}