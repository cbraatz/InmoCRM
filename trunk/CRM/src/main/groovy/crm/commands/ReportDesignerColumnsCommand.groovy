package crm.commands

import java.util.List;
import crm.ReportDesigner
import crm.ReportDesignerColumn;
import grails.validation.Validateable

class ReportDesignerColumnsCommand implements Validateable{
	List<ReportDesignerColumn> columnList=new ArrayList<ReportDesignerColumn>();
	
	public ReportDesignerColumnsCommand(){	}
	
	public ReportDesignerColumnsCommand(ReportDesigner reportDesigner){
		this.columnList=reportDesigner.getReportDesignerColumnList()
	}
	public List<ReportDesignerColumn> getColumnList(){
		return this.columnList;
	}
}
