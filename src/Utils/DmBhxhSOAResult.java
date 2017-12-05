
package Utils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class DmBhxhSOAResult {
    @XmlElement(name = "id")
    protected Long id;
    @XmlElement(name = "ma")
    protected String ma;
    @XmlElement(name = "ten")
    protected String ten;

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String value) {
        this.ma = value;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String value) {
        this.ten = value;
    }

}
