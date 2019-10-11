package AD_14_XMLproba0;

import java.util.StringJoiner;

public class Autor {

    private String codigo;
    private String nombre;
    private String titulo1;
    private String titulo2;

    public Autor() {
    }

    public Autor(String codigo, String nombre, String titulo1, String titulo2) {
        this.codigo  = codigo;
        this.nombre  = nombre;
        this.titulo1 = titulo1;
        this.titulo2 = titulo2;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitulo1() {
        return titulo1;
    }

    public void setTitulo1(String titulo1) {
        this.titulo1 = titulo1;
    }

    public String getTitulo2() {
        return titulo2;
    }

    public void setTitulo2(String titulo2) {
        this.titulo2 = titulo2;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Autor.class.getSimpleName() + "[", "]")
            .add("codigo='" + codigo + "'")
            .add("nombre='" + nombre + "'")
            .add("titulo1='" + titulo1 + "'")
            .add("titulo2='" + titulo2 + "'")
            .toString();
    }
}
