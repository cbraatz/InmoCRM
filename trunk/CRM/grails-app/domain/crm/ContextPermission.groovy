package crm

class ContextPermission {
	String name;
	String description;
	String permissionId;
	static hasMany = [contextPermissionRoles: ContextPermissionRole];
	
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..50);
		description(blank:true, nullable:true, unique:true, size:0..100);
		permissionId(blank:false, nullable:false, unique:true);
    }
}
