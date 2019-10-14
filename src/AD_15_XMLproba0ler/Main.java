/*
exercicio 15
XMLproba0ler
le o documento XML que se xenerou no exercicio anterior usando os metodos apropiados
as clases a usar son moi similares:
XMLInputFactory
XMLStreamReader

metodos da clase XMLStreamreader:

hasNext() : para preguntar se ainda quedan elementos a ler

next(): para ler o seguinte elemento

getEventType() : retorna un enteiro que  indica o tipo de elemento. Este enteiro
 correspondese con unha constante definida por a interface XMLStreamConstants

                   START_ELEMENT
                   END_ELEMENT
                   ATTRIBUTE
                   CHARACTERS
                   COMMENT
                   SPACE
                   DTD
por exemplo para preguntar se o evento leido e un Start Element  preguntaremos por XMLStreamConstants.START_ELEMENT:

getLocalName():devolta o nome do elemento actual
getAttributeValue(posidion_do_atributo_dende_0):  devolta un string con o atributo almacenado no indice especificado
getElementText() : devolta o valor de un elemento de texto

*/
package AD_15_XMLproba0ler;

import static misc.Res.RES_PATH;

import AD_14_XMLproba0.Autor;
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

        ArrayList<Autor> autores = getAutorsSAX();
        for (Autor autor : autores) {
            System.out.println(autor);
        }
        ArrayList<Autor> autores2 = getAutorsDOM();
        for (Autor autor : autores2) {
            System.out.println(autor);
        }
    }

    private static ArrayList<Autor> getAutorsSAX() {
        ArrayList<Autor> autores = new ArrayList<>();
        XMLStreamReader in = null;
        try {
            in = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(RES_PATH + "sax0.xml"));
            Autor autor = null;
            while (in.hasNext()) {
                if (in.getEventType() == XMLStreamConstants.START_ELEMENT) {
//                    System.out.println(in.getEventType() + " " + in.getLocalName());
                    if (in.getLocalName() == "autor") {
                        autor = new Autor();
                        autores.add(autor);
                        autor.setCodigo(in.getAttributeValue(0));
                    } else if (in.getLocalName() == "nombre") {
                        autor.setNombre(in.getElementText());
                    } else if (in.getLocalName() == "titulo") {
                        if (autor.getTitulo1() == null)
                            autor.setTitulo1(in.getElementText());
                        else
                            autor.setTitulo2(in.getElementText());
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
        return autores;
    }


    private static ArrayList<Autor> getAutorsDOM() {
        ArrayList<Autor> autores = new ArrayList<>();

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new FileInputStream(RES_PATH + "dom0.xml"));
            Element root = document.getDocumentElement();
            NodeList nodes = root.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node instanceof Element) {
                    Element element = (Element) node;
                    Autor autor = new Autor();
                    autor.setCodigo(element.getAttribute("codigo"));

                    Element nombre = (Element) element.getElementsByTagName("nombre").item(0);
                    autor.setNombre(nombre.getTextContent());
                    NodeList titulos = element.getElementsByTagName("titulo");
                    Element titulo1 = (Element) titulos.item(0);
                    autor.setTitulo1(titulo1.getTextContent());
                    Element titulo2 = (Element) titulos.item(1);
                    autor.setTitulo2(titulo2.getTextContent());

                    autores.add(autor);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        System.out.println("DOM List OK");
        return autores;
    }
}
