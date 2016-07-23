package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ClassicReportController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ClassicReport.list(params), model:[classicReportCount: ClassicReport.count()]
    }

    def show(ClassicReport classicReport) {
        respond classicReport
    }

    def create() {
        respond new ClassicReport(params)
    }

    @Transactional
    def save(ClassicReport classicReport) {
        if (classicReport == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (classicReport.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond classicReport.errors, view:'create'
            return
        }

        classicReport.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'classicReport.label', default: 'ClassicReport'), classicReport.id])
                redirect classicReport
            }
            '*' { respond classicReport, [status: CREATED] }
        }
    }

    def edit(ClassicReport classicReport) {
        respond classicReport
    }

    @Transactional
    def update(ClassicReport classicReport) {
        if (classicReport == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (classicReport.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond classicReport.errors, view:'edit'
            return
        }

        classicReport.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'classicReport.label', default: 'ClassicReport'), classicReport.id])
                redirect classicReport
            }
            '*'{ respond classicReport, [status: OK] }
        }
    }

    @Transactional
    def delete(ClassicReport classicReport) {

        if (classicReport == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        classicReport.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'classicReport.label', default: 'ClassicReport'), classicReport.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'classicReport.label', default: 'ClassicReport'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
