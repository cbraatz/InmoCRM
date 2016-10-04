package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class InvoicePrintingController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond InvoicePrinting.list(params), model:[invoicePrintingCount: InvoicePrinting.count()]
    }

    def show(InvoicePrinting invoicePrinting) {
        respond invoicePrinting
    }

    def create() {
        respond new InvoicePrinting(params)
    }

    @Transactional
    def save(InvoicePrinting invoicePrinting) {
        if (invoicePrinting == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (invoicePrinting.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond invoicePrinting.errors, view:'create'
            return
        }

        invoicePrinting.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'invoicePrinting.label', default: 'InvoicePrinting'), invoicePrinting.id])
                redirect invoicePrinting
            }
            '*' { respond invoicePrinting, [status: CREATED] }
        }
    }

    def edit(InvoicePrinting invoicePrinting) {
        respond invoicePrinting
    }

    @Transactional
    def update(InvoicePrinting invoicePrinting) {
        if (invoicePrinting == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (invoicePrinting.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond invoicePrinting.errors, view:'edit'
            return
        }

        invoicePrinting.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'invoicePrinting.label', default: 'InvoicePrinting'), invoicePrinting.id])
                redirect invoicePrinting
            }
            '*'{ respond invoicePrinting, [status: OK] }
        }
    }

    @Transactional
    def delete(InvoicePrinting invoicePrinting) {

        if (invoicePrinting == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        invoicePrinting.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'invoicePrinting.label', default: 'InvoicePrinting'), invoicePrinting.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'invoicePrinting.label', default: 'InvoicePrinting'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
