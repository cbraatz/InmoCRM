package crm

import crm.enums.software.CrmAction
import crm.enums.software.CrmController
import crm.exception.CRMException

class CrmActionByContextCategory extends CrmDomain{
	ContextPermissionCategory contextPermissionCategory;
	String crmController;
	String crmAction;

    static constraints = {
		contextPermissionCategory(blank:false);
		crmController(blank:false, nullable:false, size:1..3);
		crmAction(blank:false, nullable:false, unique: ['contextPermissionCategory', 'crmController'], size:1..3);
		
    }
	@Override
	public static String getPluralName(){
		return "crmActionByContextCategories";
	}
	/*public CrmActionByContextCategory getByContextPermissionCategoryAndCrmCrontrollerAndCrmAction(ContextPermissionCategory contextPermissionCategory, String controllerName, String actionName)throws CRMException{
		CrmAction crmAction=CrmAction.getCrmActionByName(actionName);
			if(crmAction !=null){
				CrmController crmController=CrmController.getCrmControllerByName(controllerName);
				if(crmController !=null){
					return CrmActionByContextCategory.findByContextPermissionCategoryCrmCrontrollerAndCrmAction(contextPermissionCategory, controllerName, actionName);
				}else{
					throw new CRMException(message(code: 'default.null.object.message', args:['crmController = '+crmController.name()]).toString());
				}
			}else{
				throw new CRMException(message(code: 'default.null.object.message', args:['crmAction = '+crmAction.name()]).toString());
			}
		
	}*/
	/*public static boolean existsByContextPermissionCategoryAndCrmCrontrollerAndCrmAction(ContextPermissionCategory contextPermissionCategory, String controllerName, String actionName)throws CRMException{
		return (this.getByContextPermissionCategoryAndCrmCrontrollerAndCrmAction(contextPermissionCategory, controllerName, actionName) != null);
	}*/
}
