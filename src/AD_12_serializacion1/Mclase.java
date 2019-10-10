package AD_12_serializacion1;

import java.io.Serializable;

public class Mclase implements Serializable {

    private String nombre;
    //private int numero1;
    private transient int numero1;
    private double numero2;

    public Mclase(String nombre) {
        this.nombre = nombre;
    }

    public Mclase(String nombre, int numero1, double numero2) {
        this.nombre  = nombre;
        this.numero1 = numero1;
        this.numero2 = numero2;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero1() {
        return numero1;
    }

    public void setNumero1(int numero1) {
        this.numero1 = numero1;
    }

    public double getNumero2() {
        return numero2;
    }

    public void setNumero2(double numero2) {
        this.numero2 = numero2;
    }

    @Override
    public String toString() {
        return "Mclase{" +
               "nombre='" + nombre + '\'' +
               ", numero1=" + numero1 +
               ", numero2=" + numero2 +
               '}';
    }

}
