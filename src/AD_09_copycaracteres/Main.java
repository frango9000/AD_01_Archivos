/*
exercicio 9
copycaracteres
copia o contido dun ficheiro de texto  denominado  texto10.txt noutro denominado texto11.txt.

Imos traballar con fluxos de caracteres para o cal imos seguir a utilizar a clases da xerarquia Writer.
    FileWriter permite que conectemos o fluxo de caracteres a un ficheiro en modo escritura

Utilizar exclusivamente os metodos read() and write() das clases FileReader e FileWriter.

       read()   retorna un caracter como un int . Se se tenta ler fora do ficheiro lese un -1
       write(int c)  graba o caracter equivalente a o int c(   c e un int que representa o caracter a ser escrito )

O contido do ficheiro texto10.txt  podes crealo previamente  con calquera editor de texto plano e o seu contido
 debe ser o seguinte:
       a arbore
       o libro
       o neno
*/


package AD_09_copycaracteres;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import misc.Res;

public class Main {

    public static void main(String[] args) {
        File file10 = new File(Res.RES_PATH + "texto10.txt");
        File file11 = new File(Res.RES_PATH + "texto11.txt");

        try (FileReader in = new FileReader(file10);
             FileWriter out = new FileWriter(file11)) {
            int byt;
            while ((byt = in.read()) != -1) {
                out.write(byt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}