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
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LabelStyleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LabelStyleType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/kml/2.2}AbstractColorStyleType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/kml/2.2}scale" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/kml/2.2}LabelStyleSimpleExtensionGroup" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/kml/2.2}LabelStyleObjectExtensionGroup" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LabelStyleType", propOrder = {
    "scale",
    "labelStyleSimpleExtensionGroup",
    "labelStyleObjectExtensionGroup"
})
public class LabelStyleType
    extends AbstractColorStyleType
{

    @XmlElementRef(name = "scale", namespace = "http://www.opengis.net/kml/2.2", type = ScaleScalar.class, required = false)
    protected ScaleScalar scale;
    @XmlElement(name = "LabelStyleSimpleExtensionGroup")
    @XmlSchemaType(name = "anySimpleType")
    protected List<Object> labelStyleSimpleExtensionGroup;
    @XmlElement(name = "LabelStyleObjectExtensionGroup")
    protected List<AbstractObjectType> labelStyleObjectExtensionGroup;

    /**
     * Gets the value of the scale property.
     * 
     * @return
     *     possible object is
     *     {@link ScaleScalar }
     *     
     */
    public ScaleScalar getScale() {
        return scale;
    }

    /**
     * Sets the value of the scale property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScaleScalar }
     *     
     */
    public void setScale(ScaleScalar value) {
        this.scale = value;
    }

    public boolean isSetScale() {
        return (this.scale!= null);
    }

    /**
     * Gets the value of the labelStyleSimpleExtensionGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the labelStyleSimpleExtensionGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLabelStyleSimpleExtensionGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getLabelStyleSimpleExtensionGroup() {
        if (labelStyleSimpleExtensionGroup == null) {
            labelStyleSimpleExtensionGroup = new ArrayList<Object>();
        }
        return this.labelStyleSimpleExtensionGroup;
    }

    public boolean isSetLabelStyleSimpleExtensionGroup() {
        return ((this.labelStyleSimpleExtensionGroup!= null)&&(!this.labelStyleSimpleExtensionGroup.isEmpty()));
    }

    public void unsetLabelStyleSimpleExtensionGroup() {
        this.labelStyleSimpleExtensionGroup = null;
    }

    /**
     * Gets the value of the labelStyleObjectExtensionGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the labelStyleObjectExtensionGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLabelStyleObjectExtensionGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AbstractObjectType }
     * 
     * 
     */
    public List<AbstractObjectType> getLabelStyleObjectExtensionGroup() {
        if (labelStyleObjectExtensionGroup == null) {
            labelStyleObjectExtensionGroup = new ArrayList<AbstractObjectType>();
        }
        return this.labelStyleObjectExtensionGroup;
    }

    public boolean isSetLabelStyleObjectExtensionGroup() {
        return ((this.labelStyleObjectExtensionGroup!= null)&&(!this.labelStyleObjectExtensionGroup.isEmpty()));
    }

    public void unsetLabelStyleObjectExtensionGroup() {
        this.labelStyleObjectExtensionGroup = null;
    }

}