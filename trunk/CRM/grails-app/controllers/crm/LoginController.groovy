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
		 def user = CrmUser.findWhere(login:params['login'], password:CrmUser.encodePassword(params['pass']));
		 CrmConfig crmConfig=CrmConfig.getAll().first();
		 session.user = user;
		 session.softwarePlan = crm.enums.software.Plan.valueOf(crmConfig.plan);
		 
		 //loading permissions
		 List<String> controllersAndActions=new ArrayList<String>();
		 List<ContextPermissionCategory> categories=new ArrayList<ContextPermissionCategory>();
		 boolean exists;
		 boolean countainsAllCategory=false;
		 for(UserGroup g:user.userGroups){//adds categories to a temp list
			 exists=false;
			 for(ContextPermissionCategory cat:categories){
				if(cat.name.equals(g.contextPermissionCategory.name)){
					exists=true;
					break;
				}
			 }
			 if(exists==false && g.contextPermissionCategory.isNone==false){
				 //verificar antes si la categoria es valida para el software plan.
				 categories.add(g.contextPermissionCategory);
				 if(g.contextPermissionCategory.isAll){
					 countainsAllCategory=true;
				 }
			 }
		 }
		 if(countainsAllCategory==false){
			 for(ContextPermissionCategory cat:categories){
				 def acts=CrmActionByContextCategory.findAllByContextPermissionCategory(cat);
				 for(CrmActionByContextCategory act:acts){
					exists=false;
					for(String caa:controllersAndActions){
						if(caa.equals(act.crmController+"@"+act.crmAction)){
							exists=true;
							break;
						}
					}
					if(exists==false){
						controllersAndActions.add(act.crmController+"@"+act.crmAction);
					}
				 }
			 }
		 }else{
		 	def acts=crm.commands.ContextCrmActionsByCategoryCommand.getCrmActionByContextCategoriesForAllCategory(session.softwarePlan);//List<CrmActionByContextCategory>
			for(CrmActionByContextCategory act:acts){
				controllersAndActions.add(act.crmController+"@"+act.crmAction);
			}
		 }
		 
		 session.user.setContextPermissions(controllersAndActions);
		 
		 if (user){
			 //user.setPermissions(); cargar aca una lista en CrmUser para no hacer consultas cada vez que se pregunte por un permiso
			 if(crmUri){
				 if(null != crmUri.id){
					redirect(controller:crmUri.controller, action:crmUri.action, id:crmUri.id);
				 }else{
				 	if(crmUri.controller!=null && crmUri.action!=null){
						redirect(controller:crmUri.controller, action:crmUri.action);
				 	}else{
					 	redirect(controller:'home', action:'show');
				 	}
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
