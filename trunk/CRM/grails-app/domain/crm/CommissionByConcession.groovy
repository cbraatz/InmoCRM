package crm

class CommissionByConcession {
	Float amount;
	Float percentage;
	Concession concession;
	Partner partner;

	static constraints = {
		amount(blank: false, nullable: false, scale:2);
		percentage(blank: false, nullable: false, min:0F, max:100F, scale:2);
		concession(nullable: false);
		partner(nullable: false);
	}
}
