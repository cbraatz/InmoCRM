package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import crm.enums.FilterCriteria;
import crm.enums.DataType;

@Transactional(readOnly = true)
class ReportDesignerController {
	//static allowedMethods = [step3: "POST", step4: "POST", step5: "POST"]
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ReportDesigner.list(params), model:[reportDesignerCount: ReportDesigner.count()]
    }
	def step1() {
		
	}
	def step2(ReportDesigner reportDesigner) {
		if(null!=params.rt || null!=reportDesigner){
			if(null==reportDesigner){
				reportDesigner=new ReportDesigner(params.rt);
			}
		}else{
			render(view:'/error', model:[message: message(code: 'reportDesigner.step2.not.valid.params.error')]);
		}
		respond reportDesigner
		//render (view:'step2', model:[reportDesigner:reportDesigner])
	}
	
	def step3(ReportDesigner reportDesigner, char cameFromStep){
		if(reportDesigner.reportType.intValue() <= 0){
			render(view:'/error', model:[message: message(code: 'reportDesigner.step3.not.valid.params.error')]);
		}
		reportDesigner.reportDesignerColumns.each{
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
			respond reportDesigner;//va a step3
		}else{//si no tiene filtro ni agrupamiento ni ordenamiento saltar el step 3
			if(cameFromStep=='2'){
				respond reportDesigner, view:'step4';
			}else{
				if(cameFromStep=='4'){
					respond reportDesigner, view:'step2';
				}else{
					respond reportDesigner;
				}
			}
		}
	}
	
	def step4(ReportDesigner reportDesigner){
		if(reportDesigner.reportType.intValue() <= 0){
			render(view:'/error', model:[message: message(code: 'reportDesigner.step3.not.valid.params.error')]);
		}
		boolean hasErrors=false;
		List<Integer> groupOrderNumbers=new ArrayList<>();
		List<Integer> sortOrderNumbers=new ArrayList<>();
		reportDesigner.reportDesignerColumns.each{
			if(true==it.selected.booleanValue()){
				if(true==it.filterBy.booleanValue()){
					if(!this.validateFilterValues(it)){
						it.errors.rejectValue('',message(code:'reportDesigner.column.filter.validation.error', args:[message(code:it.getLabelName())]).toString());
						hasErrors=true;
					}
				}
				if(true==it.groupBy.booleanValue()){
					groupOrderNumbers.add(it.groupOrder);
				}
				if(true==it.sortBy.booleanValue()){
					sortOrderNumbers.add(it.sortOrder);
				}
			}
		}
		if(!this.validateOrderNumbers(sortOrderNumbers)){
			reportDesigner.errors.rejectValue('',message(code:'reportDesigner.column.sortOrder.validation.error').toString());
			hasErrors=true;
		}
		if(!this.validateOrderNumbers(groupOrderNumbers)){
			reportDesigner.errors.rejectValue('',message(code:'reportDesigner.column.groupOrder.validation.error').toString());
			hasErrors=true;
		}
		/*if (propertyDemand.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond propertyDemand.errors, view:'create'
			return
		}*/
		if(hasErrors){
			respond reportDesigner, view:'step3';
		}else{
			respond reportDesigner
		}
	}

	def step5(ReportDesigner reportDesigner){
		if(reportDesigner.reportType.intValue() <= 0){
			render(view:'/error', model:[message: message(code: 'reportDesigner.step3.not.valid.params.error')]);
		}
		//respond reportDesigner, view:'step5'
		respond reportDesigner
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
