/*

exercicio 8
textodelimitado
(para facer este exercicio partimos de que temos creada a clase Product no 
exercicio anterior)

Graba nun ficheiro de texto delimitado mediante os metodos print e println da
clase PrintWriter ,
os contidos dos tres arrays seguintes (debes crealos tal cual en Java ) tendo 
en conta que cada tres elementos que  ocupan a mesma posicion nos arrays 
representan a os campos dun rexistro dunha taboa de produtos,
onde cada produto ten un codigo unha descricion e un prezo:
        String[] cod={"p1","p2","p3"};
        String[] desc ={"parafusos","cravos","tachas"};
        Double[] prezo ={3,4,5};
        
    Ten en conta duas cousas moi importantes : 
 -  que debes usar como separadores de campo  tabuladores : \t
 -  e que os separadores de rexistros deben ser  retornos de linea : o metodo
println(argumento) da clase PrintWriter (BufferedWriter(FileWriter(File)) 
convirte o argumento nunha  cadea de texto e inclue un cambio de linea ao final
      
     Despois de grabados ditos valores debes voltalos a ler rexistro a rexisto 
mediante ao metodo readLine() da clase BufferedReader a cal lle pasamos como
parametro a clase FilerReader cargando os seus valores nun obxecto da clase
Product  e imprimindo os valores dos atributos de dito obxecto mediante un
metodo da clase product que sobeescriba o seu metodo toString() 

notas: cada vez que leas unha linea do ficheiro debes pasar  os valores de
cada campo  a un array mediante a funcion de cadea "split()" ,  cargalos no 
obxecto da clase Product para imprimilos a continuacion.
      
Debes investigar na interede como usar a funcion de cadea split para separar 
os valores dun ha cadea
opcional: se observas o resultado impreso veras que os precios estan escritos 
co simbolo €   Podes investigar como facer isto (hai varios xeitos de facelo.
Pista:  tes a clase NumberFormat que ten o metodo getCurrencyInstance() que 
te permite crear un obxecto NumberFormat con formato monetario , co cal podes 
dar formato monetario a un numero
          
  run:
Codigo:        p1
Descricion: parafusos
Prezo:       3,00 €

Codigo:        p2
Descricion: cravos 
Prezo:       4,00 €

Codigo:        p3
Descricion: tachas
Prezo:       5,00 €

     */
package AD_08_textodelimitado;

import AD_07_Productsstream.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import misc.Res;

/**
 *
 * @author fsancheztemprano
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        
        String[] cod={"p1","p2","p3"};
        String[] desc ={"parafusos","cravos","tachas"};
        double[] prezo ={3,4,5};
        
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < cod.length; i++) {
            products.add(new Product(cod[i], desc[i], prezo[i]));
        }
        
        
        File file1 = new File(Res.RES_PATH + "productos8.txt");
        
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file1)))){
            for(Product prod : products){
                out.print(prod.getCodigo());
                out.print("\t");
                out.print(prod.getDescripcion());
                out.print("\t");
                out.print(prod.getPrecio());
                out.print("\n");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try(BufferedReader in = new BufferedReader(new FileReader(file1))){
            in.lines().forEach(action -> {
                String[] fields = action.split("\t");
                System.out.println(new Product(fields[0], fields[1], Double.parseDouble(fields[2])).toString() );
                    });
        }
        
    }
    
}
