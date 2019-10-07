package AD_07_Productsstream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import misc.Res;

public class Main {

    public static void main(String[] args) {
        Product po1 = new Product("cod1", "parafusos", 3);
        Product po2 = new Product("cod2", "cravos", 4);

        Product[] productos = {po1, po2};

        File file1 = new File(Res.RES_PATH + "productos.txt");

        try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file1)))) {
            for (Product po : productos) {
                out.writeUTF(po.getCodigo());
                out.writeUTF(po.getDescripcion());
                out.writeDouble(po.getPrecio());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file1)))) {
            while (in.available() > 0) {
                Product po3 = new Product();
                po3.setCodigo(in.readUTF());
                po3.setDescripcion(in.readUTF());
                po3.setPrecio(in.readDouble());
                System.out.println(po3.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
