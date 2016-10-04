package crm.enums

import crm.db.CrmDbTable

public enum ReportDesignerType {
	REAL_ESTATE([new CrmDbTable("Concession",null,false), new CrmDbTable("ManagedProperty", "Concession",false)]),//primero el principal sin parent, el que no es FK en ninguna otra tabla
	USERS([new CrmDbTable("CrmUser",null,false)])
	private final List<CrmDbTable> domainObjects;

	public ReportDesignerType(List<CrmDbTable> domainObjects) {
		this.domainObjects=domainObjects;
	}
	public List<CrmDbTable> getDomainObjects(){
		return this.domainObjects;
	}
	public Class getMainDomainClass(){
		for(CrmDbTable tab:domainObjects){
			if(null==tab.getParent()){
				return Class.forName("crm."+tab.getName());
			}
		}
		return null;
	}
}
