package GUI;

import Mapeo.Matricula;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ContenidoTablaMatricula extends AbstractTableModel {
    public final static int ALUMNO = 0;
    public final static int ASIGNATURA = 1;
    public final static int NOTA = 2;

    public final static String[] nombresColumnas = {"Alumno", "Asignatura", "Nota"};

    public List<Matricula> matriculas;

    public ContenidoTablaMatricula() {
        matriculas = colegioSalesianos.getInstance().controlador.getListaMatriculas();
    }

    public void refreshTablaMatricula(){
        matriculas = colegioSalesianos.getInstance().controlador.getListaMatriculas();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return matriculas.size();
    }

    @Override
    public int getColumnCount() {
        return nombresColumnas.length;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        return switch (columna) {
            case ALUMNO -> matriculas.get(fila).getAlumno().getNombre() + " " + matriculas.get(fila).getAlumno().getApellido();
            case ASIGNATURA -> matriculas.get(fila).getAsignatura().getNombre();
            case NOTA -> matriculas.get(fila).getNota();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return nombresColumnas[column];
    }
}
