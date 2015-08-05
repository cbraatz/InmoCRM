package crm

//import org.apache.jasper.tagplugins.jstl.core.Otherwise;

class CrmUser {
	String login;
	String pass;
	String emailAddress;
	boolean isAdmin;
	boolean isActive;
	boolean isDefault;
	Partner partner;
	static hasMany = [clients: Client, createdPropertyDemands:PropertyDemand, assignedPropertyDemands:PropertyDemand, concessions:Concession, commissions:Commission, comment:Comment, userNotificationSuscription:UserNotificationSubscription/*,UserContextRole, Inbox,CreatedTask,AssignedTask,tagSelectedValue,customFieldSelectedValue,userByCheckOut,addedInsuranceDemand,assignedInsuranceDemand*/];
	static mappedBy = [createdPropertyDemands: "creator", assignedPropertyDemands: "assignee"];

	static constraints = {
		login(blank:false, nullable:false, unique:true, size:5..20);
		pass(blank:false, nullable:false, size:5..30);
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
		/*String permissionId=getPermissionIdFromActionAndController(controller, action);
		System.out.println("Verifying "+permissionId+" permission.");
		if(null!=controller && null!=action && null!=permissionId){
			System.out.println(UserContextRole.findAllByUser(this).size());
			for(UserContextRole ucr: UserContextRole.findAllByUser(this)){
				System.out.println("UCR="+ucr.contextRole.name);
				System.out.println(ContextPermissionRole.findAllByContextRole(ucr.contextRole).size());
				for(ContextPermissionRole cpr: ContextPermissionRole.findAllByContextRole(ucr.contextRole)){
					System.out.println("CPR="+cpr.contextPermission.name);
					if(permissionId.equals(cpr.contextPermission.permissionId)){
						return true;
					}
				}
			}
		}else{
			System.out.println("Null value for Controller= "+controller+". Action= "+action+". permissionId="+permissionId);
		}
		return false;*/
		return this.isAdmin;
	}
	private boolean isActivateInUIX(String controller) {
		return true;
	}
	private String getPermissionIdFromActionAndController(String controller, String action){
		switch(action){
			case "index": return "VIEW_"+controller.toUpperCase();
			case "show": return "VIEW_"+controller.toUpperCase();
			case "create":  return "CREATE_"+controller.toUpperCase();
			case "edit":  return "EDIT_"+controller.toUpperCase();
			case "delete":  return "DELETE_"+controller.toUpperCase();
		}
		return null;
	}
}
