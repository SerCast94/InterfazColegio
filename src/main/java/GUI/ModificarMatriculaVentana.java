package GUI;

import Controlador.Controlador;
import Mapeo.Matricula;
import Mapeo.Alumno;
import Mapeo.Asignatura;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ModificarMatriculaVentana extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JButton btnAceptar = new JButton("Aceptar");
    private JButton btnCancelar = new JButton("Cancelar");
    private JLabel lblId = new JLabel("ID Matrícula: ");
    private JLabel lblAlumno = new JLabel("Alumno: ");
    private JLabel lblAsignatura = new JLabel("Asignatura: ");
    private JLabel lblNota = new JLabel("Nota: ");

    private JComboBox<String> cmbId = new JComboBox<>();
    private JComboBox<Alumno> cmbAlumno = new JComboBox<>();
    private JComboBox<Asignatura> cmbAsignatura = new JComboBox<>();
    private JTextField txtNota = new JTextField(20);

    public ModificarMatriculaVentana() {
        initGUI();
        initEventos();
    }

    public void initGUI() {
        setTitle("Actualizar Matrícula");
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
        agregarComponente(cmbAlumno, 1, 1);

        agregarComponente(lblAsignatura, 0, 2);
        agregarComponente(cmbAsignatura, 1, 2);

        agregarComponente(lblNota, 0, 3);
        agregarComponente(txtNota, 1, 3);

        // Botones
        gbc.gridwidth = 1;
        agregarComponente(btnAceptar, 0, 4);
        agregarComponente(btnCancelar, 1, 4);

        // Cargar IDs de matrículas en el JComboBox
        cargarIdsMatriculas();
        // Cargar alumnos y asignaturas en los JComboBox
        cargarAlumnosYAsignaturas();
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
            cmbAlumno.setSelectedItem(matricula.getAlumno());
            cmbAsignatura.setSelectedItem(matricula.getAsignatura());
            txtNota.setText(String.valueOf(matricula.getNota()));
        }
    }

    private void cargarAlumnosYAsignaturas() {
        // Obtener la lista de alumnos y asignaturas desde el controlador
        for (Alumno alumno : Controlador.getListaAlumnos()) {
            cmbAlumno.addItem(alumno);
        }
        for (Asignatura asignatura : Controlador.getListaAsignaturas()) {
            cmbAsignatura.addItem(asignatura);
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
                        cmbAlumno.setSelectedItem(matricula.getAlumno());
                        cmbAsignatura.setSelectedItem(matricula.getAsignatura());
                        txtNota.setText(String.valueOf(matricula.getNota()));
                    }
                }
            }
        });

        // Evento para actualizar matrícula
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = (String) cmbId.getSelectedItem();
                Alumno alumno = (Alumno) cmbAlumno.getSelectedItem();
                Asignatura asignatura = (Asignatura) cmbAsignatura.getSelectedItem();
                double nota = Double.parseDouble(txtNota.getText());

                Matricula matriculaActualizada = new Matricula(alumno, asignatura, nota);
                matriculaActualizada.setID(Integer.parseInt(id));

                try {
                    Controlador.actualizarMatricula(matriculaActualizada);
                    colegioSalesianos.getInstance().contenidoMatricula.refreshTablaMatricula();
                    colegioSalesianos.getInstance().tableMatricula.repaint();
                    colegioSalesianos.getInstance().tableMatricula.validate();
                    JOptionPane.showMessageDialog(null, "Matrícula actualizada correctamente");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar matrícula: " + ex.getMessage());
                }
            }
        });
    }

}