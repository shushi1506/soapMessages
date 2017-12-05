package Utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author tuananh 12/1/2017
 * test_barcode
 * PACKAGE_NAME
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Response {
    /* Element */
    @XmlElement(name = "status")
    protected Boolean status_;
    @XmlElement(name = "errorCode")
    protected String errorCode_;
    @XmlElement(name = "message")
    protected String message_;
    @XmlElement(name = "token")
    protected byte[] token_;

    public Response(){
        this.status_ = false;
        this.errorCode_ = "-1";
        this.message_ = "User is not authenticated!";
    }

    public Boolean getStatus(){
        if (this.status_ == null){
            return false;
        }else {
            return this.status_;
        }
    }
    public String getErrorCode(){
        if (this.errorCode_ == null){
            return "undefined";
        }else{
            return this.errorCode_;
        }
    }
    public String getMessage(){
        if (this.message_ == null){
            return "undefined";
        }else {
            return this.message_;
        }
    }
    public byte[] getToken() {
        return this.token_;
    }
    public void setStatus(Boolean status){
        this.status_ = status;
    }
    public void setErrorCode(String errorCode){
        this.errorCode_ = errorCode;
    }
    public void setMessage(String message) {
        this.message_ = message;
    }
    public void setToken(byte[] token){this.token_ = token;}
}
