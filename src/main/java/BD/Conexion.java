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

    public static List<Alumno> mostrarAlumnos(Session nuevaSesion) {
        if (nuevaSesion == null) {
            throw new IllegalStateException("La sesión de Hibernate es nula. Verifica la conexión.");
        }
        Query<Alumno> queryClientes = nuevaSesion.createQuery("FROM Alumno", Alumno.class);
        return queryClientes.getResultList();
    }

    public static List<Asignatura> mostrarAsignaturas(Session nuevaSesion) {
        if (nuevaSesion == null) {
            throw new IllegalStateException("La sesión de Hibernate es nula. Verifica la conexión.");
        }
        Query<Asignatura> queryAsignaturas = nuevaSesion.createQuery("FROM Asignatura", Asignatura.class);
        return queryAsignaturas.getResultList();
    }

    public static List<Matricula> mostrarMatriculas(Session nuevaSesion) {
        if (nuevaSesion == null) {
            throw new IllegalStateException("La sesión de Hibernate es nula. Verifica la conexión.");
        }
        Query<Matricula> queryMatriculas = nuevaSesion.createQuery("FROM Matricula", Matricula.class);
        return queryMatriculas.getResultList();
    }

}
