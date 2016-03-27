package crm

class CrmUri implements grails.validation.Validateable{
	String controller;
	String action;
	String id;
	
	public CrmUri() { }
	
	public CrmUri(String controllerName, String actionName, String idValue) {
		this.controller=controllerName;
		this.action=actionName;
		this.id=idValue;
	}
}
