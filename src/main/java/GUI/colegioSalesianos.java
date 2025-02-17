package GUI;

import BD.Conexion;
import Controlador.Controlador;
import Mapeo.Alumno;
import Mapeo.Asignatura;
import Mapeo.Matricula;
import org.hibernate.Session;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static BD.Conexion.*;

public class colegioSalesianos extends JFrame {
    private static colegioSalesianos colegio;
    Dimension screenSize;
    JMenuBar menuBar;
    JMenu menuAlumnos;
    JMenuItem itemAgregarAlumno;
    JMenu menuAsignaturas;
    JMenuItem itemAgregarAsignatura;
    JMenu menuMatriculas;
    JMenuItem itemAgregarMatricula;
    JMenuItem itemActualizarAlumnos;
    JMenuItem itemActualizarAsignaturas;
    JMenuItem itemActualizarMatriculas;
    JMenuItem itemBorrarAlumnos;
    JMenuItem itemBorrarAsignaturas;
    JMenuItem itemBorrarMatriculas;
    JTabbedPane tabbedPane;
    JPanel panelAlumno;
    JPanel panelAsignatura;
    JPanel panelMatricula;
    JScrollPane scrollPaneAlumno;
    JScrollPane scrollPaneAsignatura;
    JScrollPane scrollPaneMatricula;
    JTable tableAlumno;
    JTable tableAsignatura;
    JTable tableMatricula;
    ContenidoTablaAlumno contenidoAlumno;
    ContenidoTablaAsignatura contenidoAsignatura;
    ContenidoTablaMatricula contenidoMatricula;
    Controlador controlador;

    public colegioSalesianos() {
        colegio = this;
        controlador = new Controlador(crearConexion());
        initGUI();
        initEventos();
    }

    public static colegioSalesianos getInstance() {

        return colegio;
    }

    void initGUI() {
        // inicializar
        setTitle("Salesianos Córdoba");
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // barra de menú
        menuBar = new JMenuBar();
        menuAlumnos = new JMenu("Alumnos");
        itemAgregarAlumno = new JMenuItem("Agregar Alumno");
        menuAlumnos.add(itemAgregarAlumno);
        itemActualizarAlumnos = new JMenuItem("Actualizar Alumnos");
        menuAlumnos.add(itemActualizarAlumnos);
        itemBorrarAlumnos = new JMenuItem("Borrar Alumnos");
        menuAlumnos.add(itemBorrarAlumnos);

        menuAsignaturas = new JMenu("Asignaturas");
        itemAgregarAsignatura = new JMenuItem("Agregar Asignatura");
        menuAsignaturas.add(itemAgregarAsignatura);
        itemActualizarAsignaturas = new JMenuItem("Actualizar Asignaturas");
        menuAsignaturas.add(itemActualizarAsignaturas);
        itemBorrarAsignaturas = new JMenuItem("Borrar Asignaturas");
        menuAsignaturas.add(itemBorrarAsignaturas);

        menuMatriculas = new JMenu("Matrículas");
        itemAgregarMatricula = new JMenuItem("Agregar Matrícula");
        menuMatriculas.add(itemAgregarMatricula);
        itemActualizarMatriculas = new JMenuItem("Actualizar Matrículas");
        menuMatriculas.add(itemActualizarMatriculas);
        itemBorrarMatriculas = new JMenuItem("Borrar Matrículas");
        menuMatriculas.add(itemBorrarMatriculas);

        menuBar.add(menuAlumnos);
        menuBar.add(menuAsignaturas);
        menuBar.add(menuMatriculas);
        setJMenuBar(menuBar);

        // menú de pestañas
        tabbedPane = new JTabbedPane();

        // panel de alumnos
        panelAlumno = new JPanel();
        panelAlumno.setLayout(new BorderLayout());
        contenidoAlumno = new ContenidoTablaAlumno();
        tableAlumno = new JTable(contenidoAlumno);
        scrollPaneAlumno = new JScrollPane(tableAlumno);
        panelAlumno.add(scrollPaneAlumno);
        tabbedPane.addTab("Alumno", new ImageIcon("src/main/resources/img/alumno.png"), panelAlumno);

        // panel de asignatura
        panelAsignatura = new JPanel();
        panelAsignatura.setLayout(new BorderLayout());
        contenidoAsignatura = new ContenidoTablaAsignatura();
        tableAsignatura = new JTable(contenidoAsignatura);
        scrollPaneAsignatura = new JScrollPane(tableAsignatura);
        panelAsignatura.add(scrollPaneAsignatura);
        tabbedPane.addTab("Asignatura", new ImageIcon("src/main/resources/img/asignatura.png"), panelAsignatura);

        // panel de matrícula
        panelMatricula = new JPanel();
        panelMatricula.setLayout(new BorderLayout());
        contenidoMatricula = new ContenidoTablaMatricula();
        tableMatricula = new JTable(contenidoMatricula);
        scrollPaneMatricula = new JScrollPane(tableMatricula);
        panelMatricula.add(scrollPaneMatricula);
        tabbedPane.addTab("Matrícula", new ImageIcon("src/main/resources/img/matricula.png"), panelMatricula);

        add(tabbedPane);
        setVisible(true);
    }

    void initEventos() {
        itemAgregarAlumno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarAlumnoVentana();
            }
        });

        itemAgregarAsignatura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarAsignaturaVentana();
            }
        });

        itemAgregarMatricula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarMatriculaVentana();
            }
        });
        itemActualizarAlumnos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {new ModificarAlumnoVentana();
            }
        });
        itemActualizarAsignaturas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModificarAsignaturaVentana();
            }
        });
        itemActualizarMatriculas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModificarMatriculaVentana();
            }
        });
        itemBorrarAlumnos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BorrarAlumnoVentana();
            }
        });
        itemBorrarAsignaturas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BorrarAsignaturaVentana();
            }
        });
        itemBorrarMatriculas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BorrarMatriculaVentana();
            }
        });


    }

}
