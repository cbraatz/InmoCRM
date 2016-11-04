package crm.db

import crm.ReportDesignerColumn
import crm.exception.CRMException
import crm.enums.report.FilterCriteria;
import crm.enums.report.ReportDesignerType;

import java.util.List;

class CrmReportBuilder {
	private CrmReportQueryBuilder queryBuilder;
	public CrmReportBuilder(List<ReportDesignerColumn> columns, ReportDesignerType reportDesignerType){
		if(null==columns){
			throw new IllegalArgumentException(message(code: 'default.invalid.paramethers.error', args: ["columns = null"]));
		}
		this.queryBuilder=new CrmReportQueryBuilder(columns, reportDesignerType);
	}
}
