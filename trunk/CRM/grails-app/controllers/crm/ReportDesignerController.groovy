package crm

import static org.springframework.http.HttpStatus.*

import java.util.List;

import grails.transaction.Transactional
import crm.commands.ReportDesignerColumnsCommand
import crm.enums.FilterCriteria;
import crm.enums.DataType;

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
		
		reportDesigner.executeReportQuery(reportDesignerColumnsCommand.getColumnList());
		
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
		
	}
	@Transactional
	def run(ReportDesigner reportDesigner) {
		
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
						if(!DataType.getByClassName(col.dataType).validateValue(col.secondaryFilterValue)){
							return false;
						}
					}
				}		
				return DataType.getByClassName(col.dataType).validateValue(col.primaryFilterValue);
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
}
