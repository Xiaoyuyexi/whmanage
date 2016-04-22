package ecinterface.adc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="request" type="{http://adc.ecinterface/}NGEC" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "request" })
@XmlRootElement(name = "AdcServices")
public class AdcServices {

	protected NGEC request;

	/**
	 * Gets the value of the request property.
	 * 
	 * @return possible object is {@link NGEC }
	 * 
	 */
	public NGEC getRequest() {
		return request;
	}

	/**
	 * Sets the value of the request property.
	 * 
	 * @param value
	 *            allowed object is {@link NGEC }
	 * 
	 */
	public void setRequest(NGEC value) {
		this.request = value;
	}

}
