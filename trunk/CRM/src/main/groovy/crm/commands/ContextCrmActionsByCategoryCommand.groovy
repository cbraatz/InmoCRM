package crm.commands

import crm.ContextPermissionCategory
import crm.CrmActionByContextCategory
//import crm.CrmUser
//import crm.DimensionMeasuringUnit
//import crm.FeatureNameByLanguage
//import crm.BuildingFeatureByLanguage
//import crm.BuildingFeature
//import crm.BuildingType
//import crm.Language
//import crm.UserGroup
import crm.enums.software.CrmAction
import crm.enums.software.CrmController
import crm.enums.software.Plan
import crm.CrmActionListByController
import crm.CrmActionWithValue
import grails.validation.Validateable

class ContextCrmActionsByCategoryCommand implements Validateable{
	List<CrmActionListByController> actionsByController= new ArrayList<CrmActionListByController>();
	
	public ContextCrmActionsByCategoryCommand(ContextPermissionCategory category, Plan softwarePlan){
		CrmActionListByController crmActionsByController;
		def actionsByCat;
		if(category.isAll==true){
			actionsByCat=getCrmActionByContextCategoriesForAllCategory(softwarePlan);//lista de controller/action para la categoria All
		}else{
			actionsByCat=CrmActionByContextCategory.findAllByContextPermissionCategory(category);//lista de controller/action por categoria
		}
		boolean exists=false;
		//busca todas las acciones de cada controlador y si esta agrega selected=true y sino false
		for(CrmController c:CrmController.values()){
			if(c.isCrmControllersAvailableForCurrentPlan(softwarePlan)==true){
				crmActionsByController=new CrmActionListByController(c);
				for(CrmAction a:c.actions){
					exists=false;
					for(CrmActionByContextCategory abc:actionsByCat){
						if(c.name().equals(abc.crmController) && a.name().equals(abc.crmAction)){//.name() retorna el nombre del controller, ej: a1 y abc.crmController almacena ese mismo nombre
							exists=true;
							break;
						}
					}
					crmActionsByController.addCrmAction(a,exists);
				}
				actionsByController.add(crmActionsByController);
			}
		}
	}
	public static List<CrmActionByContextCategory> getCrmActionByContextCategoriesForAllCategory(Plan softwarePlan){
		List<CrmActionByContextCategory> list=new ArrayList<CrmActionByContextCategory>();
		ContextPermissionCategory allCat=ContextPermissionCategory.findByIsAll(new Boolean("true"));
		for(CrmController c:CrmController.values()){
			if(c.isCrmControllersAvailableForCurrentPlan(softwarePlan)==true){
				for(CrmAction a:c.actions){
					list.add(new CrmActionByContextCategory(contextPermissionCategory: allCat, crmController: c.name(),crmAction:a.name()));
				}
			}
		}
		return list;
	}
	/*public boolean isCrmActionSelected(String controller, String action){
		CrmController co=CrmController.valueOf(controller);
		CrmAction ac=CrmAction.valueOf(action);
		CrmActions actions=controllers.get(co);
		boolean res=actions.getActionValue(ac);
		return res;
	}*/
}



