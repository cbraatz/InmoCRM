package crm.commands

import org.apache.commons.collections.ListUtils
import org.apache.commons.collections.Factory
import crm.FeatureByProperty
import grails.validation.Validateable

class FeatureByPropertyCommand implements Validateable{
	List<FeatureByProperty> pfitems = ListUtils.lazyList([], {new FeatureByProperty()} as Factory);
	
	public FeatureByPropertyCommand(List<FeatureByProperty> fbp){
		fbp.each {
			pfitems.add(it);
		}
	}
}
