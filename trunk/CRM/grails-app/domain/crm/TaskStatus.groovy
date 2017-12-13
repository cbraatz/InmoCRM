package crm

class TaskStatus extends CrmDomain{
	String name;
	String internalID;
	static hasMany = [tasks:Task];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		internalID(blank:false, nullable:false, unique:true, size:1..10);
	}
	@Override
	public static String getPluralName(){
		return "taskStatuses";
	}
}
