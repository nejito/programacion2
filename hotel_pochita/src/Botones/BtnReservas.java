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
public class BtnReservas extends Btn {

    private Imagenes img;

    public BtnReservas() {
        super();
        img = new Imagenes();
        setIcon(img.getReservas(16, 16));
        setText("Reservas");
    }
}
