package crm.db

import crm.ReportDesignerColumn
import crm.exception.CRMException
import crm.enums.FilterCriteria;
import java.util.List;

class CrmReportBuilder {
	private CrmDbTableList tableList;
	private List<String> selectedColumns=new ArrayList<String>();
	private List<ReportFilterValue> filterValues=new ArrayList<ReportFilterValue>();
	private final String WHERE_START_STRING=" WHERE ";
	private final String SELECT_START_STRING="SELECT ";
	private final String SEPARATOR_STRING=", ";
	private final String FROM_START_STRING=" FROM ";
	private final String INNER_JOIN_STRING=" INNER JOIN ";
	private final String AS_STRING=" AS ";
	public CrmReportBuilder(List<ReportDesignerColumn> columns){
		if(null==columns){
			throw new IllegalArgumentException(message(code: 'default.invalid.paramethers.error', args: ["columns = null"]));
		}
		tableList=new CrmDbTableList(columns);
		//update column name adding table alias
		String prevTableName, prevParentTableAlias, newPropName="";
		String tblName=null;
		columns.each{
			tblName=(it.foreignTableName!=null?it.foreignTableName:it.tableName);
			if(!prevTableName.equals(tblName)){
				prevTableName=tblName;
				prevParentTableAlias=tableList.getTableAliasByName(tblName);
			}
			newPropName=prevParentTableAlias+"."+it.propertyName;
			if(true==it.selected.booleanValue()){
				this.selectedColumns.add(newPropName);
			}
			if(true==it.filterBy.booleanValue()){
				this.filterValues.add(new ReportFilterValue(newPropName, it.primaryFilterValue,	it.secondaryFilterValue, FilterCriteria.valueOf(it.filterCriteria)));
			}
		}
	}
	public String getBuildedQuery(){
		return this.getSelectClause()+this.getFromClause()+this.getWhereClause();
	}
	
	private String getFromClause(){
		StringBuilder sb = new StringBuilder();
		sb.append(FROM_START_STRING);
		boolean first=true;
		for (CrmDbTable tbl : this.tableList.getTables()){
			if(!tbl.isForeign){
				if(true==first){
					first=false;
				}else{
					sb.append(this.INNER_JOIN_STRING);
					sb.append(this.tableList.getTableAliasByName(tbl.getParent()));
					sb.append(".");
				}
				sb.append(Class.forName("crm."+tbl.getName()).getPluralName());
				sb.append(this.AS_STRING);
				sb.append(tbl.getAlias());
			}else{
				if(true==first){
					first=false;
				}else{
					sb.append(this.INNER_JOIN_STRING);
					sb.append(tbl.getAlias());
					sb.append(".");
				}
				sb.append(Class.forName("crm."+tbl.getParent()).getPluralName());
				sb.append(this.AS_STRING);
				sb.append(this.tableList.getTableAliasByName(tbl.getParent()));
			}
			
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	private String getSelectClause(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.SELECT_START_STRING);
		boolean first=true;
		selectedColumns.each{
			if(true==first){
				sb.append(it);
				first=false;
			}else{
				sb.append(this.SEPARATOR_STRING+it);
			}
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	private String getWhereClause(){
		StringBuilder sb = new StringBuilder();
		boolean first=true;
		/*for(CrmDbTable tbl : this.tableList.getTables()){
			if(true==tbl.isForeign){
				if(true==first){
					sb.append(WHERE_START_STRING);
					first=false;
				}else{
					sb.append(this.SEPARATOR_STRING);
				}
				sb.append(tbl.getAlias()+".id = ");
				sb.append(this.tableList.getTableAliasByName(tbl.getParent()));
			}
		}*/
		filterValues.each{
			if(true==first){
				sb.append(WHERE_START_STRING);
				first=false;
			}else{
				sb.append(this.SEPARATOR_STRING);
			}
			sb.append(it.whereCondition);
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
}
