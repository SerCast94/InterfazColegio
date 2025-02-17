package GUI;

import Controlador.Controlador;
import Mapeo.Alumno;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import com.toedter.calendar.JCalendar;

import static Controlador.Controlador.actualizarAlumno;

public class ModificarAlumnoVentana extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JButton btnAceptar = new JButton("Aceptar");
    private JButton btnCancelar = new JButton("Cancelar");

    private JLabel lblId = new JLabel("ID Alumno: ");
    private JLabel lblNombre = new JLabel("Nombre: ");
    private JLabel lblApellido = new JLabel("Apellido: ");
    private JLabel lblTelefono = new JLabel("Teléfono: ");
    private JLabel lblEmail = new JLabel("Email: ");
    private JLabel lblDireccion = new JLabel("Dirección: ");
    private JLabel lblEstado = new JLabel("Estado: ");
    private JLabel lblFecha = new JLabel("Fecha de Nacimiento: ");
    private JLabel lblCarnet = new JLabel("Carnet: ");

    private JComboBox<String> cmbId = new JComboBox<>();
    private JTextField txtNombre = new JTextField(20);
    private JTextField txtApellido = new JTextField(20);
    private JTextField txtTelefono = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JTextField txtDireccion = new JTextField(20);
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"Activo", "Inactivo"});

    private JTextField txtFecha = new JTextField(20);
    private JButton btnCalendario = new JButton("Seleccionar Fecha");
    private JCheckBox chkCarnet = new JCheckBox();

    private Date fechaSeleccionada;

    public ModificarAlumnoVentana() {
        initGUI();
        initEventos();
    }

    public ModificarAlumnoVentana(int filaSeleccionada) {
        initGUI();
        initEventos();
        cmbId.setSelectedIndex(filaSeleccionada);
    }

    public void initGUI() {
        setTitle("Modificar Alumno");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 400);
        setLocationRelativeTo(null);

        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel = this.getContentPane();
        panel.setLayout(gLayout);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Agregar componentes
        agregarComponente(lblId, 0, 0);
        agregarComponente(cmbId, 1, 0);

        agregarComponente(lblNombre, 0, 1);
        agregarComponente(txtNombre, 1, 1);

        agregarComponente(lblApellido, 0, 2);
        agregarComponente(txtApellido, 1, 2);

        agregarComponente(lblTelefono, 0, 3);
        agregarComponente(txtTelefono, 1, 3);

        agregarComponente(lblEmail, 0, 4);
        agregarComponente(txtEmail, 1, 4);

        agregarComponente(lblDireccion, 0, 5);
        agregarComponente(txtDireccion, 1, 5);

        agregarComponente(lblEstado, 0, 6);
        agregarComponente(cmbEstado, 1, 6);

        agregarComponente(lblFecha, 0, 7);
        agregarComponente(txtFecha, 1, 7);
        txtFecha.setEditable(false);
        gbc.gridwidth = 1;
        agregarComponente(btnCalendario, 1, 8);

        agregarComponente(lblCarnet, 0, 9);
        agregarComponente(chkCarnet, 1, 9);

        gbc.gridwidth = 1;
        agregarComponente(btnAceptar, 0, 10);
        agregarComponente(btnCancelar, 1, 10);

        cargarIdsAlumnos();
        cargarAlumnoPorDefecto();

        setVisible(true);
    }

    private void agregarComponente(Component comp, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(comp, gbc);
    }

    private void cargarIdsAlumnos() {
        for (Alumno alumno : Controlador.getListaAlumnos()) {
            cmbId.addItem(String.valueOf(alumno.getID()));
        }
    }

    private void cargarAlumnoPorDefecto() {
        if (cmbId.getItemCount() > 0) {
            String idSeleccionado = (String) cmbId.getSelectedItem();
            Alumno alumno = Controlador.buscarAlumnoPorId(Integer.parseInt(idSeleccionado));

            if (alumno != null) {
                cargarDatosAlumno(alumno);
            }
        }
    }

    private void cargarDatosAlumno(Alumno alumno) {
        txtNombre.setText(alumno.getNombre());
        txtApellido.setText(alumno.getApellido());
        txtTelefono.setText(alumno.getTelefono());
        txtEmail.setText(alumno.getEmail());
        txtDireccion.setText(alumno.getDireccion());
        cmbEstado.setSelectedItem(alumno.getEstado().toString());
        fechaSeleccionada = (Date) alumno.getFecha();
        txtFecha.setText(fechaSeleccionada != null ? fechaSeleccionada.toString() : "");

        chkCarnet.setSelected(alumno.isCarnet());
    }

    public void initEventos() {
        btnCancelar.addActionListener(e -> dispose());

        cmbId.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String idSeleccionado = (String) cmbId.getSelectedItem();
                Alumno alumno = Controlador.buscarAlumnoPorId(Integer.parseInt(idSeleccionado));

                if (alumno != null) {
                    cargarDatosAlumno(alumno);
                }
            }
        });

        btnCalendario.addActionListener(e -> abrirCalendario());

        btnAceptar.addActionListener(e -> {
            String id = (String) cmbId.getSelectedItem();
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String telefono = txtTelefono.getText();
            String email = txtEmail.getText();
            String direccion = txtDireccion.getText();
            String estado = cmbEstado.getSelectedItem().toString();
            boolean carnet = chkCarnet.isSelected();

            Alumno.Estado estadoEnum = estado.equalsIgnoreCase("activo") ? Alumno.Estado.activo : Alumno.Estado.inactivo;

            if (fechaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una fecha de nacimiento.");
                return;
            }

            Alumno alumnoActualizado = new Alumno(nombre, apellido, telefono, email, direccion, fechaSeleccionada, carnet, estadoEnum);
            alumnoActualizado.setID(Integer.parseInt(id));

            try {
                actualizarAlumno(alumnoActualizado);
                JOptionPane.showMessageDialog(null, "Alumno actualizado correctamente");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar alumno: " + ex.getMessage());
            }
        });
    }

    private void abrirCalendario() {
        JDialog calendarioDialog = new JDialog(this, "Seleccionar Fecha", true);
        JCalendar jCalendar = new JCalendar();
        JButton btnAceptarFecha = new JButton("Aceptar");

        calendarioDialog.setLayout(new BorderLayout());
        calendarioDialog.add(jCalendar, BorderLayout.CENTER);
        calendarioDialog.add(btnAceptarFecha, BorderLayout.SOUTH);
        calendarioDialog.setSize(400, 300);
        calendarioDialog.setLocationRelativeTo(this);

        btnAceptarFecha.addActionListener(e -> {
            fechaSeleccionada = new Date(jCalendar.getCalendar().getTimeInMillis());
            txtFecha.setText(fechaSeleccionada.toString());
            calendarioDialog.dispose();
        });

        calendarioDialog.setVisible(true);
    }
}
