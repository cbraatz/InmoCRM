package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class InvoicesPrintingController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond InvoicesPrinting.list(params), model:[invoicesPrintingCount: InvoicesPrinting.count()]
    }

    def show(InvoicesPrinting invoicesPrinting) {
        respond invoicesPrinting
    }

    def create() {
        respond new InvoicesPrinting(params)
    }

    @Transactional
    def save(InvoicesPrinting invoicesPrinting) {
        if (invoicesPrinting == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (invoicesPrinting.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond invoicesPrinting.errors, view:'create'
            return
        }

        invoicesPrinting.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'invoicesPrinting.label', default: 'InvoicesPrinting'), invoicesPrinting.id])
                redirect invoicesPrinting
            }
            '*' { respond invoicesPrinting, [status: CREATED] }
        }
    }

    def edit(InvoicesPrinting invoicesPrinting) {
        respond invoicesPrinting
    }

    @Transactional
    def update(InvoicesPrinting invoicesPrinting) {
        if (invoicesPrinting == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (invoicesPrinting.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond invoicesPrinting.errors, view:'edit'
            return
        }

        invoicesPrinting.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'invoicesPrinting.label', default: 'InvoicesPrinting'), invoicesPrinting.id])
                redirect invoicesPrinting
            }
            '*'{ respond invoicesPrinting, [status: OK] }
        }
    }

    @Transactional
    def delete(InvoicesPrinting invoicesPrinting) {

        if (invoicesPrinting == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        invoicesPrinting.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'invoicesPrinting.label', default: 'InvoicesPrinting'), invoicesPrinting.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'invoicesPrinting.label', default: 'InvoicesPrinting'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
