/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Miselaneos;

//import static Formularios.Principal.FrmPrincipal.escritorio1;
import java.awt.Dimension;


/**
 *
 * @author nejo
 */
public class jFrameInterno extends javax.swing.JInternalFrame {

    public Imagenes img;
    /**
     * Creates new form jFrameInterno
     */
    public jFrameInterno() {
        initComponents();
        img = new Imagenes();
        setFrameIcon(img.getSysLogo(16, 16));
        setTitle("Formulario Interno Personalizado...");
    }
    
    /*public void mostrar(){
        Dimension dim = escritorio1.getPreferredSize();
        int ancho = (int)((dim.getWidth() - getWidth()) / 2);
        int alto = (int)((dim.getHeight() - getHeight()) / 2);
        setLocation(ancho, alto);
        show();
        setVisible(true);
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}