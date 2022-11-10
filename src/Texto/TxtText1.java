/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class TxtText1 extends JTextField {

    public TxtText1() {
        setText("");
        setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 12));

        //Solo se ingresan letras y "espacio"
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

                int tecla = (int) e.getKeyChar();
                
                if (tecla != 32 &&!Character.isAlphabetic(e.getKeyChar()) && !Character.isISOControl(e.getKeyChar())) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
    }
}
