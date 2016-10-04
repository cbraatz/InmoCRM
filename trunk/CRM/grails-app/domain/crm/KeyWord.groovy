package crm

class KeyWord extends CrmDomain{
	String keys;
	Locale locale
	PropertyType propertyType;
	
    static constraints = {
		keys(blank:false, nullable:false, widget:'textArea', size:10..500);
		locale(blank:false, nullable:false);
		propertyType(blank:false, nullable:false);
    }
	@Override
	public static String getPluralName(){
		return "keyWords";
	}
}
