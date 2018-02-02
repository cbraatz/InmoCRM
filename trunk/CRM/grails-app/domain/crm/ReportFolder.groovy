package crm

class ReportFolder extends CrmDomain{
	String name;
	CrmUser crmUser;
	Boolean isPublic;
	static hasMany = [reportDesigners:ReportDesigner, customReports:CustomReport];
    static constraints = {
		name(blank:false, nullable:false, size:1..50);
		crmUser(nullable:false);
		isPublic(blank:false, nullable:false);
    }
	@Override
	public static String getPluralName(){
		return "reportFolders";
	}
}
