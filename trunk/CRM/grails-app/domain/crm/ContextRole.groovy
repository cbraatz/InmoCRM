package crm

class ContextRole extends CrmDomain{
	String name;
	Boolean isActive;
	static hasMany = [contextPermissionRoles: ContextPermissionRole, userContextRole: UserContextRole];
	
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		isActive(nullable:false);
    }
	
	@Override
	public static String getPluralName(){
		return "contextRoles";
	}
}
