package crm.commands

import org.apache.commons.collections.ListUtils
import org.apache.commons.collections.Factory
import crm.FeatureByBuilding
import grails.validation.Validateable

class FeatureByBuildingCommand implements Validateable{
	List<FeatureByBuilding> bfitems = ListUtils.lazyList([], {new FeatureByBuilding()} as Factory);
	
	public FeatureByBuildingCommand(List<FeatureByBuilding> fbb){
		fbb.each {
			bfitems.add(it);
		}
	}
}
