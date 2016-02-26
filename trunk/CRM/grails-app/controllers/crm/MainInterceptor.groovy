package crm


class MainInterceptor {

   MainInterceptor() {
		matchAll()
		.excludes(controller:"login") //no anda el exclude por eso puse la condicion antes de llamar a checkUser()
	}
    boolean before() { 
		System.err.println("Before zone controller.");
		//def an=actionName;
		//def cn=controllerName;
		if(controllerName.equals("login")){
			return false;
		}else{
			if(controllerName.equals("favicon")){
				return false;
			}else{
				this.checkUser();
			}
		}
	}

    boolean after() { true }

    void afterView() {
        // no-op
    }
	def checkUser() {
		def an2=actionName;
		def co2=controllerName;
		def uu=request.getRequestURI();
		if(!session.user) {// i.e. user not logged in
			redirect(controller:'login',action:'loginForm', params: [controllerN: "${controllerName}", actionN:"${actionName}", idV:"${params['id']}"]);//enviar por parametro pa pagina a la que debe redireccionar despues del login
			//return false;
		}else{
			//return true;.
			/*  println "Tracing action Uri ${actionUri}"; controller/action. Ej. /plant/create
				println "Tracing action Name ${actionName}"; action name. Ej. create*/
			if(session.user.isActivateInUIX("city")){
				if(session.user.checkPermissions("city", "${actionName}")){
					return true;
				}else{
					redirect(controller:'crmUser',action:'accessDenied');
				}
			}else{
				redirect(controller:'crmUser',action:'pageNotAvailable');
			}
		}
	}
}
