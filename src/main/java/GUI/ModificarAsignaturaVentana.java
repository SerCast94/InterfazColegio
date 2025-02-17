package GUI;

import Controlador.Controlador;
import Mapeo.Asignatura;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static Controlador.Controlador.actualizarAsignatura;

public class ModificarAsignaturaVentana extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JButton btnAceptar = new JButton("Aceptar");
    private JButton btnCancelar = new JButton("Cancelar");
    private JLabel lblId = new JLabel("ID Asignatura: ");
    private JLabel lblNombre = new JLabel("Nombre: ");

    private JComboBox<String> cmbId = new JComboBox<>();
    private JTextField txtNombre = new JTextField(20);

    public ModificarAsignaturaVentana() {
        initGUI();
        initEventos();
    }

    public void initGUI() {
        setTitle("Actualizar Asignatura");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
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

        // Botones
        gbc.gridwidth = 1;
        agregarComponente(btnAceptar, 0, 2);
        agregarComponente(btnCancelar, 1, 2);

        // Cargar IDs de asignaturas en el JComboBox
        cargarIdsAsignaturas();
        cargarAsignaturaPorDefecto();
        setVisible(true);
    }

    private void agregarComponente(Component comp, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(comp, gbc);
    }

    private void cargarIdsAsignaturas() {
        // Obtener la lista de asignaturas desde el controlador
        for (Asignatura asignatura : Controlador.getListaAsignaturas()) {
            cmbId.addItem(String.valueOf(asignatura.getId()));
        }
    }

    private void cargarAsignaturaPorDefecto(){
        String idSeleccionado = (String) cmbId.getSelectedItem();
        Asignatura asignatura = Controlador.buscarAsignaturaPorId(Integer.parseInt(idSeleccionado));

        if (asignatura != null) {
            txtNombre.setText(asignatura.getNombre());
        }
    }

    public void initEventos() {
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Evento para cargar datos de la asignatura seleccionada
        cmbId.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String idSeleccionado = (String) cmbId.getSelectedItem();
                    Asignatura asignatura = Controlador.buscarAsignaturaPorId(Integer.parseInt(idSeleccionado));

                    if (asignatura != null) {
                        txtNombre.setText(asignatura.getNombre());
                    }
                }
            }
        });

        // Evento para actualizar asignatura
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = (String) cmbId.getSelectedItem();
                String nombre = txtNombre.getText();

                Asignatura asignaturaActualizada = new Asignatura(nombre);
                asignaturaActualizada.setId(Integer.parseInt(id));

                try {
                    actualizarAsignatura(asignaturaActualizada);
                    colegioSalesianos.getInstance().contenidoAsignatura.refreshTablaAsignatura();
                    colegioSalesianos.getInstance().tableAsignatura.repaint();
                    colegioSalesianos.getInstance().tableAsignatura.validate();

                    JOptionPane.showMessageDialog(null, "Asignatura actualizada correctamente");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar asignatura: " + ex.getMessage());
                }
            }
        });
    }

}