package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class NeighborhoodController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Neighborhood.list(params), model:[neighborhoodCount: Neighborhood.count()]
    }

    def show(Neighborhood neighborhood) {
        respond neighborhood
    }

    def create() {
        respond new Neighborhood(params)
    }

    @Transactional
    def save(Neighborhood neighborhood) {
        if (neighborhood == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (neighborhood.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond neighborhood.errors, view:'create'
            return
        }

        neighborhood.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'neighborhood.label', default: 'Neighborhood'), neighborhood.id])
                redirect neighborhood
            }
            '*' { respond neighborhood, [status: CREATED] }
        }
    }

    def edit(Neighborhood neighborhood) {
        respond neighborhood
    }

    @Transactional
    def update(Neighborhood neighborhood) {
        if (neighborhood == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (neighborhood.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond neighborhood.errors, view:'edit'
            return
        }

        neighborhood.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'neighborhood.label', default: 'Neighborhood'), neighborhood.id])
                redirect neighborhood
            }
            '*'{ respond neighborhood, [status: OK] }
        }
    }

    @Transactional
    def delete(Neighborhood neighborhood) {

        if (neighborhood == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        neighborhood.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'neighborhood.label', default: 'Neighborhood'), neighborhood.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'neighborhood.label', default: 'Neighborhood'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
