package crm

class LoginController {
	def loginForm = {
		respond new CrmUri(params['controllerN'],params['actionN'],params['idV'])
	}
	def accessDenied = {
	}
	def pageNotAvailable = {
	}
	def doLogin(CrmUri crmUri){
		 def user = CrmUser.findWhere(name:params['login'], password:params['pass']);
		 session.user = user;
		 if (user){
			//user.setPermissions(); cargar aca una lista en CrmUser para no hacer consultas cada vez que se pregunte por un permiso
			 if(crmUri){
				 if(null != crmUri.id){
					redirect(controller:crmUri.controller, action:crmUri.action, id:crmUri.id);
				 }else{
				 	redirect(controller:crmUri.controller, action:crmUri.action);
				 }
			 }else{
				 redirect(uri:'/');
			 }
		 }else
			 redirect(controller:'login', action:'loginForm');
	 }
	 def doLogout = {
			session.user = null;
			redirect(controller:'login', action:'loginForm');
	}
}
