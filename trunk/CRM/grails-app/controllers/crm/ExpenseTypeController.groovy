package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ExpenseTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ExpenseType.list(params), model:[expenseTypeCount: ExpenseType.count()]
    }

    def show(ExpenseType expenseType) {
        respond expenseType
    }

    def create() {
        respond new ExpenseType(params)
    }

    @Transactional
    def save(ExpenseType expenseType) {
		
        if (expenseType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
        if (expenseType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond expenseType.errors, view:'create'
            return
        }

        expenseType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'expenseType.label', default: 'ExpenseType'), expenseType.id])
                redirect expenseType
            }
            '*' { respond expenseType, [status: CREATED] }
        }
    }

    def edit(ExpenseType expenseType) {
        respond expenseType
    }

    @Transactional
    def update(ExpenseType expenseType) {
        if (expenseType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (expenseType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond expenseType.errors, view:'edit'
            return
        }

        expenseType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'expenseType.label', default: 'ExpenseType'), expenseType.id])
                redirect expenseType
            }
            '*'{ respond expenseType, [status: OK] }
        }
    }

    @Transactional
    def delete(ExpenseType expenseType) {

        if (expenseType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        expenseType.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'expenseType.label', default: 'ExpenseType'), expenseType.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'expenseType.label', default: 'ExpenseType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
