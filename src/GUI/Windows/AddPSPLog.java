/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Windows;

import Domain.PSPLog;
import Domain.Practicing;
import dataAccess.CreateDocumentsFolders;
import dataAccess.OpenPDF;
import dataAccess.PSPLogDAO;
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
public class AddPSPLog extends javax.swing.JFrame {

    OpenPDF guiUtilities = new OpenPDF();
    CreateDocumentsFolders createFolders = new CreateDocumentsFolders();
    PSPLog pspLog = new PSPLog();
    Practicing practicing = new Practicing();
    String filePath;
    boolean fileReaden;
    int id_pspLog;
    
    
    public AddPSPLog() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel2.setText("Añadir Bitácora de Personal Software Process");

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel1.setText("Sistema de Prácticas Profesionales");

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
                        .addGap(311, 311, 311)
                        .addComponent(jLabel2)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
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
        savePSPLog();
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
    
    private void readPSPLog() throws SQLException, ClassNotFoundException {
        int i = 0;
        PSPLogDAO pspLogDAO = new PSPLogDAO();
        PracticingDAO practicingDAO = new PracticingDAO();
        String[] stringPspLogIDs = null;
        ArrayList<PSPLog> allPSPLogs = pspLogDAO.readAllPSPLogs();
        List<String> pspLogsIDs = new ArrayList<>();
        while (i < allPSPLogs.size()) {
           pspLogsIDs.add(String.valueOf(allPSPLogs.get(i).getId_pspLog()));
           i++;
        }
        stringPspLogIDs = pspLogsIDs.toArray(new String[pspLogsIDs.size()]);
        JComboBox jComboBox = new JComboBox(stringPspLogIDs);
        jComboBox.setEditable(true);
        JOptionPane.showMessageDialog(null, jComboBox, "Seleccione el ID de su Bitácora de PSP", JOptionPane.QUESTION_MESSAGE);
        id_pspLog = Integer.parseInt((String)jComboBox.getSelectedItem());
        pspLog = pspLogDAO.readPSPLogByIDPSPLog(id_pspLog);
        practicing = practicingDAO.searchPracticingByIDPracticing(pspLog.getId_practicing());
    }
    
    void savePSPLog() {
        if (!fileReaden) {
            JOptionPane.showMessageDialog(this, "Seleccione primero su documento.");
        } else {
            try {
                readPSPLog();
            } catch (SQLException | ClassNotFoundException sqlException) {
                JOptionPane.showMessageDialog(this, "No se puede acceder a la base de datos en este momento. Intente más tarde.");
                sqlException.printStackTrace();
            }
            createFolders.createDocumentIDFolder(practicing.getPracticingName(), "PSP Log", pspLog.getId_pspLog());
            Path pspLogFile = Paths.get(filePath);
            try {
                String completeFinalFileName = createFolders.getDirName() + "/" + pspLogFile.getFileName(); 
                Path filesPaths = Files.move(Paths.get(filePath), Paths.get(completeFinalFileName));
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(this, "No fue posible acceder al archivo.");
                ioException.printStackTrace();
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
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddPSPLog().setVisible(true);
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
