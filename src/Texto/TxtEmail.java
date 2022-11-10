/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Texto;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author nejo
 */
public class TxtEmail extends JTextField {
    public TxtEmail() {
        setText("");
        setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 12));

        //Solo se ingresan letras y "espacio"
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                int tecla = (int) e.getKeyChar();
                
                if (!Character.isAlphabetic(e.getKeyChar()) && Character.isISOControl(e.getKeyChar())) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
    }
}
