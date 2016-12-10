package crm;

import crm.enums.software.CrmAction;

public class CrmActionWithValue{
	boolean oldSelected;
	CrmAction action;
	boolean selected;
	public CrmActionWithValue(CrmAction action, boolean selected){
		this.oldSelected=selected;
		this.selected=selected;
		this.action=action;
	}
	
	public CrmAction getAction() {
		return action;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected=selected;
	}
}