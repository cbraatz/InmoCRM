package crm

class ContractType extends CrmDomain{
	String name;
	Boolean isExclusive;
	String description;
	String templateURL;
	Float commissionPercentage;
	String billingDefaultDescription;
	static hasMany = [contracts:Contract];
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		isExclusive(nullable:false);
		description(blank:false, nullable:false, widget:'textArea', size:10..200);
		templateURL(blank:true, nullable:true, size:0..200);
		commissionPercentage(blank:false, nullable:false, min:0F, max:100F, scale:2);
		billingDefaultDescription(blank:false, nullable:false, size:1..100);
    }
	@Override
	public static String getPluralName(){
		return "contractTypes";
	}
}
