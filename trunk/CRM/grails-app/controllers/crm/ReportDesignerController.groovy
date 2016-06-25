package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import crm.enums.FilterCriteria

@Transactional(readOnly = true)
class ReportDesignerController {
	static allowedMethods = [step3: "POST", step4: "POST"]
    def index() { }
	
	def step2() {
		ReportDesigner reportDesigner;
		if(null!=params.rt){
			reportDesigner=new ReportDesigner(params.rt);
		}else{
			render(view:'/error', model:[message: message(code: 'reportDesigner.step2.not.valid.params.error')]);
		}
		respond reportDesigner
	}
	
	@Transactional
	def step3(ReportDesigner reportDesigner){
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
		respond reportDesigner
	}
	
	@Transactional
	def step4(ReportDesigner reportDesigner){
		if(reportDesigner.reportType.intValue() <= 0){
			render(view:'/error', model:[message: message(code: 'reportDesigner.step3.not.valid.params.error')]);
		}
		respond reportDesigner
	}
	
	def getCategoryFieldNumberAJAX(String filterCriteriaName) {
		System.out.println("Filter Criteria en controller="+filterCriteriaName);
		def fc=FilterCriteria.valueOf(filterCriteriaName);
		System.out.println("Numero en controller="+fc.numberOfFields);
		render fc.numberOfFields
	}
}
