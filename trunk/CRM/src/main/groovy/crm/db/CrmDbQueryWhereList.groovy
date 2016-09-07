package crm.db;

import java.util.ArrayList;
import java.util.List;

import crm.ReportDesignerColumn;
import crm.enums.FilterCriteria;

public class CrmDbQueryWhereList {
	private final List<ReportFilterValue> filterValues=new ArrayList<ReportFilterValue>();
	private final String START_STRING="WHERE ";
	private final String SEPARATOR_STRING=" , ";
	public CrmDbQueryWhereList(List<ReportDesignerColumn> reportDesignerColumn) {
		reportDesignerColumn.each{
			this.filterValues.add(new ReportFilterValue(it.primaryFilterValue,	it.secondaryFilterValue, FilterCriteria.valueOf(it.filterCriteria)));
		}
	}
	public String getWhereClause(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.START_STRING);
		boolean first=true;
		filterValues.each{
			if(true==first){
				//sb.append(it.getWhereClause(String column));ver para juntar los 3 xq column debe contener c.name por ejemplo, no solo name
				first=false;
			}else{
				sb.append(this.SEPARATOR_STRING+it);
			}
		}
		return sb.toString();
	}
}
