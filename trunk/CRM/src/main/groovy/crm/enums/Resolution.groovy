package crm.enums

import java.util.List;

import crm.enums.data.BooleanDataValue;

public enum Resolution {
	NONE(null),//none
	SOLD("Vendido"),
	EXPIRED("Expirado");
	private final String className;
	public Resolution(String className) {
		this.className=className;
		
	}
	/*public static final List<Resolution> getAllValues() {
		List<Resolution> list=new ArrayList<Resolution>();
		for (Resolution rd : this.values()) {
			list.add(rd);
		}
		return list;
	}*/
}
