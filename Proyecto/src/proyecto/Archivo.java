package proyecto;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;

public class Archivo {

    private RandomAccessFile file;

    public Archivo() {

    }

    public Archivo(RandomAccessFile file) {
        this.file = file;
    }

    // Apertura del fichero
    public void abrir(AdminCampo r)
            throws IOException {
        file = new RandomAccessFile("MetaData.dat", "rw");
    }

    public void escribir(AdminCampo registro, Campos c) throws IOException {
        if (file != null) {
            registro.writeCampo(file, c);
        }
    }

    public void readC(AdminCampo reg) throws IOException, ParseException {
        reg.readCampos(file);

    }

    public void cerrar()
            throws IOException {
        if (file != null) {
            file.close();
        }
    }

    public long File_size() throws IOException {

        return file.length();
    }
    public void modificarC(AdminCampo c,Campos p) throws IOException{
        c.modificarCampo(file,p.tam
                ,p);
    }

}
