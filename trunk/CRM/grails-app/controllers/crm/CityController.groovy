package crm

import static org.springframework.http.HttpStatus.*

import java.util.List;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class CityController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond City.list(params), model:[cityCount: City.count()]
    }

    def show(City city) {
        respond city
    }

    def create() {
        respond new City(params)
    }

    @Transactional
    def save(City city) {
        if (city == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (city.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond city.errors, view:'create'
            return
        }
        if(city.save(flush:true)) {
			String desc=city.name+" "+Zone.getCenterZoneName();
			Zone zone=new Zone(name: desc, description:desc, isCenter:new Boolean("true"), city:city);
			if(!zone.save(flush:true)) {
				city.errors.rejectValue('',message(code:'zone.save.error').toString());
				transactionStatus.setRollbackOnly()
				respond city.errors, view:'create'
				return
			}
        }
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'city.label', default: 'City'), city.id])
                redirect city
            }
            '*' { respond city, [status: CREATED] }
        }
    }

    def edit(City city) {
        respond city
    }

    @Transactional
    def update(City city) {
        if (city == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (city.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond city.errors, view:'edit'
            return
        }

        city.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'city.label', default: 'City'), city.id])
                redirect city
            }
            '*'{ respond city, [status: OK] }
        }
    }

    @Transactional
    def delete(City city) {

        if (city == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        city.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'city.label', default: 'City'), city.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'city.label', default: 'City'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
