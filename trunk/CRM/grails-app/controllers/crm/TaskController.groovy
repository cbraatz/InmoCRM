package crm

import static org.springframework.http.HttpStatus.*
import crm.commands.FeatureByBuildingCommand;
import crm.commands.NotificationMethodsCommand
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TaskController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Task.list(params), model:[taskCount: Task.count()]
    }

    def show(Task task) {
        respond task
    }

    def create() {
        respond new Task(params), model:[notificationMethodsCommand: new NotificationMethodsCommand()]
    }

    @Transactional
    def save(Task task, NotificationMethodsCommand notificationMethodsCommand) {
        if (task == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (task.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond task.errors, view:'create'
            return
        }
		
        task.save flush:true
		
		/*if(task.notificationMethods){
			task.notificationMethods.each {
				it.addToTasks(task);
				it.save flush:true;
				task.addToNotificationMethods(it);
			}
			task.save flush:true;
		}*/
		if(notificationMethodsCommand.hasSelectedItems()){
			notificationMethodsCommand.items.each {
				if(it.selected==true){
					NotificationMethod nm=(NotificationMethod) it.item;
					nm.addToTasks(task);
					nm.save flush:true;
					task.addToNotificationMethods(nm);
				}
			}
			task.save flush:true;
		}
		
		
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'task.label', default: 'Task'), task.id])
                redirect task
            }
            '*' { respond task, [status: CREATED] }
        }
    }

    def edit(Task task) {
        respond task, model:[notificationMethodsCommand: new NotificationMethodsCommand(task)]
    }

    @Transactional
    def update(Task task, NotificationMethodsCommand notificationMethodsCommand) {
        if (task == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (task.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond task.errors, view:'edit'
            return
        }
		
		//verifica los notificationMethods.. agrega si no esta y quita si no debe estar
		boolean exists=false;
		notificationMethodsCommand.items.each {
			exists=false;
			if(it.selected==true){
				task.notificationMethods.each{n->
					if(n.id.equals(it.item.id)){
						exists=true;
					}
				}
				if(exists==false){
					NotificationMethod nm=(NotificationMethod) it.item;
					nm.addToTasks(task);
					nm.save flush:true;
					task.addToNotificationMethods(nm);
				}
			}else{
				task.notificationMethods.each{n->
					if(n.id.equals(it.item.id)){
						exists=true;
					}
				}
				if(exists==true){
					NotificationMethod nm=(NotificationMethod) it.item;
					nm.removeFromTasks(task);
					nm.save flush:true;
					task.removeFromNotificationMethods(nm);
				}
			}
		}
			
        task.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'task.label', default: 'Task'), task.id])
                redirect task
            }
            '*'{ respond task, [status: OK] }
        }
    }

    @Transactional
    def delete(Task task) {

        if (task == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        task.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'task.label', default: 'Task'), task.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'task.label', default: 'Task'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
