package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CustomReportController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CustomReport.list(params), model:[customReportCount: CustomReport.count()]
    }

    def show(CustomReport customReport) {
        respond customReport
    }

    def create() {
        respond new CustomReport(params)
    }

    @Transactional
    def save(CustomReport customReport) {
        if (customReport == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (customReport.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond customReport.errors, view:'create'
            return
        }

        customReport.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'customReport.label', default: 'CustomReport'), customReport.id])
                redirect customReport
            }
            '*' { respond customReport, [status: CREATED] }
        }
    }

    def edit(CustomReport customReport) {
        respond customReport
    }

    @Transactional
    def update(CustomReport customReport) {
        if (customReport == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (customReport.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond customReport.errors, view:'edit'
            return
        }

        customReport.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'customReport.label', default: 'CustomReport'), customReport.id])
                redirect customReport
            }
            '*'{ respond customReport, [status: OK] }
        }
    }

    @Transactional
    def delete(CustomReport customReport) {

        if (customReport == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        customReport.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'customReport.label', default: 'CustomReport'), customReport.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'customReport.label', default: 'CustomReport'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
