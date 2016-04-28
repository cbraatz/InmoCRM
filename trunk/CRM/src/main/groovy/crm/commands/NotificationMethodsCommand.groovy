package crm.commands

import crm.CommandOption
import crm.FeatureByProperty
import crm.NotificationMethod
import crm.Task
import grails.validation.Validateable

class NotificationMethodsCommand implements Validateable{
	List<CommandOption> items=new ArrayList<CommandOption>();
	
	public NotificationMethodsCommand(){
		NotificationMethod.getAll().each {
			items.add(new CommandOption(it));
		}
	}
	
	public NotificationMethodsCommand(Task task){
		List<NotificationMethod> allNotificationMethods=NotificationMethod.getAll();
		/*List<NotificationMethod> selectedNotificationMethods=new ArrayList<NotificationMethod>();
		task.notificationMethods.each{
			NotificationMethod not=NotificationMethod.get("")
			selectedNotificationMethods.add(it);
		}*/
		boolean exists=false;
		allNotificationMethods.each {
			exists=false;
			for(NotificationMethod sel:task.notificationMethods) {
				if(sel.id.equals(it.id)){
					exists=true;
				}
			}
			items.add(new CommandOption(it,exists));
		}
	}
	
	public boolean hasSelectedItems(){
		boolean exists=false;
		items.each{
			if(it.selected==true){
				exists=true;
			}
		}
		return exists;
	}
}
