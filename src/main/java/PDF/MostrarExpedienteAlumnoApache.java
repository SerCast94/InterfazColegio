package PDF;

import javax.swing.*;
import java.awt.*;

public class MostrarExpedienteAlumnoApache extends JFrame {
    VisorPdfApache visorPDF;

    public MostrarExpedienteAlumnoApache(String archivoPdf) {
        visorPDF = new VisorPdfApache(archivoPdf);
        initGUI();
    }

    void initGUI(){
        setTitle("Expediente del Alumno");

        // Establecer un tamaño de ventana por defecto (podrías cambiarlo)
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Agregar un JScrollPane para que el PDF sea scrollable si es más grande que la ventana
        JScrollPane scrollPane = new JScrollPane(visorPDF);
        add(scrollPane, BorderLayout.CENTER);

        // Hacer visible la ventana
        setLocationRelativeTo(null);
        visorPDF.repaintPanel();
        setVisible(true);
    }
}
