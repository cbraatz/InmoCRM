package crm

class CustomReportTemplate extends CrmDomain{
	String name;
	String description;
	Date date;
	byte[] fileData;
	static hasMany = [customReports:CustomReport];
	static constraints = {
		name(blank:false, nullable:false, size:1..50);
		description(blank:true, nullable:true, widget:'textArea', size:0..200);
		date(blank:false, nullable:false);
		fileData(blank: false, nullable:false, maxSize:5242880);
	}
	@Override
	public static String getPluralName(){
		return "customReportTemplates";
	}
}
