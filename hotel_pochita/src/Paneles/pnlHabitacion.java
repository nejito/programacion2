/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Paneles;

import Datos.Habitaciones;
import Entidades.Habitacion;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author nejo
 */
public class pnlHabitacion extends javax.swing.JPanel {

    private DefaultTableModel modelo = null;
    private TableRowSorter<TableModel> elQueOrdena = null;
    private TreeMap<Integer, Habitacion> listaHabitaciones = null;

    /**
     * Creates new form pnlHome
     */
    public pnlHabitacion() {
        initComponents();
        _cargarTabla();
        _cargarHabitaciones();
    }

    public void _cargarTabla() {
        modelo = new DefaultTableModel();
        modelo.addColumn("Código/ID");
        modelo.addColumn("Habitación");
        modelo.addColumn("Capacidad");

        elQueOrdena = new TableRowSorter<TableModel>(modelo);
        tablaHabitaciones.setModel(modelo);
        tablaHabitaciones.setRowSorter(elQueOrdena);

        tablaHabitaciones.getColumnModel().getColumn(0).setPreferredWidth(60);
        tablaHabitaciones.getColumnModel().getColumn(1).setPreferredWidth(120);
        tablaHabitaciones.getColumnModel().getColumn(2).setPreferredWidth(140);
    }

    private void _limpiarModelo() {
        if (modelo != null) {
            modelo.setRowCount(0);
        }
    }

    public void _cargarHabitaciones() {
        //Si Los botones no estan habilitados(Enabled) no seguir adelante (return)
        if (!btnActualizar.isEnabled()) {
            return;
        }
        if (!btnNuevo.isEnabled()) {
            return;
        }
        if (!btnModificar.isEnabled()) {
            return;
        }
        if (!btnBorrar.isEnabled()) {
            return;
        }
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                btnActualizar.setEnabled(false);
                btnNuevo.setEnabled(false);
                btnModificar.setEnabled(false);
                btnBorrar.setEnabled(false);

                _limpiarModelo();

                Habitaciones cnx = new Habitaciones();

                ArrayList<Habitacion> list = new ArrayList<Habitacion>();

                if (cnx.conectar()) {
                    list = cnx.getList("SELECT * FROM habitaciones");
                }

                listaHabitaciones = new TreeMap<Integer, Habitacion>();

                for (int index = 0; index < list.size(); index++) {
                    Habitacion h = list.get(index);
                    modelo.addRow(h.getInfo());
                    listaHabitaciones.put(h.getId(), h);
                }

                btnActualizar.setEnabled(true);
                btnNuevo.setEnabled(true);
                btnModificar.setEnabled(true);
                btnBorrar.setEnabled(true);
            }
        });
        t.start();
    }

    public void _filtro(String texto) {
        if (elQueOrdena != null) {
            elQueOrdena.setRowFilter(RowFilter.regexFilter(texto.trim()));
        }
    }

    private Image cargarImagenV2(String id) {
        BufferedImage image = null;
        if (!id.trim().isBlank()) {
            Habitaciones cnx = new Habitaciones();
            String query = "SELECT * FROM habitaciones WHERE id=" + id;
            if (cnx.conectar()) {
                ArrayList<Habitacion> list = cnx.getList(query);
                if (list.size() > 0) {
                    try {
                        String pathFile = list.get(0).getImg().getDescription().trim();
                        image = ImageIO.read(new File(pathFile));
                    } catch (IOException ex) {
                        image = null;
                    } catch (NullPointerException ex) {
                        image = null;
                    }
                }
            }
        }
        return image;
    }

    public void _mostrar(Habitacion hab) {
        Integer id = 0;
        String titulo = "";
        String descripcion = "";
        Integer capacidad = 0;
        boolean estado = false;
        String url = "";
        Double tarifa = 0.0d;
        ImageIcon imagen = null;
        if (hab != null) {
            id = hab.getId();
            titulo = hab.getTitulo();
            descripcion = hab.getDescripcion();
            capacidad = hab.getCapacidad_pers();
            estado = hab.isEstado();
            tarifa = hab.getTarifa();

            Image img = cargarImagenV2(hab.getId() + "");
            try {
                imagen = new ImageIcon(img);
            } catch (NullPointerException ex) {
            }
        }
        idTxt.setText(id + "");
        habitacionTxt.setText(titulo);
        descripcionTxt.setText(descripcion);
        capacidadTxt.setText(capacidad + "");
        chkActiva.setSelected(estado);
        tarifaTxt.setValue(tarifa);
        try {
            lbl_imagen.setIcon(imagen);
            lbl_imagen.setToolTipText(imagen.getDescription().trim());
        } catch (NullPointerException ex) {
            lbl_imagen.setToolTipText("");
        }
    }

    public void _seleccionar() {
        int key = -1;
        Habitacion h = null;
        try {
            int indexTable = tablaHabitaciones.getSelectedRow();
            int indexModelo = tablaHabitaciones.convertRowIndexToModel(indexTable);

            key = Integer.valueOf(modelo.getValueAt(indexModelo, 0).toString());
            if (listaHabitaciones.containsKey(key)) {
                h = listaHabitaciones.get(key);
            }
        } catch (Exception ex) {
        } finally {
            _mostrar(h);
        }
    }

    private Object[] _validar() {
        boolean ok = false;
        Habitacion h = new Habitacion();
        try {
            if (idTxt.getText().trim().isEmpty()) {
                h.setId(0);
            } else {
                h.setId(Integer.parseInt(idTxt.getText()));
            }
            h.setTitulo(habitacionTxt.getText().trim());
            h.setDescripcion(descripcionTxt.getText().trim());
            h.setCapacidad_pers(capacidadTxt.toEntero());
            h.setEstado(chkActiva.isSelected());
            h.setTarifa(tarifaTxt.toDouble());

            ImageIcon imgIcon = null;
            try {
                imgIcon = new ImageIcon(lbl_imagen.getToolTipText().trim());
            } catch (Exception ex) {
            }
            h.setImg(imgIcon);

        } catch (Exception ex) {

        } finally {
            ok = h.isValidar();
        }
        Object[] oReturn = new Object[2];
        oReturn[0] = ok;
        oReturn[1] = h;
        return oReturn;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlInfo = new javax.swing.JPanel();
        pnlBotones = new javax.swing.JPanel();
        btnNuevo = new Botones.BtnNuevo();
        btnModificar = new Botones.BtnModificar();
        btnBorrar = new Botones.BtnBorrar();
        pnlDatos = new javax.swing.JPanel();
        idTxt = new Texto.TxtNro();
        habitacionTxt = new Texto.TxtCampoMay();
        jScrollPane3 = new javax.swing.JScrollPane();
        descripcionTxt = new javax.swing.JTextArea();
        capacidadTxt = new Texto.TxtNro();
        tarifaTxt = new Texto.TxtCampoImporte();
        chkActiva = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        lbl_imagen = new Labels.lbl();
        pnlCentro = new javax.swing.JPanel();
        pnlFiltro = new javax.swing.JPanel();
        filtrar1 = new Labels.Filtrar();
        filtroTxt = new Texto.TxtMayusculas();
        btnActualizar = new Botones.BtnActualizar();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHabitaciones = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        pnlInfo.setBackground(new java.awt.Color(255, 255, 255));
        pnlInfo.setBorder(javax.swing.BorderFactory.createTitledBorder("Habitación"));
        pnlInfo.setPreferredSize(new java.awt.Dimension(300, 388));
        pnlInfo.setLayout(new java.awt.BorderLayout());

        pnlBotones.setBackground(new java.awt.Color(0, 255, 204));
        pnlBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlBotones.setPreferredSize(new java.awt.Dimension(290, 50));
        pnlBotones.setLayout(new java.awt.GridLayout(1, 5, 5, 12));

        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        pnlBotones.add(btnNuevo);

        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        pnlBotones.add(btnModificar);

        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        pnlBotones.add(btnBorrar);

        pnlInfo.add(pnlBotones, java.awt.BorderLayout.PAGE_END);

        pnlDatos.setBackground(new java.awt.Color(255, 255, 255));
        pnlDatos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        idTxt.setEditable(false);
        idTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED), "ID"));
        idTxt.setFocusable(false);

        habitacionTxt.setTituloCampo("Habitación");

        descripcionTxt.setColumns(20);
        descripcionTxt.setLineWrap(true);
        descripcionTxt.setRows(5);
        descripcionTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Descripción de Habitación"));
        jScrollPane3.setViewportView(descripcionTxt);

        capacidadTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Capacidad"));
        capacidadTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tarifaTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Tarifa"));

        chkActiva.setText("¿Activa?");

        lbl_imagen.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Imagen"));
        lbl_imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_imagen.setText("");
        lbl_imagen.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbl_imagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_imagenMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lbl_imagen);

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3)
                    .addComponent(habitacionTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addComponent(capacidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(tarifaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chkActiva)))
                .addContainerGap())
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkActiva))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(habitacionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(capacidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tarifaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlInfo.add(pnlDatos, java.awt.BorderLayout.CENTER);

        add(pnlInfo, java.awt.BorderLayout.EAST);

        pnlCentro.setBackground(new java.awt.Color(255, 255, 255));
        pnlCentro.setMinimumSize(new java.awt.Dimension(588, 457));
        pnlCentro.setLayout(new java.awt.BorderLayout());

        pnlFiltro.setBackground(new java.awt.Color(255, 255, 255));
        pnlFiltro.setPreferredSize(new java.awt.Dimension(633, 50));

        filtroTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtroTxtKeyReleased(evt);
            }
        });

        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFiltroLayout = new javax.swing.GroupLayout(pnlFiltro);
        pnlFiltro.setLayout(pnlFiltroLayout);
        pnlFiltroLayout.setHorizontalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(filtrar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filtroTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addGap(84, 84, 84)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlFiltroLayout.setVerticalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filtrar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filtroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlCentro.add(pnlFiltro, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Habitación"));

        tablaHabitaciones.setBackground(new java.awt.Color(255, 255, 255));
        tablaHabitaciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tablaHabitaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaHabitaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaHabitacionesMouseClicked(evt);
            }
        });
        tablaHabitaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaHabitacionesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaHabitaciones);

        pnlCentro.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(pnlCentro, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        Object[] oResult = _validar();
        boolean ok = (boolean) oResult[0];
        if (ok) {
            Habitacion h = (Habitacion) oResult[1];
            Habitaciones cnx = new Habitaciones();
            if (cnx.conectar()) {
                boolean okNuevo = cnx.isIngresar(h);
                if (okNuevo) {
                    _cargarHabitaciones();
                    Habitacion hBlando = new Habitacion();
                    _mostrar(hBlando);
                } else {
                    JOptionPane.showMessageDialog(pnlInfo, "No Se Registro la Habitación.-", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Object[] oResult = _validar();
        boolean ok = (boolean) oResult[0];
        if (ok) {
            Habitacion h = (Habitacion) oResult[1];
            Habitaciones cnx = new Habitaciones();
            if (cnx.conectar()) {
                boolean okModif = cnx.isModificar(h);
                if (okModif) {
                    _cargarHabitaciones();
                    _mostrar(h);
                } else {
                    JOptionPane.showMessageDialog(pnlInfo, "No Se Modificado la Habitación.-", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tablaHabitacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaHabitacionesKeyReleased
        _seleccionar();
    }//GEN-LAST:event_tablaHabitacionesKeyReleased

    private void lbl_imagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_imagenMouseClicked
        JFileChooser fc = null;
        if (fc == null) {
            fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

            FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
            fc.setFileFilter(imgFilter);
        }

        int returnVal = fc.showDialog(null, "Seleccionar Imagen");

        lbl_imagen.setToolTipText("");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File name = fc.getSelectedFile();
            float longuitudArch = name.length();
            int longMB = (int) longuitudArch / 1024000;
            if (longMB > 3) {
                JOptionPane.showMessageDialog(lbl_imagen, "El Archivo Supero la cantidad de MB permitido que es de 3 MB." + '\n'
                        + "Imposible cargar la Imagen.-", "Aviso", JOptionPane.INFORMATION_MESSAGE);

                return;
            } else {
                lbl_imagen.setIcon(new ImageIcon(name.getPath()));
                lbl_imagen.setToolTipText(name.getPath());
            }
        }
        fc.setSelectedFile(null);
    }//GEN-LAST:event_lbl_imagenMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        _cargarHabitaciones();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void filtroTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtroTxtKeyReleased
        _filtro(filtroTxt.getText().trim());
    }//GEN-LAST:event_filtroTxtKeyReleased

    private void tablaHabitacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaHabitacionesMouseClicked
        _seleccionar();
    }//GEN-LAST:event_tablaHabitacionesMouseClicked

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        Object[] oResult = _validar();
        boolean ok = (boolean) oResult[0];
        if (ok) {
            Habitacion h = (Habitacion) oResult[1];
            Habitaciones cnx = new Habitaciones();
            if (cnx.conectar()) {
                String msjElim = "Desea Eliminar la Habitación:" + h.getTitulo().trim();
                boolean okElim = false;
                if (JOptionPane.showConfirmDialog(pnlInfo, msjElim, "Atención!!!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    okElim = cnx.isEliminar(h);
                    if (okElim) {
                        _cargarHabitaciones();
                        _mostrar(new Habitacion());
                    } else {
                        JOptionPane.showMessageDialog(pnlInfo, "No Se Eliminado la Habitación.-", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnBorrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Botones.BtnActualizar btnActualizar;
    private Botones.BtnBorrar btnBorrar;
    private Botones.BtnModificar btnModificar;
    private Botones.BtnNuevo btnNuevo;
    private Texto.TxtNro capacidadTxt;
    private javax.swing.JCheckBox chkActiva;
    private javax.swing.JTextArea descripcionTxt;
    private Labels.Filtrar filtrar1;
    private Texto.TxtMayusculas filtroTxt;
    private Texto.TxtCampoMay habitacionTxt;
    private Texto.TxtNro idTxt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private Labels.lbl lbl_imagen;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlFiltro;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JTable tablaHabitaciones;
    private Texto.TxtCampoImporte tarifaTxt;
    // End of variables declaration//GEN-END:variables
}
