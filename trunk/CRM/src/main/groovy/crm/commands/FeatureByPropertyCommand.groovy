package crm.commands

import crm.FeatureByProperty
import grails.validation.Validateable
import org.apache.commons.collections.*

class FeatureByPropertyCommand implements Validateable{
	List<FeatureByProperty> pfitems = ListUtils.lazyList([], {new FeatureByProperty()} as Factory);
	
	public FeatureByPropertyCommand(List<FeatureByProperty> fbp){
		fbp.each {
			pfitems.add(it);
		}
	}
}
