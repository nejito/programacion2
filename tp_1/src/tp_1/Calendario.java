/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_1;

/**
 *
 * @author nejito
 */
public class Calendario {
    String[] diasSemana = {
        "Lunes",
        "Martes",
        "Miercoles",
        "Jueves",
        "Viernes",
        "Sabado",
        "Domingo"
    };
    
    public boolean dias(String dia){
        boolean diaEncontrado = false;
        for(int i=0; i<diasSemana.length;i++){
            if(dia.equals(diasSemana[i])){
                diaEncontrado = true;
            }
        }
        return diaEncontrado;
    }
}
