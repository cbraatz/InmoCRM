package crm

import crm.db.CrmDbTable;
import crm.enums.ReportDesignerType;
//import crm.enums.DataType;
import crm.exception.CRMException;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
	/*public ResultSet void executeReportQuery(List<ReportDesignerColumn> columns){
		String query=this.getReportQuery(columns);
		System.err.println("Executing: "+query);
		def res=this.getReportDesignerType().getMainDomainClass().executeQuery(query);
		System.err.println(res.size()+"  "+res);
	}*/
	/*public List<String> getColumnNames(){
		List<String> cols=new ArrayList<String>();
		res.each{
			cols.add(it.displayName);
		}
		return cols;
	}*/
	/*public int getColumnCount(){
		int c=0;
		res.each{
			c++;
		}
		return c;
	}*/
	private List<ReportDesignerColumn> getReportDesignerColumnsFromDb(){
		List<ReportDesignerColumn> columns=new ArrayList<>();
		def res=ReportDesignerColumn.findAllByReportDesigner(this);
		res.each{
			columns.add(it);
		}
		return columns;
	}
	/*public List<List<String>> getAllRows(){
		if(this.id==null){
			throw new CRMException("Query can not be get from not saved ReportWizard object.");
		}
		CrmReportQueryBuilder queryBuilder= new CrmReportQueryBuilder(this.getReportDesignerColumnsFromDb(), ReportDesignerType.valueOf(this.reportType));
		String query=queryBuilder.getBuildedQuery();
		List<List<Object>> res=this.getReportDesignerType().getMainDomainClass().executeQuery(query);
		for(List<Object> li:res){
			System.out.print("\n");
			for(int i=0;i<li.size();i++){
				Object ob=li.get(i);
				ob=queryBuilder.getSelectedReportColumnByIndex(i).getDataType().getValueAsString(ob);//setea el objeto a valor string correcto obtenido de DataType
				System.out.print(ob+"\t");
			}
		}
		return res;
	}*/
}
