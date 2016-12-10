package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserGroupController {

    static allowedMethods = [save: "POST", addMember: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UserGroup.list(params), model:[userGroupCount: UserGroup.count()]
    }

    def show(UserGroup userGroup) {
        respond userGroup
    }

    def create() {
        respond new UserGroup(isAdmin:false)
    }
	def members(UserGroup userGroup){
		redirect(action:'addEditMembers', params: [gid: userGroup.id])
	}
	def addEditMembers(){
		UserGroup userGroup;
		if(params.gid != null){
			userGroup=UserGroup.get(params.gid);
		}else{
			render(view:'/error', model:[message: message(code: 'default.invalid.paramethers.error', args: ["gid = null"])]);
		}
		respond userGroup, model:[someObject:new ReportFolder()]
	}
	@Transactional
	def saveMember(UserGroup userGroup, ReportFolder someObject){//uso cualquier domain object que tenga CrmUser, en este caso reportFolder
		CrmUser crmUser=someObject.crmUser;
		boolean exists=false;
		if(crmUser != null){
			for(CrmUser u:userGroup.crmUsers){
				if(u.id.equals(crmUser.id)){
					exists=true;
					break;
				}
			}
		}
		boolean saved=true;
		if (exists==false) {
			userGroup.addToCrmUsers(crmUser);
			if(!userGroup.save(flush:true)){
				saved=false;
			}else{
				crmUser.addToUserGroups(userGroup);
				if(!crmUser.save(flush:true)){
					saved=false;
				}
			}
		}

		if(!saved){
			userGroup.errors.rejectValue('',message(code:'default.save.error').toString());
			transactionStatus.setRollbackOnly()
			userGroup.crmUsers.size();//to load crmUsers and avoid LazyInitializationException, aparently is not working
			respond userGroup, view:'addEditMembers', model:[someObject:new ReportFolder(crmUser:crmUser)]
			return
		}else{
			redirect(controller:'userGroup', action:'addEditMembers', params: [gid:userGroup.id]);
		}
	}
	@Transactional
	def deleteMember(UserGroup userGroup) {
		CrmUser crmUser = CrmUser.get(params.uid);
		if(userGroup.isAdmin.booleanValue() == true && crmUser.addedBy == null){//if is default user group
			userGroup.errors.rejectValue('',message(code:'default.default.object.delete.error').toString());
			transactionStatus.setRollbackOnly();
			userGroup.crmUsers.size();//to load crmUsers and avoid LazyInitializationException
			respond userGroup, view:'addEditMembers', model:[someObject:new ReportFolder()]
			return;
		}

		if (crmUser == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}
		crmUser.removeFromUserGroups(userGroup);
		crmUser.save flush:true;
		userGroup.removeFromCrmUsers(crmUser);
		userGroup.save(flush:true)
		
		redirect(controller:'userGroup', action:'addEditMembers', params: [gid:userGroup.id]);
	}
	
    @Transactional
    def save(UserGroup userGroup) {
        if (userGroup == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userGroup.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userGroup.errors, view:'create'
            return
        }

        userGroup.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userGroup.label', default: 'UserGroup'), userGroup.id])
                redirect userGroup
            }
            '*' { respond userGroup, [status: CREATED] }
        }
    }

    def edit(UserGroup userGroup) {
        respond userGroup
    }

    @Transactional
    def update(UserGroup userGroup) {
        if (userGroup == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userGroup.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userGroup.errors, view:'edit'
            return
        }

        userGroup.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userGroup.label', default: 'UserGroup'), userGroup.id])
                redirect userGroup
            }
            '*'{ respond userGroup, [status: OK] }
        }
    }

    @Transactional
    def delete(UserGroup userGroup) {

        if (userGroup == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        userGroup.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userGroup.label', default: 'UserGroup'), userGroup.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userGroup.label', default: 'UserGroup'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
