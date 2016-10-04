package crm

class ClassicReportTemplate extends CrmDomain{
	String name;
	String description;
	static hasMany = [classicReports:ClassicReport];
	static constraints = {
		name(blank:false, nullable:false, size:1..50);
		description(blank:true, nullable:true, widget:'textArea', size:0..200);
	}
	@Override
	public static String getPluralName(){
		return "classicReportTemplates";
	}
}
