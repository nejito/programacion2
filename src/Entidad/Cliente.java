/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author nejo
 */
public class Cliente {
    //Clase cliente
    private int id;
    private String apellido;
    private String nombres;
    private String domicilio;
    private Date fecha_nac;
    private Date fecha_alta;
    private Integer nro_docu;
    private String cuil;
    private String celular1;
    private String celular2;
    private String email;
    private int cant_masc;
    private Date ult_act;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public Integer getNro_docu() {
        return nro_docu;
    }

    public void setNro_docu(Integer nro_docu) {
        this.nro_docu = nro_docu;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getCelular1() {
        return celular1;
    }

    public void setCelular1(String celular1) {
        this.celular1 = celular1;
    }

    public String getCelular2() {
        return celular2;
    }

    public void setCelular2(String celular2) {
        this.celular2 = celular2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCant_masc() {
        return cant_masc;
    }

    public void setCant_masc(int cant_masc) {
        this.cant_masc = cant_masc;
    }

    public Date getUlt_act() {
        return ult_act;
    }

    public void setUlt_act(Date ult_act) {
        this.ult_act = ult_act;
    }
    
    

    public Cliente() {
        setId(0);
        setApellido("");
        setNombres("");
        setDomicilio("");
        setFecha_nac(null);
        setFecha_alta(null);
        setNro_docu(0);
        setCuil("");
        setCelular1("");
        setCelular2("");
        setEmail("");
        setCant_masc(1);
        setUlt_act(null);
    }

    public Object[] getInfo() {

        
        Object[] oDato = {getId(),
            getApellido(),
            getNombres(),
            getNro_docu()};
        return oDato;
    }

    public boolean isValidar() {
        boolean ok = false;
        ok = true;
        return ok;
    }
}
