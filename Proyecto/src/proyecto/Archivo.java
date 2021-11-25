package proyecto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Archivo {
    private String nombre;
    private String path;
    File archivo = null;
    private ArrayList<Campo> listaCampos = new ArrayList();
    private ArrayList<String> listaRegistros = new ArrayList();

    public Archivo(String nombre, String path) {
        this.nombre = nombre;
        this.path = path;
        archivo = new File(path + ".abc");
        try{
            archivo.createNewFile();
        }catch(Exception e){
            
        }
        
    }

    public Archivo() {
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getPath() {
        return path;
    }

    public ArrayList<Campo> getListaCampos() {
        return listaCampos;
    }
    
    public ArrayList<String> getListaRegistros() {
        return listaRegistros;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setPath(String path) {
        this.path = path;
    }

    public void setListaCampos(ArrayList<Campo> listaCampos) {
        this.listaCampos = listaCampos;
    }
    
    public void setListaRegistros(ArrayList<String> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }
    
    public void addCampo(Campo campo) {
        this.listaCampos.add(campo);
    }
    
    public void addRegistro(String registro) {
        this.listaRegistros.add(registro);
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
