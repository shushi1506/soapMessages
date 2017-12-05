package Utils;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.*;
import java.util.ArrayList;

/**
 * @author tuananh 12/4/2017
 * ServicesVerifyDataSync
 * main.Test
 */
public class Test2 {
    public static final String URL_SERVICE_VSA = "http://10.0.119.31:8213/vsaService/ws/vsa.wsdl?wsdl";
    public static final String SOAPACTION_DM_BHXH_SOA = "vsaService/ws/getDmBhxhSOA";
    public static final String VSA_NAMESPACE = "http://teca.com/sms/generated/ws/vsa";
    public static void main(String[] args) throws JAXBException, SOAPException {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList = new ArrayList<>();
        arrayList.add("");
        SOAPMessage soapMessageBHXH = callSoapWebService("http://10.0.119.31:8213/vsaService/ws/vsa.wsdl?wsdl", "vsaService/ws/getDmBhxhSOA", arrayList);
        Unmarshaller unmarshaller = JAXBContext.newInstance(GetDmBhxhSOAResponse.class).createUnmarshaller();
        SOAPBody sb=soapMessageBHXH.getSOAPBody();
        GetDmBhxhSOAResponse dm = (GetDmBhxhSOAResponse) unmarshaller.unmarshal(sb.extractContentAsDocument());
        System.out.println(dm.getDmBhxhSOAResult().size());
    }

    public static SOAPMessage callSoapWebService(String soapEndpointUrl, String soapAction, ArrayList<String> arrayList) {
        SOAPMessage soapResponse = null;
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            soapResponse = soapConnection.call(createSOAPRequest2(soapAction, arrayList), soapEndpointUrl);
            soapConnection.close();
            return soapResponse;
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
        return soapResponse;
    }
    private static SOAPMessage createSOAPRequest2(String soapAction, ArrayList<String> listElem) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        createSoapDmBHXH(soapMessage, listElem.get(0));
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);
        soapMessage.saveChanges();
        return soapMessage;
    }
    private static void createSoapDmBHXH(SOAPMessage soapMessage, String elem1) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();
        String myNamespace = "vsa";
        String myNamespaceURI = "http://teca.com/sms/generated/ws/vsa";
        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
            /*
            <soapenv:Envelope
                xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                xmlns:vsa="http://teca.com/sms/generated/ws/vsa">
                   <soapenv:Header/>
                   <soapenv:Body>
                      <vsa:getDmDonViSOARequest>
                         <vsa:dmBhxhId>261</vsa:dmBhxhId>
                         <vsa:updatedDate>201704</vsa:updatedDate>
                      </vsa:getDmDonViSOARequest>
                   </soapenv:Body>
                </soapenv:Envelope>
            */
        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("getDmBhxhSOARequest", myNamespace);
    }
}
