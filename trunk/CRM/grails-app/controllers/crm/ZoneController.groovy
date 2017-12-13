package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ZoneController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Zone.list(params), model:[zoneCount: Zone.count()]
    }

    def show(Zone zone) {
        respond zone
    }

    def create() {
        respond new Zone(isCenter:new Boolean("false"))
    }

    @Transactional
    def save(Zone zone) {
        if (zone == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		zone.isCenter=false;//isCenter=true is created only when adding new city
        if (zone.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond zone.errors, view:'create'
            return
        }

        zone.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'zone.label', default: 'Zone'), zone.id])
                redirect zone
            }
            '*' { respond zone, [status: CREATED] }
        }
    }

    def edit(Zone zone) {
        respond zone
    }

    @Transactional
    def update(Zone zone) {
        if (zone == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (zone.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond zone.errors, view:'edit'
            return
        }

        zone.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'zone.label', default: 'Zone'), zone.id])
                redirect zone
            }
            '*'{ respond zone, [status: OK] }
        }
    }

    @Transactional
    def delete(Zone zone) {

        if (zone == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        zone.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'zone.label', default: 'Zone'), zone.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'zone.label', default: 'Zone'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	def getZonesByCityAJAX(String mainDomainType, String cityId, String zoneId, String update) {
		render(template:"/zone/dynamicZoneSelector", model:[doty: mainDomainType, cid:cityId, zid:zoneId, upd:update]);
	}
}
