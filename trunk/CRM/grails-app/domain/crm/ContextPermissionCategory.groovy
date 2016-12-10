package crm

class ContextPermissionCategory extends CrmDomain{
	String name;
	Boolean isAll;
	Boolean isNone;
	
	static hasMany = [crmActionByContextCategories:CrmActionByContextCategory, userGroups:UserGroup];
    static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..20);
		isAll(blank:false);
		isNone(blank:false);
    }
	public ContextPermissionCategory(){}
	
	public ContextPermissionCategory(def params){
		this.properties = params;
	}
	@Override
	public static String getPluralName(){
		return "ContextPermissionCategories";
	}
}
