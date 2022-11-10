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
public class TxtCuil extends JTextField {
    public TxtCuil() {
        super(new Integer(0));
        setText("0");
        setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 12));

        //Solo se ingresan numeros
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                
                
                int tecla = (int) e.getKeyChar();
                

                if (tecla != 45 && !Character.isDigit(e.getKeyChar()) && !Character.isISOControl(e.getKeyChar())) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
    }

    public Integer toEntero() {
        Integer varEntero = 0;
        try {
            varEntero = Integer.parseInt(getText().trim());
        } catch (NumberFormatException ex) {
            varEntero = 0;
        } finally {
            return varEntero;
        }
    }
}
