/*
exercicio 12
serializacion1

 Crear unha clase chamada mclase que implemente a interfaz serializable  (implements Serializable). Dita clase debe
 ter tres atributos  :
 unha variable String  (nome)  outra int (numero1) e outra double (numero2)

Dende  a  clase principal (a do proxecto) instanciar un obxecto da clase mclase
con tres valores:  "ola",-7, 2.7E10,   e almacenar dito obxecto  nun ficheiro denominado  'serial'
 mediante o metodo writeObject(obxecto_a_grabar) da clase  ObjectOutputStream ( que debe recibir como parametro un
 obxecto da clase FileOutputStream("ruta do ficheiro a escribir”). Pecha o ficheiro .

Despois crea outro obxecto baleiro da clase mclase e carga posteriormente os seus atributos cos valores almacenados
no ficheiro 'serial' anteriormente creado . Para isto debes usar o metodo readObject() da clase ObjectInputStream.

 se tod o funciona correctamente facer un cambio (deixando comentados as lineas que modifiquedes para facer este
 cambio) e repetir a execucion do proxecto : o cambio consistirá en  marcar a variable tipo int da clase mclase
 como 'transient'  e comprobar que poñamos  o valor que poñamos nese campo int do obxecto a gardar   o resultado
 devolto será '0' (sería un nullo se a
 variable transient fora a de tipo String

execucion antes do cambio
object1: s=ola; i=-7; d=2.7E10

execucion despois do cambio
object2: s=ola; i=0; d=2.7E10
fin da comprobacion: recuperanse os  datos excepto i por ser transient

 */
package AD_12_serializacion1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import misc.Res;

public class Main {

    public static void main(String[] args) {

        File file1 = new File(Res.RES_PATH + "serial");
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file1))) {
            Mclase mclase = new Mclase("ola", -7, 2.7E10);
            outputStream.writeObject(mclase);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file1))) {
            Mclase mclase2 = (Mclase) in.readObject();
            System.out.println(mclase2.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
