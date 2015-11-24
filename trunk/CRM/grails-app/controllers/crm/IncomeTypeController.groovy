package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class IncomeTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond IncomeType.list(params), model:[incomeTypeCount: IncomeType.count()]
    }

    def show(IncomeType incomeType) {
        respond incomeType
    }

    def create() {
        respond new IncomeType(params)
    }

    @Transactional
    def save(IncomeType incomeType) {
        if (incomeType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (incomeType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond incomeType.errors, view:'create'
            return
        }

        incomeType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'incomeType.label', default: 'IncomeType'), incomeType.id])
                redirect incomeType
            }
            '*' { respond incomeType, [status: CREATED] }
        }
    }

    def edit(IncomeType incomeType) {
        respond incomeType
    }

    @Transactional
    def update(IncomeType incomeType) {
        if (incomeType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (incomeType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond incomeType.errors, view:'edit'
            return
        }

        incomeType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'incomeType.label', default: 'IncomeType'), incomeType.id])
                redirect incomeType
            }
            '*'{ respond incomeType, [status: OK] }
        }
    }

    @Transactional
    def delete(IncomeType incomeType) {

        if (incomeType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        incomeType.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'incomeType.label', default: 'IncomeType'), incomeType.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'incomeType.label', default: 'IncomeType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
