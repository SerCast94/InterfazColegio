package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarMatriculaVentana extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JButton btnAceptar = new JButton("Aceptar");
    private JButton btnCancelar = new JButton("Cancelar");
    private JLabel lblIdAlumno = new JLabel("ID Alumno: ");
    private JLabel lblIdAsignatura = new JLabel("ID Asignatura: ");
    private JLabel lblNota = new JLabel("Nota: ");

    private JTextField txtIdAlumno = new JTextField(20);
    private JTextField txtIdAsignatura = new JTextField(20);
    private JTextField txtNota = new JTextField(20);

    public AgregarMatriculaVentana() {
        initGUI();
        initEventos();
    }

    public void initGUI() {
        setTitle("Agregar Matrícula");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel = this.getContentPane();
        panel.setLayout(gLayout);

        gbc.insets = new Insets(5, 5, 5, 5); // Margen entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Agregar componentes con restricciones
        agregarComponente(lblIdAlumno, 0, 0);
        agregarComponente(txtIdAlumno, 1, 0);

        agregarComponente(lblIdAsignatura, 0, 1);
        agregarComponente(txtIdAsignatura, 1, 1);

        agregarComponente(lblNota, 0, 2);
        agregarComponente(txtNota, 1, 2);

        // Asegurar que los botones tengan el mismo tamaño
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        agregarComponente(btnAceptar, 0, 3);
        agregarComponente(btnCancelar, 1, 3);

        setVisible(true);
    }

    private void agregarComponente(Component comp, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
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
        new AgregarMatriculaVentana();
    }
}
