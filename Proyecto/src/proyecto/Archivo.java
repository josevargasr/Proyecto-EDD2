package proyecto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Archivo {
    private String nombre;
    File archivo = null;
    private ArrayList<Campo> listaCampos = new ArrayList();

    public Archivo(String nombre) {
        this.nombre = nombre;
        archivo = new File("./"+ this.nombre + ".abc");
    }

    public Archivo() {
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Campo> getListaCampos() {
        return listaCampos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setListaCampos(ArrayList<Campo> listaCampos) {
        this.listaCampos = listaCampos;
    }
    
    public void addCampo(Campo campo) {
        this.listaCampos.add(campo);
    }
    
    public void escribirArchivo() {
        FileOutputStream fw = null;
        ObjectOutputStream bw = null;
        try {
            fw = new FileOutputStream(archivo);
            bw = new ObjectOutputStream(fw);
            for (Campo t : listaCampos) {
                bw.writeObject(t);
            }
            bw.flush();
        } catch (Exception ex) {
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception ex) {
            }
        }
    }

    @Override
    public String toString() {
        return "Archivo{" + "nombre=" + nombre + '}';
    }
    
}
