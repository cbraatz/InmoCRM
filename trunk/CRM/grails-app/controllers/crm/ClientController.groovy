package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ClientController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Client.list(params), model:[clientCount: Client.count()]
    }

    def show(Client client) {
		if(client.isActive){
			respond client
		}else{
			render(view:'/error', model:[message: message(code: 'default.object.deleted.message', default:'Object has been deleted.', args:[message(code: 'client.label', default:'Client'), client.id])]);
		}
    }

    def create() {
		Client client=new Client();
		client.isActive=true;
        respond client
    }

    @Transactional
    def save(Client client) {
        if (client == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (client.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond client.errors, view:'create'
            return
        }
		
		//////
		client.address = new Address();
		client.address.properties=params.address;
		if(client.isProspectiveClient.booleanValue()==true && client.address.addressLine == null){
			client.address.addressLine="DIRECCION."
		}
		client.address.validate();
		if (client.address.hasErrors()) {
			transactionStatus.setRollbackOnly();
			respond client.errors, view:'create', controller:'client'
			return
		}
		
		if(client.address.save(flush: true)){
			println "Address SAVED";
			if(client.save(flush: true)){
				println "client SAVED";
			}else{
				println "client DONT SAVED";
				client.errors.each {
					println it
				}
				client.delete flush:true
			}
		}else{
			println "Address DONT SAVED";
			client.address.errors.each {
				println it
			}
		}
		
		///////

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'client.label', default: 'Client'), client.id])
                redirect client
            }
            '*' { respond client, [status: CREATED] }
        }
    }

    def edit(Client client) {
        respond client
    }

    @Transactional
    def update(Client client) {
        if (client == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (client.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond client.errors, view:'edit'
            return
        }

        client.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'client.label', default: 'Client'), client.id])
                redirect client
            }
            '*'{ respond client, [status: OK] }
        }
    }

    @Transactional
    def delete(Client client) {

        if (client == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        //client.delete flush:true
		client.isActive=false;
		client.save(flush:true);

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'client.label', default: 'Client'), client.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'client.label', default: 'Client'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
