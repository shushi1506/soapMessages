package Utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author tuananh 12/1/2017
 * test_barcode
 * PACKAGE_NAME
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CrudGetAuthenticationResponse",namespace = "http://syncdata.soapservice.crud/")
public class ClassResponse {
    @XmlElement(name = "CrudData")
    protected Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
