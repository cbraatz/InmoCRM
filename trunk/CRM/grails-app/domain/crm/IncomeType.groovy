package crm

class IncomeType extends CrmDomain{
	String name;
	String description;
	TaxRate taxRate;
	String billingDefaultDescription;
	Boolean isConcessionRelated;
	static hasMany = [income:Income];
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		description(blank:false, nullable:false, widget:'textArea', size:1..200);
		taxRate(nullable:false);
		billingDefaultDescription(blank:false, nullable:false, size:1..100);
		isConcessionRelated(blank:false, nullable:false);
    }
	@Override
	public static String getPluralName(){
		return "incomeTypes";
	}
}
