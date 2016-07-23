package crm

class ClassicReportTemplate {
	String name;
	String description;
	static hasMany = [classicReports:ClassicReport];
	static constraints = {
		name(blank:false, nullable:false, size:1..50);
		description(blank:false, nullable:false, size:1..300);
	}
}
