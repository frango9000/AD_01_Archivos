/*
exercicio 13
 serializacion2
 Utilizando  a serializacion de obxectos e partindo do array seguinte:
  String[] cod={"p1","p2","p3"};
  String[] desc ={"parafusos","cravos ","tachas"};
  Double[] prezo ={3.0,4.0,5.0};

 crea tres obxectos tipo product e almacenaos nun ficheiro secuencial binario (FileOutputStream) utilizando os
 metodos adecuados da case ObjectOutputStream

 para despois leelos dende dito ficheiro e imprimilos . facer este exercicio utilizando o bucle while para ler
 os obxectos almacenados suponse que non sabemos cantos obxectos hai almacenados .

nota : o  metodo close() do ObjectOutputStream  non mete un null cando se executa dito metodo polo que
se uso un  bucle while para lelo ata o final  non teño forma de saber onde remata, dandome o erro
      "/Exception during deserialization: java.io.EOFException "
O que podemos facer e gardar un null xusto antes de lanzar o close(()  deste xeito
cando lea os obxectos podo preguntar se o obxecto lido e un  null para deter o blucle de lectura

 */
package AD_13_serializacion2;

import AD_07_Productsstream.Product;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import misc.Res;

public class Main {

    private static File file1 = new File(Res.RES_PATH + "serial13.txt");

    public static void main(String[] args) {
        String[] cod = {"p1", "p2", "p3"};
        String[] desc = {"parafusos", "cravos", "tachas"};
        Double[] prezo = {3.0, 4.0, 5.0};
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < cod.length; i++) {
            products.add(new Product(cod[i], desc[i], prezo[i]));
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file1))) {
            for (Product product : products) {
                outputStream.writeObject(product);
            }
            outputStream.writeObject(null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Product product : readProducts()) {
            System.out.println(product.toString());
        }
    }

    public static ArrayList<Product> readProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file1))) {
            while (true) {
                Object o = in.readObject();
                if (o == null)
                    break;
//                System.out.println(o.toString());
                products.add((Product) o);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return products;
    }
}
