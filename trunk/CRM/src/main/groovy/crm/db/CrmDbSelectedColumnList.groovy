package crm.db

import java.util.Map;

import crm.ReportDesignerColumn
import crm.Utils;

class CrmDbSelectedColumnList {
	private final List<String> selectedColumns=new ArrayList<String>();
	private final String START_STRING="SELECT ";
	private final String SEPARATOR_STRING=" , ";
	public CrmDbSelectedColumnList(List<ReportDesignerColumn> reportDesignerColumn, CrmDbTableList tables) {
		boolean exists=false;
		reportDesignerColumn.each{
			if(true==it.selected){
				selectedColumns.add(tables.getTableAliasByTableName(it.tableName)+"."+it.propertyName);
			}
			System.out.println(tables.getTableAliasByTableName(it.tableName)+"."+it.propertyName);
		}
	}
	public List<String> getSelectedColumns(){
		return selectedColumns;
	}
	public String getSelectClause(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.START_STRING);
		boolean first=true;
		selectedColumns.each{
			if(true==first){
				sb.append(it);
				first=false;
			}else{
				sb.append(this.SEPARATOR_STRING+it);
			}
		}
		return sb.toString();
	}
}
