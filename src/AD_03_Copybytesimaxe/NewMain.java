/*
 3)   exercicio copybytesimaxe
  
a) partindo do que aprendiches no exercicio anterior ( copybytestexto ) 
saberias copiar  o contido dun arquivo .jpg noutro ? . Comprobao copiando a
imaxe foto.jpg que se achega  nun arquivo que se denomine foto2.jpg . Canto 
ocupa foto2.jpg ? ocupa o mesmo que a imaxe orixinal ?

b) executa por segunda vez a aplicacion para que se engada de novo a imaxe 
foto.jpg  á imaxe foto2.jpg. ¿que ocorre se abrimos a imaxe, vese a imaxe 
repetida? , Canto ocupa agora o arquivo foto2.jpg? 

c) o que fixeches ata o de agora  e traballar con fluxos de datos tipo byte,
sen diferenciar se son datos de texto ou binarios (os de texto gardan un byte
por caracter , e os binarios almacenan imaxenes ou caracteres en un formato
que pode ler ou escribir diferentes tipos primitivos de datos . E dicir en
canto o que o que fixemos ate agora para o sistema non hai diferencia entre
coiar un ficheiro de texto ou unha imaxe posto que non interpreta o contido
de cada byte senon que simplesmente o lé e o copia ( un byte de cada vez).


Pero se queremos mellorar a velocidade debemos recurrir a outras clases que
combinadas con as clases anteriore  (FileInputStream e FileOutputStream ) snos
permiten mellorara velocidade dos fluxos de datos . 

O que ocorre é que a partir de agora , estas clases non sempre van ser a mesmas
para copiar ficheiro de texto ou binarios posto que teñen funcionalidades 
especificas dependendo do tipo de ficheiro de que se trata .

Para traballar con ficheiros binarios  temos duas clases que melloran a 
velociade de entrada e salida do fluxo de datos. Estas clases son as clases 
BufferedInputStream e BuffferedOutputStream. A ventaxa de usar  buffers e que
se reduce o numero de operacions de entrada saida que son feitas por o disco.
Asi, si por exemplo , se un buffer pode conter 4000 bytes , so se fará a 
operación de escritura ou lectura cando dito buffer se encha ou cando o fluxo
sexa pechado (close). En caso de non usar buffers a grabacion dos 4000 bytes
requriria centos de operacions de entrada saida , e xa que cada operacion
require a recolocacion das cabezas do disco  isto leva moito tempo.


Para poder  facer isto Java permite  combinar dous ou mais fluxos de datos
(Streams) para engadir a funcionalidade que se necesita par unha aplicación
( FileInputStream  e BufferedInputStream son fluxos de datos que se poden 
combinar ) . Para combinar fluxos de datos  en Java debes usar un obxecto 
de unha das clases como argumento para o constuctor da outra clase .

Sabendo que  a clase acepta como argumento do seu constructor a un obxecto 
da clase FileInputStream.

fai una segunda version do exercicio anteior e chamaa copybytesimaxe2 
modifica o exercicio anterior para facr o mesmo pero aumentando a velocidade 
de copia facendo uso de ditas clases combinadas . 

Ten en conta que a clase BufferedInputStream  ten un metodo read() que 
permite ler un byte e BufferedOutputStream ten un metodo   write() que perite
escribilo e que do memso xeito que as clases FileInputStream e FileOutputStream
tamén podemos detectar o fin do ficheiro mediante a consulta do valor devolto
na lectura, si e -1 indica o fin do ficheiro .
  
 Notaches a diferenza en velocidade a hora de facer a copia da imaxen con
respecto a cando NON usaches as clases BufferedInputStream e 
BufferedOutputStream ?  Se non a notas escolle unha imaxe de maior numero
de bytes ou executa varias veces o realiado  no apartado b) de este exercicio
antes de executar este apartado ( para que a imaxen sexa o suficientemente
grande para que se note a diferenza na velocidade de copia)
*/
package AD_03_Copybytesimaxe;

import AD_02_FileStream.FileStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import misc.StopWatch;

/**
 *
 * @author oracle
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File file1 = new File("/home/oracle/Desktop/compartido/Netbeans/AD_01_Archivos/src/AD_03_Copybytesimaxe/foto.jpg");
        File file2 = new File("/home/oracle/Desktop/compartido/Netbeans/AD_01_Archivos/src/AD_03_Copybytesimaxe/foto2.jpg");

        ejA(file1, file2);
        ejB(file1, file2);
        ejC(file1, file2);
        ejD(file1, file2);
    }

    private static void ejA(File file1, File file2) {
        StopWatch sw = new StopWatch();
        try(FileInputStream in = new FileInputStream(file1);
                FileOutputStream out = new FileOutputStream(file2)){
            while(in.available() > 0){
                out.write(in.read());
            }           
        } catch (IOException ex) {
            Logger.getLogger(FileStream.class.getName()).log(Level.SEVERE, null, ex);
        }   
        System.out.println("1 - Archivos" + (file1.length() == file2.length() ? " " : " no ") + "iguales");
        System.out.println("dif: " + (file1.length() - file2.length()));
        System.out.println("Time: " + sw.getElapsedFormat());
    }

    private static void ejB(File file1, File file2) {  
        StopWatch sw = new StopWatch();
        for (int i = 0; i < 2; i++) {
            try(FileInputStream in = new FileInputStream(file1);
                FileOutputStream out = new FileOutputStream(file2, i == 1)){
                while(in.available() > 0){
                    out.write(in.read());
                }           
            } catch (IOException ex) {
                Logger.getLogger(FileStream.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }    
        sw.stop();
        System.out.println("2 - Archivos" + (file1.length() == file2.length() ? " " : " no ") + "iguales");
        System.out.println("dif: " + (file1.length() - file2.length()));
        System.out.println("Time: " + sw.getElapsedFormat());
    }

        private static void ejC(File file1, File file2) {
        StopWatch sw = new StopWatch();
        try(BufferedInputStream in =new BufferedInputStream( new FileInputStream(file1));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file2))){
            while(in.available() > 0){
                out.write(in.read());
            }           
        } catch (IOException ex) {
            Logger.getLogger(FileStream.class.getName()).log(Level.SEVERE, null, ex);
        }  
        System.out.println("3 - Archivos" + (file1.length() == file2.length() ? " " : " no ") + "iguales");
        System.out.println("dif: " + (file1.length() - file2.length()));
        System.out.println("Time: " + sw.getElapsedFormat());
    }
        
    private static void ejD(File file1, File file2) {        
        StopWatch sw = new StopWatch();
        for (int i = 0; i < 2; i++) {
            try(BufferedInputStream in =new BufferedInputStream( new FileInputStream(file1));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file2, i == 1))){
                while(in.available() > 0){
                    out.write(in.read());
                }           
            } catch (IOException ex) {
                Logger.getLogger(FileStream.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }    
        System.out.println("4 - Archivos" + (file1.length() == file2.length() ? " " : " no ") + "iguales");
        System.out.println("dif: " + (file1.length() - file2.length()));
        System.out.println("Time: " + sw.getElapsedFormat());
    }
    
}
