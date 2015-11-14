package crm.commands

import crm.FeatureByBuilding
import grails.validation.Validateable
import org.apache.commons.collections.*

class FeatureByBuildingCommand implements Validateable{
	List<FeatureByBuilding> bfitems = org.apache.commons.collections.ListUtils.lazyList([], {new FeatureByBuilding()} as Factory);
	
	public FeatureByBuildingCommand(List<FeatureByBuilding> fbb){
		fbb.each {
			bfitems.add(it);
		}
	}
}
