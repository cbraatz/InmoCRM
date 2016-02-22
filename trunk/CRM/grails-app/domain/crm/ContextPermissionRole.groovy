package crm

class ContextPermissionRole {
	ContextRole contextRole;
	ContextPermission contextPermission;
	
    static constraints = {
		contextRole(blank:false, nullable:false, size:1..50);
		contextPermission(blank:false, nullable:false, unique:'contextRole');
    }
}
