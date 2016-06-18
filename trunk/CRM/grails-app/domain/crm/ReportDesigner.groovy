package crm

import crm.exception.CRMException
import java.util.ArrayList;

class ReportDesigner {
	String name;
	Boolean hasFilter=false;
	Boolean hasGroup=false;
	Boolean hasSort=false;
	Integer reportType=new Integer(0);
	List<ReportDesignerColumn> reportDesignerColumns=new ArrayList<ReportDesignerColumn>();
	static hasMany = [reportDesignerColumns:ReportDesignerColumn];
	
    static constraints = {
		name(blank:true, nullable:true, size:0..50);
		reportType(blank:true, nullable:true);
    }
	
	public ReportDesigner(){
		if(null==this.reportDesignerColumns){
			this.realEstateWithActions(["crm.ManagedProperty","crm.Concession"], "crm.Action");
		}
	}
	public ReportDesigner(def params){
		this.properties = params;
		switch(reportType){
			case 10: this.realEstateWithActions(["crm.ManagedProperty","crm.Concession"], "crm.Action");break;
			otherwise: throw new CRMException("Tipo de reporte invalido"); //String msg = message(code: 'reportDesigner.incorrect.report.type.error')
		}
	}
	
	public ReportDesigner(String reportType) throws CRMException, NumberFormatException{
		this.reportType=Integer.parseInt(reportType);
		switch(this.reportType){
			case 10: this.realEstateWithActions(["crm.ManagedProperty","crm.Concession"], "crm.Action");break;
			otherwise: throw new CRMException("Tipo de reporte invalido"); //String msg = message(code: 'reportDesigner.incorrect.report.type.error')
		}
	}
	
	private void realEstateWithActions(ArrayList<String> primaryItemNames, String secondaryItemName){
		//cargar items en un for con los fields de primaryItemsClass usando el metodo getProperties(primaryItemsClass, false)
		for(String itemName:primaryItemNames){
			Class<?> itemClass=Class.forName(itemName);
			//Primero agrego los fields que NO son many to one
			itemClass.declaredFields.each{//java.lang.reflect.Field is each one
				if(!it.isSynthetic()){
					 String t=it.getType().getName();
					//System.out.println("Name"+it.getName()+" Type "+t);
					 if(!(t.equals("java.lang.Object") || t.equals("java.util.Set") || t.equals("org.apache.commons.logging.Log") || t.equals("org.grails.datastore.gorm.GormStaticApi") || t.equals("org.grails.datastore.gorm.GormInstanceApi") || t.equals("org.grails.datastore.gorm.GormValidationApi") || t.equals("org.springframework.validation.Errors") || t.equals("org.grails.plugins.web.controllers.api.ControllersDomainBindingApi") || t.equals("org.grails.plugins.converters.api.ConvertersApi") || t.equals("java.util.List") || it.getName().equals("version"))){
						 if(!it.getType().getSuperclass().getName().equals("crm.CrmDomain")){//si NO una relacion many to one
							 //this.reportDesignerColumns.add(new ReportDesignerColumn(itemName, it));
							 this.addToReportDesignerColumns(new ReportDesignerColumn(itemName, it))
						 }
					 }
				}
			}
			//Luego agrego los fields no son many to one
			itemClass.declaredFields.each{//java.lang.reflect.Field is each one
				if(!it.isSynthetic()){
					 String t=it.getType().getName();
					 if(!(t.equals("java.lang.Object") || t.equals("java.util.Set") || t.equals("org.apache.commons.logging.Log") || t.equals("org.grails.datastore.gorm.GormStaticApi") || t.equals("org.grails.datastore.gorm.GormInstanceApi") || t.equals("org.grails.datastore.gorm.GormValidationApi") || t.equals("org.springframework.validation.Errors") || t.equals("java.util.List") || it.getName().equals("version"))){
						 if(it.getType().getSuperclass().getName().equals("crm.CrmDomain")){//si es una relacion many to one
							 //this.reportDesignerColumns.add(new ReportDesignerColumn(itemName, it));
							 this.addToReportDesignerColumns(new ReportDesignerColumn(itemName, it))
						 }
					 }
				}
			}
		}
	}
}
