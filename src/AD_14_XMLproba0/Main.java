/*
exemplo 14:
XMLproba0

informacion:
SAX (simple API for XML: usado para convertir datos a XML e viceversa)

un docuemnto XML esta composto de elementos
tod o elemento XML comeza e remata cunha etiqueta ou tag
o tag inical e o final dun elemento teñen o mesmo nome que e elexido polo disenador do ducumento.
o contido(content) dun elemento e o que vai situado entre os tags iniciais e final do elemento
exemplo de elemento:   <titulo> blade runner </titulo>
tag inicial: <titulo>
contido: blade runner
tag final: </titulo>
o elemento de mais alto nivel ou elemento pai (parent element) e o denominado elemento raiz
un documento XML so pode conter un elemento raiz
dentro dun elemento poden atoparse mais elementos denominados elementos fillos (child elements)
o primeiro elemento dun documento XML e a declaracion que indica a version do XML que vai ser usada polo documento
un elemento pode conter un ou varios tributos , que son pares variable=valor que se utilizan normalmente para
identificar certos elementos dun documento XML

exemplo de elemento con atributo:
<tenda codigo="t1">

Para escribir e ler documentos XML imos usar StAX  - Streaming API for XML
Antes de escribir nun documento XML necesitamos crear un obxecto que implemente  a interface XMLStreamWriter
Para facer isto debemos primeiro invocar o metodo estático newInstance() da clase XMLOutputFactory par crear un
obxecto XMLOutputFactory
Despois podemos xa crear un obxecto XMLStreamWriter invocando o metodo createXMLStreamWriter(FileWriter object) do
 obxecto XMLOutputFactory creado anteriormente
A partires dese momento xa se poden usar os metodos propios do obxecto XMLStreamWriter par escribir o documento

Metodos da clase XMLStreamWriter:
writeStartDocument("1.0")
   //escribe a declaracion XML con a Version especificada

writeStartElement("tenda");
 //escribe o tag de inicio de un elemento

writeAttribute("codigo","t1");
    //escribe un atributo para o lemento actual

writeCharacters("urzaiz");
    //escribe o contido do elemento

 writer.writeEndElement();
    //escribe o tag de peche do elemento actual


exercicio:
xmlproba0
graba este documento XML co nome autores.xml dende java usando os metodos apropiados
<?xm version="1.0"?>
<autores>
    <autor codigo ="a1">
    <nome>Alexandre Dumas </nome>
    <titulo> El conde de montecristo</titulo>
    <titulo> Los miserables </titulo>
    </autor>
    <autor codigo ="a2">
        <nome>Fiodor Dostoyevski</nome>
        <titulo> El idiota</titulo>
        <titulo> Noches blancas </>
    </autor>
<autores>
*/
package AD_14_XMLproba0;

import java.io.FileWriter;
import java.io.IOException;
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
        Autor a1 = new Autor("a1", "Alexandre Dumas", "El conde de montecristo", "Los miserables");
        Autor a2 = new Autor("a2", "Fiodor Dostoyevki", "El idiota", "Noches blancas");

        xmlSAX(a1, a2);
        xmlDOM(a1, a2);
    }

    private static void xmlSAX(Autor... autores) {
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter out = null;
        try {
            out = xmlOutputFactory.createXMLStreamWriter(new FileWriter(Res.RES_PATH + "sax0.xml"));
            out.writeStartDocument("1.0");
            out.writeStartElement("autores");
            for (Autor a : autores) {
                writeAuthorSAX(a, out);
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

    private static void writeAuthorSAX(Autor a1, XMLStreamWriter out) throws XMLStreamException {
        out.writeStartElement("autor");
        out.writeAttribute("codigo", a1.getCodigo());
        out.writeStartElement("nombre");
        out.writeCharacters(a1.getNombre());
        out.writeEndElement();
        out.writeStartElement("titulo");
        out.writeCharacters(a1.getTitulo1());
        out.writeEndElement();
        out.writeStartElement("titulo");
        out.writeCharacters(a1.getTitulo2());
        out.writeEndElement();
        out.writeEndElement();
    }

    private static void xmlDOM(Autor... autores) {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("root");
            document.appendChild(root);

            for (Autor a : autores) {
                root.appendChild(writeAuthorDOM(a, document));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new FileWriter(Res.RES_PATH + "dom0.xml"));

            // Output to console for testing
            //StreamResult result = new StreamResult(System.out);

            transformer.transform(domSource, streamResult);
            System.out.println("File saved with DOM!");

        } catch (ParserConfigurationException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private static Node writeAuthorDOM(Autor a1, Document doc) {
        Element autor = doc.createElement("autor");
        Attr codigo = doc.createAttribute("codigo");
        codigo.setValue(a1.getCodigo());
        autor.setAttributeNode(codigo);

        Element nombre = doc.createElement("nombre");
        nombre.appendChild(doc.createTextNode(a1.getNombre()));
        autor.appendChild(nombre);

        Element titulo1 = doc.createElement("titulo");
        titulo1.appendChild(doc.createTextNode(a1.getTitulo1()));
        autor.appendChild(titulo1);

        Element titulo2 = doc.createElement("titulo");
        titulo2.appendChild(doc.createTextNode(a1.getTitulo2()));
        autor.appendChild(titulo2);

        return autor;
    }
}
