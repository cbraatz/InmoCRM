package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ExpenseController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Expense.list(params), model:[expenseCount: Expense.count()]
    }

    def show(Expense expense) {
        respond expense
    }

    def create() {
        respond new Expense(params)
    }

    @Transactional
    def save(Expense expense) {
        if (expense == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (expense.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond expense.errors, view:'create'
            return
        }

        expense.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'expense.label', default: 'Expense'), expense.id])
                redirect expense
            }
            '*' { respond expense, [status: CREATED] }
        }
    }

    def edit(Expense expense) {
        respond expense
    }

    @Transactional
    def update(Expense expense) {
        if (expense == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (expense.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond expense.errors, view:'edit'
            return
        }

        expense.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'expense.label', default: 'Expense'), expense.id])
                redirect expense
            }
            '*'{ respond expense, [status: OK] }
        }
    }

    @Transactional
    def delete(Expense expense) {

        if (expense == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        expense.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'expense.label', default: 'Expense'), expense.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'expense.label', default: 'Expense'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
