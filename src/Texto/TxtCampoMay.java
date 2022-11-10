/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Texto;

/**
 *
 * @author nejo
 */
public class TxtCampoMay extends TxtMayusculas {

    private String tituloCampo;

    public TxtCampoMay() {
        super();
        setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED), getTituloCampo()));
    }

    public String getTituloCampo() {
        return tituloCampo;
    }

    public void setTituloCampo(String tituloCampo) {
        this.tituloCampo = tituloCampo;
        setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED), getTituloCampo()));
    }
}
