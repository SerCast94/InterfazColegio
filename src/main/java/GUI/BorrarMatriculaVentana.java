package GUI;

import Controlador.Controlador;
import Mapeo.Matricula;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BorrarMatriculaVentana extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JButton btnBorrar = new JButton("Borrar");
    private JButton btnCancelar = new JButton("Cancelar");
    private JLabel lblId = new JLabel("ID Matrícula: ");
    private JLabel lblAlumno = new JLabel("Alumno: ");
    private JLabel lblAsignatura = new JLabel("Asignatura: ");
    private JLabel lblNota = new JLabel("Nota: ");

    private JComboBox<String> cmbId = new JComboBox<>();
    private JTextField txtAlumno = new JTextField(20);
    private JTextField txtAsignatura = new JTextField(20);
    private JTextField txtNota = new JTextField(20);

    public BorrarMatriculaVentana() {
        initGUI();
        initEventos();
    }

    public void initGUI() {
        setTitle("Borrar Matrícula");
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
        agregarComponente(lblId, 0, 0);
        agregarComponente(cmbId, 1, 0);

        agregarComponente(lblAlumno, 0, 1);
        agregarComponente(txtAlumno, 1, 1);
        txtAlumno.setEditable(false); // Campo de solo lectura

        agregarComponente(lblAsignatura, 0, 2);
        agregarComponente(txtAsignatura, 1, 2);
        txtAsignatura.setEditable(false); // Campo de solo lectura

        agregarComponente(lblNota, 0, 3);
        agregarComponente(txtNota, 1, 3);
        txtNota.setEditable(false); // Campo de solo lectura

        // Botones
        gbc.gridwidth = 1;
        agregarComponente(btnBorrar, 0, 4);
        agregarComponente(btnCancelar, 1, 4);

        // Cargar IDs de matrículas en el JComboBox
        cargarIdsMatriculas();
        cargarMatriculaPorDefecto();
        setVisible(true);
    }

    private void agregarComponente(Component comp, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(comp, gbc);
    }

    private void cargarIdsMatriculas() {
        // Obtener la lista de matrículas desde el controlador
        for (Matricula matricula : Controlador.getListaMatriculas()) {
            cmbId.addItem(String.valueOf(matricula.getID()));
        }
    }
    private void cargarMatriculaPorDefecto() {
        String idSeleccionado = (String) cmbId.getSelectedItem();
        Matricula matricula = Controlador.buscarMatriculaPorId(Integer.parseInt(idSeleccionado));

        if (matricula != null) {
            txtAlumno.setText(matricula.getAlumno().getNombre() + " " + matricula.getAlumno().getApellido());
            txtAsignatura.setText(matricula.getAsignatura().getNombre());
            txtNota.setText(String.valueOf(matricula.getNota()));
        }
    }

    public void initEventos() {
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Evento para cargar datos de la matrícula seleccionada
        cmbId.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String idSeleccionado = (String) cmbId.getSelectedItem();
                    Matricula matricula = Controlador.buscarMatriculaPorId(Integer.parseInt(idSeleccionado));

                    if (matricula != null) {
                        txtAlumno.setText(matricula.getAlumno().getNombre() + " " + matricula.getAlumno().getApellido());
                        txtAsignatura.setText(matricula.getAsignatura().getNombre());
                        txtNota.setText(String.valueOf(matricula.getNota()));
                    }
                }
            }
        });

        // Evento para borrar matrícula
        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = (String) cmbId.getSelectedItem();

                // Personalizar los botones
                Object[] opciones = {"Sí", "No"};
                int confirmacion = JOptionPane.showOptionDialog(
                        null,
                        "¿Estás seguro de que deseas borrar esta matrícula?",
                        "Confirmar borrado",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[1]
                );

                if (confirmacion == JOptionPane.YES_OPTION) {
                    try {
                        Controlador.borrarMatricula(Integer.parseInt(id));
                        colegioSalesianos.getInstance().contenidoMatricula.refreshTablaMatricula();
                        colegioSalesianos.getInstance().tableMatricula.repaint();
                        colegioSalesianos.getInstance().tableMatricula.validate();
                        JOptionPane.showMessageDialog(null, "Matrícula borrada correctamente");
                        dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al borrar matrícula: " + ex.getMessage());
                    }
                }
            }
        });
    }
}