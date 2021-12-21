package proyecto;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Main extends javax.swing.JFrame {

    boolean validar = false;
    int contador = 0, cantidad = 0;
    ArrayList<Campo> listcampos = new ArrayList();
    boolean openfile = false;

    public void Salvar_Archivo() {
        
        JOptionPane.showMessageDialog(null, "Su archivo se ha guardado exitosamente");
    }

    public void Cargar_Archivo() {
        FileSuccess = 0;
        String direction;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("./"));
        FileNameExtensionFilter data = new FileNameExtensionFilter("DAT FILE", "dat");
        fileChooser.setFileFilter(data);
        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) { //Cuando le da guardar
            File Archivo;
            try {
                if (fileChooser.getFileFilter().getDescription().equals("DAT FILE")) { //Chequea si lo que quiere guardar es DAT FILE
                    direction = fileChooser.getSelectedFile().getPath() + ".dat";
                    Archivo = fileChooser.getSelectedFile();
                    this.file = Archivo;
                    JOptionPane.showMessageDialog(null, "El archivo se abrió con éxito");
                    FileSuccess = 1;
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo abrir, asegurese que su archivo sea un archivo DAT.");
                }

            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(this, "Hubo un problema.");
            }
            try {

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Hubo un error al cerrar el archivo.");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Operacion cancelada.");
        }

    }

    private void BuildTable(Metadata metadata, int funcion) {
        if (funcion == 0) {
            Object[] campos = metadata.getCampos().toArray();
            DefaultTableModel tabla = new DefaultTableModel();
            tabla.setColumnCount(campos.length);

            tabla.setColumnIdentifiers(campos);
            Table.setModel(tabla);
        } else if (funcion == 1) {
            Table.setModel(cleanTable);
        }

    }

    public void CargarMetadatos() throws ClassNotFoundException {
        try {
            RAfile = new RandomAccessFile(file, "rw");
            int tamaño = RAfile.readInt();
            byte[] data = new byte[tamaño];
            RAfile.read(data);
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            ObjectInputStream read = new ObjectInputStream(in);
            metadata = (Metadata) read.readObject();
            metadata.setSizeMeta(tamaño);
        } catch (IOException ex) {
        }
    }

    public void LeerDatosRegistro() throws ClassNotFoundException {

        try {

            RAfile = new RandomAccessFile(file, "rw");
            RAfile.seek(0);
            int tamaño = RAfile.readInt();
            RAfile.seek(tamaño + 4);

            boolean eliminado = false;

            while (RAfile.getFilePointer() < RAfile.length()) {
                eliminado = false;
                tamaño = RAfile.readInt();
                byte[] data = new byte[tamaño];
                RAfile.read(data);
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream read = new ObjectInputStream(in);
                Data d = (Data) read.readObject();
                if (d.getTam().contains("*")) {
                    eliminado = true;
                    AvailList.BestFit(tamaño, d.lugar);

                } else {
                    Export2 = new ArrayList<>();
                    Registro temporal = new Registro(d.getLlave());
                    temporal.setByteOffset(d.getLugar());
                    metadata.getArbolB().insert(temporal);
                    for (int i = 0; i < d.getDatos().size(); i++) {
                        Export2.add(d.getDatos().get(i));

                    }
                    Table_Insert_Registro();

                }

            }
            metadata.ArbolB.traverse();
            metadata.ArbolB.PrintLevels();
        } catch (IOException ex) {
        }
    }

    private void Nuevo_Archivo() {

//        String direction;
//        int option = JOptionPane.showConfirmDialog(this, "Desea salvar?");
//
//        switch (option) {
//            case JOptionPane.NO_OPTION:
//                Crear_Archivo();
//                if (FileSuccess == 1) {
//                    metadata = new Metadata();
//                    BuildTable(metadata, 1);
//                }
//                break;
//            case JOptionPane.YES_OPTION:
//                Salvar_Archivo();
//                break;
//            default:
//                break;
//        }
        Crear_Archivo();
        if (FileSuccess == 1) {
            metadata = new Metadata();
            BuildTable(metadata, 1);
            openfile = true;
        }

    }

    private void Crear_Archivo() {

        FileSuccess = 0;
        String direction;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("./"));
        FileNameExtensionFilter data = new FileNameExtensionFilter("DAT FILE", "dat");
        fileChooser.setFileFilter(data);
        int seleccion = fileChooser.showSaveDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {

            File archivo = null;
            FileOutputStream fos = null;
            ObjectOutputStream ous = null;

            try {
                if (fileChooser.getFileFilter().getDescription().equals("DAT FILE")) {
                    direction = fileChooser.getSelectedFile().getPath() + ".dat";
                    direction = direction.replace(".dat", "");
                    direction += ".dat";

                    archivo = new File(direction);
                    if (archivo.length() == 0) {
                        this.file = new File(direction);
                        JOptionPane.showMessageDialog(this, "Enhorabuena!\n Cualquier cambio sin salvar no se puede recuperar");
                        jmi_Campos.setEnabled(true);
                        jm_Registros.setEnabled(true);
                        jm_indices.setEnabled(true);
                        jm_Estandarizacion.setEnabled(true);
                        jmi_Salvar_Archivo.setEnabled(true);
                        jmi_Cerrar_Archivo.setEnabled(true);
                    } else if (archivo.exists()) {
                        archivo.delete();
                        archivo.createNewFile();
                        this.file = new File(direction);
                        JOptionPane.showMessageDialog(this, "File OverWritten, New Length: " + archivo.length());
                    }
                    FileSuccess = 1;
                } else {
                    JOptionPane.showMessageDialog(this, "No se puede salvar. Use DAT FILE.");
                }
                fos = new FileOutputStream(archivo);
                ous = new ObjectOutputStream(fos);
                ous.flush();
            } catch (HeadlessException | IOException e) {
                JOptionPane.showMessageDialog(this, "Error desconocido");
            }
            try {
                ous.close();
                fos.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Cerrando Archivos.");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Operacion cancelada!");
        }
    }

    public void Escribir_Metadatos() throws IOException {

        RAfile = new RandomAccessFile(file, "rw");
        ByteArrayOutputStream obArray = new ByteArrayOutputStream();
        ObjectOutputStream objeto = new ObjectOutputStream(obArray);
        objeto.writeObject(metadata);
        byte[] datos = obArray.toByteArray();
        RAfile.seek(0);
        RAfile.writeInt(datos.length);
        RAfile.write(datos);
        metadata.setSizeMeta((int) RAfile.length());

    }

    private void Crear_Registro() {

        TableModel model = Table.getModel();
        DefaultTableModel modelo = (DefaultTableModel) model;

        Object[] insertarray = new Object[metadata.getCampos().size()];
        for (int i = 0; i < metadata.getCampos().size(); i++) {
            String temp = JOptionPane.showInputDialog(null, "Ingrese: " + metadata.getCampos().get(i).toString() + "\n Tipo:  " + metadata.getTipos().get(i).toString());
            switch (metadata.getTipos().get(i).toString()) {
                case "Int":
                    insertarray[i] = Integer.parseInt(temp);
                    break;
                case "long":
                    insertarray[i] = Long.parseLong(temp);
                    break;
                case "String":
                    insertarray[i] = temp;
                    break;
                case "Char":
                    insertarray[i] = temp.charAt(0);
                    break;
                default:
                    break;
            }
        }

        ArrayList export2 = new ArrayList();
        export2.addAll(Arrays.asList(insertarray));
        Registro temporal = new Registro(Integer.parseInt(insertarray[0].toString()));

        if (metadata.getArbolB().search(temporal) == null) {
            metadata.getArbolB().insert(temporal);
            modelo.addRow(insertarray);
            metadata.addnumregistros();
            try {
                Escribir_Datos_Registro(export2);
                Buscar_Dato_Archivo(temporal);
            } catch (IOException | ClassNotFoundException ex) {
            }

            Table.setModel(modelo);

        } else {
            JOptionPane.showMessageDialog(null, "Este registro ya existe.");
        }

    }

    public void Escribir_Datos_Registro(ArrayList<Object> info_registro) {

        try {
            if (AvailList.head != null) {

                Data datos = new Data();
                Registro temporal = new Registro(Integer.parseInt(info_registro.get(0).toString()));
                long byteOffset = RAfile.length();
                NodoB d = metadata.getArbolB().search(temporal);
                int x = searchEnNodo(d, temporal.getLlave());

                d.key[x].setByteOffset(byteOffset);
                datos.setDatos(info_registro);
                datos.setLugar(byteOffset);

                ByteArrayOutputStream obArray = new ByteArrayOutputStream();
                ObjectOutputStream objeto = new ObjectOutputStream(obArray);
                objeto.writeObject(datos);

                byte[] dat = obArray.toByteArray();
                int required_size = dat.length;
                ListaEnlazada.Node espacio = AvailList.SearchSpace(required_size);

                if (espacio == null) {
                    RAfile.seek(byteOffset);
                    RAfile.writeInt(dat.length);
                    RAfile.write(dat);
                } else {
                    datos.setLugar(espacio.posicion);
                    int j = 0;
                    for (int i = 0; i < (espacio.data - dat.length); i++) {
                        datos.setTam(datos.getTam() + "|");
                        j++;
                    }

                    obArray = new ByteArrayOutputStream();
                    objeto = new ObjectOutputStream(obArray);
                    objeto.writeObject(datos);
                    dat = obArray.toByteArray();
                    d.key[x].setByteOffset(datos.lugar);

                    RAfile.seek(datos.lugar);
                    RAfile.writeInt(dat.length);
                    RAfile.write(dat);
                    AvailList.deleteNode(AvailList.head, espacio);
                }
            } else {
                Data datos = new Data();
                Registro temporal = new Registro(Integer.parseInt(info_registro.get(0).toString()));
                long byteOffset = RAfile.length();
                NodoB d = metadata.getArbolB().search(temporal);
                int x = searchEnNodo(d, temporal.getLlave());

                d.key[x].setByteOffset(byteOffset);
                datos.setDatos(info_registro);
                datos.setLugar(byteOffset);

                ByteArrayOutputStream obArray = new ByteArrayOutputStream();
                ObjectOutputStream objeto = new ObjectOutputStream(obArray);
                objeto.writeObject(datos);
                byte[] dat = obArray.toByteArray();
                RAfile.seek(byteOffset);
                RAfile.writeInt(dat.length);
                RAfile.write(dat);
            }

        } catch (IOException | NumberFormatException ex) {
        }

    }

    private void Table_Insert_Registro() {

        TableModel model = Table.getModel();
        DefaultTableModel modelo = (DefaultTableModel) model;
        metadata.addnumregistros();

        Object insertArray[] = Export2.toArray();

        modelo.addRow(insertArray);

        Table.setModel(model);

    }

    public Data Buscar_Dato_Archivo(Registro r) throws IOException, ClassNotFoundException {

        if (metadata.getArbolB().search(r) != null) {
            NodoB contenido = metadata.getArbolB().search(r);
            int pos = searchEnNodo(contenido, r.getLlave());
            long byteOffset = contenido.key[pos].byteOffset;
            RAfile.seek(byteOffset);
            int tamaño = RAfile.readInt();
            byte[] data = new byte[tamaño];
            RAfile.read(data);
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            ObjectInputStream read = new ObjectInputStream(in);
            Data d = (Data) read.readObject();

            return d;
        } else {
            return null;
        }

    }

    public int searchEnNodo(NodoB d, int key) {

        int pos = 0;
        if (d != null) {
            for (int i = 0; i < d.n; i++) {
                if (d.key[i].getLlave() == key) {
                    break;
                } else {
                    pos++;
                }
            }
        } else {
        }
        return pos;
    }

    public void Eliminar_Dato_Archivo(ArrayList<Object> export) {

        try {
            Registro temporal = new Registro(Integer.parseInt(export.get(0).toString()));

            if (Buscar_Dato_Archivo(temporal) != null) {

                Data temp = Buscar_Dato_Archivo(temporal);
                RAfile.seek(temp.lugar);
                int size_act = RAfile.readInt();
                temp.setTam("*");
                temp.tam = "*";
                NodoB b = metadata.ArbolB.search(temporal);
                int pos = searchEnNodo(b, temporal.llave);
                long ubicacion = b.key[pos].getByteOffset();
                temp.lugar = ubicacion;

                ByteArrayOutputStream obArray = new ByteArrayOutputStream();
                ObjectOutputStream objeto = new ObjectOutputStream(obArray);
                objeto.writeObject(temp);

                byte[] dat2 = obArray.toByteArray();
                RAfile.write(dat2);

                AvailList.BestFit(size_act, temp.lugar);
                AvailList.ImprimeListaEnlazada(AvailList.head);
                metadata.ArbolB.remove(temporal);

            }
        } catch (IOException | ClassNotFoundException | NumberFormatException ex) {
        }
    }

    public void Modificar_Dato_Archivo(ArrayList<Object> Export) {
        try {
            Registro temporal = new Registro(Integer.parseInt(Export.get(0).toString()));
            if (Buscar_Dato_Archivo(temporal) != null) {
                Data temp = Buscar_Dato_Archivo(temporal);
                temporal.setByteOffset(temp.lugar);
                RAfile.seek(temp.lugar);
                int size_act = RAfile.readInt();

                Data new_size = new Data();
                new_size.setLlave((int) Export.get(0));
                new_size.setDatos(Export);
                new_size.setLugar(temp.getLugar());
                ByteArrayOutputStream obArray = new ByteArrayOutputStream();
                ObjectOutputStream objeto = new ObjectOutputStream(obArray);
                objeto.writeObject(new_size);
                byte[] dat = obArray.toByteArray();

                if (dat.length <= size_act) {
                    for (int i = 0; i < (size_act - dat.length); i++) {
                        new_size.setTam(new_size.getTam() + "|");
                    }

                    obArray = new ByteArrayOutputStream();
                    objeto = new ObjectOutputStream(obArray);
                    objeto.writeObject(new_size);
                    dat = obArray.toByteArray();
                    RAfile.write(dat);

                } else {
                    temp.setTam("*");
                    obArray = new ByteArrayOutputStream();
                    objeto = new ObjectOutputStream(obArray);
                    objeto.writeObject(temp);
                    byte[] dat2 = obArray.toByteArray();
                    RAfile.write(dat2);

                    long byteOffset = RAfile.length();

                    new_size.setLugar(byteOffset);
                    obArray = new ByteArrayOutputStream();
                    objeto = new ObjectOutputStream(obArray);
                    objeto.writeObject(new_size);
                    dat = obArray.toByteArray();

                    RAfile.seek(byteOffset);
                    RAfile.writeInt(dat.length);
                    RAfile.write(dat);

                    NodoB tmp = metadata.getArbolB().search(temporal);
                    int ubicacion = searchEnNodo(tmp, temp.getLlave());
                    tmp.key[ubicacion].byteOffset = byteOffset;

                    AvailList.BestFit(size_act, temporal.byteOffset);
                    AvailList.ImprimeListaEnlazada(AvailList.head);

                }
            }
        } catch (IOException | ClassNotFoundException | NumberFormatException ex) {
        }
    }

    public void Cargar_Archivo_2() {
        FileSuccess2 = 0;
        String direction;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("./"));
        FileNameExtensionFilter data = new FileNameExtensionFilter("DAT FILE", "dat");
        fileChooser.setFileFilter(data);
        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File Archivo;

            try {
                if (fileChooser.getFileFilter().getDescription().equals("DAT FILE")) {
                    direction = fileChooser.getSelectedFile().getPath() + ".dat";
                    Archivo = fileChooser.getSelectedFile();
                    this.file2 = Archivo;
                    JOptionPane.showMessageDialog(null, "Sucess!");
                    FileSuccess2 = 1;
                } else {
                    JOptionPane.showMessageDialog(this, "El archivo no se cargo, asegurese que su archivo es de tipo DAT.");
                }

            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(this, "Hubo un error!");
            }
            try {
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al cerrar archivo.");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Operacion cancelada!");
        }

    }

    public void CargarMetadatos_2() throws ClassNotFoundException {
        try {
            RAfile2 = new RandomAccessFile(file2, "rw");
            int tamaño = RAfile2.readInt();
            byte[] data = new byte[tamaño];
            RAfile2.read(data);
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            ObjectInputStream read = new ObjectInputStream(in);
            metadata2 = (Metadata) read.readObject();
            metadata2.setSizeMeta(tamaño);
        } catch (IOException ex) {
        }
    }

    public Main() {
        initComponents();
        metadata = new Metadata();
        //Setting up table default design.
        this.setLocationRelativeTo(null);
        Table.setForeground(Color.BLACK);
        Table.setBackground(Color.WHITE);
        Table.setFont(new Font("", 1, 22));
        Table.setRowHeight(30);
        Table.putClientProperty("terminateEditOnFocusLost", true);
        cleanTable = Table.getModel();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JDCREAR_CAMPO1 = new javax.swing.JDialog();
        btnCrear1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtcr_nombre1 = new javax.swing.JTextField();
        cbocr_tipo1 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        JDCREAR_CAMPO = new javax.swing.JDialog();
        btnCrear = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtcr_nombre = new javax.swing.JTextField();
        cbocr_tipo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbollave_s = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        JDMODIFICAR_CAMPOS = new javax.swing.JDialog();
        cbocampos = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        txtnuevo_Nombre = new javax.swing.JTextField();
        cbonuevo_tipo = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        Listado_de_Campos = new javax.swing.JDialog();
        Listar_Campos = new javax.swing.JScrollPane();
        Table1 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        btnSalir1 = new javax.swing.JButton();
        JDELIMINAR_CAMPOS = new javax.swing.JDialog();
        cboEliminar = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jsp_Tabla = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jmb_Principal = new javax.swing.JMenuBar();
        jm_Archivo = new javax.swing.JMenu();
        jmi_Nuevo_Archivo = new javax.swing.JMenuItem();
        jmi_Cargar_Archivo = new javax.swing.JMenuItem();
        jmi_Salvar_Archivo = new javax.swing.JMenuItem();
        jmi_Cerrar_Archivo = new javax.swing.JMenuItem();
        jmi_Salir = new javax.swing.JMenuItem();
        jmi_Campos = new javax.swing.JMenu();
        jmi_Crear_Campo = new javax.swing.JMenuItem();
        jmi_Modificar_Campo = new javax.swing.JMenuItem();
        jmi_Borrar_Campo = new javax.swing.JMenuItem();
        jmi_Listar_Campos = new javax.swing.JMenuItem();
        jm_Registros = new javax.swing.JMenu();
        jmi_Crear_Registro = new javax.swing.JMenuItem();
        jmi_Borrar_Registro = new javax.swing.JMenuItem();
        jmi_Buscar_Registro = new javax.swing.JMenuItem();
        jmi_modreg = new javax.swing.JMenuItem();
        jm_indices = new javax.swing.JMenu();
        jmi_crearindices = new javax.swing.JMenuItem();
        jmi_reindexar = new javax.swing.JMenuItem();
        jm_Estandarizacion = new javax.swing.JMenu();
        jmi_Exportar_Excel = new javax.swing.JMenuItem();
        jmi_Exportrar_XML = new javax.swing.JMenuItem();

        btnCrear1.setText("Crear");
        btnCrear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrear1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Nombre");

        jLabel8.setText("Tipo");

        txtcr_nombre1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cbocr_tipo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Int", "long", "String", "Char" }));

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel11.setText("CREAR CAMPOS");

        javax.swing.GroupLayout JDCREAR_CAMPO1Layout = new javax.swing.GroupLayout(JDCREAR_CAMPO1.getContentPane());
        JDCREAR_CAMPO1.getContentPane().setLayout(JDCREAR_CAMPO1Layout);
        JDCREAR_CAMPO1Layout.setHorizontalGroup(
            JDCREAR_CAMPO1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JDCREAR_CAMPO1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JDCREAR_CAMPO1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JDCREAR_CAMPO1Layout.createSequentialGroup()
                        .addGroup(JDCREAR_CAMPO1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(30, 30, 30)
                        .addGroup(JDCREAR_CAMPO1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbocr_tipo1, 0, 186, Short.MAX_VALUE)
                            .addComponent(txtcr_nombre1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JDCREAR_CAMPO1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(141, 141, 141))))
            .addGroup(JDCREAR_CAMPO1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(btnCrear1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 86, Short.MAX_VALUE))
        );
        JDCREAR_CAMPO1Layout.setVerticalGroup(
            JDCREAR_CAMPO1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JDCREAR_CAMPO1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(JDCREAR_CAMPO1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JDCREAR_CAMPO1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtcr_nombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addGap(6, 6, 6)
                .addGroup(JDCREAR_CAMPO1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbocr_tipo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(btnCrear1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        jLabel4.setText("Nombre");

        jLabel5.setText("Tipo");

        txtcr_nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cbocr_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Int", "long", "String", "Char" }));

        jLabel10.setText("Llave Secundaria");

        cbollave_s.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "SI" }));

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel12.setText("CREAR CAMPOS");

        javax.swing.GroupLayout JDCREAR_CAMPOLayout = new javax.swing.GroupLayout(JDCREAR_CAMPO.getContentPane());
        JDCREAR_CAMPO.getContentPane().setLayout(JDCREAR_CAMPOLayout);
        JDCREAR_CAMPOLayout.setHorizontalGroup(
            JDCREAR_CAMPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDCREAR_CAMPOLayout.createSequentialGroup()
                .addGroup(JDCREAR_CAMPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JDCREAR_CAMPOLayout.createSequentialGroup()
                        .addGroup(JDCREAR_CAMPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addGroup(JDCREAR_CAMPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(JDCREAR_CAMPOLayout.createSequentialGroup()
                                    .addGap(78, 78, 78)
                                    .addComponent(jLabel4))
                                .addGroup(JDCREAR_CAMPOLayout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addComponent(jLabel10))))
                        .addGap(39, 39, 39)
                        .addGroup(JDCREAR_CAMPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtcr_nombre)
                            .addComponent(cbollave_s, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbocr_tipo, 0, 173, Short.MAX_VALUE)))
                    .addGroup(JDCREAR_CAMPOLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel12))
                    .addGroup(JDCREAR_CAMPOLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        JDCREAR_CAMPOLayout.setVerticalGroup(
            JDCREAR_CAMPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JDCREAR_CAMPOLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(JDCREAR_CAMPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtcr_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(JDCREAR_CAMPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbollave_s, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(26, 26, 26)
                .addGroup(JDCREAR_CAMPOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbocr_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        cbocampos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbocamposItemStateChanged(evt);
            }
        });

        jLabel1.setText("Campo a Modificar:");

        jLabel2.setText("Nuevo Nombre del Campo:");

        jLabel3.setText("Tipo:");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnSalir.setText("Regresar");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        cbonuevo_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Int", "long", "String", "Char" }));

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel13.setText("MODIFICAR CAMPOS");

        javax.swing.GroupLayout JDMODIFICAR_CAMPOSLayout = new javax.swing.GroupLayout(JDMODIFICAR_CAMPOS.getContentPane());
        JDMODIFICAR_CAMPOS.getContentPane().setLayout(JDMODIFICAR_CAMPOSLayout);
        JDMODIFICAR_CAMPOSLayout.setHorizontalGroup(
            JDMODIFICAR_CAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JDMODIFICAR_CAMPOSLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JDMODIFICAR_CAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JDMODIFICAR_CAMPOSLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel1))
                    .addGroup(JDMODIFICAR_CAMPOSLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel13))
                    .addGroup(JDMODIFICAR_CAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtnuevo_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbocampos, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbonuevo_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JDMODIFICAR_CAMPOSLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel2))
                    .addGroup(JDMODIFICAR_CAMPOSLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel3)))
                .addGap(103, 103, 103))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JDMODIFICAR_CAMPOSLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 67, Short.MAX_VALUE))
        );
        JDMODIFICAR_CAMPOSLayout.setVerticalGroup(
            JDMODIFICAR_CAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDMODIFICAR_CAMPOSLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel13)
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbocampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnuevo_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel3)
                .addGroup(JDMODIFICAR_CAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JDMODIFICAR_CAMPOSLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbonuevo_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JDMODIFICAR_CAMPOSLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                        .addGroup(JDMODIFICAR_CAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))))
        );

        Table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table1MouseClicked(evt);
            }
        });
        Table1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                Table1PropertyChange(evt);
            }
        });
        Listar_Campos.setViewportView(Table1);

        jLabel14.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel14.setText("LISTAR CAMPOS");

        btnSalir1.setText("Regresar");
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Listado_de_CamposLayout = new javax.swing.GroupLayout(Listado_de_Campos.getContentPane());
        Listado_de_Campos.getContentPane().setLayout(Listado_de_CamposLayout);
        Listado_de_CamposLayout.setHorizontalGroup(
            Listado_de_CamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Listado_de_CamposLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(Listar_Campos, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(Listado_de_CamposLayout.createSequentialGroup()
                .addGroup(Listado_de_CamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Listado_de_CamposLayout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jLabel14))
                    .addGroup(Listado_de_CamposLayout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(btnSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Listado_de_CamposLayout.setVerticalGroup(
            Listado_de_CamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Listado_de_CamposLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(Listar_Campos, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        cboEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEliminarActionPerformed(evt);
            }
        });

        jLabel6.setText("Campo a Eliminar:");

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel15.setText("ELIMINAR CAMPOS");

        javax.swing.GroupLayout JDELIMINAR_CAMPOSLayout = new javax.swing.GroupLayout(JDELIMINAR_CAMPOS.getContentPane());
        JDELIMINAR_CAMPOS.getContentPane().setLayout(JDELIMINAR_CAMPOSLayout);
        JDELIMINAR_CAMPOSLayout.setHorizontalGroup(
            JDELIMINAR_CAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDELIMINAR_CAMPOSLayout.createSequentialGroup()
                .addGroup(JDELIMINAR_CAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JDELIMINAR_CAMPOSLayout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jLabel6))
                    .addGroup(JDELIMINAR_CAMPOSLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel15))
                    .addGroup(JDELIMINAR_CAMPOSLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(cboEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JDELIMINAR_CAMPOSLayout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        JDELIMINAR_CAMPOSLayout.setVerticalGroup(
            JDELIMINAR_CAMPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDELIMINAR_CAMPOSLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(29, 29, 29)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(cboEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        Table.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TablePropertyChange(evt);
            }
        });
        jsp_Tabla.setViewportView(Table);

        jm_Archivo.setText("Archivo");

        jmi_Nuevo_Archivo.setText("Nuevo Archivo");
        jmi_Nuevo_Archivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_Nuevo_ArchivoActionPerformed(evt);
            }
        });
        jm_Archivo.add(jmi_Nuevo_Archivo);

        jmi_Cargar_Archivo.setText("Cargar Archivo");
        jmi_Cargar_Archivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_Cargar_ArchivoActionPerformed(evt);
            }
        });
        jm_Archivo.add(jmi_Cargar_Archivo);

        jmi_Salvar_Archivo.setText("Salvar Archivo");
        jmi_Salvar_Archivo.setEnabled(false);
        jmi_Salvar_Archivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_Salvar_ArchivoActionPerformed(evt);
            }
        });
        jm_Archivo.add(jmi_Salvar_Archivo);

        jmi_Cerrar_Archivo.setText("Cerrar Archivo");
        jmi_Cerrar_Archivo.setEnabled(false);
        jmi_Cerrar_Archivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_Cerrar_ArchivoActionPerformed(evt);
            }
        });
        jm_Archivo.add(jmi_Cerrar_Archivo);

        jmi_Salir.setText("Salir");
        jmi_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_SalirActionPerformed(evt);
            }
        });
        jm_Archivo.add(jmi_Salir);

        jmb_Principal.add(jm_Archivo);

        jmi_Campos.setText("Campos");
        jmi_Campos.setEnabled(false);

        jmi_Crear_Campo.setText("Crear Campo");
        jmi_Crear_Campo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_Crear_CampoActionPerformed(evt);
            }
        });
        jmi_Campos.add(jmi_Crear_Campo);

        jmi_Modificar_Campo.setText("Modificar Campo");
        jmi_Modificar_Campo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_Modificar_CampoActionPerformed(evt);
            }
        });
        jmi_Campos.add(jmi_Modificar_Campo);

        jmi_Borrar_Campo.setText("Borrar Campo");
        jmi_Borrar_Campo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_Borrar_CampoActionPerformed(evt);
            }
        });
        jmi_Campos.add(jmi_Borrar_Campo);

        jmi_Listar_Campos.setText("Listar Campos");
        jmi_Listar_Campos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_Listar_CamposActionPerformed(evt);
            }
        });
        jmi_Campos.add(jmi_Listar_Campos);

        jmb_Principal.add(jmi_Campos);

        jm_Registros.setText("Registros");
        jm_Registros.setEnabled(false);

        jmi_Crear_Registro.setText("Crear Registro");
        jmi_Crear_Registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_Crear_RegistroActionPerformed(evt);
            }
        });
        jm_Registros.add(jmi_Crear_Registro);

        jmi_Borrar_Registro.setText("Borrar Registro");
        jmi_Borrar_Registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_Borrar_RegistroActionPerformed(evt);
            }
        });
        jm_Registros.add(jmi_Borrar_Registro);

        jmi_Buscar_Registro.setText("Buscar Registro");
        jmi_Buscar_Registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_Buscar_RegistroActionPerformed(evt);
            }
        });
        jm_Registros.add(jmi_Buscar_Registro);

        jmi_modreg.setText("Modificar Registro");
        jmi_modreg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_modregActionPerformed(evt);
            }
        });
        jm_Registros.add(jmi_modreg);

        jmb_Principal.add(jm_Registros);

        jm_indices.setText("Indice");
        jm_indices.setEnabled(false);

        jmi_crearindices.setText("Crear Indices");
        jmi_crearindices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_crearindicesActionPerformed(evt);
            }
        });
        jm_indices.add(jmi_crearindices);

        jmi_reindexar.setText("Re Indexar Archivos");
        jmi_reindexar.setEnabled(false);
        jmi_reindexar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_reindexarActionPerformed(evt);
            }
        });
        jm_indices.add(jmi_reindexar);

        jmb_Principal.add(jm_indices);

        jm_Estandarizacion.setText("Estandarizacion");
        jm_Estandarizacion.setEnabled(false);

        jmi_Exportar_Excel.setText("Exportar EXCEL");
        jmi_Exportar_Excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_Exportar_ExcelActionPerformed(evt);
            }
        });
        jm_Estandarizacion.add(jmi_Exportar_Excel);

        jmi_Exportrar_XML.setText("Exportar XML");
        jmi_Exportrar_XML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_Exportrar_XMLActionPerformed(evt);
            }
        });
        jm_Estandarizacion.add(jmi_Exportrar_XML);

        jmb_Principal.add(jm_Estandarizacion);

        setJMenuBar(jmb_Principal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jsp_Tabla, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jsp_Tabla, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmi_Nuevo_ArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_Nuevo_ArchivoActionPerformed
        // TODO add your handling code here:
        Nuevo_Archivo();
        jmi_Campos.setEnabled(true);
        jmi_Modificar_Campo.setEnabled(true);
        jmi_Crear_Campo.setEnabled(true);
        jmi_Borrar_Campo.setEnabled(true);
        jm_Registros.setEnabled(true);
        jm_indices.setEnabled(true);
        jm_Estandarizacion.setEnabled(true);
        jmi_Salvar_Archivo.setEnabled(true);
        jmi_Cerrar_Archivo.setEnabled(true);
    }//GEN-LAST:event_jmi_Nuevo_ArchivoActionPerformed

    private void jmi_Salvar_ArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_Salvar_ArchivoActionPerformed
        // TODO add your handling code here:
        Salvar_Archivo();
    }//GEN-LAST:event_jmi_Salvar_ArchivoActionPerformed

    private void jmi_Cerrar_ArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_Cerrar_ArchivoActionPerformed
        // TODO add your handling code here:
        try {
            RAfile.close();
            Table.setModel(cleanTable);
            listcampos.clear();
            cbocampos.removeAllItems();
            cboEliminar.removeAllItems();
            metadata.getCampos().clear();
            metadata.getTipos().clear();
            contador = 0;
            cantidad = 0;
            jmi_Campos.setEnabled(false);
            jm_Registros.setEnabled(false);
            jm_indices.setEnabled(false);
            jm_Estandarizacion.setEnabled(false);
            jmi_Salvar_Archivo.setEnabled(false);
            jmi_Cerrar_Archivo.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Cerrado Exitosamente", "Cerrado", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jmi_Cerrar_ArchivoActionPerformed

    private void jmi_Cargar_ArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_Cargar_ArchivoActionPerformed
        // TODO add your handling code here:
        Cargar_Archivo();
        if (FileSuccess == 1) {
            metadata = new Metadata();
            BuildTable(metadata, 1);
            try {
                CargarMetadatos();
                BuildTable(metadata, 0);
                LeerDatosRegistro();
                for (int i = 0; i < metadata.getCampos().size(); i++) {
                    Campo c = new Campo(metadata.getCampos().get(i).toString(), metadata.getTipos().get(i).toString());
                    listcampos.add(c);
                }
                jmi_Campos.setEnabled(true);
                jmi_Modificar_Campo.setEnabled(true);
                jmi_Crear_Campo.setEnabled(true);
                jmi_Listar_Campos.setEnabled(true);
                jmi_Borrar_Campo.setEnabled(true);
                jm_Registros.setEnabled(true);
                jm_indices.setEnabled(true);
                jm_Estandarizacion.setEnabled(true);
                jmi_Salvar_Archivo.setEnabled(true);
                jmi_Cerrar_Archivo.setEnabled(true);
                contador = listcampos.size();
            } catch (ClassNotFoundException ex) {
            }
        }
    }//GEN-LAST:event_jmi_Cargar_ArchivoActionPerformed

    private void jmi_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_SalirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jmi_SalirActionPerformed

    private void jmi_Crear_CampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_Crear_CampoActionPerformed
        // TODO add your handling code here:
        if (validar == true) {
            JDCREAR_CAMPO.setModal(true);
            JDCREAR_CAMPO.pack();
            JDCREAR_CAMPO.setLocationRelativeTo(null);
            JDCREAR_CAMPO.setVisible(true);
        } else {
            JDCREAR_CAMPO1.setModal(true);
            JDCREAR_CAMPO1.pack();
            JDCREAR_CAMPO1.setLocationRelativeTo(null);
            JDCREAR_CAMPO1.setVisible(true);
        }
    }//GEN-LAST:event_jmi_Crear_CampoActionPerformed

    private void jmi_Modificar_CampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_Modificar_CampoActionPerformed
        // TODO add your handling code here:
        listcampos.stream().map((modelo1) -> new DefaultComboBoxModel(listcampos.toArray())).forEachOrdered((modelo1) -> {
            cbocampos.setModel(modelo1);
        });
        JDMODIFICAR_CAMPOS.setModal(true);
        JDMODIFICAR_CAMPOS.pack();
        JDMODIFICAR_CAMPOS.setLocationRelativeTo(null);
        JDMODIFICAR_CAMPOS.setVisible(true);

    }//GEN-LAST:event_jmi_Modificar_CampoActionPerformed

    private void jmi_Borrar_CampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_Borrar_CampoActionPerformed
        // TODO add your handling code here:
        listcampos.stream().map((modelo1) -> new DefaultComboBoxModel(listcampos.toArray())).forEachOrdered((modelo1) -> {
            cboEliminar.setModel(modelo1);
        });
        JDELIMINAR_CAMPOS.setModal(true);
        JDELIMINAR_CAMPOS.pack();
        JDELIMINAR_CAMPOS.setLocationRelativeTo(null);
        JDELIMINAR_CAMPOS.setVisible(true);

    }//GEN-LAST:event_jmi_Borrar_CampoActionPerformed

    private void jmi_Listar_CamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_Listar_CamposActionPerformed
        JOptionPane.showMessageDialog(this, "El primer campo es el campo llave");
        Listado_de_Campos.setVisible(true);
        Listado_de_Campos.setLocationRelativeTo(this);
        Listado_de_Campos.setSize(500, 500);
        Listado_de_Campos.setTitle("Listado de Campos");
        Table1.setForeground(Color.BLACK);
        Table1.setBackground(Color.WHITE);
        Table1.setFont(new Font("", 1, 22));
        Table1.setRowHeight(30);
        Table1.putClientProperty("terminateEditOnFocusLost", true);
        String[] cols = {"", ""};
        DefaultTableModel tabla = new DefaultTableModel();
        tabla.addColumn("Campo");
        tabla.addColumn("Tipo");
        String tipo;
        metadata.getCampos().forEach((_item) -> {
            tabla.addRow(cols);
        });

        Table1.setModel(tabla);
        int primero = 0;
        int segundo = 0;

        for (int i = 0; i < metadata.getCampos().size(); i++) {
            switch (metadata.getTipos().get(i).toString()) {
                case "Int":
                    tabla.setValueAt(metadata.getCampos().get(i).toString(), primero, segundo);
                    tabla.setValueAt("Entero", primero, segundo + 1);
                    Table1.setModel(tabla);
                    break;
                case "long":
                    tabla.setValueAt(metadata.getCampos().get(i), primero, segundo);
                    tabla.setValueAt("Long", primero, segundo + 1);
                    Table1.setModel(tabla);
                    break;
                case "String":
                    tabla.setValueAt(metadata.getCampos().get(i), primero, segundo);
                    tabla.setValueAt("String", primero, segundo + 1);
                    Table1.setModel(tabla);
                    break;
                case "Char":
                    tabla.setValueAt(metadata.getCampos().get(i), primero, segundo);
                    tabla.setValueAt("Char", primero, segundo + 1);
                    Table1.setModel(tabla);
                    break;
                default:
                    break;
            }
            primero++;
        }

    }//GEN-LAST:event_jmi_Listar_CamposActionPerformed

    private void jmi_Crear_RegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_Crear_RegistroActionPerformed
        // TODO add your handling code here:
        if (metadata != null) {
            if (metadata.getCampos() != null) {
                if (metadata.getCampos().size() > 0) {
                    if (file == null) {
                        while (FileSuccess == 0) {
                            Crear_Archivo();

                        }

                        try {
                            Escribir_Metadatos();
                        } catch (IOException ex) {
                        }
                        Crear_Registro();
                    } else if (metadata.getNumregistros() < 1) {
                        try {
                            file.delete();
                            file.createNewFile();
                        } catch (IOException sdj) {
                        }

                        try {
                            Escribir_Metadatos();
                        } catch (IOException ex) {
                            //ex.printStackTrace();
                        }
                        metadata.addnumregistros();
                        Crear_Registro();
                    } else {
                        metadata.addnumregistros();
                        Crear_Registro();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No hay campos creados! XTT 428");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hay campos creados! XTT 431");
            }

        } else {
            JOptionPane.showMessageDialog(null, "No hay campos creados! XTT 435");
        }

    }//GEN-LAST:event_jmi_Crear_RegistroActionPerformed

    private void jmi_Borrar_RegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_Borrar_RegistroActionPerformed
        // TODO add your handling code here:
        if (mode == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione el Registro a borrar.");
        } else {
            try {
                ArrayList export = new ArrayList();
                for (int i = 0; i < metadata.getCampos().size(); i++) {
                    export.add(Table.getValueAt(rowRemoval, i));
                }
                mode = -1;

                Eliminar_Dato_Archivo(export);
                metadata.subtractnumregistros();

                TableModel modelo = Table.getModel();
                DefaultTableModel model = (DefaultTableModel) modelo;
                model.removeRow(rowRemoval);
                Table.setModel(modelo);
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jmi_Borrar_RegistroActionPerformed

    private void jmi_Buscar_RegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_Buscar_RegistroActionPerformed
        // TODO add your handling code here:

        try {
            int Primarykey = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el dato de tipo llave primaria del registro a buscar."));
            Registro temporal = new Registro(Primarykey);
            NodoB x;
            if ((x = metadata.getArbolB().search(temporal)) == null) {
                JOptionPane.showMessageDialog(null, "No se encontro");
            } else {

                Data datos = Buscar_Dato_Archivo(temporal);
                String info = "Registro: ";
                for (int i = 0; i < datos.datos.size(); i++) {
                    info += datos.datos.get(i) + " - ";
                }
                JOptionPane.showMessageDialog(this, info);

            }
        } catch (HeadlessException | IOException | ClassNotFoundException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Operation aborted.");
        }
    }//GEN-LAST:event_jmi_Buscar_RegistroActionPerformed

    private void jmi_modregActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_modregActionPerformed
        // TODO add your handling code here:
        ArrayList export = new ArrayList();
        //pedir cual modificar
        //agregar a un temporal
        //eliminarlo
        if (mode == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un Registro para modificar.");
        } else {
            try {

                for (int i = 0; i < metadata.getCampos().size(); i++) {
                    export.add(Table.getValueAt(rowRemoval, i));
                }
                mode = -1;
                Registro temporal = new Registro(Integer.parseInt(export.get(0).toString()));
                Crear_Registro();
                Eliminar_Dato_Archivo(export);
                metadata.subtractnumregistros();

                TableModel modelo = Table.getModel();
                DefaultTableModel model = (DefaultTableModel) modelo;
                model.removeRow(rowRemoval);
                Table.setModel(modelo);
            } catch (NumberFormatException e) {
            }
        }
        //crear uno nuevo con los datos de temporal
        //guardar
    }//GEN-LAST:event_jmi_modregActionPerformed

    private void jmi_crearindicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_crearindicesActionPerformed
        // TODO add your handling code here:
        System.out.println("=========================================");
        System.out.println("Imprimiendo en Orden...");
        metadata.ArbolB.traverse();
        System.out.println("Imprimiendo en Forma de Decendencia a 3 Nieveles...");
        metadata.ArbolB.PrintLevels();
        System.out.println("=========================================");
        System.out.println(metadata.getLlave_secundaria());
    }//GEN-LAST:event_jmi_crearindicesActionPerformed

    private void jmi_Exportar_ExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_Exportar_ExcelActionPerformed


    }//GEN-LAST:event_jmi_Exportar_ExcelActionPerformed

    private void jmi_Exportrar_XMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_Exportrar_XMLActionPerformed

    }//GEN-LAST:event_jmi_Exportrar_XMLActionPerformed

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
        // TODO add your handling code here:
        rowRemoval = Table.getSelectedRow();
        mode = 0;
    }//GEN-LAST:event_TableMouseClicked

    private void TablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TablePropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_TablePropertyChange

    private void Table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Table1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Table1MouseClicked

    private void Table1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_Table1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_Table1PropertyChange

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        if (txtcr_nombre.getText().isEmpty() || cbocr_tipo.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(JDCREAR_CAMPO, "Uno o más campos están vacios");
        } else {
            try {
                String nombre, tipo, llave_secundaria;
                nombre = txtcr_nombre.getText();
                tipo = cbocr_tipo.getSelectedItem().toString();
                llave_secundaria = cbollave_s.getSelectedItem().toString();
                if (llave_secundaria.equals("SI")) {
                    cantidad++;
                    if (cantidad == 2) {
                        cbollave_s.setEnabled(false);
                    }
                }
                Campo c = new Campo(nombre, tipo);
                listcampos.add(c);
                metodos.CreateCampos(metadata, nombre, tipo, contador, llave_secundaria);
                contador++;
            } catch (IOException | ParseException ex) {
            }
        }
        txtcr_nombre.setText("");
        cbocr_tipo.setSelectedIndex(0);
        cbollave_s.setSelectedIndex(0);
        BuildTable(metadata, 0);
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int posicion;
        if (metadata.getNumregistros() == 0 && metadata.getCampos() != null) {
            try {
                posicion = cboEliminar.getSelectedIndex();
                if (metadata.getCampos().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Operacion Invalida");
                } else {
                    metodos.DeleteCampos(metadata, posicion);
                    BuildTable(metadata, 0);
                    listcampos.remove(posicion);
                }
            } catch (HeadlessException e) {
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operacion Invalida");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cbocamposItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbocamposItemStateChanged
        // TODO add your handling code here:
        if (cbocampos.getSelectedIndex() >= 0) {
            Campo s = (Campo) cbocampos.getSelectedItem();
            txtnuevo_Nombre.setText(s.getNom());
            String Tipo = s.getTipo();
            switch (Tipo) {
                case "Int":
                    cbonuevo_tipo.setSelectedIndex(1);
                    break;
                case "long":
                    cbonuevo_tipo.setSelectedIndex(2);
                    break;
                case "String":
                    cbonuevo_tipo.setSelectedIndex(3);
                    break;
                case "Char":
                    cbonuevo_tipo.setSelectedIndex(4);
                    break;
                default:
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un campo");
        }
    }//GEN-LAST:event_cbocamposItemStateChanged

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        String nuevo_nombre, nuevo_tipo;
        int posicion;
        if (txtnuevo_Nombre.getText().isEmpty() || cbonuevo_tipo.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(JDMODIFICAR_CAMPOS, "Por favor, LLene los campos vacios");
        } else {
            nuevo_nombre = txtnuevo_Nombre.getText();
            nuevo_tipo = cbonuevo_tipo.getSelectedItem().toString();
            posicion = cbocampos.getSelectedIndex();
            if (metadata.getNumregistros() == 0 && metadata.getCampos() != null) {
                try {
                    if (metadata.getCampos().isEmpty()) {

                    } else {
                        metodos.ModificarCampos(metadata, nuevo_nombre, nuevo_tipo, posicion);
                        BuildTable(metadata, 0);
                        listcampos.get(posicion).setNom(nuevo_nombre);
                        listcampos.get(posicion).setTipo(nuevo_tipo);
                    }

                } catch (Exception e) {

                }

            } else {
                JOptionPane.showMessageDialog(null, "Invalid Operation");
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCrear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrear1ActionPerformed
        // TODO add your handling code here:
        if (txtcr_nombre1.getText().isEmpty() || cbocr_tipo1.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(JDCREAR_CAMPO1, "Por favor, LLene los campos vacios");
        } else {
            try {
                String nombre, tipo;
                nombre = txtcr_nombre1.getText();
                tipo = cbocr_tipo1.getSelectedItem().toString();
                Campo c = new Campo(nombre, tipo);
                listcampos.add(c);
                metodos.CreateCampos(metadata, nombre, tipo, contador, "");
                contador++;
                BuildTable(metadata, 0);
                validar = true;
                JOptionPane.showConfirmDialog(this, "El campo se creó con éxito");
                JDCREAR_CAMPO1.setModal(false);
                JDCREAR_CAMPO1.pack();
                JDCREAR_CAMPO1.setLocationRelativeTo(null);
                JDCREAR_CAMPO1.setVisible(false);
                JDCREAR_CAMPO.setModal(true);
                JDCREAR_CAMPO.pack();
                JDCREAR_CAMPO.setLocationRelativeTo(null);
                JDCREAR_CAMPO.setVisible(true);
            } catch (IOException | ParseException ex) {
            }
        }
        txtcr_nombre.setText("");
        cbocr_tipo.setSelectedIndex(0);

    }//GEN-LAST:event_btnCrear1ActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        JDMODIFICAR_CAMPOS.setModal(false);
        JDMODIFICAR_CAMPOS.pack();
        JDMODIFICAR_CAMPOS.setLocationRelativeTo(null);
        JDMODIFICAR_CAMPOS.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void jmi_reindexarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_reindexarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmi_reindexarActionPerformed

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed
        Listado_de_Campos.setModal(false);
        Listado_de_Campos.pack();
        Listado_de_Campos.setLocationRelativeTo(null);
        Listado_de_Campos.setVisible(false);
    }//GEN-LAST:event_btnSalir1ActionPerformed

    private void cboEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboEliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog JDCREAR_CAMPO;
    private javax.swing.JDialog JDCREAR_CAMPO1;
    private javax.swing.JDialog JDELIMINAR_CAMPOS;
    private javax.swing.JDialog JDMODIFICAR_CAMPOS;
    private javax.swing.JDialog Listado_de_Campos;
    private javax.swing.JScrollPane Listar_Campos;
    private javax.swing.JTable Table;
    private javax.swing.JTable Table1;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnCrear1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSalir1;
    private javax.swing.JComboBox<String> cboEliminar;
    private javax.swing.JComboBox<String> cbocampos;
    private javax.swing.JComboBox<String> cbocr_tipo;
    private javax.swing.JComboBox<String> cbocr_tipo1;
    private javax.swing.JComboBox<String> cbollave_s;
    private javax.swing.JComboBox<String> cbonuevo_tipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jm_Archivo;
    private javax.swing.JMenu jm_Estandarizacion;
    private javax.swing.JMenu jm_Registros;
    private javax.swing.JMenu jm_indices;
    private javax.swing.JMenuBar jmb_Principal;
    private javax.swing.JMenuItem jmi_Borrar_Campo;
    private javax.swing.JMenuItem jmi_Borrar_Registro;
    private javax.swing.JMenuItem jmi_Buscar_Registro;
    private javax.swing.JMenu jmi_Campos;
    private javax.swing.JMenuItem jmi_Cargar_Archivo;
    private javax.swing.JMenuItem jmi_Cerrar_Archivo;
    private javax.swing.JMenuItem jmi_Crear_Campo;
    private javax.swing.JMenuItem jmi_Crear_Registro;
    private javax.swing.JMenuItem jmi_Exportar_Excel;
    private javax.swing.JMenuItem jmi_Exportrar_XML;
    private javax.swing.JMenuItem jmi_Listar_Campos;
    private javax.swing.JMenuItem jmi_Modificar_Campo;
    private javax.swing.JMenuItem jmi_Nuevo_Archivo;
    private javax.swing.JMenuItem jmi_Salir;
    private javax.swing.JMenuItem jmi_Salvar_Archivo;
    private javax.swing.JMenuItem jmi_crearindices;
    private javax.swing.JMenuItem jmi_modreg;
    private javax.swing.JMenuItem jmi_reindexar;
    private javax.swing.JScrollPane jsp_Tabla;
    private javax.swing.JTextField txtcr_nombre;
    private javax.swing.JTextField txtcr_nombre1;
    private javax.swing.JTextField txtnuevo_Nombre;
    // End of variables declaration//GEN-END:variables
    Metadata metadata;
    Metadata metadata2;
    int FileSuccess;
    Metodos metodos = new Metodos();
    File file;
    File file2;
    int FileSuccess2;
    RandomAccessFile RAfile;
    RandomAccessFile RAfile2;
    int mode = -1;
    int rowRemoval;
    TableModel cleanTable;
    ListaEnlazada AvailList = new ListaEnlazada();
    ArrayList<Object> Export2;
    int tablemodification;
    Object oldcellvalue;
    int currentRow;
    int currentColumn;
}
