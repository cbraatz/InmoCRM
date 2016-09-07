package crm.db

import crm.enums.FilterCriteria

class ReportFilterValue {
	private String column;
	private String value1;
	private String value2;
	private FilterCriteria criteria;
	public ReportFilterValue(String column, String value1, String value2, FilterCriteria criteria) {
		this.column=column;
		this.value1=value1;
		this.value2=value2;
		this.criteria=criteria;
	}
	public String getWhereClause(){
		return criteria.generateWhereClause(column, value1, value2);
	}
}
