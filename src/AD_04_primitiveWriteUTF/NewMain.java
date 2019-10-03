
/*
exercicio primitiveWriteUTF

Neste exercicio seguiremos tratando os fluxos binarios de datos pero abora 
tratandoos como soporte de tipos de dato primitivos e valores String

DataOutputStream dispon dunha serie de  métodos  para que partindo  de 
fluxos binaros de datos  podamos escribir tipos primitivos de datos e 
valores String


DataInputStream dispon dunha serie de  métodos  para que partindo  de
fluxos binaros de datos  podamos ler tipos primitivos de datos e valores
String  que previamente foron codificados nestes fluxos mediante 
DataOutputStream


Dado que DatainputStream admite como parametro calquer clase herdada de
InputStream , e BufferedInputStream cumpre este requisito,  o único que
temos que facer para poder usar os metodos de DataInputStream e pasarlle
como parmaetro un obxecto BufferedInputStream coma o traballado no exercicio
anterior 


Dado que DataOutputStream admite como parametro calquer clase herdada de
OutputStream , e BufferedOutputStream cumpre este requisito o único que 
temos que facer para poder usar os metodos de DataOutputStream e pasarlle
como parmaetro un obxecto BufferedOutputStream coma o traballado no exercicio 
anterior

Propoñovos un exercicio que consistirá grabar unha mesma cadea  de texto 
duas veces usando o metodo writeUTF  nun ficheiro denominado text3.txt para 
posteriormente recuperalos (voltalos a ler).

Ter en cota que writeUTF(String) escribe tipicamente 1 byte por cada caracte
do String que lle pasamos como parámetro  ( pero pode escribir ata 3 
dependendo do caracter ) e antepon a dito String  2 Bytes que conteñen 
a lonxitude en numero de bytes do String. E dicir que se o String e o texto
"o tempo está xélido" a sua escritura ocupara 23 bytes porque os bytes que 
ocupan os caracteres normas e os blancos son 1 por caracter e as vogis con 
acento son dous bytes logo temos : 14 caracteres  + 3 blancos  + 2 caracteres
acentuados =>  2 bytes que antepoñen a cadea para indicar a sua 
lonxitude + 14 + 3 + 2*2 = 23 bytes 

(podemos cambiar calquera vogal da cadea por esa mesma vogal acentuada ou 
unha letra ñ e veremos que a lonxitude da cadea varia)

cadea a grabar : "o tempo está xélido"



o resultado da execucion debería ser o seguinte:



writeUTF escribiu: 23 bytes
writeUTF escribiu: 23 bytes
bytes totais escritos = 46
lemos a primeira cadea en UTF: o tempo está xélido, numero de bytes lidos: 23 bytes.
bytes restantes por ler = 23
lemos a segunda cadea  mediante readUTF:  o tempo está xélido

nota:

 para saber se alcanzamos o fin do ficheiro podemos usar o metodo .available() 
da clase DataInputStream.

Podemos comprobar que se intentamos abrir a o ficheiro de texto  text3.txt co 
editor gedit non o permite. debemos usar nano , vi , emacs ou outro editor non 
grafico  e comprobaremos a forma en que foron grabadas as  cadeas.



 */


package AD_04_primitiveWriteUTF;

import java.awt.geom.RectangularShape;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import misc.Res;

/**
 *
 * @author oracle
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        File file1 = new File(Res.RES_PATH + "texto3.txt");
        System.out.println(file1.getPath());
        
        try(DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file1)))){
            int written = 0;
            for (int i = 0; i < 40; i++) {                
                out.writeUTF("o tempo está xélido");
                System.out.println(String.format("writeUTF escribiu: %d bytes", out.size() - written));
                written = out.size();
            }
            System.out.println("bytes totais escritos = " + out.size());
        } catch (IOException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
                            
        try(DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file1)))){
            int total = in.available();
            int cycle = 1;
            while (in.available() > 0){                
                System.out.print("lemos a "+(cycle++)+" cadea en UTF:");
                System.out.print(in.readUTF());
                System.out.print(", numero de bytes lidos: " + (total - in.available()));
                System.out.println(" bytes restantes: " + in.available());
                total -= total - in.available();
            }
        } catch (IOException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }    
}


/*
run:
writeUTF escribiu: 23 bytes
writeUTF escribiu: 23 bytes
bytes totais escritos = 46
lemos a 1 cadea en UTF:o tempo está xélido, numero de bytes lidos: 23 bytes restantes: 23
lemos a 2 cadea en UTF:o tempo está xélido, numero de bytes lidos: 23 bytes restantes: 0
BUILD SUCCESSFUL (total time: 0 seconds)
*/