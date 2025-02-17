package GUI;

import Controlador.Controlador;
import Mapeo.Alumno;
import Mapeo.Asignatura;
import Mapeo.Matricula;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static BD.Conexion.insertMatricula;

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

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idAlumno = txtIdAlumno.getText();
                String idAsignatura = txtIdAsignatura.getText();
                double nota = Double.parseDouble(txtNota.getText());

                Alumno alumnoEncontrado = null;
                Asignatura asignaturaEncontrado = null;
                boolean encontrado = false;
                boolean encontrado2 = false;

                List<Alumno> listaAlumnos = colegioSalesianos.getInstance().contenidoAlumno.getAlumnos();
                for(Alumno alumnoBusqueda : listaAlumnos) {
                    if (alumnoBusqueda.getID() == Integer.parseInt(idAlumno)) {
                        alumnoEncontrado = alumnoBusqueda;
                        encontrado = true;
                    }
                }
                if (alumnoEncontrado == null) JOptionPane.showMessageDialog(null, "No se encontro el alumno");

                List<Asignatura> listaAsignaturas = colegioSalesianos.getInstance().contenidoAsignatura.getAsignaturas();
                for(Asignatura asignaturaBusqueda : listaAsignaturas){
                    if(asignaturaBusqueda.getId() == Integer.parseInt(idAsignatura)) {
                        asignaturaEncontrado = asignaturaBusqueda;
                        encontrado2 = true;
                    }
                }
                if(asignaturaEncontrado == null) JOptionPane.showMessageDialog(null, "No se encontro el asignatura");

                if(encontrado && encontrado2){
                    Matricula nuevaMatricula = new Matricula(alumnoEncontrado,asignaturaEncontrado,nota);
                    try {
                        Controlador.anadirMatricula(nuevaMatricula);
                        colegioSalesianos.getInstance().contenidoMatricula.refreshTablaMatricula();
                        colegioSalesianos.getInstance().tableMatricula.repaint();
                        colegioSalesianos.getInstance().tableMatricula.validate();

                    }catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }else JOptionPane.showMessageDialog(null, "No se ha podido agregar la matricula");

            }
        });
    }
}
