package crm.commands

import crm.FeatureByProperty
import grails.validation.Validateable

class FeatureByPropertyCommand implements Validateable{
	List<FeatureByProperty> pfitems = new ArrayList<FeatureByProperty>();
	
	public FeatureByPropertyCommand(List<FeatureByProperty> fbp){
		fbp.each {
			pfitems.add(it);
		}
	}
}
