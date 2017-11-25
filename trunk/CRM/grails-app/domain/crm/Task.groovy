package crm

class Task extends CrmDomain{
	String name;
	String description;
	String estimatedTime;
	Date dateTime;
	Integer minutesBeforeNotification;
	PriorityLevel priorityLevel;
	CrmUser creator;
	CrmUser assignee;
	TaskStatus taskStatus;
	String internalID;
	
	static hasMany = [notificationMethods:NotificationMethod, comments:Comment];
	static belongsTo = NotificationMethod;
    static constraints = {
		name(blank:false, nullable:false, size:1..50);
		description(blank:false, nullable:false, widget:'textArea', size:10..500);
		estimatedTime(blank:true, nullable:true, size:0..40);
		dateTime(blank:true, nullable:true);
		minutesBeforeNotification(nullable:true);
		priorityLevel(nullable:false);
		creator(nullable:true);
		assignee(nullable:true);
		taskStatus(nullable:false);
		internalID(blank:true, nullable:true, size:0..40);
    }
	public Task(){}
	
	public Task(def params){
		this.properties = params;
	}
	@Override
	public static String getPluralName(){
		return "tasks";
	}
	@Override
	public static SearchAttribute[] searchByAttributes() {
		return [new SearchAttribute("name")];
	}
}
