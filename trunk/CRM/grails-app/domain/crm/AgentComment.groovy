package crm

class AgentComment extends CrmDomain{
	String contact;
	Locale locale;
	CrmUser crmUser;
    static constraints = {
		contact(blank:false, nullable:false, widget:'textArea', size:100..255);
		locale(blank:false, nullable:false);
		crmUser(blank:false, nullable:false);
    }
	@Override
	public static String getPluralName(){
		return "agentComments";
	}
}
