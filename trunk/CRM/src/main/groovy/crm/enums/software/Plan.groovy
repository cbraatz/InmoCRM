package crm.enums.software

import java.util.List;

import crm.enums.data.BooleanDataValue;

public enum Plan {
	FULL(),
	REAL_ESTATE(),
	SHOP()
		
	private final Module[] modules;

	public Plan(Module[] modules) {
		this.modules=modules;
	}
}
