package crm

import static org.springframework.http.HttpStatus.*

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRCsvExporter
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput
import net.sf.jasperreports.export.SimpleWriterExporterOutput
import grails.transaction.Transactional
import crm.commands.ReportDesignerColumnsCommand
import crm.db.CrmReportQueryBuilder
import crm.enums.data.DataType;
import crm.enums.report.FilterCriteria;
import crm.enums.report.ReportDesignerType;
import crm.exception.CRMException
import crm.report.CrmDynamicColumnDataSource;
import crm.report.CrmDynamicReportBuilder;
import crm.report.CrmDynamicColumnReportService;

@Transactional(readOnly = true)
class ReportDesignerController {
	static allowedMethods = [save: "POST", step3: "POST", step4: "POST"]
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ReportDesigner.list(params), model:[reportDesignerCount: ReportDesigner.count()]
    }
	def step1() {
		
	}
	def step2(ReportDesigner reportDesigner, ReportDesignerColumnsCommand reportDesignerColumnsCommand) {
		if(null != params.rt || null != reportDesigner){
			if(null == reportDesigner){
				reportDesigner=new ReportDesigner(params.rt);
				reportDesignerColumnsCommand=new ReportDesignerColumnsCommand(reportDesigner);
			}
		}else{
			render(view:'/error', model:[message: message(code: 'reportDesigner.step2.not.valid.params.error', default:'INVALID PARAMETHERS ERROR')]);
		}
		respond reportDesigner, model:[reportDesignerColumnsCommand:reportDesignerColumnsCommand]
		//render (view:'step2', model:[reportDesigner:reportDesigner])
	}
	
	def step3(ReportDesigner reportDesigner, char cameFromStep, ReportDesignerColumnsCommand reportDesignerColumnsCommand){
		if(null == reportDesigner || null == reportDesignerColumnsCommand){
			notFound()
			return
		}
		reportDesignerColumnsCommand.columnList.each{
			if(true==it.selected.booleanValue()){
				if(true==it.filterBy.booleanValue()){
					reportDesigner.hasFilter=true;
				}
				if(true==it.groupBy.booleanValue()){
					reportDesigner.hasGroup=true;
				}
				if(true==it.sortBy.booleanValue()){
					reportDesigner.hasSort=true;
				}
			}
		}
		if(true==reportDesigner.hasFilter.booleanValue() || true==reportDesigner.hasGroup.booleanValue() || true==reportDesigner.hasSort.booleanValue()){
			respond reportDesigner, model:[reportDesignerColumnsCommand:reportDesignerColumnsCommand]//va a step3
		}else{//si no tiene filtro ni agrupamiento ni ordenamiento saltar el step 3
			if(cameFromStep=='2'){
				respond reportDesigner, view:'step4', model:[reportDesignerColumnsCommand:reportDesignerColumnsCommand]
			}else{
				if(cameFromStep=='4'){
					respond reportDesigner, view:'step2', model:[reportDesignerColumnsCommand:reportDesignerColumnsCommand]
				}else{
					respond reportDesigner, model:[reportDesignerColumnsCommand:reportDesignerColumnsCommand]
				}
			}
		}
	}
	
	def step4(ReportDesigner reportDesigner, ReportDesignerColumnsCommand reportDesignerColumnsCommand){
		/*if(null == reportDesigner || null == reportDesignerColumnsCommand){
			render(view:'/error', model:[message: message(code: 'reportDesigner.step4.not.valid.params.error', default:'INVALID PARAMETHERS ERROR')]);
		}*/
		if(null == reportDesigner || null == reportDesignerColumnsCommand){
			notFound()
			return
		}
		boolean hasErrors=false;
		List<Integer> groupPositionNumbers=new ArrayList<>();
		List<Integer> sortPositionNumbers=new ArrayList<>();
		reportDesignerColumnsCommand.columnList.each{
			if(true==it.selected.booleanValue()){
				if(true==it.filterBy.booleanValue()){
					if(!this.validateFilterValues(it)){
						it.errors.rejectValue('',message(code:'reportDesigner.column.filter.validation.error', args:[message(code:it.getLabelName())]).toString());
						hasErrors=true;
					}
				}
				if(true==it.groupBy.booleanValue()){
					groupPositionNumbers.add(it.groupPosition);
				}
				if(true==it.sortBy.booleanValue()){
					sortPositionNumbers.add(it.sortPosition);
				}
			}
		}
		if(!this.validateOrderNumbers(sortPositionNumbers)){
			reportDesigner.errors.rejectValue('',message(code:'reportDesigner.column.sortPosition.validation.error').toString());
			hasErrors=true;
		}
		if(!this.validateOrderNumbers(groupPositionNumbers)){
			reportDesigner.errors.rejectValue('',message(code:'reportDesigner.column.groupPosition.validation.error').toString());
			hasErrors=true;
		}
		/*if (propertyDemand.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond propertyDemand.errors, view:'create'
			return
		}*/
		if(hasErrors){
			respond reportDesigner, view:'step3', model:[reportDesignerColumnsCommand:reportDesignerColumnsCommand]
		}else{
			respond reportDesigner, model:[reportDesignerColumnsCommand:reportDesignerColumnsCommand]
		}
	}

	def step5(ReportDesigner reportDesigner, ReportDesignerColumnsCommand reportDesignerColumnsCommand){
		/*if(null == reportDesigner || null == reportDesignerColumnsCommand){
			render(view:'/error', model:[message: message(code: 'reportDesigner.step5.not.valid.params.error', default:'INVALID PARAMETHERS ERROR')]);
		}*/
		if(null == reportDesigner || null == reportDesignerColumnsCommand){
			notFound()
			return
		}
		respond reportDesigner, model:[reportDesignerColumnsCommand:reportDesignerColumnsCommand]
	}
	@Transactional
	def save(ReportDesigner reportDesigner, ReportDesignerColumnsCommand reportDesignerColumnsCommand) {
		if (reportDesigner == null || reportDesignerColumnsCommand == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}
		
		if (reportDesigner.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond reportDesigner/*.errors*/, view:'step5', model:[reportDesignerColumnsCommand:reportDesignerColumnsCommand]
			return
		}
		
		boolean saveError=false;
		if (reportDesigner.save(flush:true)){
			reportDesignerColumnsCommand.columnList.each{
				it.setReportDesigner(reportDesigner);
				it.validate();
				if (it.hasErrors()) {
					saveError=true;
				}else{
					if(false == it.save(flush:true)){
						it.errors.rejectValue('',message(code:'default.save.error', args:['ReportDesignerColumn']).toString());
						GUtils.printErrors(it, "Error al guardar nuevo ReportDesignerColumn");
						saveError=true;
					}
				}
			}
		}else{
			reportDesigner.errors.rejectValue('',message(code:'default.save.error', args:['ReportDesigner']).toString());
			GUtils.printErrors(reportDesigner, "Error al guardar nuevo ReportDesigner");
			saveError=true;
		}
		if(true==saveError){
			transactionStatus.setRollbackOnly()
			respond reportDesigner, view:'step5', model:[reportDesignerColumnsCommand:reportDesignerColumnsCommand]
			return
		}
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'reportDesigner.label', default: 'Report Designer'), reportDesigner.id])
				redirect reportDesigner
			}
			'*' { respond reportDesigner, [status: CREATED] }
		}
	}
	def show(ReportDesigner reportDesigner){
		respond reportDesigner
	}
	@Transactional
	def delete(ReportDesigner reportDesigner) {
		boolean deleted=true;
		reportDesigner.reportDesignerColumns.each {
			if(!it.delete(flush:true)){
				deleted=false;
				reportDesigner.errors.rejectValue('',message(code:'default.save.error', args:[message(code:'reportDesignerColumn.label')]).toString()+"="+it.id);
			}
		}
		if(!reportDesigner.delete(flush:true)){
			deleted=false;
			reportDesigner.errors.rejectValue('',message(code:'default.save.error', args:[message(code:'reportDesigner.label')]).toString()+"="+reportDesigner.id);
		}
		if(!deleted) {
			transactionStatus.setRollbackOnly()
			respond reportDesigner.errors, view:'show'
			return
		}
	}
	@Transactional
	def run(ReportDesigner reportDesigner) {
		CrmReportQueryBuilder queryBuilder= new CrmReportQueryBuilder(reportDesigner.getReportDesignerColumnsFromDb(), ReportDesignerType.valueOf(reportDesigner.reportType));
		String query=queryBuilder.getBuildedQuery();
		List<List<Object>> res=reportDesigner.getReportDesignerType().getMainDomainClass().executeQuery(query);
		/*for(List<Object> li:res){
			System.out.print("\n");
			for(int i=0;i<li.size();i++){
				Object ob=li.get(i);
				ob=queryBuilder.getSelectedReportColumnByIndex(i).getDataType().getValueAsString(ob);//setea el objeto a valor string correcto obtenido de DataType
				System.out.print(ob+"\t");
			}
		}*/
		
		
		List<List<String>> res2=new ArrayList<List<String>>();
		List<String> rowx;
		Iterator<List<Object>> itr = res.iterator();
		List<Object> li;
		while(itr.hasNext()){
		   li = itr.next();
		   rowx=new ArrayList<String>();
		   for(int i=0;i<li.size();i++){
				Object ob=li.get(i);
				ob=queryBuilder.getSelectedReportColumnByIndex(i).getDataType().getValueAsString(ob);//setea el objeto a valor string correcto obtenido de DataType
				rowx.add(ob.toString());
		   }
		   res2.add(rowx);
		   itr.remove();
		}
		

		//System.err.println("Numero filas="+res2.size());
		try {
			this.runReport(queryBuilder.getSelectedColumnDisplayNames(), res2, reportDesigner.name, params.ef);
		} catch (JRException e) {
			 // TODO Auto-generated catch block
			CrmLogger.logException(this.getClass(), "JRException running Report "+reportDesigner.name+".", e);
			 //e.printStackTrace();
		} catch (FileNotFoundException e) {
		 // TODO Auto-generated catch block
			CrmLogger.logException(this.getClass(), "FileNotFoundException running Report "+reportDesigner.name+".", e);
		// e.printStackTrace();
		}
	}
	
	public void runReport(List<String> columnHeaders, List<List<String>> rows, String reportName, String exportFormat) throws JRException, FileNotFoundException, CRMException {
		if(exportFormat==null){
			throw new CRMException("Export type can not be null in runReport method.");
		}
		File file=CrmDynamicColumnReportService.runReport(columnHeaders, rows, reportName, exportFormat);
		String fileName=reportName+"."+exportFormat;
		if (file.exists()) {
			response.setContentType("application/octet-stream")
			response.setHeader("Content-disposition", "filename=${fileName}")
			response.outputStream << file.bytes
			return
		}else{
			throw new CRMException(message(code: 'reportDesigner.report.creation.error'));
		}
	}
			
	
	def getCategoryFieldNumberAJAX(String filterCriteriaName) {//retorna si los numeros que contiene la lista de enteros tiene todos los numeros empezando de 1 hasta la cantidad de numeros que contenga la lista
		System.out.println("Filter Criteria en controller="+filterCriteriaName);
		def fc=FilterCriteria.valueOf(filterCriteriaName);
		System.out.println("Numero en controller="+fc.numberOfFields);
		render fc.numberOfFields
	}
	private boolean validateOrderNumbers(List<Integer> numbers){
		int sum, sum2=0;
		for(int i=1;i<=numbers.size();i++){
			sum=sum+i;
		}
		numbers.each{
			sum2=sum2+it.intValue();
		}
		return (sum==sum2);
	}
	private boolean validateFilterValues(ReportDesignerColumn col){
		short fn=FilterCriteria.valueOf(col.filterCriteria).numberOfFields.shortValue();
		if(col.filterBy){
			if(null != col.primaryFilterValue && !col.primaryFilterValue.isEmpty()){
				if(fn==2){
					if(null == col.secondaryFilterValue || col.secondaryFilterValue.isEmpty()){
						return false;
					}else{
						if(!DataType.valueOf(col.dataType).validateValue(col.secondaryFilterValue)){
							return false;
						}
					}
				}		
				return DataType.valueOf(col.dataType).validateValue(col.primaryFilterValue);
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
}
