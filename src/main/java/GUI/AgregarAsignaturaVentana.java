package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarAsignaturaVentana extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JButton btnAceptar = new JButton("Aceptar");
    private JButton btnCancelar = new JButton("Cancelar");
    private JLabel lblNombreAsignatura = new JLabel("Nombre de la Asignatura: ");
    private JTextField txtNombreAsignatura = new JTextField(20);

    public AgregarAsignaturaVentana() {
        initGUI();
        initEventos();
    }

    public void initGUI() {
        setTitle("Agregar Asignatura");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 150);
        setLocationRelativeTo(null);

        gLayout = new GridBagLayout();
        gbc = new GridBagConstraints();
        panel = this.getContentPane();
        panel.setLayout(gLayout);

        gbc.insets = new Insets(5, 5, 5, 5); // Margen entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Agregar componentes con restricciones
        agregarComponente(lblNombreAsignatura, 0, 0);
        agregarComponente(txtNombreAsignatura, 1, 0);

        // Asegurar que los botones tengan el mismo tamaño
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        agregarComponente(btnAceptar, 0, 1);
        agregarComponente(btnCancelar, 1, 1);

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
        new AgregarAsignaturaVentana();
    }
}
