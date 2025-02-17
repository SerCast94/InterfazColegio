package GUI;

import Controlador.Controlador;
import Mapeo.Alumno;
import Mapeo.Asignatura;
import Mapeo.Matricula;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import static BD.Conexion.insertMatricula;

public class AgregarMatriculaVentana extends JFrame {
    private Container panel;
    private GridBagLayout gLayout;
    private GridBagConstraints gbc;

    private JButton btnAceptar = new JButton("Aceptar");
    private JButton btnCancelar = new JButton("Cancelar");
    private JLabel lblIdAlumno = new JLabel("Alumno: ");
    private JLabel lblIdAsignatura = new JLabel("Asignatura: ");
    private JLabel lblNota = new JLabel("Nota: ");

    private JComboBox<Alumno> cmbAlumnos = new JComboBox<>();
    private JComboBox<Asignatura> cmbAsignaturas = new JComboBox<>();
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
        agregarComponente(cmbAlumnos, 1, 0);

        agregarComponente(lblIdAsignatura, 0, 1);
        agregarComponente(cmbAsignaturas, 1, 1);

        agregarComponente(lblNota, 0, 2);
        agregarComponente(txtNota, 1, 2);

        // Asegurar que los botones tengan el mismo tamaño
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        agregarComponente(btnAceptar, 0, 3);
        agregarComponente(btnCancelar, 1, 3);
        cargarComboBoxes();
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
        // Acción de cancelar (cerrar ventana)
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Acción de aceptar (agregar matrícula)
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Alumno alumnoSeleccionado = (Alumno) cmbAlumnos.getSelectedItem();
                Asignatura asignaturaSeleccionada = (Asignatura) cmbAsignaturas.getSelectedItem();
                double nota = Double.parseDouble(txtNota.getText());

                if (alumnoSeleccionado == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona un alumno.");
                    return;
                }
                if (asignaturaSeleccionada == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona una asignatura.");
                    return;
                }

                Matricula nuevaMatricula = new Matricula(alumnoSeleccionado, asignaturaSeleccionada, nota);
                try {
                    Controlador.anadirMatricula(nuevaMatricula);
                    colegioSalesianos.getInstance().contenidoMatricula.refreshTablaMatricula();
                    colegioSalesianos.getInstance().tableMatricula.repaint();
                    colegioSalesianos.getInstance().tableMatricula.validate();
                    JOptionPane.showMessageDialog(null, "Matrícula añadida correctamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

    }

    // Método para cargar los ComboBox con los datos de Alumnos y Asignaturas
    public void cargarComboBoxes() {
        // Cargar lista de alumnos
        List<Alumno> listaAlumnos = Controlador.getListaAlumnos();
        for (Alumno alumno : listaAlumnos) {
            cmbAlumnos.addItem(alumno); // Agregar cada alumno al ComboBox
        }

        // Cargar lista de asignaturas
        List<Asignatura> listaAsignaturas = Controlador.getListaAsignaturas();
        for (Asignatura asignatura : listaAsignaturas) {
            cmbAsignaturas.addItem(asignatura); // Agregar cada asignatura al ComboBox
        }
    }

}
