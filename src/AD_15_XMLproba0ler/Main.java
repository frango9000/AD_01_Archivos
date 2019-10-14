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
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Main {

    public static void main(String[] args) {

        XMLStreamReader in;
        try {
            in = XMLInputFactory.newInstance().createXMLStreamReader(new FileReader(RES_PATH + "sax0.xml"));
            while (in.hasNext()) {
                if (in.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    if (in.getLocalName() == "autor") {
                        Autor autor = new Autor();
                        autor.setCodigo(in.getAttributeValue(0));
                        in.next();
                        autor.setNombre(in.getElementText());
                        System.out.println(autor.toString());
                    }
                }
                in.next();
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
