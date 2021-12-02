package proyecto;

import java.io.Serializable;


public class Campo implements Serializable{
    String tipo;
    String nombre;
    int longitud;
    boolean llave_primaria;

    public Campo(String nombre, String tipo, int longitud, boolean llave_primaria) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.longitud = longitud;
        this.llave_primaria = llave_primaria;
    }

    public Campo() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isLlavePrimaria() {
        return llave_primaria;
    }
    
    @Override
    public String toString() {
        return  nombre;
    }
}
