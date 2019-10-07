/*

exercicio 6
primitiveutfchars

 Neste exercicio seguiremos tratando os fluxos binarios de datos pero abora tratandoos como soporte de tipos de
  dato primitivos e valores String

DataInputStream dispon dunha serie de  métodos  para que partindo  de fluxos binaros de datos  podamos ler tipos
 primitivos de datos e valores String

DataOutputStream dispon dunha serie de  métodos  para que partindo  de fluxos binaros de datos  podamos escribir
 tipos primitivos de datos e valores String que previamente foron codificados nestes fluxos mediante DataInputStream

Dado que DatainputStream admite como parametro calquer clase herdada de InputStream , e BufferedInputStream cumpre
 este requisito,  o único que temos que facer para poder usar os metodos de DataInputStream e pasarlle como parmaetro
  un obxecto BufferedInputStream coma otraballado no exercicio anterior


Dado que DataOutputStream admite como parametro calquer clase herdada de OutputStream , e BufferedOutputStream cumpre
 este requisito o único que temos que facer para poder usar os metodos de DataOutputStream e pasarlle como parmaetro
  un obxecto BufferedOutputStream coma o traballado no exercicio anterior

Propoñovos un exercicio que consistirá grabar unha mesma cadea  de texto tres veces usando dous metodos distintos
(writeUTF e writeChars) nun ficheiro denominado text6.txt para posteriormente recuperalos.
Lembrade que para recuperar unha cadea de gravada con witeUTF debedes usar readUTF
Pero para recuperar unha cadea escrita con writeChars debedes usar readChar() que so lé un caracter de cada vez ,
 isto implca que debedes usar un bucle que faga tantas lecturas como caracteres ten a cadea orixinal.


cadea a grabar : "Está en casa"

a primeira vez debe grabase usando writeUTF(String)
a segunda usando writeChars(String)
a terceira usando de novo writeUTF(String)


o resultado da execucion debería ser o seguinte:

writeUTF escribiu: Está en casa

writeChars escribiu: Está en casa

writeUTF escribiu: Está en casa


lemos a primeira cadea mediante  readUTF: Está en casa

lemos a segunda cadea mediante readChar (con bucle):  Está en casa

lemos a terceira cadea  mediante readUTF:  Está en casa


Podemos comprobar que se intentamos abrir a o ficheiro de texto   co editor gedit non o permite. debemos usar
 nano , vi , emacs ou outro editor non grafico  e comprobaremos a forma en que foron grabadas as  cadeas.

 */
package AD_06_primitiveutfchars;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import misc.Res;

public class PrimitiveUtfChars {

    public static void main(String[] args) {
        int[] bucles = {2,2,2};
        System.out.println(new File("").getAbsolutePath());
        File file1 = new File(Res.RES_PATH + "texto6.txt");
        String str = "Está en casa";
        System.out.println("Bucles: " + Arrays.toString(bucles));
        System.out.println("String a escribir: " + str);
        System.out.println("Cantidad de caracteres: " + str.length());

        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file1)))) {
            int written = 0;
            for (int i = 1; i <= bucles[0]; i++) {
                out.writeUTF(str);
                System.out.println("writeUtf escribio " + (out.size() - written));
                written = out.size();
            }
            for (int i = 1; i <= bucles[1]; i++) {
                out.writeChars(str);
                System.out.println("writeChars escribio " + (out.size() - written));
                written = out.size();
            }
            for (int i = 1; i <= bucles[2]; i++) {
                out.writeUTF(str);
                System.out.println("writeUtf escribio " + (out.size() - written));
                written = out.size();
            }
            System.out.println("Bytes totales escritos: " + out.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file1)))) {
            int loopbytes = in.available();
            for (int i = 1; i <= bucles[0]; i++) {
                System.out.print("1 readUtf numero: " + i + " : ");
                System.out.println(in.readUTF());
                System.out.println("Bytes leidos: " + (loopbytes - in.available()));
                System.out.println("Bytes restantes: " + in.available());
            }
            for (int i = 1; i <= bucles[1]; i++) {
                System.out.print("readChar numero: " + i + " : ");
                StringBuilder read = new StringBuilder();
                for (int j = 0; j < str.length(); j++) {
                    read.append(in.readChar());
                }
                System.out.println(read.toString());
                System.out.println("Bytes leidos: " + (loopbytes - in.available()));
                System.out.println("Bytes restantes: " + in.available());
            }
            for (int i = 1; i <= bucles[2]; i++) {
                System.out.print("1 readUtf numero: " + i + " : ");
                System.out.println(in.readUTF());
                System.out.println("Bytes leidos: " + (loopbytes - in.available()));
                System.out.println("Bytes restantes: " + in.available());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
