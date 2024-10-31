
package Klassen;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für university complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="university"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="lectures" type="{}lectures"/&gt;
 *         &lt;element name="curricula" type="{}curricula"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "university", propOrder = {
    "lectures",
    "curricula"
})
public class University {

    @XmlElement(required = true)
    protected Lectures lectures;
    @XmlElement(required = true)
    protected Curricula curricula;

    /**
     * Ruft den Wert der lectures-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Lectures }
     *     
     */
    public Lectures getLectures() {
        return lectures;
    }

    /**
     * Legt den Wert der lectures-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Lectures }
     *     
     */
    public void setLectures(Lectures value) {
        this.lectures = value;
    }

    /**
     * Ruft den Wert der curricula-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Curricula }
     *     
     */
    public Curricula getCurricula() {
        return curricula;
    }

    /**
     * Legt den Wert der curricula-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Curricula }
     *     
     */
    public void setCurricula(Curricula value) {
        this.curricula = value;
    }

}
