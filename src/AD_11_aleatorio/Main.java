/*
exercicio 11
aleatorio
     Gardar os contidos dos tres arrays seguintes nun ficheiro aleatorio (creado en modo lectura escritura)
     tendo en conta que cada tres elementos que  ocupan a mesma posicion nos arrays
     representan os campos dun rexistro dunha taboa de produtos , onde cada produto ten
      un codigo unha descricion e un prezo:
        String[] codes={"p1","p2","p3"};
        String[] descricion ={"parafusos","cravos ","tachas"};
        int[] prices ={3,4,5};

      nota: ter en conta que asignaremos unha lonxitude fixa a cada rexistro
      así para o codigo asignaremos 3 caracteres(6 bytes), para a descricion 10caracteres(20bytes) eo prezo por ser un
      enteiro terá asignados 4 bytes, sendo o tamaño total do rexistro de 30 bytes
      nota: a clase a usar e RandomAcessFile e os seus metodos para escritura e lectura
      de tipos primitivos de datos (writeChars(..)  , writeInt(..) , readChar()..readInt())


      debera crearse ou utilizarse   un método que encha cos espazos que faltan os campos   codigo e descricion ata
       completar as lonxitudes de ditos campos cun caracter calquera.
 Pista : A clase String ten un metodo denonimado format que permite dar formato a unha cadea
 cadea :  format("%" + width + "s", t)
onde:
	width:   e o ancho final da cadea en numero de caracteres
      t  :        e a cadea
	"%" : enche con blancos pola esquerda  (con "%"- encheria con blancos pola dereita)
 se ademais lle aplicamos ao metodo format  o metodo   replace(' ', '0')     cambiara os blancos por ceros
  ( esto e interesante se nun primeiro momento queremos ver o efecto de aplicar ‘format’ porque os blancos
  non se ven na impresion pero os cero si)


      unha vez almacenados os tres rexistros deberemos ler e amosar o contido dos campos
      do rexistro que ocupa a posicion numero 2   (utilizando o metodo seek(int n  ) da clase RandomAccessFile
       que permite posicionarnos no byte indicado dentro de dito ficheiro), cargalos nun obxecto da clase Product
       e imprimir o seu contido

ter en conta que ao ler o rexistro deberemos voltar a separar os seus contidos e quitarlle os blancos as cadeas
de texto para poder poder cargar correctamente ditos valores nos atributos correspondentes do   obxecto da clase
Product ( excepto o valor que foi grabado con writeInt()  que sera lido con readInt())

      e decir deberia amosarse algo parecido a isto:

       p2
        cravos
      4
 */

package AD_11_aleatorio;

import AD_07_Productsstream.Product;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import misc.Res;

public class Main {

    static int prodsize = 30;
    static File file1 = new File(Res.RES_PATH + "productos8.txt");

    public static void main(String[] args) {
        String[] cod = {"p1", "p2", "p3"};
        String[] desc = {"parafusos", "cravos", "tachas"};
        double[] prezo = {3, 4, 5};
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < cod.length; i++) {
            products.add(new Product(cod[i], desc[i], prezo[i]));
        }

        File file1 = new File(Res.RES_PATH + "productos8.txt");

        try (RandomAccessFile out = new RandomAccessFile(file1, "rw")) {
            for (Product prod : products) {
                out.writeChars(String.format("%" + 3 + "s", prod.getCodigo()));
                out.writeChars(String.format("%" + 10 + "s", prod.getDescripcion()));
                out.writeInt((int) prod.getPrecio());
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        readProductNo(1);
    }

    private static void readProductNo(int pos) {
        try (RandomAccessFile in = new RandomAccessFile(file1, "r")) {
            in.seek(prodsize * pos);
            StringBuilder tcod = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                tcod.append(in.readChar());
            }
            StringBuilder tdesc = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                tdesc.append(in.readChar());
            }
            Product product = new Product(tcod.toString().trim(), tdesc.toString().trim(), in.readInt());
            System.out.println(product.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}