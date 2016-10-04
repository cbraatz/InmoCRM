package crm.db

import java.util.List;
import java.util.Map;

import crm.ReportDesignerColumn
import crm.Utils;

class CrmDbSelectedColumnList {

	public CrmDbSelectedColumnList(List<ReportDesignerColumn> reportDesignerColumn, CrmDbTableList tables) {
		if(null==reportDesignerColumn){
			throw new IllegalArgumentException(message(code: 'default.invalid.paramethers.error', args: ["reportDesignerColumn = null"]));
		}
		reportDesignerColumn.each{
			if(true==it.selected.booleanValue()){
				selectedColumns.add(it.propertyName);
			}
		}
	}
	public List<String> getSelectedColumns(){
		return selectedColumns;
	}
	
}
