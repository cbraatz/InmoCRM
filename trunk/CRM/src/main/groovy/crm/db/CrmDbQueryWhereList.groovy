package crm.db;

import java.util.ArrayList;
import java.util.List;

import crm.ReportDesignerColumn;
import crm.enums.FilterCriteria;

public class CrmDbQueryWhereList {
	
	
	private final String SEPARATOR_STRING=", ";
	public CrmDbQueryWhereList(List<ReportDesignerColumn> reportDesignerColumn) {
		if(null==reportDesignerColumn){
			throw new IllegalArgumentException(message(code: 'default.invalid.paramethers.error', args: ["reportDesignerColumn = null"]));
		}
		reportDesignerColumn.each{
			if(true==it.filterBy.booleanValue()){
				this.filterValues.add(new ReportFilterValue(it.propertyName, it.primaryFilterValue,	it.secondaryFilterValue, FilterCriteria.valueOf(it.filterCriteria)));
			}
		}
	}
	
}
