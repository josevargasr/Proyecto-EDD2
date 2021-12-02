/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author josevargas
 */
public class FramePrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FramePrincipal
     */
    public FramePrincipal() {
        initComponents();
        openfile = false;
        ArchivoAbierto();
        this.setLocationRelativeTo(null);
        ButtonGroup bg = new ButtonGroup();
        bg.add(jRadioButtonSi);
        bg.add(jRadioButtonNo);
        jRadioButtonNo.setSelected(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDCrearCampo = new javax.swing.JDialog();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jRadioButtonSi = new javax.swing.JRadioButton();
        jRadioButtonNo = new javax.swing.JRadioButton();
        jButtonGuardarCampo = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jDModificarCampo = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        PopMenuModificar = new javax.swing.JPopupMenu();
        jMenuModificarCampo = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        jMenuItemNuevoArchivo = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuCampos = new javax.swing.JMenu();
        jMenuItemNuevoCampo = new javax.swing.JMenuItem();
        jMenuItemListarCampos = new javax.swing.JMenuItem();
        jMenuItemModificarCampos = new javax.swing.JMenuItem();
        jMenuItemBorrarCampos = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();

        jLabel1.setText("Nombre del Campo:");

        jLabel2.setText("Campo Llave:");

        jRadioButtonSi.setText("Si");

        jRadioButtonNo.setSelected(true);
        jRadioButtonNo.setText("No");

        jButtonGuardarCampo.setText("Guardar Campo");
        jButtonGuardarCampo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonGuardarCampoMouseClicked(evt);
            }
        });

        jLabel3.setText("Tipo de Campo:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "String", "Int", "Char", "Long" }));

        jButton1.setText("Regresar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel5.setText("Longitud del Campo:");

        javax.swing.GroupLayout jDCrearCampoLayout = new javax.swing.GroupLayout(jDCrearCampo.getContentPane());
        jDCrearCampo.getContentPane().setLayout(jDCrearCampoLayout);
        jDCrearCampoLayout.setHorizontalGroup(
            jDCrearCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDCrearCampoLayout.createSequentialGroup()
                .addGroup(jDCrearCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDCrearCampoLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addComponent(jButtonGuardarCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDCrearCampoLayout.createSequentialGroup()
                        .addGroup(jDCrearCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDCrearCampoLayout.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addComponent(jLabel2))
                            .addGroup(jDCrearCampoLayout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(jRadioButtonSi)
                                .addGap(44, 44, 44)
                                .addComponent(jRadioButtonNo))
                            .addGroup(jDCrearCampoLayout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addComponent(jLabel3))
                            .addGroup(jDCrearCampoLayout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addGroup(jDCrearCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jDCrearCampoLayout.createSequentialGroup()
                                .addGap(119, 119, 119)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(28, 28, 28))
            .addGroup(jDCrearCampoLayout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addGroup(jDCrearCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jDCrearCampoLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDCrearCampoLayout.setVerticalGroup(
            jDCrearCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDCrearCampoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jDCrearCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonSi)
                    .addComponent(jRadioButtonNo))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jDCrearCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardarCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);

        javax.swing.GroupLayout jDModificarCampoLayout = new javax.swing.GroupLayout(jDModificarCampo.getContentPane());
        jDModificarCampo.getContentPane().setLayout(jDModificarCampoLayout);
        jDModificarCampoLayout.setHorizontalGroup(
            jDModificarCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDModificarCampoLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jDModificarCampoLayout.setVerticalGroup(
            jDModificarCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDModificarCampoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jMenuModificarCampo.setText("jMenuItem16");
        jMenuModificarCampo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuModificarCampoActionPerformed(evt);
            }
        });
        PopMenuModificar.add(jMenuModificarCampo);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel4.setText("File Manager");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        jMenuArchivo.setText("Archivo");

        jMenuItemNuevoArchivo.setText("Nuevo Archivo");
        jMenuItemNuevoArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNuevoArchivoActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemNuevoArchivo);

        jMenuItem1.setText("Abrir Archivo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItem1);

        jMenuItem2.setText("Salvar Archivo");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItem2);

        jMenuItem3.setText("Cerrar Archivo");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItem3);

        jMenuItem4.setText("Salir");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItem4);

        jMenuBar1.add(jMenuArchivo);

        jMenuCampos.setText("Campos");

        jMenuItemNuevoCampo.setText("Agregar Campos");
        jMenuItemNuevoCampo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItemNuevoCampoMouseClicked(evt);
            }
        });
        jMenuItemNuevoCampo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNuevoCampoActionPerformed(evt);
            }
        });
        jMenuCampos.add(jMenuItemNuevoCampo);

        jMenuItemListarCampos.setText("Listar Campos");
        jMenuItemListarCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemListarCamposActionPerformed(evt);
            }
        });
        jMenuCampos.add(jMenuItemListarCampos);

        jMenuItemModificarCampos.setText("Modificar Campos");
        jMenuItemModificarCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemModificarCamposActionPerformed(evt);
            }
        });
        jMenuItemModificarCampos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jMenuItemModificarCamposKeyPressed(evt);
            }
        });
        jMenuCampos.add(jMenuItemModificarCampos);

        jMenuItemBorrarCampos.setText("Borrar Campos");
        jMenuItemBorrarCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBorrarCamposActionPerformed(evt);
            }
        });
        jMenuCampos.add(jMenuItemBorrarCampos);

        jMenuBar1.add(jMenuCampos);

        jMenu1.setText("Registros");

        jMenuItem7.setText("Introducir Registros");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem8.setText("Modificar Registros");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem9.setText("Buscar Registros");
        jMenu1.add(jMenuItem9);

        jMenuItem10.setText("Borrar Registros");
        jMenu1.add(jMenuItem10);

        jMenuItem11.setText("Listar Registros");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Indices");

        jMenuItem12.setText("Crear Indices");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);

        jMenuItem13.setText("Re Indexar Archivos");
        jMenu2.add(jMenuItem13);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Estandarización");

        jMenuItem14.setText("Exportar Excel");
        jMenu3.add(jMenuItem14);

        jMenuItem15.setText("Exportar XML con Schema");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem15);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemNuevoCampoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItemNuevoCampoMouseClicked
        jDCrearCampo.setModal(true);
        jDCrearCampo.pack();
        jDCrearCampo.setLocationRelativeTo(null);
        jDCrearCampo.setVisible(true);
    }//GEN-LAST:event_jMenuItemNuevoCampoMouseClicked

    private void jButtonGuardarCampoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonGuardarCampoMouseClicked
        if (jTextField1.toString() != "") {
            String nombre = jTextField1.getText();
            String tipo = jComboBox1.getSelectedItem().toString();
            int longitud = (Integer) jSpinner1.getValue();
            boolean campo_llave = false;
            if (jRadioButtonSi.isSelected()) {
                campo_llave = true;
            }
            Campo campo = new Campo(nombre, tipo, longitud, campo_llave);
            archivo.addCampo(campo);
            JOptionPane.showMessageDialog(null, "El campo fue creado exitósamente");
            jTextField1.setText("");
            jDCrearCampo.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre para el campo");
        }


    }//GEN-LAST:event_jButtonGuardarCampoMouseClicked

    private void jMenuItemNuevoCampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNuevoCampoActionPerformed
        jDCrearCampo.setModal(true);
        jDCrearCampo.pack();
        jDCrearCampo.setLocationRelativeTo(null);
        jDCrearCampo.setVisible(true);
    }//GEN-LAST:event_jMenuItemNuevoCampoActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        jDCrearCampo.setVisible(false);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jMenuItemNuevoArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNuevoArchivoActionPerformed
        archivo = new Archivo();
        JOptionPane.showMessageDialog(null, "El archivo fue creado exitósamente");
        openfile = true;
        ArchivoAbierto();

    }//GEN-LAST:event_jMenuItemNuevoArchivoActionPerformed

    private void jMenuItemListarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemListarCamposActionPerformed
        // TODO add your handling code here:

        DefaultTableModel tabla = new DefaultTableModel();
        tabla.addColumn("Campo");
        tabla.addColumn("Tipo");
        tabla.addColumn("Longitud");
        Object[] fila = new Object[3];

        for (int i = 0; i < archivo.getListaCampos().size(); i++) {

            fila[0] = archivo.getListaCampos().get(i).nombre;
            fila[1] = archivo.getListaCampos().get(i).tipo;
            fila[2] = archivo.getListaCampos().get(i).longitud;
            tabla.addRow(fila);
        }

        jTable1.setModel(tabla);

    }//GEN-LAST:event_jMenuItemListarCamposActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("./"));
        FileNameExtensionFilter data = new FileNameExtensionFilter("ABC FILE", "abc");
        fileChooser.setFileFilter(data);
        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            try {
                if (fileChooser.getFileFilter().getDescription().equals("ABC FILE")) {
                    archivo = new Archivo(fileChooser.getSelectedFile().getPath());
                    archivo.cargarArchivo();
                    JOptionPane.showMessageDialog(null, "El archivo se abrio exitósamente");
                    openfile = true;
                    ArchivoAbierto();
                } else {
                    JOptionPane.showMessageDialog(this, "Solo puede crear archivos ABC");
                }
            } catch (Exception e) {

            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "¿Le gustaría guardar los cambios realizados al archivo?", "Alerta", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            archivo.escribirArchivo();
            JOptionPane.showMessageDialog(this, "El archivo se guardó con éxito");
        } else {
            JOptionPane.showMessageDialog(this, "El archivo se cerró con éxito");
            openfile = false;
            ArchivoAbierto();
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        String registro;
        for (int i = 0; i < archivo.getListaCampos().size(); i++) {
            registro = JOptionPane.showInputDialog("Ingrese el " + archivo.getListaCampos().get(i) + ":");
            archivo.addRegistro(registro);
        }
        JOptionPane.showMessageDialog(this, "El registro ha sido agregado con éxito");
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("./"));
        FileNameExtensionFilter data = new FileNameExtensionFilter("ABC FILE", "abc");
        fileChooser.setFileFilter(data);
        int seleccion = fileChooser.showSaveDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            try {
                if (fileChooser.getFileFilter().getDescription().equals("ABC FILE")) {
                    archivo.setPath(fileChooser.getSelectedFile().getPath());
                    archivo.escribirArchivo();
                    JOptionPane.showMessageDialog(null, "El archivo fue guardado exitósamente");
                } else {
                    JOptionPane.showMessageDialog(this, "Solo puede guardar archivos ABC");
                }
            } catch (Exception e) {

            }
        }

        JOptionPane.showMessageDialog(this, "El archivo se guardó con éxito");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItemModificarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModificarCamposActionPerformed
        // TODO add your handling code here:

        DefaultTableModel tabla = new DefaultTableModel();
        tabla.addColumn("Campo");
        tabla.addColumn("Tipo");
        Object[] fila = new Object[2];

        for (int i = 0; i < archivo.getListaCampos().size(); i++) {

            fila[0] = archivo.getListaCampos().get(i).nombre;
            fila[1] = archivo.getListaCampos().get(i).tipo;
            tabla.addRow(fila);
        }

        jTable2.setModel(tabla);

        jDModificarCampo.setModal(true);
        jDModificarCampo.pack();
        jDModificarCampo.setLocationRelativeTo(null);
        jDModificarCampo.setVisible(true);


    }//GEN-LAST:event_jMenuItemModificarCamposActionPerformed

    private void jMenuItemBorrarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBorrarCamposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemBorrarCamposActionPerformed

    private void jMenuItemModificarCamposKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMenuItemModificarCamposKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenuItemModificarCamposKeyPressed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        DefaultTableModel tabla = new DefaultTableModel();
        try {
            if (evt.isMetaDown()) {

                PopMenuModificar.show(evt.getComponent(), evt.getX(), evt.getY());

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Seleccione algo antes de dar click derecho");

        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jMenuModificarCampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuModificarCampoActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabla = new DefaultTableModel();

        archivo.setNombre(JOptionPane.showInputDialog("Ingrese el nuevo nombre"));
        tabla.addColumn("Campo");
        tabla.addColumn("Tipo");
        Object[] fila = new Object[2];

        for (int i = 0; i < archivo.getListaCampos().size(); i++) {

            fila[0] = archivo.getListaCampos().get(i).nombre;
            fila[1] = archivo.getListaCampos().get(i).tipo;
            tabla.addRow(fila);
        }
        jTable2.setModel(tabla);
        JOptionPane.showMessageDialog(jDModificarCampo, "Modificado con exito la tarea");


    }//GEN-LAST:event_jMenuModificarCampoActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        DefaultTableModel tabla = new DefaultTableModel();
        for (int i = 0; i < archivo.getListaCampos().size(); i++) {
            tabla.addColumn(archivo.getListaCampos().get(i));
        }
        Object[] fila = new Object[tabla.getColumnCount()];
        int cont = 0;
        for (int i = 0; i < archivo.getListaRegistros().size(); i++) {
            if (tabla.getColumnCount() - 1 == cont) {
                fila[cont] = archivo.getListaRegistros().get(i);
                tabla.addRow(fila);
                cont = 0;
            } else {
                fila[cont] = archivo.getListaRegistros().get(i);
                cont++;
            }
        }

        jTable1.setModel(tabla);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    public void ArchivoAbierto() {
        jMenuCampos.setEnabled(openfile);
        jMenu1.setEnabled(openfile);
        jMenu2.setEnabled(openfile);
        jMenu3.setEnabled(openfile);
        jMenuItem2.setEnabled(openfile);
        jMenuItem3.setEnabled(openfile);
    }

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu PopMenuModificar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonGuardarCampo;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDialog jDCrearCampo;
    private javax.swing.JDialog jDModificarCampo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCampos;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItemBorrarCampos;
    private javax.swing.JMenuItem jMenuItemListarCampos;
    private javax.swing.JMenuItem jMenuItemModificarCampos;
    private javax.swing.JMenuItem jMenuItemNuevoArchivo;
    private javax.swing.JMenuItem jMenuItemNuevoCampo;
    private javax.swing.JMenuItem jMenuModificarCampo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonNo;
    private javax.swing.JRadioButton jRadioButtonSi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    Archivo archivo;
    Campo nodo_campo;
    boolean openfile;
}
