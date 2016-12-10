package crm

class IncomeType extends CrmDomain{
	String name;
	String description;
	TaxRate taxRate;
	String billingDefaultDescription;
	String relatedDomain;
	static hasMany = [incomes:Income];
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		description(blank:false, nullable:false, widget:'textArea', size:1..200);
		taxRate(nullable:false);
		billingDefaultDescription(blank:false, nullable:false, size:1..100);
		relatedDomain(blank:false, nullable:false, size:1..15);
    }
	public IncomeType(){}
	
	public IncomeType(def params){
		this.properties = params;
	}
	
	@Override
	public static String getPluralName(){
		return "incomeTypes";
	}
}
