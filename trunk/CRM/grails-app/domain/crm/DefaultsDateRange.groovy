package crm

class DefaultsDateRange {
	Date start;
	Date end;
	Currency currency;
	TaxRate taxRate;
    static constraints = {
		start(nullable:false);
		end(nullable:true);//end = null when is actual default
		currency(nullable:true);
		taxRate(nullable:true);
    }
}
