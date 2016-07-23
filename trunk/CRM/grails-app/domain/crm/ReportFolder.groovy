package crm

class ReportFolder {
	String name;
	CrmUser crmUser;
	Boolean isPublic;
	static hasMany = [reportDesigners:ReportDesigner, classicReports:ClassicReport];
    static constraints = {
		name(blank:false, nullable:false, size:1..50);
		crmUser(nullable:false);
		isPublic(blank:false, nullable:false);
    }
}
