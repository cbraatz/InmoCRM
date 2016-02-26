package crm

class CrmUri {
	String controller;
	String action;
	String id;
	public CrmUri() {
	}
	public CrmUri(String controllerName, String actionName, String idValue) {
		this.controller=controllerName;
		this.action=actionName;
		this.id=idValue;
	}
}
