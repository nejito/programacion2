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
public class BtnCancelar extends Btn {

    private Imagenes img;

    public BtnCancelar() {
        super();
        img = new Imagenes();
        setIcon(img.getCancelar(16, 16));
        setText("Cancelar");
    }
}
