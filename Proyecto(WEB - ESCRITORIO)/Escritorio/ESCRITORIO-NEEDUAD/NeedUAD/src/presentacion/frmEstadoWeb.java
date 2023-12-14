/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import negocio.clsNTomcat;

/**
 *
 * @author fichu
 */
public class frmEstadoWeb extends javax.swing.JFrame {
    clsNTomcat tomcatManager = new clsNTomcat();
    
    /**
     * Creates new form frmEstadoWeb
     */
    public frmEstadoWeb() {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSwitch0 = new javax.swing.JButton();
        btnSwitch1 = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        lblEstadoTomcat = new javax.swing.JLabel();
        REGRESAR = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSwitch0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/apagado.png"))); // NOI18N
        btnSwitch0.setText("Apagar Servidor WEB");
        btnSwitch0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSwitch0ActionPerformed(evt);
            }
        });

        btnSwitch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/encender.png"))); // NOI18N
        btnSwitch1.setText("Encender Servidor WEB");
        btnSwitch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSwitch1ActionPerformed(evt);
            }
        });

        REGRESAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/back.png"))); // NOI18N
        REGRESAR.setText("Regresar MAIN");
        REGRESAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REGRESARActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblEstadoTomcat, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(REGRESAR))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSwitch0, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(btnSwitch1)))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSwitch0)
                    .addComponent(btnSwitch1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEstadoTomcat, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(REGRESAR)
                        .addGap(11, 11, 11)))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSwitch0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSwitch0ActionPerformed
            mostrarBarraDeCarga();

            new Thread(() -> {
                tomcatManager.apagarTomcat();
  
                ocultarBarraDeCarga();
                mostrarAlerta("Servidor apagado correctamente");
            }).start();
    }//GEN-LAST:event_btnSwitch0ActionPerformed

    
    private void btnSwitch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSwitch1ActionPerformed
        mostrarBarraDeCarga();

        new Thread(() -> {
            tomcatManager.encenderTomcat();
            
            ocultarBarraDeCarga();
            mostrarAlerta("Servidor encendido correctamente");
        }).start();
        
    }//GEN-LAST:event_btnSwitch1ActionPerformed

    private void REGRESARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REGRESARActionPerformed
    this.dispose();
    frmPrincipal frmPrincipal = new frmPrincipal();
    frmPrincipal.setVisible(true);
    }//GEN-LAST:event_REGRESARActionPerformed
    
    private void mostrarBarraDeCarga() {
        progressBar.setIndeterminate(true);
        progressBar.setVisible(true);
    }

    private void ocultarBarraDeCarga() {
        progressBar.setIndeterminate(false);
        progressBar.setVisible(false);
    }
       private void mostrarAlerta(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Alerta", JOptionPane.INFORMATION_MESSAGE);
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
            java.util.logging.Logger.getLogger(frmEstadoWeb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmEstadoWeb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmEstadoWeb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmEstadoWeb.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmEstadoWeb().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton REGRESAR;
    private javax.swing.JButton btnSwitch0;
    private javax.swing.JButton btnSwitch1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblEstadoTomcat;
    private javax.swing.JProgressBar progressBar;
    // End of variables declaration//GEN-END:variables
}
