/*package crm.commands

import org.apache.commons.collections.ListUtils
import org.apache.commons.collections.Factory
import crm.Neighborhood
import grails.validation.Validateable

class TestCommand implements Validateable{
	List<Neighborhood> items = ListUtils.lazyList([], {new Neighborhood()} as Factory);
	
	public TestCommand(List<Neighborhood> ns){
		items=ns;
	}
}*/

/* poner esto en la vista y en el controller pasarlo por parametro
 * http://stackoverflow.com/questions/19406065/submitting-multiple-grails-domain-objects-at-once
 * <g:set var="command" value="${new TestCommand(crm.Neighborhood.list())}" />
                <dl>
	                <g:each in="${command.items}" var="item" status="i">
	                	<dt>name: </dt>
	                	<dd>
					  		<g:textField name="items[$i].name" value="${item.name}" />
					  	</dd>
					</g:each>
				</dl>
 * */
