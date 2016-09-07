package crm

//import org.apache.jasper.tagplugins.jstl.core.Otherwise;

class CrmUser extends CrmDomain{
	String name;
	String password;
	String emailAddress;
	Boolean isAdmin;
	Boolean isActive;
	Boolean isDefault;
	Partner partner;
	//Office office
	static hasMany = [clients: Client, createdPropertyDemands:PropertyDemand, assignedPropertyDemands:PropertyDemand, concessions:Concession, commissions:Commission, comments:Comment, userNotificationSuscriptions:UserNotificationSubscription, userContextRoles:UserContextRole, agentComments:AgentComment, createdTasks:Task, assignedTasks:Task, inboxes:Inbox, classicReports:ClassicReport, reportDesigners:ReportDesigner, reportFolders:ReportFolder/*tagSelectedValue,customFieldSelectedValue,userByCheckOut,addedInsuranceDemand,assignedInsuranceDemand*/];
	static mappedBy = [createdPropertyDemands: "owner", assignedPropertyDemands: "assignee", createdTasks: "owner", assignedTasks: "assignee"];

	static constraints = {
		name(blank:false, nullable:false, unique:true, size:1..20);
		password(blank:false, nullable:false);//al editar un usuario no es necesario volver a cargar el pass.
		emailAddress(blank:false, nullable:false, unique:true, email: true);		
		isAdmin(nullable:false);
		isActive(nullable:false);
		isDefault(nullable:false);
		partner(nullable:false);
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
		switch(action){
			case "index": return "VIEW_"+controller.toUpperCase();
			case "show": return "VIEW_"+controller.toUpperCase();
			case "create":  return "CREATE_"+controller.toUpperCase();
			case "edit":  return "EDIT_"+controller.toUpperCase();
			case "delete":  return "DELETE_"+controller.toUpperCase();
		}
		return null;
	}
	
	@Override
	public static SearchAttribute[] searchByAttributes() {
		return [new SearchAttribute("name"), new SearchAttribute("emailAddress")];
	}
}
