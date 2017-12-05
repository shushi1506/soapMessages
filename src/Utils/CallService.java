package Utils;

import javax.xml.soap.*;

/**
 * @author tuananh 12/4/2017
 * test_barcode
 * Utils
 */
public class CallService {
    private static void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(Defined.nameSpace, Defined.nameSpaceURI);
//        envelope.addNamespaceDeclaration(myNamespace2,myNamespaceURI2);
//        System.out.println(envelope.getNamespaceURI("soap"));
            /*
            Constructed SOAP Request Message:
            <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/" xmlns:myNamespace="https://www.w3schools.com/xml/">
                <SOAP-ENV:Header/>
                <SOAP-ENV:Body>
                    <myNamespace:CelsiusToFahrenheit>
                        <myNamespace:Celsius>100</myNamespace:Celsius>
                    </myNamespace:CelsiusToFahrenheit>
                </SOAP-ENV:Body>
            </SOAP-ENV:Envelope>
            */

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
//        SOAPElement soapBodyElem = soapBody.addChildElement("CrudGetAuthenticationRequest", nameSpace);
//        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("request", nameSpace);
//        QName user=new QName("username");
//        QName pass=new QName("password");
//        soapBodyElem1.addAttribute(user,"vanthu.dba@mofa.gov.vn");
//        soapBodyElem1.addAttribute(pass,"123456a@");
//        SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("authToken", nameSpace);
        SOAPElement soapBodyElem = soapBody.addChildElement("getDmBhxhSOARequest", Defined.nameSpace);
//        SOAPElement soapBodyElem = soapBody.addChildElement("getDmDonViSOARequest", nameSpace);
//        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("dmBhxhId", nameSpace);
//        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("updatedDate", nameSpace);
//        soapBodyElem1.addTextNode("265");
//        soapBodyElem2.addTextNode("201709");
//        SOAPElement soapBodyElem = soapBody.addChildElement("CrudGetAuthenticationRequest", myNamespace);
//        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("request", myNamespace);
//        QName user=new QName("username");
//        QName pass=new QName("password");
//        soapBodyElem1.addAttribute(user,"andn@vss.gov.vn");
//        soapBodyElem1.addAttribute(pass,"123456aA@");
//        SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("authToken", myNamespace);
//        soapBodyElem2.addTextNode("WMjWEUsAPK2FvYXJqnGjSsoORSG+1O1KlikgAlWko0Hv77I1xzGRkr9hxcLqEtLmMWL8VapyAXFbVEH0/sHkwM3l4jVU+Ja3shELl2g88b/VOsKw+1mHiTBdDgANKVyaZ4lshbkgIhk8q1jmRfKV9CqzmVvx2I8JvLBBdoUJPQW5AUzfy7iWY4Vf7ppkYSjHxeEbVha385wU28AHT1rHLGL5toplxfNixpGAZhhkWNtqt9Ky2HoadrzCBI7tygUcQ4Cc0RQqoWUqtvjPxEawgJYYFg7lGHnDKpHBmoOUmMzToewx8BLeMXoNJmf13eU8YTLuE2d9tt9X9LDRIYrlyA==");
    }
    private static SOAPMessage createSOAPRequest2(String soapAction) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        createSoapDmBHXH(soapMessage);
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);
        soapMessage.saveChanges();
        return soapMessage;
    }
    private static void createSoapDmBHXH(SOAPMessage soapMessage) throws SOAPException {
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
//        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("matinh", myNamespace);
//        soapBodyElem1.addTextNode(elem1);
    }
    public static SOAPMessage callSoapWebService(String soapEndpointUrl, String soapAction) {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest2(soapAction), soapEndpointUrl);

            soapConnection.close();

            return soapResponse;

        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
        return null;
    }
}
