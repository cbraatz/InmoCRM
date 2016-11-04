package crm

//import org.apache.jasper.tagplugins.jstl.core.Otherwise;

class CrmUser extends CrmDomain{
	String name;
	String password;
	String emailAddress;
	Boolean isActive;
	Partner partner;
	CrmUser addedBy;//self referenced properties should not have the same name than the Domain. See Partner Domain Class for more info
	//Office office
	static hasMany = [clients: Client, propertyDemandsCreator:PropertyDemand, propertyDemandsAssignee:PropertyDemand, concessions:Concession, commissions:Commission, comments:Comment, userNotificationSuscriptions:UserNotificationSubscription, userGroups:UserGroup, agentComments:AgentComment, tasksCreator:Task, tasksAssignee:Task, inboxes:Inbox, classicReports:ClassicReport, reportDesigners:ReportDesigner, reportFolders:ReportFolder, crmUsersAddedBy:CrmUser/*tagSelectedValue,customFieldSelectedValue,userByCheckOut,addedInsuranceDemand,assignedInsuranceDemand*/];
	static mappedBy = [propertyDemandsCreator: "creator", propertyDemandsAssignee: "assignee", tasksCreator: "creator", tasksAssignee: "assignee"];
	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..20);
		password(blank:false, nullable:false);//al editar un usuario no es necesario volver a cargar el pass.
		emailAddress(blank:false, nullable:false, unique:true, email: true);		
		isActive(nullable:false);
		partner(nullable:false);
		addedBy(nullable:true);
	}
	@Override
	public static String getPluralName(){
		return "crmUsers";
	}
	private boolean checkPermissions(String controller, String action) {
			if(isActivateInUIX(controller)){
				return hasPermission(controller, action);
			}else{
				return false;
			}
	}
	def hasPermission(String controller, String action) {
		/*String permissionID=getpermissionIDFromActionAndController(controller, action);
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
		return false;*/
		return this.isAdmin;
	}
	
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
		return [new SearchAttribute("name"), new SearchAttribute("emailAddress")];
	}
}
