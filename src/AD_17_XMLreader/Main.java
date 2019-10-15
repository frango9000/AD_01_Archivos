/*
  Exercicio 17
XMLreader
 ler dende o ficheiro products.xml que creaches no exercicio XMLwriter os datos que almacenache nel
  e crear un Arraylist de obxectos product  imprimindo as variables de ditos    obxectos dende o Arraylist
*/
package AD_17_XMLreader;

import static misc.Res.RES_PATH;

import AD_07_Productsstream.Product;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {

    public static void main(String[] args) {

        ArrayList<Product> productos = getAutorsSAX();
        for (Product product : productos) {
            System.out.println(product);
        }
        ArrayList<Product> productos2 = getAutorsDOM();
        for (Product product : productos2) {
            System.out.println(product);
        }
    }

    private static ArrayList<Product> getAutorsSAX() {
        ArrayList<Product> productos = new ArrayList<>();
        XMLStreamReader in = null;
        try {
            in = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(RES_PATH + "products16sax.xml"));
            Product product = null;
            while (in.hasNext()) {
                if (in.getEventType() == XMLStreamConstants.START_ELEMENT) {
//                    System.out.println(in.getEventType() + " " + in.getLocalName());
                    if (in.getLocalName() == "producto") {
                        product = new Product();
                        productos.add(product);
                        product.setCodigo(in.getAttributeValue(0));
                    } else if (in.getLocalName() == "descripcion") {
                        product.setDescripcion(in.getElementText());
                    } else if (in.getLocalName() == "precio") {

                        product.setPrecio(Double.parseDouble(in.getElementText()));
                    }
                }
                in.next();
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
        System.out.println("SAX List OK");
        return productos;
    }


    private static ArrayList<Product> getAutorsDOM() {
        ArrayList<Product> productos = new ArrayList<>();

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new FileInputStream(RES_PATH + "products16dom.xml"));
            Element root = document.getDocumentElement();
            NodeList nodes = root.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node instanceof Element) {
                    Element element = (Element) node;
                    Product product = new Product();
                    product.setCodigo(element.getAttribute("codigo"));

                    Element desc = (Element) element.getElementsByTagName("descripcion").item(0);
                    product.setDescripcion(desc.getTextContent());
                    Element precio = (Element) element.getElementsByTagName("precio").item(0);
                    product.setPrecio(Double.parseDouble(precio.getTextContent()));

                    productos.add(product);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        System.out.println("DOM List OK");
        return productos;
    }
}
