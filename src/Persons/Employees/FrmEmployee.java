/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Persons.Employees;

import javax.swing.DefaultComboBoxModel;
import Persons.Employees.Role;
import Persons.Employees.Employee;
import Exceptions.SalaryException;
import Persons.Employees.EmployeeDirectory;
import java.time.LocalDate;
/**
 *
 * @author Valdelomaar
 */
public class FrmEmployee extends javax.swing.JFrame {
private final Persons.Employees.EmployeeDirectory directory = new Persons.Employees.EmployeeDirectory();
    /**
     * Creates new form FrmEmployee
     */
    public FrmEmployee() {
        initComponents();
        cargarRoles();
        
        txtRole.addItemListener(e -> {
    if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
        actualizarUIRol();
    }
});

// Listener único
txtRole.addItemListener(e -> {
    if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
        actualizarUIRol();
    }
});

// Asegura selección inicial y aplica lógica YA
if (txtRole.getItemCount() > 0) {
    txtRole.setSelectedIndex(0); // si ya estaba en 0, no pasa nada
}
actualizarUIRol();

// Y por si el layout aún no está listo, vuelve a aplicar tras el primer paint:
java.awt.EventQueue.invokeLater(this::actualizarUIRol);
    
    
 txtSpeciality.setVisible(false);
    lblSpeciality.setVisible(false);
    lblAviso.setVisible(false);

    txtIdioma.setVisible(false);
    lblIdiomaState.setVisible(false);
    lblAvisoIdioma.setVisible(false);

    // Cambios al seleccionar rol
    txtRole.addItemListener(e -> {
        if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            Role rolSeleccionado = null;
            Object item = e.getItem();

            // Soporta JComboBox<Role> y JComboBox<String>
            if (item instanceof Role) {
                rolSeleccionado = (Role) item;
            } else if (item != null) {
                try {
                    rolSeleccionado = Role.valueOf(item.toString());
                } catch (IllegalArgumentException ex) {
                    rolSeleccionado = null;
                }
            }

            boolean esZookeeper = rolSeleccionado == Role.ZOOKEEPER;
            boolean esGuide     = rolSeleccionado == Role.GUIDE;

            // --- ZOOKEEPER ---
            txtSpeciality.setVisible(esZookeeper);
            lblSpeciality.setVisible(esZookeeper);
            lblAviso.setVisible(esZookeeper);
            if (!esZookeeper) {
                txtSpeciality.setText("");
                lblSpeciality.setText("");
                lblAviso.setText("");
            } else {
                lblSpeciality.setText("✗");
                lblSpeciality.setForeground(java.awt.Color.RED);
                lblAviso.setText("Debes ingresar una especialidad");
                lblAviso.setForeground(java.awt.Color.RED);
            }

            // --- GUIDE ---
            txtIdioma.setVisible(esGuide);
            lblIdiomaState.setVisible(esGuide);
            lblAvisoIdioma.setVisible(esGuide);
            if (!esGuide) {
                txtIdioma.setText("");
                lblIdiomaState.setText("");
                lblAvisoIdioma.setText("");
            } else {
                lblIdiomaState.setText("✗");
                lblIdiomaState.setForeground(java.awt.Color.RED);
                lblAvisoIdioma.setText("Debes ingresar un idioma");
                lblAvisoIdioma.setForeground(java.awt.Color.RED);
            }

            // Refrescar layout
            java.awt.Container parent = txtRole.getParent();
            if (parent != null) {
                parent.revalidate();
                parent.repaint();
            }
        }
    });

    // Validación Especialidad
    txtSpeciality.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusLost(java.awt.event.FocusEvent evt) {
            if (!txtSpeciality.getText().trim().isEmpty()) {
                lblSpeciality.setText("✓");
                lblSpeciality.setForeground(java.awt.Color.GREEN);
                lblAviso.setText("");
            } else {
                lblSpeciality.setText("✗");
                lblSpeciality.setForeground(java.awt.Color.RED);
                lblAviso.setText("Debes ingresar una especialidad");
                lblAviso.setForeground(java.awt.Color.RED);
            }
        }
    });

    txtCedula.addKeyListener(new java.awt.event.KeyAdapter() {

    @Override
    public void keyTyped(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();

        // Solo dígitos; el guion lo ponemos nosotros automáticamente
        if (!Character.isDigit(c)) {
            evt.consume();
            lblCedula.setText("NO SE PUEDE INGRESAR LETRAS");
            lblCedula.setForeground(java.awt.Color.RED);
            return;
        }

        // No permitir más de 9 dígitos
        String digits = txtCedula.getText().replaceAll("\\D", "");
        if (digits.length() >= 9) {
            evt.consume();
        }
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent evt) {
        // Reconstruir a partir de los dígitos que tenga
        String digits = txtCedula.getText().replaceAll("\\D", "");
        if (digits.length() > 9) digits = digits.substring(0, 9);

        String formatted = formatCedula(digits); // X-XXXX-XXXX
        txtCedula.setText(formatted);

        // Mensaje de estado
        if (digits.length() == 9) {
            lblCedula.setText("✓");
            lblCedula.setForeground(java.awt.Color.GREEN);
        } else {
            lblCedula.setText("SOLO SE PUEDE INGRESAR 9 DIGITOS");
            lblCedula.setForeground(java.awt.Color.RED);
        }
    }
});
    
    // Validación Idioma
    txtIdioma.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusLost(java.awt.event.FocusEvent evt) {
            if (!txtIdioma.getText().trim().isEmpty()) {
                lblIdiomaState.setText("✓");
                lblIdiomaState.setForeground(java.awt.Color.GREEN);
                lblAvisoIdioma.setText("");
            } else {
                lblIdiomaState.setText("✗");
                lblIdiomaState.setForeground(java.awt.Color.RED);
                lblAvisoIdioma.setText("Debes ingresar un idioma");
                lblAvisoIdioma.setForeground(java.awt.Color.RED);
            }
        }
    });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtRole = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtCedula = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtFecha = new javax.swing.JFormattedTextField();
        txtSalario = new javax.swing.JTextField();
        lblCedula = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblNumero = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblSalario = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        lblErrorGeneral = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ListaTable = new javax.swing.JTable();
        txtSpeciality = new javax.swing.JTextField();
        lblAviso = new javax.swing.JLabel();
        lblSpeciality = new javax.swing.JLabel();
        lblAvisoIdioma = new javax.swing.JLabel();
        txtIdioma = new javax.swing.JTextField();
        lblIdiomaState = new javax.swing.JLabel();
        lblNumState = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Ingrese el Numero de Cédula*");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Ingrese el Nombre Completo*");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Ingrese el Numero de Telefono*");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Ingrese la Fecha de Nacimiento*");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setText("Ingrese el Salario*");

        txtRole.setToolTipText("INGRESE EL ROL AL QUE PERTENECE");
        txtRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRoleActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel22.setText("AGREGAR EMPLEADO");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setText("Rol del Empleado*");

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/3floppy_unmount (4).png"))); // NOI18N
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/application_exit (4).png"))); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtCedula.setToolTipText("Formato: X-XXXX-XXXX (9 dígitos");
        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });

        txtNombre.setToolTipText("NOMBRE COMPLETO");

        txtFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM))));
        txtFecha.setToolTipText("FECHA DE NACIMIENTO");
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        txtSalario.setToolTipText("SALARIO MAYOR O IGUAL A 3000");
        txtSalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalarioActionPerformed(evt);
            }
        });

        lblCedula.setText("State");

        lblNombre.setText("State");

        lblNumero.setText("State");

        lblFecha.setText("State");

        lblSalario.setText("State");

        lblTipo.setText("State");

        lblErrorGeneral.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblErrorGeneral.setForeground(new java.awt.Color(255, 51, 51));
        lblErrorGeneral.setText("State");

        ListaTable.setBackground(new java.awt.Color(204, 255, 204));
        ListaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(ListaTable);

        txtSpeciality.setToolTipText("INGRESE SU ESPECIALIDAD (MAMIFERO, REPTILES, ETC)");

        lblAviso.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAviso.setText("Ingrese Su Especialidad");

        lblSpeciality.setText("State");

        lblAvisoIdioma.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblAvisoIdioma.setText("Ingrese su Idioma");

        txtIdioma.setToolTipText("INGRESE EL IDIOMA");

        lblIdiomaState.setText("State");

        lblNumState.setText("State");

        txtNumero.setToolTipText("NUMERO DE TELEFONO DE 8 DIGITOS");
        txtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtSalario, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtFecha, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                            .addComponent(jLabel21)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(lblSalario)))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblTipo)))
                                .addGap(79, 79, 79)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtIdioma)
                                            .addComponent(txtSpeciality))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblSpeciality, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblIdiomaState, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblAvisoIdioma)
                                            .addComponent(lblAviso))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(18, 18, 18))
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNumero)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNumState))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18))
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(lblNombre))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblCedula))))
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFecha))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblErrorGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jSeparator3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jSeparator4)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator6)
                            .addComponent(jSeparator5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jSeparator7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(3, 3, 3)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(lblCedula))
                        .addGap(17, 17, 17)
                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(lblNombre))
                        .addGap(18, 18, 18)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(lblNumero)
                            .addComponent(lblNumState))
                        .addGap(18, 18, 18)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(lblFecha))
                        .addGap(14, 14, 14)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lblAvisoIdioma))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblSalario))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblIdiomaState))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(lblSpeciality))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblAviso)
                                    .addComponent(jLabel23)
                                    .addComponent(lblTipo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSpeciality, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(29, 29, 29)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(lblErrorGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(54, 54, 54))))
        );

        jScrollPane1.setViewportView(jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1055, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1049, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 815, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRoleActionPerformed
        txtRole.addItemListener(e -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                Role seleccion = (Role) e.getItem();
                System.out.println("Seleccionaste: " + seleccion);
            }
        });
    }//GEN-LAST:event_txtRoleActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        lblErrorGeneral.setText("");

        if (!validarFormulario()) {
            lblErrorGeneral.setText("Todos los campos son obligatorios");
            lblErrorGeneral.setForeground(java.awt.Color.RED);
            return;
        }

        String id   = txtCedula.getText().trim();
        String name = txtNombre.getText().trim();

        java.time.LocalDate birthDate = validarYObtenerFechaNacimiento();
        if (birthDate == null) return;

        String phone = txtNumero.getText().replaceAll("\\D", "");
if (phone.length() != 8) {
    txtNumero.setText("");
    lblNumState.setText("Debe de ser de 8 dígitos");
    lblNumState.setForeground(java.awt.Color.RED);
    return; // No guarda
} else {
    lblNumState.setText("✓");
    lblNumState.setForeground(java.awt.Color.GREEN);
}

        Double salary;
        try {
            salary = Double.parseDouble(txtSalario.getText().trim());
        } catch (NumberFormatException ex) {
            lblSalario.setText("✗");
            lblSalario.setForeground(java.awt.Color.RED);
            lblErrorGeneral.setText("El salario debe ser numérico");
            lblErrorGeneral.setForeground(java.awt.Color.RED);
            return;
        }

        Persons.Employees.Role role = null;
        Object sel = txtRole.getSelectedItem();
        if (sel instanceof Persons.Employees.Role) {
            role = (Persons.Employees.Role) sel;
        } else if (sel != null) {
            try { role = Persons.Employees.Role.valueOf(sel.toString()); } catch (IllegalArgumentException ignore) {}
        }
        if (role == null) {
            lblTipo.setText("✗");
            lblTipo.setForeground(java.awt.Color.RED);
            lblErrorGeneral.setText("Debe seleccionar un rol");
            lblErrorGeneral.setForeground(java.awt.Color.RED);
            return;
        }

        try {
            if (role == Persons.Employees.Role.ZOOKEEPER) {
                String speciality = txtSpeciality.getText().trim();
                if (speciality.isEmpty()) {
                    lblSpeciality.setText("✗");
                    lblSpeciality.setForeground(java.awt.Color.RED);
                    lblAviso.setText("Debes ingresar una especialidad");
                    lblAviso.setForeground(java.awt.Color.RED);
                    return;
                }
                directory.add(role, id, name, birthDate, phone, salary, speciality);

            } else if (role == Persons.Employees.Role.GUIDE) {
                String idioma = txtIdioma.getText().trim();
                if (idioma.isEmpty()) {
                    lblIdiomaState.setText("✗");
                    lblIdiomaState.setForeground(java.awt.Color.RED);
                    lblAvisoIdioma.setText("Debes ingresar un idioma");
                    lblAvisoIdioma.setForeground(java.awt.Color.RED);
                    return;
                }
                directory.add(role, id, name, birthDate, phone, salary);

            } else {
                directory.add(role, id, name, birthDate, phone, salary);
            }

            directory.refreshTable(ListaTable);
            lblErrorGeneral.setText("Empleado agregado con éxito");
            lblErrorGeneral.setForeground(java.awt.Color.GREEN);
            limpiarFormulario();

        } catch (Exceptions.SalaryException se) {
            lblSalario.setText("✗");
            lblSalario.setForeground(java.awt.Color.RED);
            lblErrorGeneral.setText(se.getMessage());
            lblErrorGeneral.setForeground(java.awt.Color.RED);
        } catch (IllegalArgumentException iae) {
            lblErrorGeneral.setText(iae.getMessage());
            lblErrorGeneral.setForeground(java.awt.Color.RED);
        } catch (Exception ex) {
            lblErrorGeneral.setText("Error: " + ex.getMessage());
            lblErrorGeneral.setForeground(java.awt.Color.RED);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        lblErrorGeneral.setText("");

        // 1) Tomar ID desde la tabla o desde el campo
        String id = null;
        int sel = ListaTable.getSelectedRow();
        if (sel >= 0) {
            Object val = ListaTable.getValueAt(sel, 0); // Columna 0 = ID
            if (val != null) id = val.toString().trim();
        }
        if (id == null || id.isEmpty()) {
            String typed = txtCedula.getText().trim();
            if (!typed.isEmpty()) id = typed;
        }

        // 2) Validar que tengamos un ID
        if (id == null || id.isEmpty()) {
            lblErrorGeneral.setText("Seleccione un empleado en la tabla o ingrese un ID.");
            lblErrorGeneral.setForeground(java.awt.Color.RED);
            return;
        }

        // 3) Eliminar en el directorio (borra de all y de su lista de rol)
        boolean removed = directory.removeById(id);

        if (removed) {
            // 4) Refrescar tabla y feedback
        directory.refreshTable(ListaTable);
        lblErrorGeneral.setText("Empleado eliminado: " + id);
        lblErrorGeneral.setForeground(java.awt.Color.GREEN);

        // Si el formulario mostraba ese mismo ID, límpialo
        if (id.equalsIgnoreCase(txtCedula.getText().trim())) {
            limpiarFormulario();
        }
        } else {
            lblErrorGeneral.setText("No existe un empleado con ese ID.");
            lblErrorGeneral.setForeground(java.awt.Color.RED);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed

    }//GEN-LAST:event_txtCedulaActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        validarYObtenerFechaNacimiento();
    }//GEN-LAST:event_txtFechaActionPerformed

    private void txtSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalarioActionPerformed

    }//GEN-LAST:event_txtSalarioActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        String phone = txtNumero.getText().replaceAll("\\D", "");
        if (phone.length() != 8) {
            txtNumero.setText("");
            lblNumState.setText("Debe de ser de 8 dígitos");
            lblNumState.setForeground(java.awt.Color.RED);
            return; // No guarda
        } else {
            lblNumState.setText("✓");
            lblNumState.setForeground(java.awt.Color.GREEN);
        }
    }//GEN-LAST:event_txtNumeroActionPerformed


    
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
            java.util.logging.Logger.getLogger(FrmEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEmployee().setVisible(true);
            }
        });
    }

    //METODOS
    
    private void actualizarUIRol() {
   Persons.Employees.Role rol = null;
    Object item = txtRole.getSelectedItem();
    if (item instanceof Persons.Employees.Role) {
        rol = (Persons.Employees.Role) item;
    } else if (item != null) {
        try { rol = Persons.Employees.Role.valueOf(item.toString()); } catch (IllegalArgumentException ignore) {}
    }

    boolean esZookeeper = rol == Persons.Employees.Role.ZOOKEEPER;
    boolean esGuide     = rol == Persons.Employees.Role.GUIDE;

    // Zookeeper
    txtSpeciality.setVisible(esZookeeper);
    lblSpeciality.setVisible(esZookeeper);
    lblAviso.setVisible(esZookeeper);
    if (!esZookeeper) {
        txtSpeciality.setText("");
        lblSpeciality.setText("");
        lblAviso.setText("");
    } else {
        lblSpeciality.setText("✗");
        lblSpeciality.setForeground(java.awt.Color.RED);
        lblAviso.setText("Debes ingresar una especialidad");
        lblAviso.setForeground(java.awt.Color.RED);
    }

    // Guide
    txtIdioma.setVisible(esGuide);
    lblIdiomaState.setVisible(esGuide);
    lblAvisoIdioma.setVisible(esGuide);
    if (!esGuide) {
        txtIdioma.setText("");
        lblIdiomaState.setText("");
        lblAvisoIdioma.setText("");
    } else {
        lblIdiomaState.setText("✗");
        lblIdiomaState.setForeground(java.awt.Color.RED);
        lblAvisoIdioma.setText("Debes ingresar un idioma");
        lblAvisoIdioma.setForeground(java.awt.Color.RED);
    }

    // refrescar layout
    java.awt.Container parent = txtRole.getParent();
    if (parent != null) { parent.revalidate(); parent.repaint(); }
    
    }
    
      private void limpiarFormulario() {
    txtCedula.setText("");
    txtNombre.setText("");
    txtNumero.setText("");
    txtFecha.setText("");
    txtSalario.setText("");
    txtSpeciality.setText("");
    txtIdioma.setText("");
    txtRole.setSelectedIndex(-1);

    // Limpiar labels de validación
    lblCedula.setText("");
    lblNombre.setText("");
    lblNumero.setText("");
    lblFecha.setText("");
    lblSalario.setText("");
    lblTipo.setText("");
    lblSpeciality.setText("");
    lblIdiomaState.setText("");
    lblAviso.setText("");
    lblAvisoIdioma.setText("");
}
    
    private boolean validarFormulario() {
    boolean valido = true;
    lblErrorGeneral.setText("");

    // Cédula
    if (txtCedula.getText().trim().isEmpty()) {
        lblCedula.setText("✗");
        lblCedula.setForeground(java.awt.Color.RED);
        valido = false;
    } else {
        lblCedula.setText("✓");
        lblCedula.setForeground(java.awt.Color.GREEN);
    }

    // Nombre
    if (txtNombre.getText().trim().isEmpty()) {
        lblNombre.setText("✗");
        lblNombre.setForeground(java.awt.Color.RED);
        valido = false;
    } else {
        lblNombre.setText("✓");
        lblNombre.setForeground(java.awt.Color.GREEN);
    }

    // Número
    String tel = validarYObtenerTelefono();
    if (tel == null) {
    lblNumero.setText("✗");
    lblNumero.setForeground(java.awt.Color.RED);
    valido = false;
    } else {
    lblNumero.setText("✓");
    lblNumero.setForeground(java.awt.Color.GREEN);
    }

    // Fecha
    if (txtFecha.getText().trim().isEmpty()) {
        lblFecha.setText("✗");
        lblFecha.setForeground(java.awt.Color.RED);
        valido = false;
    } else {
        lblFecha.setText("✓");
        lblFecha.setForeground(java.awt.Color.GREEN);
    }

    // Salario
    if (txtSalario.getText().trim().isEmpty()) {
    lblSalario.setText("✗");
    lblSalario.setForeground(java.awt.Color.RED);
    valido = false;
} else {
    try {
        double salarioValor = Double.parseDouble(txtSalario.getText().trim());
        if (salarioValor < 3000) {
            throw new SalaryException("El valor debe ser mayor o igual a 3000");
        }
        lblSalario.setText("✓");
        lblSalario.setForeground(java.awt.Color.GREEN);
    } catch (SalaryException ex) {
        lblSalario.setText(ex.getMessage());
        lblSalario.setForeground(java.awt.Color.RED);
        valido = false;
    } catch (NumberFormatException ex) {
        lblSalario.setText("Solo se permiten números");
        lblSalario.setForeground(java.awt.Color.RED);
        valido = false;
    }
}

    // Tipo (ComboBox)
    if (txtRole.getSelectedItem() == null) {
        lblTipo.setText("✗");
        lblTipo.setForeground(java.awt.Color.RED);
        valido = false;
    } else {
        lblTipo.setText("✓");
        lblTipo.setForeground(java.awt.Color.GREEN);
    }

    // Mensaje general
    if (!valido) {
        lblErrorGeneral.setText("Todos los campos son obligatorios");
    }

    return valido;
}
    
    private java.time.LocalDate validarYObtenerFechaNacimiento() {
    lblFecha.setText("");
    lblErrorGeneral.setText("");

    try {
        // Usa el mismo formato que configuraste: "d MMM y" (MEDIUM)
        java.text.DateFormat df =
            java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM);
        df.setLenient(false); // NO permitir fechas inexistentes

        java.util.Date fechaUtil = df.parse(txtFecha.getText().trim()); // o txtEmpFecha
        java.time.LocalDate fecha = fechaUtil.toInstant()
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDate();

        java.time.LocalDate hoy = java.time.LocalDate.now();

        if (fecha.isAfter(hoy)) {
            lblFecha.setText("✗");
            lblFecha.setForeground(java.awt.Color.RED);
            lblErrorGeneral.setText("La fecha no puede ser futura");
            return null;
        }

        int edad = java.time.Period.between(fecha, hoy).getYears();
        if (edad < 18) {
            lblFecha.setText("✗");
            lblFecha.setForeground(java.awt.Color.RED);
            lblErrorGeneral.setText("El empleado debe tener al menos 18 años");
            return null;
        }

        lblFecha.setText("✓");
        lblFecha.setForeground(java.awt.Color.GREEN);
        return fecha;

    } catch (java.text.ParseException e) {
        lblFecha.setText("✗");
        lblFecha.setForeground(java.awt.Color.RED);
        lblErrorGeneral.setText("Formato o fecha inválida");
        return null;
    }
}
    
    private String validarYObtenerTelefono() {
     lblNumState.setText("");
    String raw = txtNumero.getText();
    if (raw == null) raw = "";

    // ¿Hay caracteres no numéricos?
    boolean tieneNoDigitos = !raw.matches("\\d*");
    if (tieneNoDigitos) {
        txtNumero.setText("");
        lblNumState.setText("SOLO SE PERMITEN NUMEROS");
        lblNumState.setForeground(java.awt.Color.RED);
        return null;
    }

    // Solo dígitos
    String digits = raw;
    if (digits.length() != 8) {
        // Vacía para forzar reingreso correcto
        txtNumero.setText("");
        lblNumState.setText("SE PERMITEN SOLO 8 NUMEROS");
        lblNumState.setForeground(java.awt.Color.RED);
        return null;
    }

    lblNumState.setText("✓");
    lblNumState.setForeground(java.awt.Color.GREEN);
    return digits; // devuelve los 8 dígitos
}
    
    private static String formatCedula(String digits) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < digits.length(); i++) {
        sb.append(digits.charAt(i));
        if (i == 0 || i == 4) {            // después del 1er y 5to dígito
            if (i != digits.length() - 1)  // evita guion al final
                sb.append('-');
        }
    }
    return sb.toString();
}
    
    private void cargarRoles() {
    DefaultComboBoxModel<Role> model = new DefaultComboBoxModel<>(Role.values());
    txtRole.setModel(model);
    if (model.getSize() > 0) {
        txtRole.setSelectedIndex(0);
    }
    System.out.println("Roles cargados: " + model.getSize());
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ListaTable;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel lblAviso;
    private javax.swing.JLabel lblAvisoIdioma;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblErrorGeneral;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblIdiomaState;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNumState;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblSalario;
    private javax.swing.JLabel lblSpeciality;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JFormattedTextField txtFecha;
    private javax.swing.JTextField txtIdioma;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JComboBox<Persons.Employees.Role> txtRole;
    private javax.swing.JTextField txtSalario;
    private javax.swing.JTextField txtSpeciality;
    // End of variables declaration//GEN-END:variables
}
