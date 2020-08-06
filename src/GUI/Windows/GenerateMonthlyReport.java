/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Windows;

import Domain.Activity;
import Domain.Practicing;
import Domain.Project;
import dataAccess.PracticingDAO;
import dataAccess.ProjectDAO;
import dataAccess.generatePDF;
import java.awt.Component;
import java.awt.Container;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author gabrielflores
 */
public class GenerateMonthlyReport extends javax.swing.JFrame {
    
    
    generatePDF generatePDF = new generatePDF();
    InputStream templateFile = null;
    Project project = null;
    Practicing practicing = null;
    Activity activity = null;
    
    public GenerateMonthlyReport() {
        initComponents();
    }

    boolean checkForEmptyJText(Container container) {
        boolean empty = false;
        for (Component component : container.getComponents()) {
            if (component instanceof JTextField) {
                if (((JTextField)component).getText().isEmpty()) {
                    empty = true;
                }
            }
        }
        return empty;
    }
    
    void cancelAction() {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "¿Estás seguro de cancelar la operación?","Cancelar operación", dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION) {
            setVisible(false);
            PrincipalWindowPracticing mainWindow = new PrincipalWindowPracticing();
            mainWindow.setVisible(true);
        } else {}
    }
    
    JPanel fillContainer() {
        JPanel container = new JPanel();
        container.add(jTextNameActivity);
        container.add(jTextActivityDescription);
        container.add(jTextPlannedTime);
        container.add(jTextInvestedTime);
        container.add(jTextPracticingEnrollment);
        return container;
    }
    
    void createActivity() {
        activity = new Activity();
        activity.setActivityName(jTextNameActivity.getText());
        activity.setDescription(jTextActivityDescription.getText());
        activity.setTimePlanned(Integer.parseInt((jTextPlannedTime.getText())));
        activity.setTimeInvested(Integer.parseInt((jTextInvestedTime.getText())));
    }
    
    void getPracticing() {
        PracticingDAO practicingDAO = new PracticingDAO();
        try {
            practicing = practicingDAO.searchPracticingByEnrollment(jTextPracticingEnrollment.getText());
        } catch (SQLException | ClassNotFoundException exception) {
            JOptionPane.showMessageDialog(this, "No es posible acceder a la base de datos en este momento. Inténtelo más tarde.");
            Logger.getLogger(GeneratePartialReportWindow.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
    void getProject() {
        ProjectDAO projectDAO = new ProjectDAO();
        int id_project = practicing.getId_project();
        try {
            project = projectDAO.searchProjectByIDProject(id_project);
        } catch (SQLException | ClassNotFoundException exception) {
            JOptionPane.showMessageDialog(this, "No es posible acceder a la base de datos en este momento. Inténtelo más tarde.");
            Logger.getLogger(GeneratePartialReportWindow.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
    void generateMonthlyReport() {
        if (checkForEmptyJText(fillContainer())) {
            JOptionPane.showMessageDialog(this, "No deje campos vacíos.");
        }
        createActivity();
        List<Activity> listItems = new ArrayList<>();
        listItems.add(activity);
        
        String outputFile = "/Users/gabrielflores/JaspersoftWorkspace/ReporteParcial/" + "PruebaReporteParcial.pdf";
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);
       
        Map<String, Object> parameters = new HashMap<String, Object>();
        getProject();
        getPracticing();
        parameters.put("CollectionDataSet", itemsJRBean);
        parameters.put("practicingName", practicing.getPracticingName());
        parameters.put("projectName", project.getProjectName());
        
        generatePDF generatePDF = new generatePDF();
        InputStream templateFile = null;
        
        try {
            templateFile = generatePDF.readTemplateFile();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "No es posible acceder al archivo base del reporte. Inténtelo más tarde.");
            Logger.getLogger(GeneratePartialReportWindow.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        try {
            generatePDF.generatePartialReport(parameters, templateFile);
        } catch (JRException reportCreatingException) {
           JOptionPane.showMessageDialog(this, "No es posible finalizar con su reporte en este momento, inténtelo más tarde.");
           Logger.getLogger(GeneratePartialReportWindow.class.getName()).log(Level.SEVERE, null, reportCreatingException);
        }
        
        try {
            generatePDF.showResultingPDF();
        } catch (IOException readPDFException) {
            JOptionPane.showMessageDialog(this, "No es posible mostrar su archivo en este momento.");
            Logger.getLogger(GeneratePartialReportWindow.class.getName()).log(Level.SEVERE, null, readPDFException);
        }
        JOptionPane.showMessageDialog(this, "Su reporte fue creado de manera correcta.");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextNameActivity = new javax.swing.JTextField();
        jTextPracticingEnrollment = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextActivityDescription = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextPlannedTime = new javax.swing.JTextField();
        jTextInvestedTime = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButtonCancel = new javax.swing.JButton();
        jButtonFinalize = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setText("Sistema de Prácticas Profesionales");

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel3.setText("Generar Reporte Mensual");

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jLabel1.setText("Información de actividad");

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel4.setText("Nombre de actividad");

        jTextNameActivity.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextNameActivity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNameActivityActionPerformed(evt);
            }
        });

        jTextPracticingEnrollment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextPracticingEnrollmentActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Matrícula de Practicante");

        jTextActivityDescription.setColumns(20);
        jTextActivityDescription.setRows(5);
        jScrollPane1.setViewportView(jTextActivityDescription);

        jLabel5.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel5.setText("Descripción de la actividad");

        jLabel6.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel6.setText("Tiempo planteado (en semanas)");

        jTextPlannedTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextPlannedTimeActionPerformed(evt);
            }
        });

        jTextInvestedTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextInvestedTimeActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel7.setText("Tiempo real implementado (en semanas)");

        jLabel9.setText("*La información necesaria para concluir tu reporte será extraída de la base de datos.");

        jButtonCancel.setText("Cancelar");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonFinalize.setText("Finalizar");
        jButtonFinalize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalizeActionPerformed(evt);
            }
        });

        jButton1.setText("Agregar Actividad");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextNameActivity, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(194, 194, 194)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextPracticingEnrollment, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)))
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextPlannedTime, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonFinalize)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonCancel))
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(71, 71, 71)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextInvestedTime, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))))
                        .addGap(0, 54, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextPracticingEnrollment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextNameActivity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextInvestedTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextPlannedTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancel)
                    .addComponent(jButtonFinalize)
                    .addComponent(jButton1))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextNameActivityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNameActivityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNameActivityActionPerformed

    private void jTextPracticingEnrollmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextPracticingEnrollmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextPracticingEnrollmentActionPerformed

    private void jTextPlannedTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextPlannedTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextPlannedTimeActionPerformed

    private void jTextInvestedTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextInvestedTimeActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextInvestedTimeActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        cancelAction();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonFinalizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizeActionPerformed
        generateMonthlyReport();
    }//GEN-LAST:event_jButtonFinalizeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(GenerateMonthlyReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerateMonthlyReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerateMonthlyReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerateMonthlyReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerateMonthlyReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonFinalize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextActivityDescription;
    private javax.swing.JTextField jTextInvestedTime;
    private javax.swing.JTextField jTextNameActivity;
    private javax.swing.JTextField jTextPlannedTime;
    private javax.swing.JTextField jTextPracticingEnrollment;
    // End of variables declaration//GEN-END:variables
}
