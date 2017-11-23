package crm

class Action extends CrmDomain{
	Date date;
	String description;
	ManagedProperty managedProperty;
	ActionType actionType;
	CrmUser crmUser;
	Float cost;
	Currency currency;
    static constraints = {
		date(blank:false, nullable:false);
		description(blank:false, nullable:false, widget:'textArea', size:1..300);
		managedProperty(blank:false, nullable:false);
		actionType(blank:false, nullable:false);
		crmUser(blank:true, nullable:true);
		cost(blank:true, nullable:true);
		currency(blank:true, nullable:true);
    }
	@Override
	public static String getPluralName(){
		return "actions";
	}
}
