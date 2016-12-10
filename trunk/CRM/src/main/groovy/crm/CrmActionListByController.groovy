package crm

import java.util.ArrayList;
import java.util.List;
import crm.enums.software.CrmAction;
import crm.enums.software.CrmController;

public class CrmActionListByController{
	final CrmController controller;
	List<CrmActionWithValue> actions;
	
	public CrmActionListByController(CrmController controller){
		this.controller=controller;
		this.actions=new ArrayList<CrmActionWithValue>();
	}
	
	public void addCrmAction(CrmAction crmAction, boolean selected){
		actions.add(new CrmActionWithValue(crmAction, selected));
	}
	
	public List<CrmActionWithValue> getActions(){
		return actions;
	}
	
	public Boolean getActionValue(CrmAction crmAction){
		for(CrmActionWithValue ac:this.actions){
			if(ac.toString().equals(crmAction.toString())){
				return ac.isSelected();
			}
		}
		return null;
	}
	
	public CrmController getController() {
		return controller;
	}
}

