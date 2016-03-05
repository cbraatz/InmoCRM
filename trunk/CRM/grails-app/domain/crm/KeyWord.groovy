package crm

class KeyWord {
	String keys;
	Locale locale
	PropertyType propertyType;
	
    static constraints = {
		keys(blank:false, nullable:false, widget:'textArea', size:10..500);
		locale(blank:false, nullable:false);
		propertyType(blank:false, nullable:false);
    }
}
