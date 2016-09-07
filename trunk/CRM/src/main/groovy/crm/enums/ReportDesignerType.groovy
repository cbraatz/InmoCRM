package crm.enums

public enum ReportDesignerType {
	REAL_ESTATE(["ManagedProperty","Concession"])
	private final List<String> domainObjects;

	public ReportDesignerType(List<String> domainObjects) {
		this.domainObjects=domainObjects;
	}
	public List<String> getDomainObjects(){
		return this.domainObjects;
	}
}
