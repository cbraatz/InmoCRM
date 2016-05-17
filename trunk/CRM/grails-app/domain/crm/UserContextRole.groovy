package crm

class UserContextRole extends CrmDomain{
	CrmUser user;
	ContextRole contextRole;
	
    static constraints = {
		user(nullable:false);
		contextRole(nullable:false);
    }
}
