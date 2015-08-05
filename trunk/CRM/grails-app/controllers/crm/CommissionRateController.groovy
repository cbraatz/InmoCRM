package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CommissionRateController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CommissionRate.list(params), model:[commissionRateCount: CommissionRate.count()]
    }

    def show(CommissionRate commissionRate) {
        respond commissionRate
    }

    def create() {
        respond new CommissionRate(params)
    }

    @Transactional
    def save(CommissionRate commissionRate) {
        System.out.println(commissionRate.percentage);
		if (commissionRate == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (commissionRate.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond commissionRate.errors, view:'create'
            return
        }

        commissionRate.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'commissionRate.label', default: 'CommissionRate'), commissionRate.id])
                redirect commissionRate
            }
            '*' { respond commissionRate, [status: CREATED] }
        }
    }

    def edit(CommissionRate commissionRate) {
        respond commissionRate
    }

    @Transactional
    def update(CommissionRate commissionRate) {
        if (commissionRate == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (commissionRate.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond commissionRate.errors, view:'edit'
            return
        }

        commissionRate.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'commissionRate.label', default: 'CommissionRate'), commissionRate.id])
                redirect commissionRate
            }
            '*'{ respond commissionRate, [status: OK] }
        }
    }

    @Transactional
    def delete(CommissionRate commissionRate) {

        if (commissionRate == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        commissionRate.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'commissionRate.label', default: 'CommissionRate'), commissionRate.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'commissionRate.label', default: 'CommissionRate'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
