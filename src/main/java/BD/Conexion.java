package BD;

import Mapeo.Alumno;
import Mapeo.Asignatura;
import Mapeo.Matricula;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class Conexion {

    public static Session crearConexion() {
        // Establece la conexión mediante Session gracias a los datos de hibernate.cfg.xml
        Session newSession = null;
        try {
            newSession = new Configuration().configure().buildSessionFactory().openSession();
        } catch (Throwable ex) {
            System.err.println("Error al crear la sesión de Hibernate: " + ex.getMessage());
            ex.printStackTrace();
        }
        return newSession;
    }

    public static List<Alumno> consultaAlumnos(Session nuevaSesion) {
        if (nuevaSesion == null) {
            throw new IllegalStateException("La sesión de Hibernate es nula. Verifica la conexión.");
        }
        Query<Alumno> queryClientes = nuevaSesion.createQuery("FROM Alumno", Alumno.class);
        return queryClientes.getResultList();
    }

    public static List<Asignatura> consultaAsignaturas(Session nuevaSesion) {
        if (nuevaSesion == null) {
            throw new IllegalStateException("La sesión de Hibernate es nula. Verifica la conexión.");
        }
        Query<Asignatura> queryAsignaturas = nuevaSesion.createQuery("FROM Asignatura", Asignatura.class);
        return queryAsignaturas.getResultList();
    }

    public static List<Matricula> consultaMatriculas(Session nuevaSesion) {
        if (nuevaSesion == null) {
            throw new IllegalStateException("La sesión de Hibernate es nula. Verifica la conexión.");
        }
        Query<Matricula> queryMatriculas = nuevaSesion.createQuery("FROM Matricula", Matricula.class);
        return queryMatriculas.getResultList();
    }

    public static void insertAlumno(Session nuevaSesion, Alumno alumno) throws Exception {

        if (nuevaSesion == null) {
            throw new IllegalStateException("La sesión de Hibernate es nula. Verifica la conexión.");
        }
        if (alumno == null) {
            throw new Exception("Alumno nulo");
        }

        try {
            nuevaSesion.beginTransaction();
            nuevaSesion.save(alumno);
            nuevaSesion.getTransaction().commit();
        } catch (Exception e) {
            if (nuevaSesion.getTransaction().isActive()) {
                nuevaSesion.getTransaction().rollback();
            }
            throw new Exception("Error al insertar el alumno: " + e.getMessage(), e);
        }
    }

    public static void insertAsignatura(Session nuevaSesion, Asignatura asignatura) throws Exception {

        if (nuevaSesion == null) {
            throw new IllegalStateException("La sesión de Hibernate es nula. Verifica la conexión.");
        }
        if (asignatura == null) {
            throw new Exception("Asignatura nula");
        }

        try {
            nuevaSesion.beginTransaction();
            nuevaSesion.save(asignatura);
            nuevaSesion.getTransaction().commit();
        } catch (Exception e) {
            if (nuevaSesion.getTransaction().isActive()) {
                nuevaSesion.getTransaction().rollback();
            }
            throw new Exception("Error al insertar la asignatura: " + e.getMessage(), e);
        }
    }

    public static void insertMatricula(Session nuevaSesion, Matricula matricula) throws Exception {

        if (nuevaSesion == null) {
            throw new IllegalStateException("La sesión de Hibernate es nula. Verifica la conexión.");
        }
        if (matricula == null) {
            throw new Exception("Matricula nula");
        }

        try {
            nuevaSesion.beginTransaction();
            nuevaSesion.save(matricula);
            nuevaSesion.getTransaction().commit();
        } catch (Exception e) {
            if (nuevaSesion.getTransaction().isActive()) {
                nuevaSesion.getTransaction().rollback();
            }
            throw new Exception("Error al insertar la matricula: " + e.getMessage(), e);
        }

    }

    public static void updateAlumno(Session nuevaSesion, Alumno alumno) {
        try {
            nuevaSesion.beginTransaction();
            nuevaSesion.merge(alumno);
            nuevaSesion.getTransaction().commit();
        } catch (Exception e) {
            if (nuevaSesion.getTransaction() != null) {
                nuevaSesion.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualizar alumno: " + e.getMessage());
        }
    }

    public static void deleteAlumno(Session nuevaSesion, Alumno alumno) {
        try {
            nuevaSesion.beginTransaction();
            nuevaSesion.delete(alumno);
            nuevaSesion.getTransaction().commit();
        } catch (Exception e) {
            if (nuevaSesion.getTransaction() != null) {
                nuevaSesion.getTransaction().rollback();
            }
            throw new RuntimeException("Error al borrar alumno: " + e.getMessage());
        }
    }

    public static void updateAsignatura(Session nuevaSesion, Asignatura asignatura) {
        try {
            nuevaSesion.beginTransaction();
            nuevaSesion.merge(asignatura);
            nuevaSesion.getTransaction().commit();
        } catch (Exception e) {
            if (nuevaSesion.getTransaction() != null) {
                nuevaSesion.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualizar asignatura: " + e.getMessage());
        }
    }

    public static void deleteAsignatura(Session nuevaSesion, int id) {
        try {
            nuevaSesion.beginTransaction();
            Asignatura asignatura = nuevaSesion.get(Asignatura.class, id);
            if (asignatura != null) {
                nuevaSesion.delete(asignatura);
            }
            nuevaSesion.getTransaction().commit();
        } catch (Exception e) {
            if (nuevaSesion.getTransaction() != null) {
                nuevaSesion.getTransaction().rollback();
            }
            throw new RuntimeException("Error al borrar asignatura: " + e.getMessage());
        }
    }

    public static void updateMatricula(Session nuevaSesion, Matricula matricula) {
        try {
            nuevaSesion.beginTransaction();
            nuevaSesion.merge(matricula);
            nuevaSesion.getTransaction().commit();
        } catch (Exception e) {
            if (nuevaSesion.getTransaction() != null) {
                nuevaSesion.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualizar matricula: " + e.getMessage());
        }
    }

    public static void deleteMatricula(Session nuevaSesion, int id) {
        try {
            nuevaSesion.beginTransaction();
            Matricula matricula = nuevaSesion.get(Matricula.class, id);
            if (matricula != null) {
                nuevaSesion.delete(matricula);
            }
            nuevaSesion.getTransaction().commit();
        } catch (Exception e) {
            if (nuevaSesion.getTransaction() != null) {
                nuevaSesion.getTransaction().rollback();
            }
            throw new RuntimeException("Error al borrar matricula: " + e.getMessage());
        }
    }

}
