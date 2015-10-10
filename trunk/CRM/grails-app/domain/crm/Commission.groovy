package crm

class Commission {
	Float percentage;
	String description;
	Double fixedAmount;
	Double taxTotal;
	Currency currency;
	CrmUser owner;
	Partner partner;
	CommissionRate commissionRate;
	static hasMany = [expenses:Expense];
    static constraints = {
		percentage(blank: true, nullable:true);
		description(blank: true, nullable:true, widget:'textArea', size:0..200);
		fixedAmount(blank: true, nullable:true);
		currency(nullable:false);
		owner(nullable:false);
		taxTotal(blank: false, nullable:false);
		partner(nullable:false);
		commissionRate(nullable:false);
    }
}
