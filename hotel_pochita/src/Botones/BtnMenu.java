/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Botones;

import Miselaneos.Btn;
import Miselaneos.Imagenes;

/**
 *
 * @author nejo
 */
public class BtnMenu extends Btn {

    private Imagenes img;

    public BtnMenu() {
        super();
        img = new Imagenes();
        setIcon(img.getMenu(32, 32));
        setText("Menu");
    }
}
