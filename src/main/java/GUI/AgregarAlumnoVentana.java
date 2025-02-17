package GUI;

import Controlador.Controlador;
import Mapeo.Alumno;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import com.toedter.calendar.JCalendar;

public class AgregarAlumnoVentana extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JButton btnAceptar = new JButton("Aceptar");
    private JButton btnCancelar = new JButton("Cancelar");

    private JLabel lblNombre = new JLabel("Nombre: ");
    private JLabel lblApellido = new JLabel("Apellido: ");
    private JLabel lblTelefono = new JLabel("Teléfono: ");
    private JLabel lblEmail = new JLabel("Email: ");
    private JLabel lblDireccion = new JLabel("Dirección: ");
    private JLabel lblEstado = new JLabel("Estado: ");
    private JLabel lblTerminos = new JLabel("Acepto los términos y condiciones");

    // Nuevo: Fecha de nacimiento y carnet
    private JLabel lblFecha = new JLabel("Fecha de nacimiento: ");
    private JTextField txtFecha = new JTextField(20);
    private JButton btnCalendario = new JButton("Seleccionar Fecha");
    private JLabel lblCarnet = new JLabel("Carnet:");
    private JCheckBox chkCarnet = new JCheckBox();

    private JTextField txtNombre = new JTextField(20);
    private JTextField txtApellido = new JTextField(20);
    private JTextField txtTelefono = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JTextField txtDireccion = new JTextField(20);
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"Activo", "Inactivo"});
    private JCheckBox chkTerminos = new JCheckBox();

    private Date fechaSeleccionada;

    public AgregarAlumnoVentana() {
        initGUI();
        initEventos();
    }

    public void initGUI() {
        setTitle("Agregar Alumno");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 400); // Ajustamos el tamaño
        setLocationRelativeTo(null);

        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel = this.getContentPane();
        panel.setLayout(gLayout);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Agregar componentes
        agregarComponente(lblNombre, 0, 0);
        agregarComponente(txtNombre, 1, 0);

        agregarComponente(lblApellido, 0, 1);
        agregarComponente(txtApellido, 1, 1);

        agregarComponente(lblTelefono, 0, 2);
        agregarComponente(txtTelefono, 1, 2);

        agregarComponente(lblEmail, 0, 3);
        agregarComponente(txtEmail, 1, 3);

        agregarComponente(lblDireccion, 0, 4);
        agregarComponente(txtDireccion, 1, 4);

        agregarComponente(lblEstado, 0, 5);
        agregarComponente(cmbEstado, 1, 5);

        agregarComponente(lblFecha, 0, 6);
        agregarComponente(txtFecha, 1, 6);
        txtFecha.setEditable(false);
        gbc.gridwidth = 1;
        agregarComponente(btnCalendario, 1, 7);

        agregarComponente(lblCarnet, 0, 8);
        agregarComponente(chkCarnet, 1, 8);

        agregarComponente(lblTerminos, 0, 9);
        agregarComponente(chkTerminos, 1, 9);

        gbc.gridwidth = 1;
        agregarComponente(btnAceptar, 0, 10);
        agregarComponente(btnCancelar, 1, 10);

        btnAceptar.setEnabled(false);
        setVisible(true);
    }

    private void agregarComponente(Component comp, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(comp, gbc);
    }

    public void initEventos() {
        btnCancelar.addActionListener(e -> dispose());

        chkTerminos.addActionListener(e -> btnAceptar.setEnabled(chkTerminos.isSelected()));

        btnCalendario.addActionListener(e -> abrirCalendario());

        btnAceptar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String telefono = txtTelefono.getText();
            String email = txtEmail.getText();
            String direccion = txtDireccion.getText();
            String estadoStr = (String) cmbEstado.getSelectedItem();
            boolean carnet = chkCarnet.isSelected(); // Checkbox para carnet

            Alumno.Estado estado = estadoStr.equalsIgnoreCase("activo") ? Alumno.Estado.activo : Alumno.Estado.inactivo;

            if (fechaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una fecha de nacimiento.");
                return;
            }

            Alumno nuevoAlumno = new Alumno(nombre, apellido, telefono, email, direccion, fechaSeleccionada, carnet, estado);

            try {
                Controlador.anadirAlumno(nuevoAlumno);
                JOptionPane.showMessageDialog(null, "Alumno creado correctamente");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
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
