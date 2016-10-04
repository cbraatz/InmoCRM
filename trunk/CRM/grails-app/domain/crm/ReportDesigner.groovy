package crm

import crm.db.CrmDbTable
import crm.db.CrmReportBuilder
import crm.enums.ReportDesignerType
import crm.exception.CRMException

import java.lang.reflect.Field
import java.sql.ResultSet
import java.util.ArrayList;
import java.util.List;

class ReportDesigner extends CrmDomain{
	String name;
	String description;
	Boolean hasFilter=false;
	Boolean hasGroup=false;
	Boolean hasSort=false;
	String reportType;
	CrmUser crmUser;
	ReportFolder reportFolder;

	static hasMany = [reportDesignerColumns:ReportDesignerColumn];

    static constraints = {
		name(blank:false, nullable:false, size:1..50);
		description(blank:true, nullable:true, widget:'textArea', size:0..200);
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
	@Override
	public static String getPluralName(){
		return "reportDesigners";
	}
	public List<ReportDesignerColumn> getReportDesignerColumnList() throws CRMException, NumberFormatException{
		List<ReportDesignerColumn> columnList=new ArrayList<ReportDesignerColumn>();
		ReportDesignerType rdt=this.getReportDesignerType();
		for(CrmDbTable tbl:rdt.getDomainObjects()){//solo el primero es root y los demas dependientes
			for(Field f:tbl.getAllTableFields()){
				columnList.add(new ReportDesignerColumn(tbl.getName(), tbl.getParent(), f));
			}
		}
		return columnList;
	}
	
	private ReportDesignerType getReportDesignerType(){
		return ReportDesignerType.valueOf(this.reportType);
	}
	private String getReportQuery(List<ReportDesignerColumn> columns){
		if(null==columns){
			throw new IllegalArgumentException(message(code: 'default.invalid.paramethers.error', args: ["columns = null"]));
		}
		return new CrmReportBuilder(columns).getBuildedQuery();
	}
	public /*ResultSet*/ void executeReportQuery(List<ReportDesignerColumn> columns){
		def res=this.getReportDesignerType().getMainDomainClass().executeQuery(this.getReportQuery(columns));
		System.err.println(res.size());
	}
}
