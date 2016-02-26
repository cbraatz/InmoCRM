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
				 esto a continuacion no es necesario, ya que el id esta en crmUri, ver como hacer el redirect con el id...
				 crmUri.action=(crmUri.action.equals("show")?"index":crmUri.action);
				 redirect(controller:crmUri.controller, action:crmUri.action);//if it is redirected to a page different tha index
			 }else{
				 redirect(controller:'client', action:'create');//should be redirected to index
			 }
		 }else
			 redirect(controller:'login', action:'loginForm');
	 }
	 def doLogout = {
			session.user = null;
			redirect(controller:'login', action:'loginForm');
	}
}
