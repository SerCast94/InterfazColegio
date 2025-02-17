package GUI;

import Mapeo.Asignatura;

import javax.swing.table.AbstractTableModel;
import java.util.List;


public class ContenidoTablaAsignatura extends AbstractTableModel {
    public final static int ID = 0;
    public final static int NOMBRE = 1;

    public final static String[] nombresColumnas = {"ID", "Nombre"};

    public List<Asignatura> asignaturas;

    public ContenidoTablaAsignatura() {
        asignaturas = colegioSalesianos.getInstance().controlador.getListaAsignaturas();
    }

    public void refreshTablaAsignatura(){
        asignaturas = colegioSalesianos.getInstance().controlador.getListaAsignaturas();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return asignaturas.size();
    }

    @Override
    public int getColumnCount() {
        return nombresColumnas.length;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        return switch (columna) {
            case ID -> asignaturas.get(fila).getId();
            case NOMBRE -> asignaturas.get(fila).getNombre();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return nombresColumnas[column];
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }
}