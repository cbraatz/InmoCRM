package crm

import java.util.List;

class ClassicReport extends CrmDomain{
	String name;
	ClassicReportTemplate classicReportTemplate;
	CrmUser crmUser;
	ReportFolder reportFolder;
	
	static constraints = {
		name(blank:false, nullable:false, size:1..50);
		classicReportTemplate(nullable:false);
		crmUser(nullable:false);
		reportFolder(nullable:true);
	}
	@Override
	public static String getPluralName(){
		return "classicReports";
	}
}
