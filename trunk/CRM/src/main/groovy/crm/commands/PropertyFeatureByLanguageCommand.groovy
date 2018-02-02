package crm.commands

import crm.DimensionMeasuringUnit
import crm.ItemNameByLanguage
import crm.PropertyFeatureByLanguage
import crm.PropertyFeature
import crm.PropertyType
import crm.Language
import grails.validation.Validateable

class PropertyFeatureByLanguageCommand implements Validateable{
	List<ItemNameByLanguage> items = new ArrayList<ItemNameByLanguage>();
	
	public PropertyFeatureByLanguageCommand(PropertyFeature propertyFeature){
		Language defLan=Language.getDefaultLanguage();
		def savedFeatures=PropertyFeatureByLanguage.findAllByPropertyFeature(propertyFeature);
		boolean exists=false;
		PropertyFeatureByLanguage fbl=null;
		Language.findAll().each {
			if(!it.id.equals(defLan.id)){
				exists=false;
				savedFeatures.each { sf->
					if(!sf.language.id.equals(defLan.id) && it.id.equals(sf.language.id)){
						exists=true;
						fbl=sf;
					}
				}
				if(exists==true){
					items.add(new ItemNameByLanguage(fbl.name, fbl.plural, fbl.language));
				}else{
					items.add(new ItemNameByLanguage(null,null,it));
				}
				
			}
		}
	}
}
