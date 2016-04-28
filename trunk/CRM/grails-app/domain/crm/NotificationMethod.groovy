package crm

class NotificationMethod {
	String name;
	Boolean isEmail;
	Boolean isSms
	Boolean isInternetMessage1;
	static hasMany = [tasks:Task];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
	}
}
