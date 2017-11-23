package crm

class CommissionByProperty extends CrmDomain{
	Float percentage;
	String description;
	Double amount;
	Currency currency;
	CrmUser crmUser;
	Partner partner;
	CommissionRate commissionRate;
	ManagedProperty managedProperty;
	static hasMany = [expenses:Expense];
    static constraints = {
		percentage(blank: true, nullable:true, min:0F, max:100F, scale:2);
		description(blank: true, nullable:true, widget:'textArea', size:0..200);
		amount(blank: true, nullable:true);
		currency(nullable:false);
		crmUser(nullable:false);
		partner(nullable:false);
		commissionRate(nullable:false);
		managedProperty(nullable:false);
    }
	@Override
	public static String getPluralName(){
		return "commissionsByProperty";
	}
}
