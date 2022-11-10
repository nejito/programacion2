/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Texto;

/**
 *
 * @author nejo
 */
public class TxtCampoEmail extends TxtMayusculasEmail {
    private String tituloCampo;

    public TxtCampoEmail() {
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
