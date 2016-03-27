package crm.commands

import crm.FeatureByBuilding
import grails.validation.Validateable

class FeatureByBuildingCommand implements Validateable{
	List<FeatureByBuilding> bfitems = new ArrayList<FeatureByBuilding>();
	
	public FeatureByBuildingCommand(List<FeatureByBuilding> fbb){
		fbb.each {
			bfitems.add(it);
		}
	}
}
