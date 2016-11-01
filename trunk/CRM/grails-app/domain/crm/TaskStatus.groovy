package crm

class TaskStatus extends CrmDomain{
	String name;
	Boolean isNew;
	Boolean isClosed;
	static hasMany = [tasks:Task];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		isNew(nullable:false);
		isClosed(nullable:false);
	}
	@Override
	public static String getPluralName(){
		return "taskStatuses";
	}
}
