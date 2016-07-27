package crm

import crm.enums.ReportDesignerType
import crm.exception.CRMException

import java.util.ArrayList;

class ReportDesigner extends CrmDomain{
	String name;
	String description;
	Boolean hasFilter=false;
	Boolean hasGroup=false;
	Boolean hasSort=false;
	String reportType;
	CrmUser crmUser;
	ReportFolder reportFolder;
	//List<ReportDesignerColumn> reportDesignerColumns=new ArrayList<ReportDesignerColumn>();
	static hasMany = [reportDesignerColumns:ReportDesignerColumn];
	//static transients = ["reportDesignerColumns"];
	/*static mapping = {
		reportDesignerColumns sort:'propertyName';
	}*/
	
    static constraints = {
		name(blank:false, nullable:false, size:1..50);
		description(blank:true, nullable:true, size:0..200);
		hasFilter(blank:false, nullable:false);
		hasGroup(blank:false, nullable:false);
		hasSort(blank:false, nullable:false);
		reportType(blank:false, nullable:false, size:1..30);
		crmUser(nullable:false);
		reportFolder(nullable:true);
    }
	
	public ReportDesigner(){	}
	
	public ReportDesigner(def params){
		this.properties = params;
		/*switch(reportType){
			case 10: this.realEstateWithActions(["crm.ManagedProperty","crm.Concession"], "crm.Action");break;
			otherwise: throw new CRMException("Tipo de reporte invalido"); //String msg = message(code: 'reportDesigner.incorrect.report.type.error')
		}*/
	}
	
	public ReportDesigner(String reportType) throws IllegalArgumentException{
		ReportDesignerType reportDesignerType=ReportDesignerType.REAL_ESTATE;
		ReportDesignerType.valueOf(reportType);//to test if reportType is correct if not throws IllegalArgumentException
		this.reportType=reportDesignerType;
		this.name="New Report";
	}
	public List<ReportDesignerColumn> getReportDesignerColumnList() throws CRMException, NumberFormatException{
		List<ReportDesignerColumn> columnList=new ArrayList<ReportDesignerColumn>();
		ReportDesignerType rdt=ReportDesignerType.valueOf(this.reportType);
		for(String tableName:rdt.getDomainObjects()){
			Class<?> itemClass=Class.forName("crm."+tableName);
			//Primero agrego los fields que NO son many to one
			itemClass.declaredFields.each{
				if(!it.isSynthetic()){
					String t=it.getType().getName();
				   //System.out.println("Name"+it.getName()+" Type "+t);
					if(!(t.equals("java.lang.Object") || t.equals("java.util.Set") || t.equals("org.apache.commons.logging.Log") || t.equals("org.grails.datastore.gorm.GormStaticApi") || t.equals("org.grails.datastore.gorm.GormInstanceApi") || t.equals("org.grails.datastore.gorm.GormValidationApi") || t.equals("org.springframework.validation.Errors") || t.equals("org.grails.plugins.web.controllers.api.ControllersDomainBindingApi") || t.equals("org.grails.plugins.converters.api.ConvertersApi") || t.equals("java.util.List") || it.getName().equals("version"))){
						if(!it.getType().getSuperclass().getName().equals("crm.CrmDomain")){//si NO es una relacion many to one
							columnList.add(new ReportDesignerColumn(tableName, it));
						}
					}
				}
			}
			//Luego agrego los fields no son many to one
			itemClass.declaredFields.each{
				if(!it.isSynthetic()){
					String t=it.getType().getName();
				   //System.out.println("Name"+it.getName()+" Type "+t);
					if(!(t.equals("java.lang.Object") || t.equals("java.util.Set") || t.equals("org.apache.commons.logging.Log") || t.equals("org.grails.datastore.gorm.GormStaticApi") || t.equals("org.grails.datastore.gorm.GormInstanceApi") || t.equals("org.grails.datastore.gorm.GormValidationApi") || t.equals("org.springframework.validation.Errors") || t.equals("org.grails.plugins.web.controllers.api.ControllersDomainBindingApi") || t.equals("org.grails.plugins.converters.api.ConvertersApi") || t.equals("java.util.List") || it.getName().equals("version"))){
						if(it.getType().getSuperclass().getName().equals("crm.CrmDomain")){//si es una relacion many to one
							columnList.add(new ReportDesignerColumn(tableName, it));
						}
					}
				}
			}
		}
		return columnList;
	}
}
