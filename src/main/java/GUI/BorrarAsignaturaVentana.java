package GUI;

import Controlador.Controlador;
import Mapeo.Asignatura;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static Controlador.Controlador.borrarAsignatura;

public class BorrarAsignaturaVentana extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JButton btnBorrar = new JButton("Borrar");
    private JButton btnCancelar = new JButton("Cancelar");
    private JLabel lblId = new JLabel("ID Asignatura: ");
    private JLabel lblNombre = new JLabel("Nombre: ");

    private JComboBox<String> cmbId = new JComboBox<>();
    private JTextField txtNombre = new JTextField(20);

    public BorrarAsignaturaVentana() {
        initGUI();
        initEventos();
    }

    public void initGUI() {
        setTitle("Borrar Asignatura");
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
        txtNombre.setEditable(false); // Campo de solo lectura

        // Botones
        gbc.gridwidth = 1;
        agregarComponente(btnBorrar, 0, 2);
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
    private void cargarAsignaturaPorDefecto() {
        if (cmbId.getItemCount() > 0) {
            String idSeleccionado = (String) cmbId.getItemAt(0);
            Asignatura asignatura = Controlador.buscarAsignaturaPorId(Integer.parseInt(idSeleccionado));

            if (asignatura != null) {
                txtNombre.setText(asignatura.getNombre());
            }
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

        // Evento para borrar asignatura
        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = (String) cmbId.getSelectedItem();

                // Personalizar los botones
                Object[] opciones = {"Sí", "No"};
                int confirmacion = JOptionPane.showOptionDialog(
                        null,
                        "¿Estás seguro de que deseas borrar esta asignatura?",
                        "Confirmar borrado",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[1]
                );

                if (confirmacion == JOptionPane.YES_OPTION) {
                    try {
                        borrarAsignatura(Integer.parseInt(id));
                        colegioSalesianos.getInstance().contenidoAsignatura.refreshTablaAsignatura();
                        colegioSalesianos.getInstance().tableAsignatura.repaint();
                        colegioSalesianos.getInstance().tableAsignatura.validate();
                        JOptionPane.showMessageDialog(null, "Asignatura borrada correctamente");
                        dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al borrar asignatura: " + ex.getMessage());
                    }
                }
            }
        });
    }


}