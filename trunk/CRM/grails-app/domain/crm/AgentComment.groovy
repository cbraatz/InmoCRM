package crm

class AgentComment {
	String contact;
	Locale locale;
	CrmUser crmUser;
    static constraints = {
		contact(blank:false, nullable:false, widget:'textArea', size:100..255);
		locale(blank:false, nullable:false);
		crmUser(blank:false, nullable:false);
    }
}
