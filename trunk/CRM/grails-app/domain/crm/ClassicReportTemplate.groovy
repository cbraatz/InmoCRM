package crm

class ClassicReportTemplate extends CrmDomain{
	String name;
	String description;
	static hasMany = [classicReports:ClassicReport];
	static constraints = {
		name(blank:false, nullable:false, size:1..50);
		description(blank:true, nullable:true, size:0..200);
	}
}
