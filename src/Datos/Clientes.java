/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import Conexion.ConexionMySQL;
import Entidad.Cliente;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nejo
 */
public class Clientes extends ConexionMySQL {

    @Override
    public ArrayList getList(String query) {
        ArrayList<Cliente> clienteList = new ArrayList<Cliente>();
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setApellido(rs.getString("apellido"));
                c.setNombres(rs.getString("nombres"));
                c.setDomicilio(rs.getString("domicilio"));
                c.setFecha_nac(rs.getDate("fecha_nac"));
                c.setFecha_alta(rs.getDate("fecha_alta"));
                c.setNro_docu(rs.getInt("nro_docu"));
                c.setCuil(rs.getString("cuil"));
                c.setCelular1(rs.getString("celular1"));
                c.setCelular2(rs.getString("celular2"));
                c.setEmail(rs.getString("email"));
                c.setCant_masc(rs.getInt("cant_masc"));
                clienteList.add(c);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally {
            return clienteList;
        }
    }

    @Override
    public boolean isIngresar(Object obj) {
        boolean ok = false;
        Cliente p = (Cliente) obj;
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT * FROM clientes WHERE id=0";

            ResultSet rs = st.executeQuery(query);

            rs.moveToInsertRow();

            rs.updateString("apellido", p.getApellido());
            rs.updateString("nombres", p.getNombres());
            rs.updateString("domicilio", p.getDomicilio());
            if (p.getFecha_nac() != null) {
                String patron1 = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(patron1);
                String mFechaNac = simpleDateFormat1.format(p.getFecha_nac());
                rs.updateString("fecha_nac", mFechaNac);
            } else {
                rs.updateDate("fecha_nac", null);
            }
            if (p.getFecha_alta() != null) {
                String patron2 = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(patron2);
                String mFechaAlta = simpleDateFormat2.format(p.getFecha_alta());
                rs.updateString("fecha_alta", mFechaAlta);
            } else {
                rs.updateDate("fecha_alta", null);
            }
            rs.updateInt("nro_docu", p.getNro_docu());
            rs.updateString("cuil", p.getCuil());
            rs.updateString("celular1", p.getCelular1());
            rs.updateString("celular2", p.getCelular2());
            rs.updateString("email", p.getEmail());
            rs.updateInt("cant_masc", p.getCant_masc());

            rs.insertRow();
            rs.close();
            st.close();

            ok = true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ok = false;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ok = false;
        } finally {
            return ok;
        }
    }

    @Override
    public boolean isEliminar(Object obj) {
        boolean ok = false;
        Cliente p = (Cliente) obj;
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT * FROM clientes WHERE id=" + p.getId();

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                rs.deleteRow();
                ok = true;
            } else {
                ok = false;
            }

            rs.close();
            st.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ok = false;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ok = false;
        } finally {
            return ok;
        }
    }

    @Override
    public boolean isModificar(Object obj) {
        boolean ok = false;
        Cliente p = (Cliente) obj;
        try {
            Statement st = (Statement) getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT * FROM clientes WHERE id=" + p.getId();

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                rs.updateString("apellido", p.getApellido());
                rs.updateString("nombres", p.getNombres());
                rs.updateString("domicilio", p.getDomicilio());
                if (p.getFecha_nac() != null) {
                    String patron1 = "yyyy-MM-dd";
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(patron1);
                    String mFechaNac = simpleDateFormat1.format(p.getFecha_nac());
                    rs.updateString("fecha_nac", mFechaNac);
                } else {
                    rs.updateDate("fecha_nac", null);
                }
                if (p.getFecha_alta() != null) {
                    String patron2 = "yyyy-MM-dd";
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(patron2);
                    String mFechaAlta = simpleDateFormat2.format(p.getFecha_alta());
                    rs.updateString("fecha_alta", mFechaAlta);
                } else {
                    rs.updateDate("fecha_alta", null);
                }
                rs.updateInt("nro_docu", p.getNro_docu());
                rs.updateString("cuil", p.getCuil());
                rs.updateString("celular1", p.getCelular1());
                rs.updateString("celular2", p.getCelular2());
                rs.updateString("email", p.getEmail());
                rs.updateInt("cant_masc", p.getCant_masc());

                rs.updateRow();
                ok = true;
            } else {
                ok = false;
            }

            rs.close();
            st.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ok = false;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ok = false;
        } finally {
            return ok;
        }
    }

    @Override
    public boolean isActualizar(String update) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
