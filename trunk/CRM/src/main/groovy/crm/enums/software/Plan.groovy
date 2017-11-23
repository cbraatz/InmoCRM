package crm.enums.software

import java.util.List;

import crm.enums.data.BooleanDataValue;

public enum Plan {
	FULL(new ArrayList<Module>());//hace new solo para enviar algo como parametro
	//REAL_ESTATE(Arrays.asList(Module.REAL_ESTATE)),
	//SHOP()
		
	private final List<Module> modules;

	public Plan(List<Module> modules) {
		this.modules=modules;
	}
	public List<Module> getModules(){
		return this.modules;
	}
}
