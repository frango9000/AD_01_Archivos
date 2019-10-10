package AD_07_Productsstream;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.StringJoiner;

public class Product implements Serializable {
    private String codigo;
    private String descripcion;
    private double precio;

    public Product() {
        codigo = "";
        descripcion = "";
        precio = 0;
    }

    public Product(String codigo, String descripcion, double precio) {
        this.codigo      = codigo;
        this.descripcion = descripcion;
        this.precio      = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
            .add("codigo='" + codigo + "'")
            .add("descripcion='" + descripcion + "'")
            .add("precio=" + NumberFormat.getCurrencyInstance(java.util.Locale.GERMANY).format(precio))
            .toString();
    }
}
