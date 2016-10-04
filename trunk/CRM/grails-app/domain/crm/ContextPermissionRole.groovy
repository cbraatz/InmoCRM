package crm

class ContextPermissionRole extends CrmDomain{
	ContextRole contextRole;
	ContextPermission contextPermission;
	
    static constraints = {
		contextRole(blank:false, nullable:false, size:1..50);
		contextPermission(blank:false, nullable:false);
    }
	@Override
	public static String getPluralName(){
		return "contextPermissionRoles";
	}
}
