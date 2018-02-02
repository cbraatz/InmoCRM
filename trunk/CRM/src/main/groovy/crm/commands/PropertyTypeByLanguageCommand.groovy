package crm.commands

import crm.DimensionMeasuringUnit
import crm.PropertyTypeByLanguage
import crm.PropertyType
import crm.Language
import grails.validation.Validateable
import crm.ItemNameByLanguage

class PropertyTypeByLanguageCommand implements Validateable{
	List<ItemNameByLanguage> items = new ArrayList<ItemNameByLanguage>();
	
	public PropertyTypeByLanguageCommand(PropertyType propertyType){
		Language defLan=Language.getDefaultLanguage();
		def savedItems=PropertyTypeByLanguage.findAllByPropertyType(propertyType);
		boolean exists=false;
		PropertyTypeByLanguage ptbl=null;
		Language.findAll().each {
			if(!it.id.equals(defLan.id)){
				exists=false;
				savedItems.each { si->
					if(!si.language.id.equals(defLan.id) && it.id.equals(si.language.id)){
						exists=true;
						ptbl=si;
					}
				}
				if(exists==true){
					items.add(new ItemNameByLanguage(ptbl.name,ptbl.nameForWeb, ptbl.plural, ptbl.language));
				}else{
					items.add(new ItemNameByLanguage(null,null,null,it));
				}
				
			}
		}
	}
}