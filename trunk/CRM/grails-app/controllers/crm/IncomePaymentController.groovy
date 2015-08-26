package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class IncomePaymentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond IncomePayment.list(params), model:[incomePaymentCount: IncomePayment.count()]
    }

    def show(IncomePayment incomePayment) {
        respond incomePayment
    }

    def create() {
        respond new IncomePayment(params)
    }

    @Transactional
    def save(IncomePayment incomePayment) {
        if (incomePayment == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (incomePayment.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond incomePayment.errors, view:'create'
            return
        }

        incomePayment.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'incomePayment.label', default: 'IncomePayment'), incomePayment.id])
                redirect incomePayment
            }
            '*' { respond incomePayment, [status: CREATED] }
        }
    }

    def edit(IncomePayment incomePayment) {
        respond incomePayment
    }

    @Transactional
    def update(IncomePayment incomePayment) {
        if (incomePayment == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (incomePayment.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond incomePayment.errors, view:'edit'
            return
        }

        incomePayment.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'incomePayment.label', default: 'IncomePayment'), incomePayment.id])
                redirect incomePayment
            }
            '*'{ respond incomePayment, [status: OK] }
        }
    }

    @Transactional
    def delete(IncomePayment incomePayment) {

        if (incomePayment == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        incomePayment.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'incomePayment.label', default: 'IncomePayment'), incomePayment.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'incomePayment.label', default: 'IncomePayment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
