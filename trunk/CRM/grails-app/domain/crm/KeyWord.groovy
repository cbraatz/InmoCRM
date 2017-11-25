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
	
	public String getExtendedKeys(ManagedProperty managedProperty) {
		StringBuilder res=new StringBuilder()
		res.append(this.keys);
		String prep=this.locale.language.prepositionOfPlace;
		String[] array=this.keys.split(",");
		for(String s:array) {
			s=s.trim();//saca los espacios de adelante y atras
		}

		if(managedProperty.address.city) {
			for(String s:array) {
				res.append(", "+s+" "+prep+" "+managedProperty.address.city.name);
			}
		}
		if(managedProperty.address.neighborhood) {
			for(String s:array) {
				res.append(", "+s+" "+prep+" "+managedProperty.address.neighborhood.name);
			}
		}
		if(managedProperty.address.zone) {
			for(String s:array) {
				res.append(", "+s+" "+prep+" "+managedProperty.address.zone.name);
			}
		}
		if(managedProperty.address.city?.department) {
			for(String s:array) {
				res.append(", "+s+" "+prep+" "+managedProperty.address.city.department.name);
			}
		}
		if(managedProperty.address.city?.department?.country) {
			for(String s:array) {
				res.append(", "+s+" "+prep+" "+managedProperty.address.city.department.country.name);
			}
		}
		if(!this.keys.isEmpty()) {
			res.append(".");
		}
		return res.toString();
	}
}
