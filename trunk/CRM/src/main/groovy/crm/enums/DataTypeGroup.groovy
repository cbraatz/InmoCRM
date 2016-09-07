package crm.enums



public enum DataTypeGroup {
	STRINGS("Strings"),
	CHARACTERS("Characters"),
	NUMBERS("Numbers"),
	BOOLEANS("Booleans"),
	DATES("Dates"),	
	DOMAIN_CLASSES("Domain-Classes")
	private final String name;

	public DataTypeGroup(String name) {
		this.name=name;
	}
}