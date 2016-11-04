package crm.enums.software

import java.util.List;

import crm.enums.data.BooleanDataValue;

public enum Module {
	BASIC([CrmController.address, CrmController.city]),
	FINANCE(),
	REAL_ESTATE([CrmController.action, CrmController.agentComment, CrmController.building, CrmController.buildingFeature, CrmController.buildingType]),
	REPORT()	
		
	private final CrmController[] controllers;

	public Module(CrmController[] controllers) {
		this.controllers=controllers;
	}
}
