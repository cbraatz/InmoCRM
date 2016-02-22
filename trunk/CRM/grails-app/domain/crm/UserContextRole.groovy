package crm

class UserContextRole {
	CrmUser user;
	ContextRole contextRole;
	
    static constraints = {
		user(nullable:false);
		contextRole(nullable:false);
    }
}
