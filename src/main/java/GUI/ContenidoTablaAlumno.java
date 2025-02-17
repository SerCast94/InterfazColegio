package GUI;

import Mapeo.Alumno;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

public class ContenidoTablaAlumno extends AbstractTableModel {
    public final static int ID = 0;
    public final static int NOMBRE = 1;
    public final static int APELLIDO = 2;
    public final static int TELEFONO = 3;
    public final static int CORREO = 4;
    public final static int DIRECCION = 5;
    public final static int ESTADO = 6;
    public final static int FECHA = 7; // Nueva columna Fecha
    public final static int CARNET = 8; // Nueva columna Carnet

    // Actualización del array de nombres de columnas
    public final static String[] nombresColumnas = {
            "ID", "Nombre", "Apellido", "Telefono", "Correo", "Direccion", "Estado", "Fecha", "Carnet"
    };

    public List<Alumno> alumnos;

    public ContenidoTablaAlumno() {
        alumnos = colegioSalesianos.getInstance().controlador.getListaAlumnos();
    }

    public void refreshTablaAlumno(){
        alumnos = colegioSalesianos.getInstance().controlador.getListaAlumnos();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return alumnos.size();
    }

    @Override
    public int getColumnCount() {
        return nombresColumnas.length;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Alumno alumno = alumnos.get(fila);
        switch (columna){
            case ID: return alumno.getID();
            case NOMBRE: return alumno.getNombre();
            case APELLIDO: return alumno.getApellido();
            case TELEFONO: return alumno.getTelefono();
            case CORREO: return alumno.getEmail();
            case DIRECCION: return alumno.getDireccion();
            case ESTADO: return alumno.getEstado();
            case FECHA:
                // Mostrar la fecha en formato "dd/MM/yyyy"
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                return sdf.format(alumno.getFecha());
            case CARNET:
                // El carnet es un booleano, se devuelve el valor como JCheckBox
                return alumno.isCarnet();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return nombresColumnas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == CARNET) {
            return Boolean.class; // Indicar que la columna carnet contiene valores booleanos
        }
        return super.getColumnClass(columnIndex);
    }

    // Método para obtener la lista de alumnos
    public List<Alumno> getAlumnos() {
        return alumnos;
    }
}
