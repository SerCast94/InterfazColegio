package GUI;

import Controlador.Controlador;
import Mapeo.Alumno;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;

public class BorrarAlumnoVentana extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JButton btnBorrar = new JButton("Borrar");
    private JButton btnCancelar = new JButton("Cancelar");

    private JLabel lblId = new JLabel("ID Alumno: ");
    private JLabel lblNombre = new JLabel("Nombre: ");
    private JLabel lblApellido = new JLabel("Apellido: ");
    private JLabel lblTelefono = new JLabel("Telefono: ");
    private JLabel lblEmail = new JLabel("Email: ");
    private JLabel lblDireccion = new JLabel("Dirección: ");
    private JLabel lblFecha = new JLabel("Fecha: ");
    private JLabel lblCarnet = new JLabel("Carnet: ");
    private JLabel lblEstado = new JLabel("Estado: ");

    private JComboBox<String> cmbId = new JComboBox<>();
    private JTextField txtNombre = new JTextField(20);
    private JTextField txtApellido = new JTextField(20);
    private JTextField txtTelefono = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JTextField txtDireccion = new JTextField(20);
    private JTextField txtFecha = new JTextField(20);
    private JCheckBox chkCarnet = new JCheckBox("Tiene Carnet");
    private JTextField txtEstado = new JTextField(20);

    public BorrarAlumnoVentana() {
        initGUI();
        initEventos();
    }

    public void initGUI() {
        setTitle("Borrar Alumno");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel = this.getContentPane();
        panel.setLayout(gLayout);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Agregar componentes con restricciones
        agregarComponente(lblId, 0, 0);
        agregarComponente(cmbId, 1, 0);

        agregarComponente(lblNombre, 0, 1);
        agregarComponente(txtNombre, 1, 1);
        txtNombre.setEditable(false);

        agregarComponente(lblApellido, 0, 2);
        agregarComponente(txtApellido, 1, 2);
        txtApellido.setEditable(false);

        agregarComponente(lblTelefono, 0, 3);
        agregarComponente(txtTelefono, 1, 3);
        txtTelefono.setEditable(false);

        agregarComponente(lblEmail, 0, 4);
        agregarComponente(txtEmail, 1, 4);
        txtEmail.setEditable(false);

        agregarComponente(lblDireccion, 0, 5);
        agregarComponente(txtDireccion, 1, 5);
        txtDireccion.setEditable(false);

        agregarComponente(lblFecha, 0, 6);
        agregarComponente(txtFecha, 1, 6);
        txtFecha.setEditable(false);

        agregarComponente(lblCarnet, 0, 7);
        agregarComponente(chkCarnet, 1, 7);
        chkCarnet.setEnabled(false);

        agregarComponente(lblEstado, 0, 8);
        agregarComponente(txtEstado, 1, 8);
        txtEstado.setEditable(false);

        // Botones
        gbc.gridwidth = 1;
        agregarComponente(btnBorrar, 0, 9);
        agregarComponente(btnCancelar, 1, 9);

        // Cargar IDs de alumnos en el JComboBox
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
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                txtNombre.setText(alumno.getNombre());
                txtApellido.setText(alumno.getApellido());
                txtTelefono.setText(alumno.getTelefono());
                txtEmail.setText(alumno.getEmail());
                txtDireccion.setText(alumno.getDireccion());
                txtFecha.setText(sdf.format(alumno.getFecha()));
                chkCarnet.setSelected(alumno.isCarnet());
                txtEstado.setText(alumno.getEstado().toString());
            }
        }
    }

    public void initEventos() {
        btnCancelar.addActionListener(e -> dispose());

        // Evento para cargar datos del alumno seleccionado
        cmbId.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String idSeleccionado = (String) cmbId.getSelectedItem();
                    Alumno alumno = Controlador.buscarAlumnoPorId(Integer.parseInt(idSeleccionado));

                    if (alumno != null) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                        txtNombre.setText(alumno.getNombre());
                        txtApellido.setText(alumno.getApellido());
                        txtTelefono.setText(alumno.getTelefono());
                        txtEmail.setText(alumno.getEmail());
                        txtDireccion.setText(alumno.getDireccion());
                        txtFecha.setText(sdf.format(alumno.getFecha()));
                        chkCarnet.setSelected(alumno.isCarnet());
                        txtEstado.setText(alumno.getEstado().toString());
                    }
                }
            }
        });

        // Evento para borrar alumno
        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = (String) cmbId.getSelectedItem();
                Object[] opciones = {"Sí", "No"};
                int confirmacion = JOptionPane.showOptionDialog(
                        null,
                        "¿Estás seguro de que deseas borrar este alumno?",
                        "Confirmar borrado",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[1]
                );

                if (confirmacion == JOptionPane.YES_OPTION) {
                    try {
                        Controlador.borrarAlumno(Integer.parseInt(id));
                        JOptionPane.showMessageDialog(null, "Alumno borrado correctamente");
                        dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al borrar alumno: " + ex.getMessage());
                    }
                }
            }
        });
    }
}
