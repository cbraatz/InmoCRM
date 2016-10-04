package crm

class Inbox extends CrmDomain{

    static constraints = {
    }
	@Override
	public static String getPluralName(){
		return "inboxes";
	}
}
