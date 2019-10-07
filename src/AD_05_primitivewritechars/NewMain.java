/*
exercicio 5
 primitivewritechars
 
Neste exercicio seguiremos tratando os fluxos binarios de datos pero agora tratandoos como soporte de tipos de datos primitivos e valores String

DataInputStream dispon dunha serie de  métodos  para que partindo  de fluxos binaros de datos  podamos ler tipos primitivos de datos e valores String 

DataOutputStream dispon dunha serie de  métodos  para que partindo  de fluxos binaros de datos  podamos escribir tipos primitivos de datos e valores String que previamente foron codificados nestes fluxos mediante DataInputStream

Dado que DataInputStream admite como parametro calquer clase herdada de InputStream , e BufferedInputStream cumpre este requisito,  o único que temos que facer para poder usar os metodos de DataInputStream e pasarlle como parmaetro un obxecto BufferedInputStream coma o traballado no exercicio anterior (exercicio 3)

Dado que DataOutputStream admite como parametro calquer clase herdada de OutputStream , e BufferedOutputStream cumpre este requisito o único que temos que facer para poder usar os metodos de DataOutputStream e pasarlle como parmaetro un obxecto BufferedOutputStream coma o traballado no exercicio anterior (exercicio 3)

Propoñovos un exercicio que consistirá grabar unha mesma cadea  de texto duas veces usando o metodo  writeChars(String) nun ficheiro denominado text4.txt para posteriormente recuperalas (voltalas a ler) mediante o metodo readChar().

Ter en cota que writeChars(String) escribe 2 bytes por caracter  (podemos cambiar calquera vogal da cadea por esa mesma vogal acentuada ou unha letra ñ e veremos que a lonxitude da cadea non varia

Ter en cota que readChar() lé un caracter de cada vez co cal haberá que facer un bucle que  execute esta instruccion tantas veces como letras ten a cadea previamente grabada 

cadea a grabar : "o tempo está xélido"

metodos a usar :
clase DataOutputStream, metodo writeChars(String) -- escribe o String (ocupa 2 bytes por caracter)
clase DataInputStream, metodo readChar() -- lee un caracter de cada vez (2 bytes)

o metodo .available() dun obxecto de tipo DataInputStream dinos cantos bytes quedan por ler dun ficheiro
o metodo .length() aplicado a calquera String danos a sua lonxitude en numero de caracteres.
o metodo .size() aplicado a un obxecto de tipo DataOutputStream devolvenos o que ocupa este en bytes


nota: dado que grabamos duas cadeas de igual lonxitude , para lelas debemos repetir o codigo correspondente a una lectura duas veces, ou facer un bucle ata que o contido a ler (available ) sexa de una lonxitude igual a 0


o resultado da execucion debería ser o seguinte:



a cadea a escribir e : o tempo está xélido
a lonxitude desta cadea en carateres e: 19
pero cando se grabe co metodo writeChars vai a ocupar 38 bytes  posto que se usan 2 bytes por cada caracter  sempre
writeChars escribiu:  38bytes
writeChars escribiu:  38bytes
bytes totais escritos = 76
lemos a primeira cadea:  o tempo está xélido
o numero de bytes lidos e :38
bytes restantes por ler : 38
lemos a segunda cadea:  o tempo está xélido
o numero de bytes lidos e :38
bytes restantes por ler : 0


Notas:
Podemos comprobar que se intentamos abrir a o ficheiro de texto  text3.txt co editor gedit non o permite. debemos usar nano , vi , emacs ou outro editor non grafico  e comprobaremos a forma en que foron grabadas as  cadeas.
podemos ver o contido do ficheiro de texto en hexadecimal e en codigo ASCII  con:  hexdump -C nome_ficheiro
ou ben en binario puro : xxd -b nome_ficheiro
mostrar lonxitude en bytes do ficheiro: wc -c nome_ficheiro

 
 */
package AD_05_primitivewritechars;

/**
 *
 * @author oracle
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
