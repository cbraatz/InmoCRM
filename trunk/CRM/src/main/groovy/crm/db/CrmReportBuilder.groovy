package crm.db

import crm.ReportDesignerColumn
import java.util.List;

class CrmReportBuilder {
	CrmDbTableList tableList;
	CrmDbSelectedColumnList selectedColumnList;
	CrmDbQueryWhereList whereList;
	
	public CrmReportBuilder(List<ReportDesignerColumn> columns) {
		tableList=new CrmDbTableList(columns);
		selectedColumnList=new CrmDbSelectedColumnList(columns, tableList);
		whereList=new CrmDbQueryWhereList(columns);
	}

}
