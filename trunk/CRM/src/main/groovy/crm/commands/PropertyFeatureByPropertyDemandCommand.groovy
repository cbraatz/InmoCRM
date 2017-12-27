package crm.commands

import crm.PropertyFeatureByPropertyDemand
import grails.validation.Validateable

class PropertyFeatureByPropertyDemandCommand implements Validateable{
	List<PropertyFeatureByPropertyDemand> pfitems = new ArrayList<PropertyFeatureByPropertyDemand>();
	
	public PropertyFeatureByPropertyDemandCommand(List<PropertyFeatureByPropertyDemand> fbp){
		fbp.each {
			pfitems.add(it);
		}
	}
}
