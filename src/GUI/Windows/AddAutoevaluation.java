/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Windows;

import Domain.Autoevaluation;
import Domain.Practicing;
import dataAccess.AutoevaluationDAO;
import dataAccess.CreateDocumentsFolders;
import dataAccess.OpenPDF;
import dataAccess.PracticingDAO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author gabrielflores
 */
public class AddAutoevaluation extends javax.swing.JFrame {

    private int id_autoevaluation = 0;
    private Autoevaluation autoevaluation = null;
    private Practicing practicing = null;
    private String filePath;
    private boolean fileReaden;
    OpenPDF guiUtilities = new OpenPDF();
    CreateDocumentsFolders createFolders = new CreateDocumentsFolders();
    
    public AddAutoevaluation() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel1.setText("Sistema de Prácticas Profesionales");

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel2.setText("Añadir Autoevaluación");

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Seleccionar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 855, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(jButton1)
                        .addGap(79, 79, 79)
                        .addComponent(jButton3)
                        .addGap(84, 84, 84)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(335, 335, 335)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(386, 386, 386)
                        .addComponent(jLabel2)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        saveAutoevaluation();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        filePath = guiUtilities.chooseFile(jScrollPane1);
        fileReaden = true;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cancelAction();
    }//GEN-LAST:event_jButton2ActionPerformed

    void cancelAction() {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "¿Estás seguro de cancelar la operación?","Cancelar operación", dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION) {
            setVisible(false);
            PrincipalWindowPracticing mainWindow = new PrincipalWindowPracticing();
            mainWindow.setVisible(true);
        } else {}
    }
    
    private void readAutoevaluation() throws SQLException, ClassNotFoundException {
        int i = 0;
        AutoevaluationDAO autoevaluationDAO = new AutoevaluationDAO();
        PracticingDAO practicingDAO = new PracticingDAO();
        String[] stringAutoevaluationIDs = null;
        ArrayList<Autoevaluation> autoevaluations = autoevaluationDAO.readAllAutoevaluations();
        List<String> autoevaluationsIDs = new ArrayList<>();
        while (i < autoevaluations.size()) {
           autoevaluationsIDs.add(String.valueOf(autoevaluations.get(i).getId_autoevaluation()));
           i++;
        }
        stringAutoevaluationIDs = autoevaluationsIDs.toArray(new String[autoevaluationsIDs.size()]);
        JComboBox jComboBox = new JComboBox(stringAutoevaluationIDs);
        jComboBox.setEditable(true);
        JOptionPane.showMessageDialog(null, jComboBox, "Seleccione el ID de su Autoevaluación", JOptionPane.QUESTION_MESSAGE);
        id_autoevaluation = Integer.parseInt((String)jComboBox.getSelectedItem());
        autoevaluation = autoevaluationDAO.readAutoevaluationByIDAutoevaluation(id_autoevaluation);
        practicing = practicingDAO.searchPracticingByIDPracticing(autoevaluation.getId_practicing());
    }
    
    void saveAutoevaluation() {
        if (!fileReaden) {
            JOptionPane.showMessageDialog(this, "Seleccione primero su documento.");
        } else {
            try {
                readAutoevaluation();
            } catch (SQLException | ClassNotFoundException sqlException) {
                JOptionPane.showMessageDialog(this, "No se puede acceder a la base de datos en este momento. Intente más tarde.");
                sqlException.printStackTrace();
            }
            createFolders.createDocumentIDFolder(practicing.getPracticingName(), "Autoevaluation", autoevaluation.getId_autoevaluation());
            Path autoevaluationFile = Paths.get(filePath);
            try {
                String completeFinalFileName = createFolders.getDirName() + "/" + autoevaluationFile.getFileName(); 
                Path filesPaths = Files.move(Paths.get(filePath), Paths.get(completeFinalFileName));
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(this, "No fue posible acceder al archivo.");
            }    
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "¿Estás seguro de guardar este documento?","Confirmación", dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "¡Se guardó su reporte con éxito!");
            } else {
                
            }
        }
    }
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
            java.util.logging.Logger.getLogger(AddAutoevaluation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddAutoevaluation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddAutoevaluation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddAutoevaluation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddAutoevaluation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
