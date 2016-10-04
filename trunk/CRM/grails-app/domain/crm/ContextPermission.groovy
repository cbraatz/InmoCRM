package crm

class ContextPermission extends CrmDomain{
	String name;
	String description;
	String permissionID;
	static hasMany = [contextPermissionRoles: ContextPermissionRole];
	
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		description(blank:true, nullable:true, unique:true, size:0..100);
		permissionID(blank:false, nullable:false, unique:true);
    }
	@Override
	public static String getPluralName(){
		return "contextPermissions";
	}
}
