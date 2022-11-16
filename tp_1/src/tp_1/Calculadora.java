/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_1;

/**
 *
 * @author nejito
 */
public class Calculadora {
    private int entero1,entero2;
    private double doble1,doble2;
    
    Calculadora(int a, int b, double c, double d){
        entero1 = a;
        entero2 = b;
        doble1 = c;
        doble2 = d;
    }
    int sumaEntero(){
        return entero1+entero2;
    }
    double sumaDouble(){
        return (doble1) + (doble2);
    }
    int restaEntero(){
        return entero1-entero2;
    }
    double restaDouble(){
        return doble1-doble2;
    }
    int divisionEntero(){
        return entero1/entero2;
    }
    double divisionDouble(){
        return doble1/doble2;
    }
    int multiplicacionEntero(){
        return entero1*entero2;
    }
    double multiplicacionDouble(){
        return doble1*doble2;
    }
    
}
