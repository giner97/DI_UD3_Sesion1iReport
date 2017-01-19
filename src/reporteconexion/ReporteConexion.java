
package reporteconexion;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JRViewer;

public class ReporteConexion {
    
    public static void main(String[] args) {
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/informes_di","root", "campus");
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile("reporte_sesion1.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, conexion);

            //Exportamos el reporte para pdf
            
                Exporter exporter;
                exporter = new JRPdfExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new java.io.File("reporteSesion1PDF.pdf")));
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            
            JFrame frame = new JFrame("Reporte Sesion 1");
            frame.getContentPane().add(new JRViewer(jasperPrint));
            frame.pack();
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
        } 
        
        catch(Exception e) {
            
            e.printStackTrace();
            
        }
    }
}
