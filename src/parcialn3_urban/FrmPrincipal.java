/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package parcialn3_urban;

import Datos.Clientes;
import Entidad.Cliente;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author nejo
 */
public class FrmPrincipal extends javax.swing.JFrame {

    //nuevo modelo de tabla, para ordenar y treemap para clientes
    private DefaultTableModel modelo = null;
    private TableRowSorter<TableModel> elQueOrdena = null;
    private TreeMap<String, Cliente> listaClientes = null;

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
        _tabla();
        _cargarCliente();

        tablaClientes.getTableHeader().setFont(new Font("MS Reference Sans Serif", Font.BOLD, 12));
        tablaClientes.getTableHeader().setOpaque(false);
        tablaClientes.getTableHeader().setBackground(new Color(32, 136, 203));
        tablaClientes.getTableHeader().setForeground(new Color(255, 255, 255));
        tablaClientes.setRowHeight(25);
        setIconImage(new ImageIcon(getClass().getResource("/parcialn3_urban/java_coffee_13071.png")).getImage());
    }

    //Funcion con una expresion para saber si solo contiene letras.
    public static boolean isAlpha(String a) {
        return a != null && a.matches("^[a-zA-Z\\s]*$");
    }

    //Funcion para validar que sea un correo electronico.
    public static boolean esCorreo(String a) {
        return a != null && a.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }

    //Filtro de datos de los clientes, de forma general
    public void _filtrar(String texto) {
        if (elQueOrdena != null) {
            elQueOrdena.setRowFilter(RowFilter.regexFilter(texto.trim()));
        }
    }

    //Configuracion de la tabla, de los datos mas importantes a mostrar en tabla
    public void _tabla() {
        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("NOMBRES");
        modelo.addColumn("DOCUMENTO");

        elQueOrdena = new TableRowSorter<TableModel>(modelo);
        tablaClientes.setModel(modelo);
        tablaClientes.setRowSorter(elQueOrdena);

        tablaClientes.getColumnModel().getColumn(0).setPreferredWidth(60);
        tablaClientes.getColumnModel().getColumn(1).setPreferredWidth(120);
        tablaClientes.getColumnModel().getColumn(2).setPreferredWidth(140);
        tablaClientes.getColumnModel().getColumn(3).setPreferredWidth(100);
    }

    //Limpia la tabla
    private void _limpiarModelo() {
        if (modelo != null) {
            modelo.setRowCount(0);
        }
    }

    //Vacia los campos de datos
    private void _limpiar() {
        apellidoTxt.setText("");
        nombresTxt.setText("");
        domicilioTxt.setText("");
        fecha_nac.setCalendar(null);
        fecha_alta.setCalendar(null);
        nroDocumentoTxt.setText("");
        cuilTxt.setText("");
        celular1Txt.setText("");
        celular2Txt.setText("");
        emailTxt.setText("");
        cant_mascTxt.setText("1");
    }

    //Carga de clientes en treemap
    public void _cargarCliente() {
        _limpiarModelo();
        Clientes cnx = new Clientes();

        ArrayList<Cliente> list = new ArrayList<Cliente>();

        if (cnx.conectar()) {
            list = cnx.getList("SELECT * FROM clientes");
        }

        listaClientes = new TreeMap<String, Cliente>();

        for (int index = 0; index < list.size(); index++) {
            Cliente p = list.get(index);
            modelo.addRow(p.getInfo());

            String key = p.getId() + "";
            listaClientes.put(key, p);
        }
    }

    public void _mostrarClientes(Cliente cli) {
        String id = "";
        String apellido = "";
        String nombres = "";
        String domicilio = "";
        Date fechaNac = null;
        Date fechaAlta = null;
        String nroDoc = "";
        String cuil = "";
        String celular1 = "";
        String celular2 = "";
        String email = "";
        String cant_masc = "1";
        if (cli != null) {
            id = cli.getId() + "";
            apellido = cli.getApellido().trim();
            nombres = cli.getNombres().trim();
            domicilio = cli.getDomicilio().trim();
            fechaNac = cli.getFecha_nac();
            fechaAlta = cli.getFecha_alta();
            nroDoc = cli.getNro_docu() + "";
            cuil = cli.getCuil().trim();
            celular1 = cli.getCelular1().toString();
            celular2 = cli.getCelular2().toString();
            email = cli.getEmail().trim();
            cant_masc = cli.getCant_masc() + "";
        }
        idTxt.setText(id);
        apellidoTxt.setText(apellido);
        nombresTxt.setText(nombres);
        domicilioTxt.setText(domicilio);
        fecha_nac.setDate(fechaNac);
        fecha_alta.setDate(fechaAlta);
        nroDocumentoTxt.setText(nroDoc);
        cuilTxt.setText(cuil);
        celular1Txt.setText(celular1);
        celular2Txt.setText(celular2);
        emailTxt.setText(email);
        cant_mascTxt.setText(cant_masc);
    }

    //Para tabla, al seleccionar un dato carga todos los datos en los campos
    public void _seleccionar() {
        String key = "";
        Cliente p = null;
        try {
            int indexTable = tablaClientes.getSelectedRow();
            int indexModelo = tablaClientes.convertRowIndexToModel(indexTable);

            key = modelo.getValueAt(indexModelo, 0).toString();
            if (listaClientes.containsKey(key)) {
                p = listaClientes.get(key);
            }
        } catch (Exception ex) {
        } finally {
            _mostrarClientes(p);
        }
    }

    //Validacion de datos del cliente que se agrega
    private Object[] _validar() {
        boolean ok = false;
        Cliente c = new Cliente();

        Object[] oResult = new Object[2];
        oResult[0] = ok;

        String apellido = apellidoTxt.getText().trim();
        apellido = (apellido.substring(0, 1).toUpperCase() + apellido.substring(1).toLowerCase());
        String nombres = nombresTxt.getText().trim();
        nombres = (nombres.substring(0, 1).toUpperCase() + nombres.substring(1).toLowerCase());
        String domicilio = domicilioTxt.getText().trim();
        domicilio = (domicilio.substring(0, 1).toUpperCase() + domicilio.substring(1).toLowerCase());
        String email = emailTxt.getText().trim();
        String emailValido = "";

        //Paso de string a int
        String String_cant_masc = cant_mascTxt.getText();
        int int_cant_masc = Integer.parseInt(String_cant_masc);

        //Paso de string a int
        String String_nroDocu = nroDocumentoTxt.getText();
        int int_nroDocu = Integer.parseInt(String_nroDocu);
        
        c.setApellido(apellido);
        c.setNombres(nombres);
        c.setDomicilio(domicilio);
        c.setFecha_nac(fecha_nac.getDate());
        c.setFecha_alta(fecha_alta.getDate());
        c.setNro_docu(int_nroDocu);
        c.setCuil(cuilTxt.getText().trim());
        c.setCelular1(celular1Txt.getText().trim());
        c.setCelular2(celular2Txt.getText().trim());
        

        try {
            if (idTxt.getText().trim().isEmpty()) {
                c.setId(0);
            } else {
                c.setId(Integer.parseInt(idTxt.getText()));
            }
            c.setApellido(apellidoTxt.getText().trim());
            c.setNombres(nombresTxt.getText().trim());
        } catch (Exception ex) {
        }


        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String date = sdf.format(fecha_nac.getDate());

        //Se valida que una fecha este colocada en el calendario
        if (date.isEmpty()) {
            JOptionPane.showMessageDialog(nuevoBtn, "¡Seleccione una fecha en el calendario!", "Atencion", JOptionPane.WARNING_MESSAGE);
        }

        SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
        String date2 = sdf2.format(fecha_alta.getDate());

        //Se valida que una fecha este colocada en el calendario
        if (date2.isEmpty()) {
            JOptionPane.showMessageDialog(nuevoBtn, "¡Seleccione una fecha en el calendario!", "Atencion", JOptionPane.WARNING_MESSAGE);
        }

        //Valida que el correo ingresado sea uno valido
        if (esCorreo(email)) {
            emailValido = email.toLowerCase();
            c.setEmail(emailValido);
            ok = true;
        } else { //Si no, arroja un mensaje, deja el campo vacio y recupera el foco
            JOptionPane.showMessageDialog(nuevoBtn, "¡El CORREO no es valido!", "¡Atención!", JOptionPane.WARNING_MESSAGE);
            emailTxt.setText("");
            emailTxt.requestFocus();
            ok = false;
            oResult[0] = ok;
            return oResult;
        }

        //Valida que la cantidad de mascotas sea mayor a 0
        if (int_cant_masc > 0) {
            c.setCant_masc(int_cant_masc);
            ok = true;
        } else {
            JOptionPane.showMessageDialog(nuevoBtn, "¡La cantidad de mascotas debe ser 1 o superior!", "¡Atención!", JOptionPane.WARNING_MESSAGE);
            cant_mascTxt.setText("");
            cant_mascTxt.requestFocus();
            ok = false;
            oResult[0] = ok;
            return oResult;
        }

        Object[] oReturn = new Object[2];
        oReturn[0] = ok;
        oReturn[1] = c;
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

        pnlFiltro = new javax.swing.JPanel();
        reloj1 = new parcialn3_urban.Reloj();
        filtrar2Txt = new Texto.TxtCampoEmail();
        jButton1 = new javax.swing.JButton();
        pnlGeneral = new javax.swing.JPanel();
        pnlBotones = new javax.swing.JPanel();
        nuevoBtn = new javax.swing.JButton();
        modificarBtn = new javax.swing.JButton();
        borrarBtn = new javax.swing.JButton();
        vaciarBtn = new javax.swing.JButton();
        pnlDatos = new javax.swing.JPanel();
        fecha_nac = new com.toedter.calendar.JDateChooser();
        fecha_alta = new com.toedter.calendar.JDateChooser();
        idTxt = new Texto.TxtNro();
        apellidoTxt = new Texto.TxtCampoMay();
        nombresTxt = new Texto.TxtCampoMay();
        nroDocumentoTxt = new Texto.TxtNro();
        celular1Txt = new Texto.TxtNro();
        celular2Txt = new Texto.TxtNro();
        cant_mascTxt = new Texto.TxtNro();
        cuilTxt = new Texto.TxtCuil();
        domicilioTxt = new Texto.TxtCampoDomicilio();
        emailTxt = new Texto.TxtCampoEmail();
        pnlTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Parcial N°3 - Urban Daniel");
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1024, 600));

        pnlFiltro.setBackground(new java.awt.Color(204, 204, 204));
        pnlFiltro.setPreferredSize(new java.awt.Dimension(967, 60));

        reloj1.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 18)); // NOI18N

        filtrar2Txt.setBackground(new java.awt.Color(204, 204, 204));
        filtrar2Txt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Filtrar"));
        filtrar2Txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filtrar2TxtKeyReleased(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ACTUALIZAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFiltroLayout = new javax.swing.GroupLayout(pnlFiltro);
        pnlFiltro.setLayout(pnlFiltroLayout);
        pnlFiltroLayout.setHorizontalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filtrar2Txt, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(221, 221, 221)
                .addComponent(reloj1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlFiltroLayout.setVerticalGroup(
            pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(filtrar2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addComponent(reloj1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        getContentPane().add(pnlFiltro, java.awt.BorderLayout.NORTH);

        pnlGeneral.setBackground(new java.awt.Color(255, 255, 255));
        pnlGeneral.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos de Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MS Reference Sans Serif", 1, 12))); // NOI18N
        pnlGeneral.setPreferredSize(new java.awt.Dimension(500, 459));
        pnlGeneral.setLayout(new java.awt.BorderLayout());

        pnlBotones.setBackground(new java.awt.Color(32, 136, 203));

        nuevoBtn.setBackground(new java.awt.Color(255, 255, 255));
        nuevoBtn.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 8)); // NOI18N
        nuevoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add1.png"))); // NOI18N
        nuevoBtn.setText("NUEVO");
        nuevoBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nuevoBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        nuevoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoBtnActionPerformed(evt);
            }
        });
        pnlBotones.add(nuevoBtn);

        modificarBtn.setBackground(new java.awt.Color(255, 255, 255));
        modificarBtn.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 8)); // NOI18N
        modificarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/modify.png"))); // NOI18N
        modificarBtn.setText("MODIFICAR");
        modificarBtn.setFocusTraversalPolicyProvider(true);
        modificarBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        modificarBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        modificarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarBtnActionPerformed(evt);
            }
        });
        pnlBotones.add(modificarBtn);

        borrarBtn.setBackground(new java.awt.Color(255, 255, 255));
        borrarBtn.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 8)); // NOI18N
        borrarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/remove.png"))); // NOI18N
        borrarBtn.setText("BORRAR");
        borrarBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        borrarBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        borrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarBtnActionPerformed(evt);
            }
        });
        pnlBotones.add(borrarBtn);

        vaciarBtn.setBackground(new java.awt.Color(255, 255, 255));
        vaciarBtn.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 8)); // NOI18N
        vaciarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/clean.png"))); // NOI18N
        vaciarBtn.setText("LIMPIAR");
        vaciarBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        vaciarBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        vaciarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vaciarBtnActionPerformed(evt);
            }
        });
        pnlBotones.add(vaciarBtn);

        pnlGeneral.add(pnlBotones, java.awt.BorderLayout.SOUTH);

        pnlDatos.setBackground(new java.awt.Color(255, 255, 255));

        fecha_nac.setBackground(new java.awt.Color(255, 255, 255));
        fecha_nac.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Fecha de Nacimiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MS Reference Sans Serif", 1, 12))); // NOI18N
        fecha_nac.setForeground(new java.awt.Color(255, 255, 255));

        fecha_alta.setBackground(new java.awt.Color(255, 255, 255));
        fecha_alta.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Fecha de Alta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MS Reference Sans Serif", 1, 12))); // NOI18N

        idTxt.setEditable(false);
        idTxt.setBackground(new java.awt.Color(255, 255, 255));
        idTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MS Reference Sans Serif", 1, 12))); // NOI18N
        idTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        apellidoTxt.setBackground(new java.awt.Color(255, 255, 255));
        apellidoTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Apellido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MS Reference Sans Serif", 1, 12))); // NOI18N
        apellidoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidoTxtKeyTyped(evt);
            }
        });

        nombresTxt.setBackground(new java.awt.Color(255, 255, 255));
        nombresTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Nombres", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MS Reference Sans Serif", 1, 12))); // NOI18N
        nombresTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombresTxtActionPerformed(evt);
            }
        });
        nombresTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombresTxtKeyTyped(evt);
            }
        });

        nroDocumentoTxt.setBackground(new java.awt.Color(255, 255, 255));
        nroDocumentoTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Documento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MS Reference Sans Serif", 1, 12))); // NOI18N
        nroDocumentoTxt.setText("");
        nroDocumentoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nroDocumentoTxtKeyTyped(evt);
            }
        });

        celular1Txt.setBackground(new java.awt.Color(255, 255, 255));
        celular1Txt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Celular 1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MS Reference Sans Serif", 1, 12))); // NOI18N
        celular1Txt.setText("");
        celular1Txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                celular1TxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                celular1TxtKeyTyped(evt);
            }
        });

        celular2Txt.setBackground(new java.awt.Color(255, 255, 255));
        celular2Txt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Celular 2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MS Reference Sans Serif", 1, 12))); // NOI18N
        celular2Txt.setText("");
        celular2Txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                celular2TxtKeyTyped(evt);
            }
        });

        cant_mascTxt.setBackground(new java.awt.Color(255, 255, 255));
        cant_mascTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Cantidad de Mascotas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MS Reference Sans Serif", 1, 12))); // NOI18N
        cant_mascTxt.setText("1");
        cant_mascTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cant_mascTxtKeyTyped(evt);
            }
        });

        cuilTxt.setBackground(new java.awt.Color(255, 255, 255));
        cuilTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Cuil"));
        cuilTxt.setText("");
        cuilTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cuilTxtKeyTyped(evt);
            }
        });

        domicilioTxt.setBackground(new java.awt.Color(255, 255, 255));
        domicilioTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Domicilio"));
        domicilioTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                domicilioTxtKeyTyped(evt);
            }
        });

        emailTxt.setBackground(new java.awt.Color(255, 255, 255));
        emailTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Email"));
        emailTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emailTxtKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addComponent(apellidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nombresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(domicilioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(celular1Txt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nroDocumentoTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fecha_nac, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fecha_alta, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(celular2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cant_mascTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cuilTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(apellidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(nombresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(domicilioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(fecha_nac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(fecha_alta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nroDocumentoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cuilTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(celular1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celular2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cant_mascTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pnlGeneral.add(pnlDatos, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlGeneral, java.awt.BorderLayout.EAST);

        pnlTabla.setBackground(new java.awt.Color(255, 255, 255));
        pnlTabla.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MS Reference Sans Serif", 1, 12))); // NOI18N

        tablaClientes.setBackground(new java.awt.Color(255, 255, 255));
        tablaClientes.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 12)); // NOI18N
        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaClientes.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tablaClientesAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaClientes);

        javax.swing.GroupLayout pnlTablaLayout = new javax.swing.GroupLayout(pnlTabla);
        pnlTabla.setLayout(pnlTablaLayout);
        pnlTablaLayout.setHorizontalGroup(
            pnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTablaLayout.setVerticalGroup(
            pnlTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(pnlTabla, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoBtnActionPerformed
        //Boton para agregar un nuevo cliente
        Object[] oReturn = _validar();
        boolean ok = (boolean) oReturn[0];
        if (ok) {
            Cliente pNuevo = (Cliente) oReturn[1];
            Clientes cnx = new Clientes();
            if (cnx.conectar()) {
                ok = cnx.isIngresar(pNuevo);
                _limpiar();
            }
            if (ok) {
                _cargarCliente();
                _mostrarClientes(new Cliente());
            } else {
                JOptionPane.showMessageDialog(pnlDatos, "No se pudo cargar los datos!", "Error!", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(pnlDatos, "Verifique los datos ingresados!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_nuevoBtnActionPerformed

    private void vaciarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vaciarBtnActionPerformed
        //Boton para vaciar todos los campos, si tuviese algun dato escrito
        _limpiar();
    }//GEN-LAST:event_vaciarBtnActionPerformed

    private void borrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarBtnActionPerformed
        //Boton para eliminar datos, se debe seleccionar un registro
        int index = tablaClientes.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(null, "Para eliminar un registro, seleccionelo", "¡Atención!", JOptionPane.WARNING_MESSAGE);
        } else {
            Object[] oReturn = _validar();
            boolean ok = (boolean) oReturn[0];
            if (ok) {
                Cliente cEliminar = (Cliente) oReturn[1];
                String msj = "¿Desea eliminar a " + cEliminar.getApellido().trim() + " " + cEliminar.getNombres().trim() + "?";
                if (JOptionPane.showConfirmDialog(pnlDatos, msj, "Atención", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                    Clientes cnx = new Clientes();
                    if (cnx.conectar()) {
                        ok = cnx.isEliminar(cEliminar);
                        JOptionPane.showMessageDialog(pnlDatos, "Registro eliminado con exito", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (ok) {
                        _cargarCliente();
                        _limpiar();
                    } else {
                        JOptionPane.showMessageDialog(pnlDatos, "No se elimino al usuario", "Error", JOptionPane.WARNING_MESSAGE);
                    }

                }
            } else {
                JOptionPane.showMessageDialog(pnlDatos, "Controle los datos", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_borrarBtnActionPerformed

    private void tablaClientesAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tablaClientesAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaClientesAncestorAdded

    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseClicked
        //Al seleccionar un registro de la tabla, carga los campos
        _seleccionar();
    }//GEN-LAST:event_tablaClientesMouseClicked

    private void modificarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarBtnActionPerformed
        //Boton para modificar datos
        int index = tablaClientes.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(null, "Para modificar un registro, seleccionelo.", "¡Atención!", JOptionPane.WARNING_MESSAGE);
        } else {
            Object[] oReturn = _validar();
            boolean ok = (boolean) oReturn[0];
            if (ok) {
                Cliente cModificar = (Cliente) oReturn[1];
                String msj = "¿Desea modificar los datos de " + cModificar.getApellido().trim() + " " + cModificar.getNombres().trim() + "?";
                if (JOptionPane.showConfirmDialog(pnlDatos, msj, "Atención", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    Clientes cnx = new Clientes();
                    if (cnx.conectar()) {
                        ok = cnx.isModificar(cModificar);
                        JOptionPane.showMessageDialog(pnlDatos, "Registro modificado con exito!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (ok) {
                        _cargarCliente();
                        _limpiar();
                    } else {
                        JOptionPane.showMessageDialog(pnlDatos, "No se pudo actualizar!", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(pnlDatos, "Controle los datos!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_modificarBtnActionPerformed

    private void apellidoTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoTxtKeyTyped
        //Limita la cantidad de caracteres introducidos a 35
        if (apellidoTxt.getText().length() >= 35) {
            evt.consume();
        }
    }//GEN-LAST:event_apellidoTxtKeyTyped

    private void nombresTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombresTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombresTxtActionPerformed

    private void nombresTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombresTxtKeyTyped
        //Limita la cantidad de caracteres introducidos a 30
        if (nombresTxt.getText().length() >= 30) {
            evt.consume();
        }
    }//GEN-LAST:event_nombresTxtKeyTyped

    private void nroDocumentoTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nroDocumentoTxtKeyTyped
        //Limita la cantidad de caracteres introducidos a 8
        if (nroDocumentoTxt.getText().length() >= 8) {
            evt.consume();
        }
    }//GEN-LAST:event_nroDocumentoTxtKeyTyped

    private void celular1TxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_celular1TxtKeyReleased

    }//GEN-LAST:event_celular1TxtKeyReleased

    private void celular2TxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_celular2TxtKeyTyped
        //Limita la cantidad de caracteres introducidos a 30
        if (celular2Txt.getText().length() >= 30) {
            evt.consume();
        }
    }//GEN-LAST:event_celular2TxtKeyTyped

    private void cant_mascTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cant_mascTxtKeyTyped
        //Limita la cantidad de caracteres introducidos a 3
        if (cant_mascTxt.getText().length() >= 3) {
            evt.consume();
        }
    }//GEN-LAST:event_cant_mascTxtKeyTyped

    private void cuilTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cuilTxtKeyTyped
        //Limita la cantidad de caracteres introducidos a 13
        if (cuilTxt.getText().length() >= 13) {
            evt.consume();
        }
    }//GEN-LAST:event_cuilTxtKeyTyped

    private void domicilioTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_domicilioTxtKeyTyped
        //Limita la cantidad de caracteres introducidos a 60
        if (domicilioTxt.getText().length() >= 60) {
            evt.consume();
        }
    }//GEN-LAST:event_domicilioTxtKeyTyped

    private void emailTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailTxtKeyTyped
        //Limita la cantidad de caracteres introducidos a 60
        if (emailTxt.getText().length() >= 60) {
            evt.consume();
        }
    }//GEN-LAST:event_emailTxtKeyTyped

    private void celular1TxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_celular1TxtKeyTyped
        //Limita la cantidad de caracteres introducidos a 30
        if (celular1Txt.getText().length() >= 30) {
            evt.consume();
        }
    }//GEN-LAST:event_celular1TxtKeyTyped

    private void filtrar2TxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtrar2TxtKeyReleased
        _filtrar(filtrar2Txt.getText().trim());
    }//GEN-LAST:event_filtrar2TxtKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        _cargarCliente();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Texto.TxtCampoMay apellidoTxt;
    private javax.swing.JButton borrarBtn;
    private Texto.TxtNro cant_mascTxt;
    private Texto.TxtNro celular1Txt;
    private Texto.TxtNro celular2Txt;
    private Texto.TxtCuil cuilTxt;
    private Texto.TxtCampoDomicilio domicilioTxt;
    private Texto.TxtCampoEmail emailTxt;
    private com.toedter.calendar.JDateChooser fecha_alta;
    private com.toedter.calendar.JDateChooser fecha_nac;
    private Texto.TxtCampoEmail filtrar2Txt;
    private Texto.TxtNro idTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modificarBtn;
    private Texto.TxtCampoMay nombresTxt;
    private Texto.TxtNro nroDocumentoTxt;
    private javax.swing.JButton nuevoBtn;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlFiltro;
    private javax.swing.JPanel pnlGeneral;
    private javax.swing.JPanel pnlTabla;
    private parcialn3_urban.Reloj reloj1;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JButton vaciarBtn;
    // End of variables declaration//GEN-END:variables
}
