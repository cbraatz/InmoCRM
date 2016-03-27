package crm

class RealEstateAction {
	Date date;
	String description;
	Concession concession;
	RealEstateActionType realEstateActionType;
	Partner partner;
	Float cost;
	Currency currency;
    static constraints = {
		date(blank:false, nullable:false);
		description(blank:false, nullable:false, widget:'textArea', size:1..300);
		concession(blank:false, nullable:false);
		realEstateActionType(blank:false, nullable:false);
		partner(blank:true, nullable:true);
		cost(blank:true, nullable:true);
		currency(blank:true, nullable:true);
    }
}
