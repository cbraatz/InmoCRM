package crm

class Contract extends CrmDomain{
	Date date;
	String documentURL;
	ContractType contractType;
	String internalID;
	static hasMany = [concessions:Concession];
    static constraints = {
		date(blank: false, nullable:false);
		documentURL(blank: true, nullable:true, size:0..200);
		contractType(nullable:false);
		internalID(blank: false, nullable:false, size:1..40, unique:true);
    }
	@Override
	public static String getDefaultPropertyName(){
		return "internalID";
	}
	@Override
	public static String getPluralName(){
		return "contracts";
	}
}
