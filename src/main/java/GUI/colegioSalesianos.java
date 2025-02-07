package GUI;

import BD.Conexion;
import Mapeo.Alumno;
import Mapeo.Asignatura;
import Mapeo.Matricula;
import org.hibernate.Session;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static BD.Conexion.*;

public class colegioSalesianos extends JFrame {
    Dimension screenSize;
    JMenuBar menuBar;
    JMenu menuAlumnos;
    JMenuItem itemAgregarAlumno;
    JMenu menuAsignaturas;
    JMenuItem itemAgregarAsignatura;
    JMenu menuMatriculas;
    JMenuItem itemAgregarMatricula;
    JToolBar toolBar;
    JButton btnAgregar;
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
    Session sesion;

    public colegioSalesianos() {
        sesion = Conexion.crearConexion();
        initGUI();
        initEventos();
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

        menuAsignaturas = new JMenu("Asignaturas");
        itemAgregarAsignatura = new JMenuItem("Agregar Asignatura");
        menuAsignaturas.add(itemAgregarAsignatura);

        menuMatriculas = new JMenu("Matrículas");
        itemAgregarMatricula = new JMenuItem("Agregar Matrícula");
        menuMatriculas.add(itemAgregarMatricula);

        menuBar.add(menuAlumnos);
        menuBar.add(menuAsignaturas);
        menuBar.add(menuMatriculas);
        setJMenuBar(menuBar);

        // barra de herramientas
        toolBar = new JToolBar();
        btnAgregar = new JButton(new ImageIcon("src/main/resources/img/notas.png"));
        toolBar.add(btnAgregar);
        add(toolBar, BorderLayout.NORTH);

        // menú de pestañas
        tabbedPane = new JTabbedPane();

        // panel de alumnos
        panelAlumno = new JPanel();
        panelAlumno.setLayout(new BorderLayout());
        tableAlumno = new JTable(contenidoTablaAlumno(sesion));
        scrollPaneAlumno = new JScrollPane(tableAlumno);
        panelAlumno.add(scrollPaneAlumno);
        tabbedPane.addTab("Alumno", new ImageIcon("src/main/resources/img/alumno.png"), panelAlumno);

        // panel de asignatura
        panelAsignatura = new JPanel();
        panelAsignatura.setLayout(new BorderLayout());
        tableAsignatura = new JTable(contenidoTablaAsignatura(sesion));
        scrollPaneAsignatura = new JScrollPane(tableAsignatura);
        panelAsignatura.add(scrollPaneAsignatura);
        tabbedPane.addTab("Asignatura", new ImageIcon("src/main/resources/img/asignatura.png"), panelAsignatura);

        // panel de matrícula
        panelMatricula = new JPanel();
        panelMatricula.setLayout(new BorderLayout());
        tableMatricula = new JTable(contenidoTablaMatricula(sesion));
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
    }

    static TableModel contenidoTablaAlumno(Session sesion) {
        List<Alumno> listaAlumnos = mostrarAlumnos(sesion);

        String[] columnNamesAlumno = {"Nombre", "Apellido", "Teléfono", "Email", "Dirección", "Estado"};
        Object[][] dataNotas = new Object[listaAlumnos.size()][6];

        for (int i = 0; i < listaAlumnos.size(); i++) {
            Alumno alumno = listaAlumnos.get(i);
            dataNotas[i][0] = alumno.getNombre();
            dataNotas[i][1] = alumno.getApellido();
            dataNotas[i][2] = alumno.getTelefono();
            dataNotas[i][3] = alumno.getEmail();
            dataNotas[i][4] = alumno.getDireccion();
            dataNotas[i][5] = alumno.getEstado();
        }

        DefaultTableModel tableModel = new DefaultTableModel(dataNotas, columnNamesAlumno) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        return tableModel;
    }

    public static TableModel contenidoTablaAsignatura(Session sesion) {
        List<Asignatura> listaAsignaturas = mostrarAsignaturas(sesion);

        String[] columnNamesAsignatura = {"ID", "Nombre"};
        Object[][] dataAsignaturas = new Object[listaAsignaturas.size()][2];

        for (int i = 0; i < listaAsignaturas.size(); i++) {
            Asignatura asignatura = listaAsignaturas.get(i);
            dataAsignaturas[i][0] = asignatura.getId();
            dataAsignaturas[i][1] = asignatura.getNombre();
        }

        DefaultTableModel tableModel = new DefaultTableModel(dataAsignaturas, columnNamesAsignatura) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        return tableModel;
    }

    public static TableModel contenidoTablaMatricula(Session sesion) {
        List<Matricula> listaMatriculas = mostrarMatriculas(sesion);

        String[] columnNamesMatricula = {"ID", "Alumno", "Asignatura", "Nota"};
        Object[][] dataMatriculas = new Object[listaMatriculas.size()][4];

        for (int i = 0; i < listaMatriculas.size(); i++) {
            Matricula matricula = listaMatriculas.get(i);
            dataMatriculas[i][0] = matricula.getID();
            dataMatriculas[i][1] = matricula.getAlumno().getNombre() + " " + matricula.getAlumno().getApellido();
            dataMatriculas[i][2] = matricula.getAsignatura().getNombre();
            dataMatriculas[i][3] = matricula.getNota();
        }
        DefaultTableModel tableModel = new DefaultTableModel(dataMatriculas, columnNamesMatricula) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        return tableModel;
    }




    public static void main(String[] args) {
        new colegioSalesianos();
    }
}
