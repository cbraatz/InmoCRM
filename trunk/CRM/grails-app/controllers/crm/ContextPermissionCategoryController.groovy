package crm

import static org.springframework.http.HttpStatus.*
import crm.commands.ContextCrmActionsByCategoryCommand
import crm.enums.software.CrmAction
import crm.enums.software.CrmController
import crm.exception.CRMException
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ContextPermissionCategoryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ContextPermissionCategory.list(params), model:[contextPermissionCategoryCount: ContextPermissionCategory.count()]
    }

    def show(ContextPermissionCategory contextPermissionCategory) {
        respond contextPermissionCategory
    }

    def create() {
        respond new ContextPermissionCategory(isAll:false, isNone:false)
    }
	
    @Transactional
    def save(ContextPermissionCategory contextPermissionCategory) {
        if (contextPermissionCategory == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (contextPermissionCategory.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond contextPermissionCategory.errors, view:'create'
            return
        }

        contextPermissionCategory.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'contextPermissionCategory.label', default: 'ContextPermissionCategory'), contextPermissionCategory.id])
                redirect contextPermissionCategory
            }
            '*' { respond contextPermissionCategory, [status: CREATED] }
        }
    }
	def define(ContextPermissionCategory contextPermissionCategory){
		respond contextPermissionCategory, model:[contextCrmActionsByCategoryCommand:new ContextCrmActionsByCategoryCommand(contextPermissionCategory, session.softwarePlan)]
	}
    def edit(ContextPermissionCategory contextPermissionCategory) {
        respond contextPermissionCategory
    }
	@Transactional
	def saveDefine(ContextPermissionCategory contextPermissionCategory, ContextCrmActionsByCategoryCommand contextCrmActionsByCategoryCommand) {
		if (contextPermissionCategory == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}
		if(!(contextPermissionCategory.isAll==true || contextPermissionCategory.isNone==true)){
			boolean updateError=false;
			for(CrmActionListByController abc : contextCrmActionsByCategoryCommand.actionsByController){
				if(updateError == false){
					for(CrmActionWithValue awv:abc.actions){
						if(awv.selected != awv.oldSelected){//si se chequeó o deschequeó el checkbox de esta accion en particular
							CrmActionByContextCategory aa=CrmActionByContextCategory.findByContextPermissionCategoryAndCrmControllerAndCrmAction(contextPermissionCategory, abc.getController().name(), awv.getAction().name());
							if(aa == null){//si no existe en la base de datos
								if(true == awv.selected){//si no estuvo seleccionado y se seleccionó para agregar
									aa=new CrmActionByContextCategory(crmController: abc.getController().name(), crmAction: awv.getAction().name(), contextPermissionCategory: contextPermissionCategory);
									if(!aa.save(flush:false)){
										updateError=true;
										System.err.println("Error saving CrmActionByContextCategory with crmController="+abc.getController().name()+", crmAction="+awv.getAction().name()+", contextPermissionCategory="+contextPermissionCategory+"Errors="+aa.errors);
										//hay que loguear este error
										break;
									}
								}else{
									updateError=true;
									System.err.println("Unknown Error #1 saving CrmActionByContextCategory with crmController="+abc.getController().name()+", crmAction="+awv.getAction().name()+", contextPermissionCategory="+contextPermissionCategory);
									//hay que loguear este error
									break;
								}
							}else{
								if(false == awv.selected){
									aa.delete(flush:false)
								}else{
									updateError=true;
									System.err.println("Unknown Error #2 saving CrmActionByContextCategory with crmController="+abc.getController().name()+", crmAction="+awv.getAction().name()+", contextPermissionCategory="+contextPermissionCategory);
									//hay que loguear este error
									break;
								}
							}
						}
					}
				}else{
					break;
				}
			}
			if (updateError == true) {
				transactionStatus.setRollbackOnly()
				contextPermissionCategory.errors.rejectValue('',message(code:'default.save.error', args:['ContextPermissionCategory']).toString());
				respond contextPermissionCategory.errors, view:'define', model:[contextCrmActionsByCategoryCommand:new ContextCrmActionsByCategoryCommand(contextPermissionCategory, session.softwarePlan)]
				return
			}
		}else{
			transactionStatus.setRollbackOnly()
			contextPermissionCategory.errors.rejectValue('',message(code:'default.default.object.edit.error').toString());
			respond contextPermissionCategory.errors, view:'show', model:[id:contextPermissionCategory.id]
			return
		}
		
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'contextPermissionCategory.label', default: 'ContextPermissionCategory'), contextPermissionCategory.id])
				redirect contextPermissionCategory
			}
			'*' { respond contextPermissionCategory, [status: CREATED] }
		}
	}
    @Transactional
    def update(ContextPermissionCategory contextPermissionCategory) {
        if (contextPermissionCategory == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (contextPermissionCategory.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond contextPermissionCategory.errors, view:'edit'
            return
        }

        contextPermissionCategory.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'contextPermissionCategory.label', default: 'ContextPermissionCategory'), contextPermissionCategory.id])
                redirect contextPermissionCategory
            }
            '*'{ respond contextPermissionCategory, [status: OK] }
        }
    }

    @Transactional
    def delete(ContextPermissionCategory contextPermissionCategory) {

        if (contextPermissionCategory == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		if(contextPermissionCategory.isAll.booleanValue() == true || contextPermissionCategory.isNone.booleanValue() == true){//if is default category
			contextPermissionCategory.errors.rejectValue('',message(code:'default.default.object.delete.error').toString());
			transactionStatus.setRollbackOnly();
			respond contextPermissionCategory, view:'show'
			return;
		}else{
        	contextPermissionCategory.delete flush:true
		}
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'contextPermissionCategory.label', default: 'ContextPermissionCategory'), contextPermissionCategory.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'contextPermissionCategory.label', default: 'ContextPermissionCategory'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
