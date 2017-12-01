import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Iterator;


import com.sun.xml.internal.messaging.saaj.soap.ver1_2.SOAPPart1_2Impl;
import org.apache.commons.codec.binary.Base64;

import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code128.Code128Constants;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.impl.upcean.EAN8Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.output.bitmap.BitmapEncoder;
import org.krysalis.barcode4j.output.bitmap.BitmapEncoderRegistry;
import org.krysalis.barcode4j.tools.MimeTypes;
import org.krysalis.barcode4j.tools.UnitConv;


import javax.imageio.ImageIO;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.Service;

/**
 * @author tuananh 11/2/2017
 * test_barcode
 * PACKAGE_NAME
 */
public class main {
    public static void main(String[] args) throws IOException {
        String soapEndpointUrl = "http://10.200.200.41:4001/TrackingVanBan?wsdl";
        String soapAction = "http://set.vbdhtracking.soapservice/GetAuthenticationRequest";

        callSoapWebService(soapEndpointUrl, soapAction);

//        File outputFile = new File("C:\\Users\\Admin\\Documents\\Barcode\\barcode4j_ean13_3.png") ;// existing file in the file system
//        OutputStream out = new FileOutputStream(outputFile);
//        try {
//            String code="8931496956620";
//            //'codeDigits' is the code for which we want to generate bar-code image
//            EAN13Bean bean = new EAN13Bean();
//            final int dpi = 90;
//            bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));
//            bean.setFontSize(4.0);
//            bean.setFontName("Times New Roman");
//            bean.doQuietZone(false);
//            bean.setMsgPosition(HumanReadablePlacement.HRP_NONE);
////            BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(
////                    out, MimeTypes.MIME_PNG, 150, BufferedImage.TYPE_BYTE_GRAY, true, 0);
//            // class to convert provided image into barcode image
//            BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider( 90,BufferedImage.TYPE_INT_ARGB, false, 0);
////            BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/x-png", dpi,   BufferedImage.TYPE_INT_ARGB, false, 0);
////            BitmapCanvasProvider canvasProvider = new BitmapCanvasProvider(out,
////                    "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false,
////                    0);
//            bean.generateBarcode(canvasProvider, code);
//            canvasProvider.finish();
//
//            BufferedImage bar=canvasProvider.getBufferedImage();
//            int w= (int) (bar.getWidth()*1.5);
//            int h= (int) (bar.getHeight()*1.5);
//            BufferedImage bImage = new BufferedImage(w,h+15, BufferedImage.TYPE_INT_ARGB);
//            Graphics2D g = bImage.createGraphics();
//            Font font = new Font("Times New Roman", Font.PLAIN, 15);
//            g.drawImage(bar, 0, 0, w,h,null);
//            g.setFont(font);
//            g.setColor(Color.BLACK);
//            drawCenteredString(code, bImage.getWidth(), bImage.getHeight()+h, g);
//            g.dispose();
//            ByteArrayOutputStream bos = null;
//            bos = new ByteArrayOutputStream();
//            ImageIO.write(bImage, "png", bos);
//            String t= Base64.encodeBase64String(bos.toByteArray());
//            System.out.println(t);
//            saveFileFromStringByte("C:\\Users\\Admin\\Documents\\Barcode\\barcode4j_ean13_buff_2.png",t);
//        }finally {
//           // out.close();
//        }
    }

    private static void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();



        String myNamespace = "set";
        String myNamespaceURI = "http://set.vbdhtracking.soapservice/";

//        String myNamespace2="soap";
//        String myNamespaceURI2 = "http://www.w3.org/2003/05/soap-envelope";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();


        envelope.setPrefix("soap");

        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);
//        envelope.addNamespaceDeclaration(myNamespace2,myNamespaceURI2);
        System.out.println(envelope.getNamespaceURI("soap"));

        SOAPHeader soapHeader =envelope.getHeader();
        soapHeader.setPrefix("soap");



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
        soapBody.setPrefix(myNamespace);
        SOAPElement soapBodyElem = soapBody.addChildElement("GetAuthenticationRequest", myNamespace);
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("request", myNamespace);
        QName user=new QName("username");
        QName pass=new QName("password");
        soapBodyElem1.addAttribute(user,"vanthu.dba@mofa.gov.vn");
        soapBodyElem1.addAttribute(pass,"123456a@");
        SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("authToken", myNamespace);

//        SOAPElement soapBodyElem = soapBody.addChildElement("CrudGetAuthenticationRequest", myNamespace);
//        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("request", myNamespace);
//        QName user=new QName("username");
//        QName pass=new QName("password");
//        soapBodyElem1.addAttribute(user,"andn@vss.gov.vn");
//        soapBodyElem1.addAttribute(pass,"123456aA@");
//        SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("authToken", myNamespace);
//        soapBodyElem2.addTextNode("WMjWEUsAPK2FvYXJqnGjSsoORSG+1O1KlikgAlWko0Hv77I1xzGRkr9hxcLqEtLmMWL8VapyAXFbVEH0/sHkwM3l4jVU+Ja3shELl2g88b/VOsKw+1mHiTBdDgANKVyaZ4lshbkgIhk8q1jmRfKV9CqzmVvx2I8JvLBBdoUJPQW5AUzfy7iWY4Vf7ppkYSjHxeEbVha385wU28AHT1rHLGL5toplxfNixpGAZhhkWNtqt9Ky2HoadrzCBI7tygUcQ4Cc0RQqoWUqtvjPxEawgJYYFg7lGHnDKpHBmoOUmMzToewx8BLeMXoNJmf13eU8YTLuE2d9tt9X9LDRIYrlyA==");
    }

    private static void callSoapWebService(String soapEndpointUrl, String soapAction) {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpointUrl);
            SOAPPart sp=  soapResponse.getSOAPPart();
            SOAPEnvelope ser=sp.getEnvelope();
            SOAPBody sbr=ser.getBody();
            Name bn=ser.createName("GetAuthenticationResponse","set","http://set.vbdhtracking.soapservice/");

            Iterator cc=sbr.getChildElements(bn);
            while(cc.hasNext()){
                SOAPBodyElement ccc= (SOAPBodyElement) cc.next();
                System.out.println(ccc.getValue());
            }
//            SOAPElement e= (SOAPElement) sbr.getFirstChild();
//            Iterator c=  e.getChildElements();
//            while (c.hasNext()){
//                SOAPBodyElement k= (SOAPBodyElement) c.next();
//                System.out.println(k.getValue());
//            }
//            SOAPElement f= (SOAPElement) c.getFirstChild();

            soapResponse.writeTo(System.out);

            System.out.println();

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
    }

    private static SOAPMessage createSOAPRequest(String soapAction) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        SOAPMessage soapMessage = messageFactory.createMessage();

        createSoapEnvelope(soapMessage);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        /* Print the request message, just for debugging purposes */
        System.out.println("Request SOAP Message:");

        soapMessage.writeTo(System.out);

        System.out.println("\n");

        return soapMessage;
    }
    public static void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }
    public static byte[] decodeImage(String imageDataString) {
        return Base64.decodeBase64(imageDataString);
    }

    public static void saveFileFromStringByte(String path, String arrayByte) {
        byte[] imageByArray = decodeImage(arrayByte);
        try {
            FileOutputStream fileOutputStreams = new FileOutputStream(path);
            fileOutputStreams.write(imageByArray);
            fileOutputStreams.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
//    public static void saveFileFromStringByte(String path,byte[]imageByArray) {
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(path);
//            fileOutputStream.write(imageByArray);
//            fileOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
