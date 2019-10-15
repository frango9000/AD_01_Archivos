/*
exercicio 16
XMLwriter
ler dende o  ficheiro que creache no  exercicio Serializacion2 os obxectos que almacenaches nel
 e almacenaos en formato XML nun ficheiro denominado "products.xml"

 IMPORTANTE: debes importar o proxecto denominado serialiazion2 para usar a sua clase Product para ler os obxectos
  que almacenaches en dito exercicio  , NON debes copiar e pegar a clase Product en ningún caso.
 */
package AD_16_XMLwriter;

import AD_07_Productsstream.Product;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import misc.Res;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Main {

    public static void main(String[] args) {

        ArrayList<Product> products = AD_13_serializacion2.Main.readProducts();
        for (Product product : products) {
            System.out.println(product.toString());
        }
        xmlSAX(products);
        xmlDOM(products);

    }

    private static void xmlSAX(ArrayList<Product> products) {
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter out = null;
        try {
            out = xmlOutputFactory.createXMLStreamWriter(new FileWriter(Res.RES_PATH + "products16sax.xml"));
            out.writeStartDocument("1.0");
            out.writeStartElement("productos");
            for (Product pro : products) {
                writeProductSAX(pro, out);
            }
            out.writeEndElement();
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                    System.out.println("File saved with SAX!");
                }
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeProductSAX(Product product, XMLStreamWriter out) throws XMLStreamException {
        out.writeStartElement("producto");
        out.writeAttribute("codigo", product.getCodigo());
        out.writeStartElement("descripcion");
        out.writeCharacters(product.getDescripcion());
        out.writeEndElement();
        out.writeStartElement("precio");
        out.writeCharacters(Double.toString(product.getPrecio()));
        out.writeEndElement();
        out.writeEndElement();
    }


    private static void xmlDOM(ArrayList<Product> products) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("products");
            document.appendChild(root);

            for (Product product : products) {
                root.appendChild(writeProductDOM(product, document));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new FileWriter(Res.RES_PATH + "products16dom.xml"));

            transformer.transform(domSource, streamResult);
            System.out.println("File saved with DOM!");

        } catch (ParserConfigurationException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static Node writeProductDOM(Product product, Document doc) {
        Element autor = doc.createElement("product");
        Attr codigo = doc.createAttribute("codigo");
        codigo.setValue(product.getCodigo());
        autor.setAttributeNode(codigo);

        Element nombre = doc.createElement("descripcion");
        nombre.appendChild(doc.createTextNode(product.getDescripcion()));
        autor.appendChild(nombre);

        Element precio = doc.createElement("precio");
        precio.appendChild(doc.createTextNode(Double.toString(product.getPrecio())));
        autor.appendChild(precio);
        return autor;
    }
}
