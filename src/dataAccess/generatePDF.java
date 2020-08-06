/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;


import Domain.PartialReport;
import Domain.Practicing;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author gabrielflores
 */
public class generatePDF  {
    
    CreateDocumentsFolders createFolders = new CreateDocumentsFolders();
    PartialReport partialReport = new PartialReport();
    
    public InputStream readTemplateFile () throws IOException {
    InputStream input = null;
    input = new FileInputStream(new File("/Users/gabrielflores/JaspersoftWorkspace/ReporteParcial/ReporteParcialBueno.jrxml"));
    return input;
    }
    
    public void showResultingPDF() throws IOException {
        String outputFile = createFolders.getDirName() + "ID" + partialReport.getId_partial() + "_ReporteParcial";
        Desktop.getDesktop().open(new File(outputFile));
    }
    
    public void savePartialReportFile(Practicing practicing, JasperPrint jasperPrint) throws SQLException, ClassNotFoundException, JRException, FileNotFoundException {
        PartialReportDAO partialReportDAO = new PartialReportDAO();
        partialReportDAO.savePartialReport(practicing.getId_person());
        partialReport = partialReportDAO.readPartialReportByIDPracticing(practicing.getId_person());
        createFolders.createDocumentIDFolder(practicing.getPracticingName(), "Partial Report", partialReport.getId_partial());
        OutputStream outputStream = null;
        String outputFile = createFolders.getDirName() + "ID" + partialReport.getId_partial() + "_ReporteParcial";
        outputStream = new FileOutputStream(new File(outputFile));
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    }
    
    public JasperPrint generatePartialReport (Map<String, Object> parameters, InputStream inputTemplateFile) throws JRException {
        JasperDesign jasperDesign = JRXmlLoader.load(inputTemplateFile);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        return jasperPrint;
    }
    
    
}
