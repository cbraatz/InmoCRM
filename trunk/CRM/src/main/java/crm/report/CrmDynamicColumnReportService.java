package crm.report;

//import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import crm.exception.CRMException;

public class CrmDynamicColumnReportService {
    public static File runReport(List<String> columnHeaders, List<List<String>> rows, String reportName, String exportFormat) throws JRException, FileNotFoundException, CRMException {
    	InputStream is = new FileInputStream(new File("D:/empty_template.jrxml"));
		JasperDesign jasperReportDesign = JRXmlLoader.load(is);
		
		//System.out.println("Adding the dynamic columns");
		CrmDynamicReportBuilder reportBuilder = new CrmDynamicReportBuilder(jasperReportDesign, columnHeaders.size());
		reportBuilder.addDynamicColumns();
		
		//System.out.println("Compiling the report");
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperReportDesign);
					Map<String, Object> params = new HashMap<String, Object>();
		params.put("REPORT_TITLE", reportName);
		CrmDynamicColumnDataSource pdfDataSource = new CrmDynamicColumnDataSource(columnHeaders, rows);
		//System.out.println("Filling the report");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, pdfDataSource);
		
		//GUtils.removeAllFilesFromDirectory("temp");
		
		String exportedTempFilePath="temp/dynamicTempReport_"+crm.Utils.getShortUUID();
		switch(exportFormat){
			case "pdf": JasperExportManager.exportReportToPdfFile(jasperPrint, exportedTempFilePath);break;
			case "html":JasperExportManager.exportReportToHtmlFile(jasperPrint, exportedTempFilePath);break;
			case "csv": JRCsvExporter csvExporter = new JRCsvExporter();
						csvExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
						csvExporter.setExporterOutput(new SimpleWriterExporterOutput(exportedTempFilePath));
						csvExporter.exportReport();break;
			default: throw new CRMException("Export format = '"+exportFormat+"' is not a valid export format.");
		}
		return new File(exportedTempFilePath);		
    }
}
