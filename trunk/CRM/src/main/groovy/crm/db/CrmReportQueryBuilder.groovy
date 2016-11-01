package crm.db

import crm.ReportDesignerColumn
import crm.exception.CRMException
import crm.enums.FilterCriteria;
import crm.enums.ReportDesignerType;

import java.util.List;

class CrmReportQueryBuilder {
	private CrmDbTableList tableList;
	private List<SelectedReportColumn> selectedColumns=new ArrayList<SelectedReportColumn>();
	private List<ReportFilterValue> filterValues=new ArrayList<ReportFilterValue>();
	private final String WHERE_START_STRING=" WHERE ";
	private final String SELECT_START_STRING="SELECT ";
	private final String SEPARATOR_STRING=", ";
	private final String WHERE_SEPARATOR_STRING=" and ";
	private final String FROM_START_STRING=" FROM ";
	private final String INNER_JOIN_STRING=" INNER JOIN ";
	private final String JOIN_ON_STRING=" ON ";
	private final String AS_STRING=" AS ";
	public CrmReportQueryBuilder(List<ReportDesignerColumn> columns, ReportDesignerType reportDesignerType){
		if(null==columns){
			throw new IllegalArgumentException(message(code: 'default.invalid.paramethers.error', args: ["columns = null"]));
		}
		tableList=new CrmDbTableList(columns, reportDesignerType);
		//update column name adding table alias
		String prevTableName, prevParentTableAlias, newPropName="";
		String tblName=null;
		columns.each{
			if(it.selected.booleanValue()==true || it.groupBy.booleanValue()==true || it.filterBy.booleanValue()==true){
				tblName=(it.foreignTableName!=null?it.foreignTableName:it.tableName);
				if(!prevTableName.equals(tblName)){
					prevTableName=tblName;
					prevParentTableAlias=tableList.getTableAliasByName(tblName);
				}
				newPropName=prevParentTableAlias+"."+it.propertyName;
				if(true==it.selected.booleanValue()){
					this.selectedColumns.add(new SelectedReportColumn(newPropName, it.dataType, it.displayName));
				}
				if(true==it.filterBy.booleanValue()){
					this.filterValues.add(new ReportFilterValue(newPropName, it.primaryFilterValue,	it.secondaryFilterValue, FilterCriteria.valueOf(it.filterCriteria)));
				}
			}
		}
	}
	public SelectedReportColumn getSelectedReportColumnByIndex(int idx){
		return this.selectedColumns.get(idx);
	}
	public List<String> getSelectedColumnDisplayNames(){
		List<String> list=new ArrayList<String>();
		this.selectedColumns.each{
			list.add(it.getDisplayName());
		}
		return list;
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
					if(tbl.getParent()!=null){
						throw new CRMException("First table should have null parent. Table name = "+tbl.getName());
					}
					sb.append(tbl.getName());
					first=false;
				}else{
					sb.append(this.INNER_JOIN_STRING);
					sb.append(this.tableList.getTableAliasByName(tbl.getParent()));
					sb.append(".");
					sb.append(Class.forName("crm."+tbl.getName()).getPluralName());
				}
				
				sb.append(this.AS_STRING);
				sb.append(tbl.getAlias());
			}
		}
		for (CrmDbTable tbl : this.tableList.getTables()){
			if(tbl.isForeign){
				if(true==first){
					sb.append(tbl.getName());
					first=false;
				}else{
					sb.append(this.SEPARATOR_STRING);
					sb.append(tbl.getName());
				}
				
				sb.append(this.AS_STRING);
				sb.append(tbl.getAlias());
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
				sb.append(it.name);
				first=false;
			}else{
				sb.append(this.SEPARATOR_STRING+it.name);
			}
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	private String getWhereClause(){
		StringBuilder sb = new StringBuilder();
		boolean first=true;
		for (CrmDbTable tbl : this.tableList.getTables()){
			if(tbl.isForeign){
				if(true==first){
					sb.append(WHERE_START_STRING);
					first=false;
				}else{
					sb.append(this.WHERE_SEPARATOR_STRING);
				}
				sb.append(this.tableList.getTableAliasByName(tbl.getName()));
				sb.append(".id = ");
				sb.append(this.tableList.getTableAliasByName(tbl.getParent()));
				sb.append(".");
				sb.append(tbl.getForeignKeyNameFromOneToManySet());
				sb.append(".id");			
			}
			
		}
		filterValues.each{
			if(true==first){
				sb.append(WHERE_START_STRING);
				first=false;
			}else{
				sb.append(this.WHERE_SEPARATOR_STRING);
			}
			sb.append(it.whereCondition);
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
}