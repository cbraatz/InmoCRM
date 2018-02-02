package crm.commands

import crm.DimensionMeasuringUnit
import crm.ItemNameByLanguage
import crm.BuildingFeatureByLanguage
import crm.BuildingFeature
import crm.BuildingType
import crm.Language
import grails.validation.Validateable

class BuildingFeatureByLanguageCommand implements Validateable{
	List<ItemNameByLanguage> items = new ArrayList<ItemNameByLanguage>();
	
	public BuildingFeatureByLanguageCommand(BuildingFeature buildingFeature){
		Language defLan=Language.getDefaultLanguage();
		def savedFeatures=BuildingFeatureByLanguage.findAllByBuildingFeature(buildingFeature);
		boolean exists=false;
		BuildingFeatureByLanguage fbl=null;
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