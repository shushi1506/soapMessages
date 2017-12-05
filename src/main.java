import java.awt.*;
import java.io.*;
import java.util.Iterator;


import Utils.CallService;
import Utils.Defined;
import org.apache.commons.codec.binary.Base64;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import javax.xml.bind.Unmarshaller;

import javax.xml.soap.*;


/**
 * @author tuananh 11/2/2017
 * test_barcode
 * PACKAGE_NAME
 */
public class main {


//    public static String nameSpaceURI="http://syncdata.soapservice.crud/";
//    public static String nameSpace="syn";
//    public static String endPointUrl="http://10.0.123.18:9101/CategoriesCrud.wsdl?wsdl";
//    public static String action="CategoriesCrud/GetAuthentication";

    public static void main(String[] args) throws IOException, JAXBException, SOAPException {
//        String soapEndpointUrl = "http://10.0.123.12:9101/CategoriesCrud?wsdl";
//        String soapAction = "CategoriesCrud/GetAuthentication";


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





    private static Name getName(SOAPMessage message) {
        Name rvalue = null;
        SOAPPart soap = message.getSOAPPart();
        if (soap != null) {
            try {
                SOAPEnvelope envelope = soap.getEnvelope();
                if (envelope != null) {
                    SOAPBody body = envelope.getBody();
                    if (body != null) {
                        Iterator it = body.getChildElements();
                        while (it.hasNext()) {
                            Object o = it.next();
                            if (o instanceof SOAPElement) {
                                Iterator ite=((SOAPElement) o).getChildElements();
                                SOAPElement k= (SOAPElement) ite.next();
                                Iterator ite2=k.getChildElements();
                                while (ite2.hasNext()){
                                    SOAPElement temp= (SOAPElement) ite2.next();

                                }
//                                while (ite.hasNext()){
//                                    Object k=it.next();
//                                    if(k instanceof SOAPElement){
//                                        break;
//                                    }
//                                }
                                rvalue = ((SOAPElement) o).getElementName();

                                break;
                            }
                        }
                    }
                }
            } catch (SOAPException se) {
//                if (_logger.isLoggable(Level.FINE)) {
//                    _logger.log(Level.FINE, "WSS: Unable to get SOAP envelope",
//                            se);
//                }
            }
        }
        return rvalue;
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
