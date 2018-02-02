package crm

import java.util.List;

class CustomReport extends CrmDomain{
	String name;
	CustomReportTemplate customReportTemplate;
	CrmUser crmUser;
	ReportFolder reportFolder;
	
	static constraints = {
		name(blank:false, nullable:false, size:1..50);
		customReportTemplate(nullable:false);
		crmUser(nullable:false);
		reportFolder(nullable:true);
	}
	@Override
	public static String getPluralName(){
		return "customReports";
	}
}
