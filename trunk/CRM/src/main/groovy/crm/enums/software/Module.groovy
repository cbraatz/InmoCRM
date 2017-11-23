package crm.enums.software

import java.util.List;

import crm.enums.data.BooleanDataValue;

public enum Module {
	//los controllers no deben repetirse en los modulos sino al hacer la lista de los modulos disponibles se repetirian
	//BASIC(),
	//FINANCE(),
	REAL_ESTATE(Arrays.asList(CrmController.a1,CrmController.a2,CrmController.a3,CrmController.b1,CrmController.b2,CrmController.b3,CrmController.b4,CrmController.c1,CrmController.c2,CrmController.c3,CrmController.c4,CrmController.c5,CrmController.c6,CrmController.c7,CrmController.c8,CrmController.c9,CrmController.e1,CrmController.e2,CrmController.e3,CrmController.h1,CrmController.i1,CrmController.i2,CrmController.i3,CrmController.i4,CrmController.i5,CrmController.k1,CrmController.m1,CrmController.n1,CrmController.p1,CrmController.p2,CrmController.p3,CrmController.p4,CrmController.p5,CrmController.p6,CrmController.r1,CrmController.s1,CrmController.s2,CrmController.t1,CrmController.u1,CrmController.u2,CrmController.u3,CrmController.v1,CrmController.w1,CrmController.z1));
	//REPORT()	
	
	private final List<CrmController> controllers;

	public Module(List<CrmController> controllers) {
		this.controllers=controllers;
	}
	
	public List <CrmController> getControllers(){
		return this.controllers;
	}
}
