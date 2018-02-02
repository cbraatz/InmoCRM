package crm

import crm.enums.software.CrmAction
import crm.enums.software.CrmController
import crm.enums.software.Plan
import crm.exception.CRMException

//import org.apache.jasper.tagplugins.jstl.core.Otherwise;

class CrmUser extends CrmDomain{
	String login;
	String name;
	String password;
	String emailAddress;
	Boolean isActive;
	Boolean isAdmin;
	Boolean hasAccess;//si deseamos que el usuario pueda hacer login
	Partner partner;
	CrmUser addedBy;//self referenced properties should not have the same name than the Domain. See Partner Domain Class for more info

	private List<String> contextPermissions;
	static transients = ["contextPermissions", "name"];
	static hasMany = [clients: Client, propertyDemandsOwner:PropertyDemand, propertyDemandsAssignee:PropertyDemand, concessions:Concession, commissionsByProperty:CommissionByProperty, comments:Comment, userNotificationSuscriptions:UserNotificationSubscription, userGroups:UserGroup, agentComments:AgentComment, tasksCreator:Task, tasksAssignee:Task, inboxes:Inbox, customReports:CustomReport, reportDesigners:ReportDesigner, reportFolders:ReportFolder, crmUsersAddedBy:CrmUser, actions:Action, contacts:Contact, soldProperties:SoldProperty/*tagSelectedValue,customFieldSelectedValue,userByCheckOut,addedInsuranceDemand,assignedInsuranceDemand*/];
	static mappedBy = [propertyDemandsOwner: "owner", propertyDemandsAssignee: "assignee", tasksCreator: "creator", tasksAssignee: "assignee"];
	static constraints = {
		login(blank:false, nullable:false, unique:true, size:1..20);
		password(blank:false, nullable:false);//al editar un usuario no es necesario volver a cargar el pass.
		emailAddress(blank:false, nullable:false, unique:true, email: true);		
		isActive(nullable:false);
		isAdmin(nullable:false);
		hasAccess(nullable:false);
		partner(nullable:false);
		addedBy(nullable:true);
	}
	@Override
	public static String getPluralName(){
		return "crmUsers";
	}
	/*private boolean checkPermissions(String controller, String action) {
			if(isActivateInUIX(controller)){
				return hasPermission(controller, action);
			}else{
				return false;
			}
	}*/
	//public boolean isAdmin(){
		/*for(UserGroup g:this.userGroups){
			if(g.isAdmin==true){
				return true;
			}
		}
		return false;*/
		//return this.isAdmin;
	//}
	def hasPermission(String controllerName, String actionName, Plan softwarePlan) throws CRMException{
		if(this.isActive && this.hasAccess) {
			boolean skip=false;
			for(String aa:CrmAction.getActionsToSkip()){
				if(aa.equals(actionName)){
					skip=true;
					break;
				}
			}
			if(skip == false){
				CrmAction crmAction=CrmAction.getCrmActionByName(actionName);
				if(crmAction !=null){
					CrmController crmController=CrmController.getCrmControllerByName(controllerName);
					if(crmController != null){
						if(crmController.isCrmControllersAvailableForCurrentPlan(softwarePlan)){
							if(this.isAdmin){
								return true;
							}else{
								if(false == crmController.isAdminOnly()){	
									for(String p:this.getContextPermissions()){
										if(p.equals(crmController.name()+"@"+crmAction.name())){
											return true;
										}
									}
								}
							}
						}
						return null;//returns null when the plan is not available for this user
					}else{
						throw new CRMException("CrmController has null value. Not found CrmController with name="+controllerName);
					}
				}else{
					throw new CRMException("CrmAction has null value. Not found CrmAction with name="+actionName);
				}
			}else{
				return true;
			}		
		}else {
			return false;
		}
	}
	/*def hasPermission(String controller, String action) {
		String permissionID=getpermissionIDFromActionAndController(controller, action);
		System.out.println("Verifying "+permissionID+" permission.");
		if(null!=controller && null!=action && null!=permissionID){
			System.out.println(UserContextRole.findAllByUser(this).size());
			for(UserContextRole ucr: UserContextRole.findAllByUser(this)){
				System.out.println("UCR="+ucr.contextRole.name);
				System.out.println(ContextPermissionRole.findAllByContextRole(ucr.contextRole).size());
				for(ContextPermissionRole cpr: ContextPermissionRole.findAllByContextRole(ucr.contextRole)){
					System.out.println("CPR="+cpr.contextPermission.name);
					if(permissionID.equals(cpr.contextPermission.permissionID)){
						return true;
					}
				}
			}
		}else{
			System.out.println("Null value for Controller= "+controller+". Action= "+action+". permissionID="+permissionID);
		}
		return false;
		return this.isAdmin;
	}*/
	public static String encodePassword(String pass){
		return Utils.getSHA512Password(pass);
	}
	
	public String getEncodedPassword(){
		return CrmUser.encodePassword(this.password);
	}
	
	private boolean isActivateInUIX(String controller) {
		return true;
	}
	private String getpermissionIDFromActionAndController(String controller, String action){
		if(null!= action && null!= controller && !action.isEmpty() && !controller.isEmpty()){
			switch(action){
				case "show": return "VIEW_"+controller.toUpperCase();
				case "create":  return "EDIT_"+controller.toUpperCase();
				case "edit":  return "EDIT_"+controller.toUpperCase();
				case "index": return "VIEW_"+controller.toUpperCase();
				case "delete":  return "DELETE_"+controller.toUpperCase();
				default:  return "SPECIAL_"+controller.toUpperCase();
			}
		}
		return null;
	}
	
	@Override
	public static SearchAttribute[] searchByAttributes() {
		return [new SearchAttribute("login"), new SearchAttribute("emailAddress")];
	}

	public List<String> getContextPermissions() {
		return contextPermissions;
	}
	public void setContextPermissions(List<String> contextPermissions) {
		this.contextPermissions = contextPermissions;
	}
	@Override
	public static String getDefaultPropertyName(){
		return "login";
	}
	public String getName(){
		return this.partner.name+" > "+this.login;
	}
}
