package proyecto;


import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Metodos {

    ArrayList<String> campos = new ArrayList<String>();
    ArrayList<String> tipos = new ArrayList<String>();
    ArrayList<String> llave = new ArrayList<String>();

    static Scanner input = new Scanner(System.in);

    public Metodos() {

    }

    public static void Delete(int position) throws IOException {

        File file = null;
        FileReader fr = null;
        FileWriter fw = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            file = new File("Registro.txt");
            fr = new FileReader(file);
            fw = new FileWriter(file, true);

            br = new BufferedReader(fr);
            bw = new BufferedWriter(fw);
            boolean continuar = true;
            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            char actual;
            char invalido = (char) -1;
            int contador = 0;
            int contadorchar = 0;
            int DisqueByte = -1;
            int BytePosition = -1;
            int DeleterStart = 0;
            int ByteLength = 0;

            while ((actual = (char) br.read()) != invalido) {

                DisqueByte++;
                BytePosition++;
                if (actual == '/' && contadorchar == 0) {
                    br.mark(DisqueByte);
                    contadorchar++;
                } else if (actual == '/' && contadorchar == 1) {
                    contador++;
                    if (contador == position) {
                        br.reset();
                        String insertion = "";
                        ByteLength = (BytePosition - 1) - DeleterStart;
                        insertion += Integer.toString(ByteLength);
                        for (int i = insertion.length(); i < ByteLength; i++) {
                            insertion += "*";

                        }
                        raf.writeBytes(insertion);
                        break;
                    } else {
                        DeleterStart = DisqueByte;
                        br.mark(DisqueByte);

                    }

                }

            }

        } catch (IOException e) {
        }
        br.close();
        bw.close();
        fr.close();
        fw.close();

    }

    public static void ByteDelete() {
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            file = new File("Metadata.project");
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);

        } catch (IOException e) {

        }

    }

    public static void Write(Metadata meta) {
        File file = null;
        FileOutputStream fis = null;
        ObjectOutputStream ous = null;

        try {
            file = new File("Metadata.project");
            fis = new FileOutputStream(file);
            ous = new ObjectOutputStream(fis);

            ous.writeObject(meta);
        } catch (IOException e) {
        }
        try {
            ous.close();
            fis.close();
        } catch (IOException e) {
        }
    }

    public void CreateCampos(Metadata metadata, String nombre, String tipo, int contador, String keyS) throws IOException, ParseException {
        if (metadata.getNumregistros() == 0) {
            tipos.add(contador, tipo);
            campos.add(contador, nombre);
            metadata.setCampos(campos);
            metadata.setTipos(tipos);
            metadata.setLlave_secundaria(keyS);
            metadata.setNombre(campos.toString());
            JOptionPane.showMessageDialog(null, "Se agrego el campo a la tabla.");
        } else {
            JOptionPane.showMessageDialog(null, "Campo no ingresado, imposible realizar accion.");
        }

    }

    public void ModificarCampos(Metadata metadata, String nuevo_nombre, String nuevo_tipo, int posicion) {
        if (metadata.getNumregistros() == 0) {
            try {
                int CP = posicion;
                ArrayList camps = metadata.getCampos();
                ArrayList tipo = metadata.getTipos();
                if (CP >= 0 && CP < camps.size() && CP == 0) {
                    camps.set(CP, nuevo_nombre);
                    metadata.setCampos(camps);
                    JOptionPane.showMessageDialog(null, "Success! Check Table");
                } else if (CP >= 0 && CP < camps.size()) {
                    camps.set(CP, nuevo_nombre);
                    tipo.set(CP, nuevo_tipo);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Size");
                }

            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Incorret Value Inserted.");
            }
        }

    }

    public void DeleteCampos(Metadata metadata, int posicion) {
        if (metadata.getNumregistros() == 0) {
            int campo = posicion;
            ArrayList camps = metadata.getCampos();
            ArrayList tipo = metadata.getTipos();
            if (campo >= 0 && campo < camps.size()) {
                if (campo == 1) {
                    JOptionPane.showMessageDialog(null, "No se puede borrar la llave primaria!");
                } else {
                    camps.remove(campo);
                    tipo.remove(campo);
                    metadata.setCampos(camps);
                    metadata.setTipos(tipo);
                    JOptionPane.showMessageDialog(null, "Success Check table");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Action could not be performed!");
            }
        }
    }
    
    public static boolean validaNumeroEnteroPositivo_Exp(String texto) {
        return texto.matches("^[0-9]+([\\.,][0-9]+)?$");
    }

    public static boolean validaTexto_Exp(String texto) {

        return texto.matches("[a-zA-Z]*");
    }

    public void IntroducirRegistros(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        String registro;
        ArrayList<String> datos = new ArrayList<>();
        for (int i = 0; i < modelo.getColumnCount(); i++) {
            registro = JOptionPane.showInputDialog(null, "Ingrese " + modelo.getColumnName(i));
            datos.add(registro);

        }
        modelo.addRow(datos.toArray());
        tabla.setModel(modelo);

    }

    public void BorrarRegistros(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        String registro;
        int row = 0;
        boolean confirm = false;
        registro = JOptionPane.showInputDialog(null, "Ingrese el criterio definido que quiera eliminar");

        for (int i = 0; i < modelo.getColumnCount(); i++) {
            for (int k = 0; k < modelo.getRowCount(); k++) {
                if (modelo.getValueAt(k, i).equals(registro)) {
                    confirm = true;
                    row = k;
                }
            }
        }

        if (confirm) {
            modelo.removeRow(row);
            tabla.setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(null, "No existe ese criterio");
        }

    }
}
