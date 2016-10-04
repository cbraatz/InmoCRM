package crm

class Gender extends CrmDomain{
	String name;
	static hasMany = [partners:Partner, clients:Client];
	
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
	}
	@Override
	public static String getPluralName(){
		return "genders";
	}
}
