package PDF;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class VisorPdfApache extends JPanel {

    private PDDocument documento;
    private PDFRenderer pdfRenderer;

    public VisorPdfApache(String rutaArchivo) {
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(this, "El archivo PDF no se encuentra", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            documento = PDDocument.load(archivo);
            if (documento.getNumberOfPages() == 0) {
                JOptionPane.showMessageDialog(this, "El archivo PDF está vacío", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                pdfRenderer = new PDFRenderer(documento);

                repaint();  // Solicitar repintado para mostrar la primera página
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar el PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (documento != null && documento.getNumberOfPages() > 0) {
            try {
                // Obtener las dimensiones de la página
                float pageWidth = documento.getPage(0).getMediaBox().getWidth();
                float pageHeight = documento.getPage(0).getMediaBox().getHeight();

                // Escalar el renderizado si el tamaño de la página es mayor que el panel
                float scaleX = (float) getWidth() / pageWidth;
                float scaleY = (float) getHeight() / pageHeight;
                float scale = Math.min(scaleX, scaleY);  // Mantener la relación de aspecto

                // Renderizar la página en el gráfico con el tamaño escalado
                pdfRenderer.renderPageToGraphics(0, (Graphics2D) g, scale);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al renderizar el PDF", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Repaint para asegurarse de que se actualiza el panel cuando se necesite
    public void repaintPanel() {
        repaint();
    }
}
