/*
Intitution: Universidad Veracruzana 
File creator: Brandon Trujillo
Class name: EvaluatePartialReport
Date of creation: July 2nd. 2020 
*/    
package GUI.Windows;

import javax.swing.JOptionPane;

public class EvaluatePartialReport extends javax.swing.JFrame {

    public EvaluatePartialReport() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Reportes disponibles");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        jButtonCancel = new javax.swing.JButton();
        jComboBoxReports = new javax.swing.JComboBox<>();
        jLabelSelectReport = new javax.swing.JLabel();
        jLabelReportIcon = new javax.swing.JLabel();
        jLabelFound = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitle.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabelTitle.setText("Evaluar Reporte Parcial");
        getContentPane().add(jLabelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, -1, -1));

        jButtonCancel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButtonCancel.setText("Cancelar");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, 100, 30));

        jComboBoxReports.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBoxReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 210, 30));

        jLabelSelectReport.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabelSelectReport.setText("Por favor, seleccione un reporte");
        getContentPane().add(jLabelSelectReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, -1, -1));

        jLabelReportIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/EvaluateReport.png"))); // NOI18N
        getContentPane().add(jLabelReportIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 10, -1, -1));

        jLabelFound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Images/Fondo.jpg"))); // NOI18N
        getContentPane().add(jLabelFound, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 250));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        cancelOption();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    void cancelOption(){
        String [] cancelButtons = {"Sí", "No"};
        int optionSelected = JOptionPane.showOptionDialog
                (this, "¿Seguro que desea cancelar?", "Cancelar Evaluar Reporte",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.CANCEL_OPTION, null, cancelButtons, cancelButtons [0]); 
         if (optionSelected == JOptionPane.YES_NO_OPTION) {
            PrincipalWindowProfessor returnToPrincipalWindowProfessor = new PrincipalWindowProfessor(); 
            returnToPrincipalWindowProfessor.setVisible(true); 
            dispose(); 
        }
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EvaluatePartialReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JComboBox<String> jComboBoxReports;
    private javax.swing.JLabel jLabelFound;
    private javax.swing.JLabel jLabelReportIcon;
    private javax.swing.JLabel jLabelSelectReport;
    private javax.swing.JLabel jLabelTitle;
    // End of variables declaration//GEN-END:variables
}
