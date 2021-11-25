/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
        JMenuItemListaCampos = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
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

        javax.swing.GroupLayout jDCrearCampoLayout = new javax.swing.GroupLayout(jDCrearCampo.getContentPane());
        jDCrearCampo.getContentPane().setLayout(jDCrearCampoLayout);
        jDCrearCampoLayout.setHorizontalGroup(
            jDCrearCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDCrearCampoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jButtonGuardarCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
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
                .addGap(26, 26, 26)
                .addGroup(jDCrearCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGuardarCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

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
                .addContainerGap(27, Short.MAX_VALUE))
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

        JMenuItemListaCampos.setText("Listar Campos");
        JMenuItemListaCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMenuItemListaCamposActionPerformed(evt);
            }
        });
        jMenuCampos.add(JMenuItemListaCampos);

        jMenuItem5.setText("Modificar Campos");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenuCampos.add(jMenuItem5);

        jMenuItem6.setText("Borrar Campos");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenuCampos.add(jMenuItem6);

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
        if (jTextField1.toString() != " ") {
            String nombre = jTextField1.getText();
            String tipo = jComboBox1.getSelectedItem().toString();
            boolean campo_llave = false;
            if (jRadioButtonSi.isSelected()) {
                campo_llave = true;
            }
            Campo campo = new Campo(nombre, tipo, campo_llave);
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
        String nombre = JOptionPane.showInputDialog("Ingrese un nombre para el archivo: ");
        archivo = new Archivo(nombre);
        archivo.escribirArchivo();
        JOptionPane.showMessageDialog(null, "El archivo fue creado exitósamente");
    }//GEN-LAST:event_jMenuItemNuevoArchivoActionPerformed

    private void JMenuItemListaCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMenuItemListaCamposActionPerformed
        // TODO add your handling code here:
        String[] cols = {"", ""};
        DefaultTableModel tabla = new DefaultTableModel();
        tabla.addColumn("Campo");
        tabla.addColumn("Tipo");
        Object[] fila = new Object[2];

        for (int i = 0; i < archivo.getListaCampos().size(); i++) {

            fila[0] = archivo.getListaCampos().get(i).nombre;
            fila[1] = archivo.getListaCampos().get(i).tipo;
            tabla.addRow(fila);
        }

        jTable1.setModel(tabla);

    }//GEN-LAST:event_JMenuItemListaCamposActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
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
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

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
    private javax.swing.JMenuItem JMenuItemListaCampos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonGuardarCampo;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDialog jDCrearCampo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItemNuevoArchivo;
    private javax.swing.JMenuItem jMenuItemNuevoCampo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonNo;
    private javax.swing.JRadioButton jRadioButtonSi;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    Archivo archivo;
}
