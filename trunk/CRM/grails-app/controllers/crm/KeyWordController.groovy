package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class KeyWordController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond KeyWord.list(params), model:[keyWordCount: KeyWord.count()]
    }

    def show(KeyWord keyWord) {
        respond keyWord
    }

    def create() {
        respond new KeyWord(params)
    }

    @Transactional
    def save(KeyWord keyWord) {
        if (keyWord == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (keyWord.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond keyWord.errors, view:'create'
            return
        }

        keyWord.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'keyWord.label', default: 'KeyWord'), keyWord.id])
                redirect keyWord
            }
            '*' { respond keyWord, [status: CREATED] }
        }
    }

    def edit(KeyWord keyWord) {
        respond keyWord
    }

    @Transactional
    def update(KeyWord keyWord) {
        if (keyWord == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (keyWord.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond keyWord.errors, view:'edit'
            return
        }

        keyWord.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'keyWord.label', default: 'KeyWord'), keyWord.id])
                redirect keyWord
            }
            '*'{ respond keyWord, [status: OK] }
        }
    }

    @Transactional
    def delete(KeyWord keyWord) {

        if (keyWord == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        keyWord.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'keyWord.label', default: 'KeyWord'), keyWord.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'keyWord.label', default: 'KeyWord'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
