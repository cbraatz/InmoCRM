package crm.enums.software

import java.util.List;

public enum CrmAction {
	ix(Arrays.asList("index")),
	sh(Arrays.asList("show")),
	cr(Arrays.asList("create", "save")),
	ed(Arrays.asList("edit", "update")),
	de(Arrays.asList("delete")),
	sp(Arrays.asList("run", "refresh", "confirmPayment", "step1", "step2", "step3", "step4", "step5", "addEditFiles", "members", "addEditMembers", "saveMember", "deleteMember", "translate", "saveTranslations", "define", "saveDefine", "commissions"/*, "hqlQuery"*/));//los mas usados primero
	private final List<String> actionNames;
	
	private CrmAction(List<String> actionNames) {
		this.actionNames=actionNames;
	}
	public static CrmAction getCrmActionByName(String actionName){
		for(CrmAction a:CrmAction.values()){
			for(String n:a.actionNames){
				if(n.equals(actionName)){
					return a;
				}
			}
		}
		return null;
	}
	public static List<String> getActionsToSkip(){
		return Arrays.asList("getNeighborhoodsByCityAJAX","getCategoryFieldNumberAJAX");
	}
}
