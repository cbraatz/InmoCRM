package crm
import crm.enums.software.CrmAction;
import crm.enums.software.CrmController;

class MainInterceptor {
   MainInterceptor() {
		matchAll()
		.excludes(controller:"login") //no anda el exclude por eso puse la condicion antes de llamar a checkUser()
	}
    boolean before() { 
		if(controllerName.equals("login")){
			return false;
		}else{
			if(controllerName.equals("favicon")){
				return false;
			}else{
				this.checkUser(); //borrar el return true...
				//return true;
			}
		}
	}

    boolean after() { true }

    void afterView() {
        // no-op
    }
	def checkUser() {
		if(!session.user) {// i.e. user not logged in
			redirect(controller:'login',action:'loginForm', params: [controllerN: controllerName, actionN:actionName, idV:params['id']]);//enviar por parametro p
		}else{
		try{
			return session.user.hasPermission(controllerName, actionName, session.softwarePlan);
		}catch(Exception e){
			render(view:'/error', model:[message: e.getMessage()]);
		}
			/*boolean skip=false;
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
						//System.err.println(session.user.getContextPermissions().contains(crmController.name()+"@"+crmAction.name()));
						for(String p:session.user.getContextPermissions()){
							if(p.equals(crmController.name()+"@"+crmAction.name())){
								return true;
								//System.err.println("TRUE");
							}
						}
						return false;
					}else{
						render(view:'/error', model:[message: "CrmController has null value. Not found CrmController with name="+controllerName]);
					}
				}else{
					render(view:'/error', model:[message: "CrmAction has null value. Not found CrmAction with name="+actionName]);
				}
			}else{
				return true;
			}*/
		}
	}
}
