/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hotel_pochita;

import Formularios.Principal.FrmPrincipal;

/**
 *
 * @author nejo
 */
public class Hotel_pochita {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            //Planifica la intervención del hilo de gestión de eventos (event-dispatching thread):
            //creación y representación de la interfaz gráfica de esta aplicación.
            public void run() {
                FrmPrincipal frmPrincipal = new FrmPrincipal();

                frmPrincipal.setVisible(true);
            }
        });
    }

}
