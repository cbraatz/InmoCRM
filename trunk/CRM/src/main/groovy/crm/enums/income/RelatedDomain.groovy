package crm.enums.income

import java.util.List;

import crm.enums.data.BooleanDataValue;

public enum RelatedDomain {
	NONE(null),//none
	CONCESSION("Concession")
	private final String className;
	public RelatedDomain(String className) {
		this.className=className;
		
	}
	/*public static final List<RelatedDomain> getAllValues() {
		List<RelatedDomain> list=new ArrayList<RelatedDomain>();
		for (RelatedDomain rd : this.values()) {
			list.add(rd);
		}
		return list;
	}*/
}
