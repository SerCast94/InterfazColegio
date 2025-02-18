package PDF;

import javax.swing.*;
import java.awt.*;

public class MostrarExpedienteAlumnoApache extends JFrame {
    VisorPdfApache visorPDF;

    public MostrarExpedienteAlumnoApache(String archivoPdf) {
        visorPDF = new VisorPdfApache(archivoPdf);
        initGUI();
    }

    void initGUI() {
        setTitle("Expediente del Alumno");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        setSize(700,1000);

        JScrollPane scrollPane = new JScrollPane(visorPDF);
        add(scrollPane, BorderLayout.CENTER);

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);

        // Hacer visible la ventana
        visorPDF.repaintPanel();
        setVisible(true);
    }
}