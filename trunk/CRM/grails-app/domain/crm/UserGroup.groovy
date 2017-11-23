package crm

class UserGroup extends CrmDomain{
	String name;
	ContextPermissionCategory contextPermissionCategory;
	//Boolean isAdmin;
	
	static hasMany = [crmUsers:CrmUser];
	static belongsTo = CrmUser;
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		contextPermissionCategory(nullable:false);
		//isAdmin(nullable:false);
    }
	@Override
	public static String getPluralName(){
		return "UserGroups";
	}
}
