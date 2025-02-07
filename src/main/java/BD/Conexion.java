package BD;

import Mapeo.Alumno;
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
            System.err.println(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
        return newSession;
    }

    public static List<Alumno> mostrarAlumnos(Session nuevaSesion) {
        // Query de los clientes
        Query<Alumno> queryClientes = nuevaSesion.createQuery("FROM Alumno", Alumno.class);

        // Guardo los resultados en una lista de clientes
        List<Alumno> resultList = queryClientes.getResultList();
        System.out.println("Número de clientes: " + resultList.size());

        // Muestro resultados
        System.out.println("Código cliente | Nombre completo | Teléfono | Email | Dirección | Estado");
        System.out.println("--------------------------------------------------------------------------------");

        // Itero sobre la lista antes creada para mostrar los datos
        for (Alumno alumno : resultList) {
            System.out.printf("%-15s %-20s %-10s %-25s %-30s %-10s%n",
                    alumno.getID(),
                    alumno.getNombre() + " " + alumno.getApellido(),
                    alumno.getTelefono(),
                    alumno.getEmail(),
                    alumno.getDireccion(),
                    alumno.getEstado());
        }

        return resultList;
    }
}
