package Controlador;

import Mapeo.Alumno;
import Mapeo.Asignatura;
import Mapeo.Matricula;
import org.hibernate.Session;

import java.util.List;

import static BD.Conexion.*;

public class Controlador {
    static Session session;
    static List<Alumno> listaAlumnos;
    static List<Asignatura> listaAsignaturas;
    static List<Matricula> listaMatriculas;

    public Controlador(Session session) {
        Controlador.session = session;
        listaAlumnos = consultaAlumnos(session);
        listaAsignaturas = consultaAsignaturas(session);
        listaMatriculas = consultaMatriculas(session);
    }

    // Métodos Alumnos
    public static List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public static void anadirAlumno(Alumno alumno) {
        try {
            insertAlumno(session, alumno);
        } catch (Exception e) {
            System.out.println("Error al insertar alumno: " + e.getMessage());
        }
        listaAlumnos = consultaAlumnos(session);
    }

    public static void actualizarAlumno(Alumno alumno) {
        try {
            updateAlumno(session, alumno);
        } catch (Exception e) {
            System.out.println("Error al actualizar alumno: " + e.getMessage());
        }
        listaAlumnos = consultaAlumnos(session);
    }

    public static Alumno buscarAlumnoPorId(int id) {
        for (Alumno alumno : listaAlumnos) {
            if (alumno.getID() == id) {
                return alumno;
            }
        }
        return null;
    }

    public static void borrarAlumno(int id) {
        try {
            deleteAlumno(session, buscarAlumnoPorId(id));
        } catch (Exception e) {
            System.out.println("Error al borrar alumno: " + e.getMessage());
        }
        listaAlumnos = consultaAlumnos(session);
    }

    // Métodos Asignaturas
    public static List<Asignatura> getListaAsignaturas() {
        return listaAsignaturas;
    }

    public static void anadirAsignatura(Asignatura asignatura) {
        try {
            insertAsignatura(session, asignatura);
        } catch (Exception e) {
            System.out.println("Error al insertar asignatura: " + e.getMessage());
        }
        listaAsignaturas = consultaAsignaturas(session);
    }

    public static void actualizarAsignatura(Asignatura asignatura) {
        try {
            updateAsignatura(session, asignatura);
        } catch (Exception e) {
            System.out.println("Error al actualizar asignatura: " + e.getMessage());
        }
        listaAsignaturas = consultaAsignaturas(session);
    }

    public static Asignatura buscarAsignaturaPorId(int id) {
        for (Asignatura asignatura : listaAsignaturas) {
            if (asignatura.getId() == id) {
                return asignatura;
            }
        }
        return null;
    }

    public static void borrarAsignatura(int id) {
        try {
            deleteAsignatura(session, id);
        } catch (Exception e) {
            System.out.println("Error al borrar asignatura: " + e.getMessage());
        }
        listaAsignaturas = consultaAsignaturas(session);
    }

    // Métodos Matrículas
    public static List<Matricula> getListaMatriculas() {
        return listaMatriculas;
    }

    public static void anadirMatricula(Matricula matricula) {
        try {
            insertMatricula(session, matricula);
        } catch (Exception e) {
            System.out.println("Error al insertar matrícula: " + e.getMessage());
        }
        listaMatriculas = consultaMatriculas(session);
    }

    public static void actualizarMatricula(Matricula matricula) {
        try {
            updateMatricula(session, matricula);
        } catch (Exception e) {
            System.out.println("Error al actualizar matrícula: " + e.getMessage());
        }
        listaMatriculas = consultaMatriculas(session);
    }

    public static Matricula buscarMatriculaPorId(int id) {
        for (Matricula matricula : listaMatriculas) {
            if (matricula.getAlumno().getID() == id) {
                return matricula;
            }
        }
        return null;
    }

    public static void borrarMatricula(int id) {
        try {
            deleteMatricula(session, id);
        } catch (Exception e) {
            System.out.println("Error al borrar matrícula: " + e.getMessage());
        }
        listaMatriculas = consultaMatriculas(session);
    }

    // Métodos generales
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        Controlador.session = session;
    }

    public void setListaAlumnos(List<Alumno> listaAlumnos) {
        Controlador.listaAlumnos = listaAlumnos;
    }

    public void setListaAsignaturas(List<Asignatura> listaAsignaturas) {
        Controlador.listaAsignaturas = listaAsignaturas;
    }

    public void setListaMatriculas(List<Matricula> listaMatriculas) {
        Controlador.listaMatriculas = listaMatriculas;
    }
}