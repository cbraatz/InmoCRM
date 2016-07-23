package crm

class UserContextRole extends CrmDomain{
	CrmUser crmUser;
	ContextRole contextRole;
	
    static constraints = {
		crmUser(nullable:false);
		contextRole(nullable:false);
    }
}
