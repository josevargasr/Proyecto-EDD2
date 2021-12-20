package proyecto;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable{
    long lugar;
    int llave;
    String tam;
    ArrayList<Object> datos=new ArrayList<>();

    public Data() {
        tam="|";
    }

    
    public long getLugar() {
        return lugar;
    }

    public void setLugar(long lugar) {
        this.lugar = lugar;
    }

    public int getLlave() {
        return llave;
    }

    public void setLlave(int llave) {
        this.llave = llave;
    }


    public String getTam() {
        return tam;
    }

    public void setTam(String tam) {
        this.tam = tam;
    }

    public ArrayList<Object> getDatos() {
        return datos;
    }

    public void setDatos(ArrayList<Object> datos) {
        this.datos = datos;
        llave=(int)datos.get(0);
    }

    @Override
    public String toString() {
        return "Data{" + "ubicacion=" + lugar + ", key=" + llave +'}';
    }   

}