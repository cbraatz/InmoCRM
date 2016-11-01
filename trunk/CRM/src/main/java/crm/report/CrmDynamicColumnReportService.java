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
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

import java.util.Iterator;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrmDynamicColumnReportService {

    public void runReport(List<String> columnHeaders, List<List<String>> rows) throws JRException, FileNotFoundException {

        System.out.println("Loading the .jrxml");
        System.out.println(getClass().getName());
        //InputStream is = getClass().getResourceAsStream("/../empty_template.jrxml");
		 InputStream is = new FileInputStream(new File("D:/empty_template_2.jrxml"));
        JasperDesign jasperReportDesign = JRXmlLoader.load(is);

        System.out.println("Adding the dynamic columns");
        CrmDynamicReportBuilder reportBuilder = new CrmDynamicReportBuilder(jasperReportDesign, columnHeaders.size());
        reportBuilder.addDynamicColumns();
        System.out.println("Compiling the report");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperReportDesign);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("REPORT_TITLE", "Sample Dynamic Columns Report");
        CrmDynamicColumnDataSource dataSource = new CrmDynamicColumnDataSource(columnHeaders, rows);
        System.out.println("Filling the report");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

        System.out.println("Exporting the report to pdf");
        //pdf funciona
        JasperExportManager.exportReportToPdfFile(jasperPrint, "temp/dynamicTestColumns");
        
        //csv funciona
       /* JRCsvExporter csvExporter = new JRCsvExporter();
        csvExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        csvExporter.setExporterOutput(new SimpleWriterExporterOutput("D:/DynamicColumns2.csv"));
        csvExporter.exportReport();*/
        
        //html funciona
        //JasperExportManager.exportReportToHtmlFile(jasperPrint, "D:/DynamicColumns2.html");
        
        

    }
    /*public static void main(String[] args) {
       
 	   List<String> headers=new ArrayList<String>();
 	   headers.add("Título 1");
 	   headers.add("Título 2");
 	   List<Object> row1=new ArrayList<Object>();
 	   row1.add("CBRow 1_Col 1");
 	   row1.add("CBRow 1_Col 2");
 	   List<Object> row2=new ArrayList<Object>();
 	   row2.add("CBRow 2_Col 1");
 	   row2.add("CBRow 2_Col 2");
 	   List<List<Object>> rows=new ArrayList<List<Object>>();
 	   rows.add(row1);
 	   rows.add(row2);
 	   
 	   	List<List<String>> rows2=new ArrayList<List<String>>();
 	    List<String> rowx;
		Iterator<List<Object>> itr = rows.iterator();
		List<Object> li;
		while(itr.hasNext()){
		   li = itr.next();
		   rowx=new ArrayList<String>();
		   for(int i=0;i<li.size();i++){
				Object ob=li.get(i);
				//ob=queryBuilder.getSelectedReportColumnByIndex(i).getDataType().getValueAsString(ob);//setea el objeto a valor string correcto obtenido de DataType
				rowx.add(ob.toString());
		   }
		   rows2.add(rowx);
		   itr.remove();
	    }
		System.out.println(rows2.size()+"-"+rows.size());
		try {
			new CrmDynamicColumnReportService().runReport(headers,rows2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/
}
