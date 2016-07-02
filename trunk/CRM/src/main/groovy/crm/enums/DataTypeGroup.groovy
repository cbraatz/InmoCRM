package crm.enumsimport java.util.List;



public enum DataTypeGroup {
	STRINGS("Strings"),
	CHARACTERS("Characters"),
	NUMBERS("Numbers"),
	BOOLEANS("Booleans"),
	DATES("Dates")	
	
	private final String name;

	public DataTypeGroup(String name) {
		this.name=name;
	}
}
