package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarAlumnoVentana extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JButton btnAceptar = new JButton("Aceptar");
    private JButton btnCancelar = new JButton("Cancelar");
    private JLabel lblNombre = new JLabel("Nombre: ");
    private JLabel lblApellido = new JLabel("Apellido: ");
    private JLabel lblTelefono = new JLabel("Telefono: ");
    private JLabel lblEmail = new JLabel("Email: ");
    private JLabel lblDireccion = new JLabel("Dirección: ");
    private JLabel lblEstado = new JLabel("Estado: ");

    private JTextField txtNombre = new JTextField(20);
    private JTextField txtApellido = new JTextField(20);
    private JTextField txtTelefono = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JTextField txtDireccion = new JTextField(20);
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"Activo", "Inactivo"});

    public AgregarAlumnoVentana() {
        initGUI();
        initEventos();
    }

    public void initGUI() {
        setTitle("Agregar Alumno");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel = this.getContentPane();
        panel.setLayout(gLayout);

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Agregar componentes con restricciones
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

        // Botones
        gbc.gridwidth = 1;
        agregarComponente(btnAceptar, 0, 6);
        agregarComponente(btnCancelar, 1, 6);

        setVisible(true);
    }

    private void agregarComponente(Component comp, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(comp, gbc);
    }

    public void initEventos() {
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new AgregarAlumnoVentana();
    }
}