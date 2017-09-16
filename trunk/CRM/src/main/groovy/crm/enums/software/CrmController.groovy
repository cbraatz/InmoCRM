package crm.enums.software
public enum CrmController {
	a1("action", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),//fm
	a2("address", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	a3("agentComment", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	b1("building", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed), false),
	b2("buildingFeatureByLanguage", Arrays.asList(CrmAction.sh), false),
	b3("buildingFeature", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de, CrmAction.sp), false),
	b4("buildingType", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	c1("city", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	//cx("classicReport", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	//cy("classicReportTemplate", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), true),
	c2("client", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	c3("commissionByConcession", Arrays.asList(CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	c4("commissionRate", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	c5("concession", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	c6("contact", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	c7("contextPermissionCategory", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de, CrmAction.sp), true),
	c8("crmUser", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	e1("expense", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	e2("expensePayment", Arrays.asList(CrmAction.ix, CrmAction.sh), false),
	e3("expenseType", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), true),
	h1("home", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	i1("income", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	i2("incomePayment", Arrays.asList(CrmAction.ix, CrmAction.sh), false),
	i3("incomeType", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), true),
	i4("incomingInvoice", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), true),
	i5("issuedInvoice", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de, CrmAction.sp), false),
	k1("keyWorkd", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	//l1("login", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	m1("managedProperty", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed), false),
	n1("neighborhood", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	p1("partner", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	p2("payment", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de, CrmAction.sp), false),
	p3("propertyDemand", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	p4("propertyFeatureByLanguage", Arrays.asList(CrmAction.sh), false),
	p5("propertyFeature", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de, CrmAction.sp), false),
	p6("propertyType", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	r1("reportDesigner", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	s1("search", Arrays.asList(CrmAction.sh), false),
	t1("task", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	
	
	t2("test", Arrays.asList(CrmAction.sp), false),
	
	
	u1("upload", Arrays.asList(CrmAction.ix, CrmAction.ed, CrmAction.de), false),
	u2("userGroup", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de, CrmAction.sp), false),
	v1("vendor", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	w1("webPage", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false),
	z1("zone", Arrays.asList(CrmAction.ix, CrmAction.sh, CrmAction.cr, CrmAction.ed, CrmAction.de), false);
	
	private final String controllerName;
	private final List<CrmAction> actions;
	private final boolean isAdminOnly;
	
	private CrmController(String controllerName, List<CrmAction> actions, boolean isAdminOnly) {
		this.controllerName=controllerName;
		this.actions=actions;
		this.isAdminOnly=isAdminOnly;
	}
	public static CrmController getCrmControllerByName(String controllerName){
		for(CrmController c:this.values()){
			if(c.controllerName.equals(controllerName)){
				return c;
			}
		}
		return null;
	}
	public boolean isCrmControllersAvailableForCurrentPlan(Plan softwarePlan){
		for(Module m:softwarePlan.getModules()){
			for(CrmController c:m.getControllers()){
				if(c.equals(this)){
					return true;
				}
				
			}
		}
		return false;
	}
	public boolean isAdminOnly(){
		return this.isAdminOnly;
	}
}
