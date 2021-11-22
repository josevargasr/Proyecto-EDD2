package proyecto;


public class Campo {
    String tipo;
    String nombre;
    boolean llave_primaria;

    public Campo(String nombre, String tipo, boolean llave_primaria) {
        this.nombre = nombre;
        this.tipo = tipo;
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
