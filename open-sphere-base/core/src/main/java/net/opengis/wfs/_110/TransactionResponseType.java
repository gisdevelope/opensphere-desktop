//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2010.01.26 at 02:04:22 PM MST
//


package net.opengis.wfs._110;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 *
 *             The response for a transaction request that was successfully
 *             completed. If the transaction failed for any reason, an
 *             exception report is returned instead.
 *
 *
 * <p>Java class for TransactionResponseType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TransactionResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TransactionSummary" type="{http://www.opengis.net/wfs}TransactionSummaryType"/>
 *         &lt;element name="TransactionResults" type="{http://www.opengis.net/wfs}TransactionResultsType" minOccurs="0"/>
 *         &lt;element name="InsertResults" type="{http://www.opengis.net/wfs}InsertResultsType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="1.1.0" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="TransactionResponse")
@XmlType(name = "TransactionResponseType", propOrder = {
    "transactionSummary",
    "transactionResults",
    "insertResults"
})
public class TransactionResponseType {

    @XmlElement(name = "TransactionSummary", required = true)
    protected TransactionSummaryType transactionSummary;
    @XmlElement(name = "TransactionResults")
    protected TransactionResultsType transactionResults;
    @XmlElement(name = "InsertResults", required = true)
    protected InsertResultsType insertResults;
    @XmlAttribute(name = "version", required = true)
    protected String version;

    /**
     * Gets the value of the transactionSummary property.
     *
     * @return
     *     possible object is
     *     {@link TransactionSummaryType }
     *
     */
    public TransactionSummaryType getTransactionSummary() {
        return transactionSummary;
    }

    /**
     * Sets the value of the transactionSummary property.
     *
     * @param value
     *     allowed object is
     *     {@link TransactionSummaryType }
     *
     */
    public void setTransactionSummary(TransactionSummaryType value) {
        transactionSummary = value;
    }

    /**
     * Gets the value of the transactionResults property.
     *
     * @return
     *     possible object is
     *     {@link TransactionResultsType }
     *
     */
    public TransactionResultsType getTransactionResults() {
        return transactionResults;
    }

    /**
     * Sets the value of the transactionResults property.
     *
     * @param value
     *     allowed object is
     *     {@link TransactionResultsType }
     *
     */
    public void setTransactionResults(TransactionResultsType value) {
        transactionResults = value;
    }

    /**
     * Gets the value of the insertResults property.
     *
     * @return
     *     possible object is
     *     {@link InsertResultsType }
     *
     */
    public InsertResultsType getInsertResults() {
        return insertResults;
    }

    /**
     * Sets the value of the insertResults property.
     *
     * @param value
     *     allowed object is
     *     {@link InsertResultsType }
     *
     */
    public void setInsertResults(InsertResultsType value) {
        insertResults = value;
    }

    /**
     * Gets the value of the version property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getVersion() {
        if (version == null) {
            return "1.1.0";
        } else {
            return version;
        }
    }

    /**
     * Sets the value of the version property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setVersion(String value) {
        version = value;
    }

}