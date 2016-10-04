package crm


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
			/*if(session.user.isActivateInUIX(controllerName)){
				if(session.user.checkPermissions(controllerName, actionName)){
					return true;
				}else{
					redirect(controller:'crmUser',action:'accessDenied');
				}
			}else{
				redirect(controller:'crmUser',action:'pageNotAvailable');
			}*/
			return true;
		}
	}
}
