package proyecto;


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
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Metodos {

    ArrayList<String> campos = new ArrayList<String>();
    ArrayList<String> types = new ArrayList<String>();
    ArrayList<String> key_s = new ArrayList<String>();

    static Scanner read = new Scanner(System.in);

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

        } catch (Exception e) {
            e.printStackTrace();

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

        } catch (Exception e) {

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ous.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CreateCampos(Metadata metadata, String nombre, String tipo, int contador, String keyS) throws IOException, ParseException {
        if (metadata.getNumregistros() == 0) {
            types.add(contador, tipo);
            campos.add(contador, nombre);
            metadata.setCampos(campos);
            metadata.setTipos(types);
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
                int campo = posicion;
                ArrayList campos = metadata.getCampos();
                ArrayList tipos = metadata.getTipos();
                if (campo >= 0 && campo < campos.size() && campo == 0) {
                    campos.set(campo, nuevo_nombre);
                    metadata.setCampos(campos);
                    JOptionPane.showMessageDialog(null, "Success! Check Table");
                } else if (campo >= 0 && campo < campos.size()) {
                    campos.set(campo, nuevo_nombre);
                    tipos.set(campo, nuevo_tipo);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Size");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Incorret Value Inserted.");
            }
        }

    }

    public void DeleteCampos(Metadata metadata, int posicion) {
        if (metadata.getNumregistros() == 0) {
            int campo = posicion;
            ArrayList campos = metadata.getCampos();
            ArrayList tipos = metadata.getTipos();
            if (campo >= 0 && campo < campos.size()) {
                if (campo == 1) {
                    JOptionPane.showMessageDialog(null, "No se puede borrar la llave primaria!");
                } else {
                    campos.remove(campo);
                    tipos.remove(campo);
                    metadata.setCampos(campos);
                    metadata.setTipos(tipos);
                    JOptionPane.showMessageDialog(null, "Success Check table");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Action could not be performed!");
            }
        }
    }

//    public void ExportToExcel(Metadata metadata, String name, JTable table) {
//        //Blank workbook
//        XSSFWorkbook workbook = new XSSFWorkbook();
//
//        //Create a blank sheet
//        XSSFSheet sheet = workbook.createSheet("Estructura de Datos");
//        int registros = table.getModel().getRowCount();
//
//        Map<String, Object[]> data = new TreeMap<String, Object[]>();
//        data.put("1", metadata.getCampos().toArray());
//        for (int i = 0; i < registros; i++) {
//            ArrayList Registro = new ArrayList();
//            for (int j = 0; j < metadata.getCampos().size(); j++) {
//                Registro.add(table.getValueAt(i, j));
//            }
//            data.put(Integer.toString(i + 2), Registro.toArray());
//        }
//
//        //Iterate over data and write to sheet
//        Set<String> keyset = data.keySet();
//        int rownum = 0;
//        for (String key : keyset) {
//            Row row = sheet.createRow(rownum++);
//            Object[] objArr = data.get(key);
//            int cellnum = 0;
//            for (Object obj : objArr) {
//                Cell cell = row.createCell(cellnum++);
//                if (obj instanceof String) {
//                    cell.setCellValue((String) obj);
//                } else if (obj instanceof Integer) {
//                    cell.setCellValue((Integer) obj);
//                }
//
//            }
//        }
//        try {
//            //Write the workbook in file system
//            File filer = new File(name += ".xlsx");
//            filer.delete();
//            filer.createNewFile();
//            FileOutputStream out = new FileOutputStream(filer);
//            workbook.write(out);
//            out.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static boolean validaNumeroEnteroPositivo_Exp(String texto) {
        return texto.matches("^[0-9]+([\\.,][0-9]+)?$");
    }

    public static boolean validaTexto_Exp(String texto) {

        return texto.matches("[a-zA-Z]*");
    }

    public void IntroducirRegistros(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        String registro;
        ArrayList<String> datos = new ArrayList<String>();
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
