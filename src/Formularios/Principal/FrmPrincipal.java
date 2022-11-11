/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios.Principal;

import Formularios.Huespedes.FrmPruebaHabitacion;
import Miselaneos.jFrameProyecto;
import Paneles.CambiaPanel;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author nejo
 */
public class FrmPrincipal extends jFrameProyecto {

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(this);
        this.btnHome1.setSelected(true);
        
        new CambiaPanel(pnlPrincipal, new Paneles.pnlHome());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlGeneral = new Miselaneos.pnlSys();
        pnlSalir = new Miselaneos.pnlSys();
        btnSalir1 = new Botones.BtnSalir();
        pnlOpciones = new Miselaneos.pnlSys();
        btnHome1 = new Botones.BtnHome();
        btnHabitacion1 = new Botones.BtnHabitacion();
        btnHuesped = new Botones.BtnHuesped();
        btnPersonal1 = new Botones.BtnPersonal();
        btnReservas1 = new Botones.BtnReservas();
        pnlStatus = new Miselaneos.pnlSys();
        reloj = new Miselaneos.Reloj();
        pnlTitulo = new Miselaneos.pnlSys();
        btnMenu = new Botones.BtnMenu();
        jLabel3 = new javax.swing.JLabel();
        pnlPrincipal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hotel POCHITA");
        setMinimumSize(new java.awt.Dimension(1, 1));
        getContentPane().setLayout(new java.awt.BorderLayout());

        pnlGeneral.setBackground(new java.awt.Color(239, 238, 244));
        pnlGeneral.setBorder(null);
        pnlGeneral.setPreferredSize(new java.awt.Dimension(100, 432));
        pnlGeneral.setLayout(new java.awt.BorderLayout());

        pnlSalir.setBackground(new java.awt.Color(239, 238, 244));
        pnlSalir.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(60, 63, 65), null));
        pnlSalir.setForeground(new java.awt.Color(255, 255, 255));
        pnlSalir.setPreferredSize(new java.awt.Dimension(96, 70));
        pnlSalir.setLayout(new java.awt.GridLayout(1, 1, 1, 1));

        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });
        pnlSalir.add(btnSalir1);

        pnlGeneral.add(pnlSalir, java.awt.BorderLayout.SOUTH);

        pnlOpciones.setBackground(new java.awt.Color(239, 238, 244));
        pnlOpciones.setLayout(new java.awt.GridLayout(10, 1, 2, 2));

        btnHome1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHome1ActionPerformed(evt);
            }
        });
        pnlOpciones.add(btnHome1);

        btnHabitacion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHabitacion1ActionPerformed(evt);
            }
        });
        pnlOpciones.add(btnHabitacion1);

        btnHuesped.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuespedActionPerformed(evt);
            }
        });
        pnlOpciones.add(btnHuesped);
        pnlOpciones.add(btnPersonal1);

        btnReservas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservas1ActionPerformed(evt);
            }
        });
        pnlOpciones.add(btnReservas1);

        pnlGeneral.add(pnlOpciones, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlGeneral, java.awt.BorderLayout.WEST);

        pnlStatus.setBackground(new java.awt.Color(239, 238, 244));
        pnlStatus.setLayout(new java.awt.BorderLayout());

        reloj.setBackground(new java.awt.Color(255, 255, 255));
        reloj.setForeground(new java.awt.Color(0, 0, 0));
        pnlStatus.add(reloj, java.awt.BorderLayout.EAST);

        getContentPane().add(pnlStatus, java.awt.BorderLayout.PAGE_END);

        pnlTitulo.setBackground(new java.awt.Color(0, 102, 204));
        pnlTitulo.setLayout(new java.awt.BorderLayout());

        btnMenu.setText("");
        btnMenu.setColorHover(new java.awt.Color(0, 102, 204));
        btnMenu.setColorNormal(new java.awt.Color(0, 102, 204));
        btnMenu.setColorPressed(new java.awt.Color(0, 102, 204));
        btnMenu.setPreferredSize(new java.awt.Dimension(75, 41));
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });
        pnlTitulo.add(btnMenu, java.awt.BorderLayout.WEST);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("HOTEL \"POCHITA\" V0.3  ");
        pnlTitulo.add(jLabel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlTitulo, java.awt.BorderLayout.PAGE_START);

        pnlPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        pnlPrincipal.setPreferredSize(new java.awt.Dimension(1024, 600));
        pnlPrincipal.setLayout(new java.awt.BorderLayout());
        getContentPane().add(pnlPrincipal, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        if (pnlGeneral.isVisible()) {
            pnlGeneral.setVisible(false);
        } else {
            pnlGeneral.setVisible(true);
        }
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed
        if(JOptionPane.showConfirmDialog(pnlPrincipal, "¿Desea cerrar el sistema?", "Atención", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_btnSalir1ActionPerformed

    private void btnHabitacion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHabitacion1ActionPerformed
        new CambiaPanel(pnlPrincipal, new Paneles.pnlHabitacion());
    }//GEN-LAST:event_btnHabitacion1ActionPerformed

    private void btnHome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHome1ActionPerformed
        new CambiaPanel(pnlPrincipal, new Paneles.pnlHome());
    }//GEN-LAST:event_btnHome1ActionPerformed

    private void btnHuespedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuespedActionPerformed
        new CambiaPanel(pnlPrincipal, new Paneles.pnlHuesped());
    }//GEN-LAST:event_btnHuespedActionPerformed

    private void btnReservas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservas1ActionPerformed
        JOptionPane.showMessageDialog(pnlOpciones,"Proximamente","Aviso",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnReservas1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Botones.BtnHabitacion btnHabitacion1;
    private Botones.BtnHome btnHome1;
    private Botones.BtnHuesped btnHuesped;
    private Botones.BtnMenu btnMenu;
    private Botones.BtnPersonal btnPersonal1;
    private Botones.BtnReservas btnReservas1;
    private Botones.BtnSalir btnSalir1;
    private javax.swing.JLabel jLabel3;
    private Miselaneos.pnlSys pnlGeneral;
    private Miselaneos.pnlSys pnlOpciones;
    private javax.swing.JPanel pnlPrincipal;
    private Miselaneos.pnlSys pnlSalir;
    private Miselaneos.pnlSys pnlStatus;
    private Miselaneos.pnlSys pnlTitulo;
    private Miselaneos.Reloj reloj;
    // End of variables declaration//GEN-END:variables
}
