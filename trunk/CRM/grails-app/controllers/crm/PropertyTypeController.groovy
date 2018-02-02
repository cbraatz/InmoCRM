package crm

import static org.springframework.http.HttpStatus.*

import crm.commands.PropertyTypeByLanguageCommand
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PropertyTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PropertyType.list(params), model:[propertyTypeCount: PropertyType.count()]
    }

    def show(PropertyType propertyType) {
        respond propertyType
    }

    def create() {
        respond new PropertyType(params)
    }

    @Transactional
    def save(PropertyType propertyType) {
        if (propertyType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (propertyType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond propertyType.errors, view:'create'
            return
        }

        propertyType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'propertyType.label', default: 'PropertyType'), propertyType.id])
                redirect propertyType
            }
            '*' { respond propertyType, [status: CREATED] }
        }
    }
	
	
    def edit(PropertyType propertyType) {
        respond propertyType
    }

    @Transactional
    def update(PropertyType propertyType) {
        if (propertyType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (propertyType.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond propertyType.errors, view:'edit'
            return
        }

        propertyType.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'propertyType.label', default: 'PropertyType'), propertyType.id])
                redirect propertyType
            }
            '*'{ respond propertyType, [status: OK] }
        }
    }

    @Transactional
    def delete(PropertyType propertyType) {

        if (propertyType == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        propertyType.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'propertyType.label', default: 'PropertyType'), propertyType.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
	def translate() {
		PropertyType propertyType=PropertyType.get(params.ptid);
		respond propertyType
	}
	
	@Transactional
	def saveTranslations(PropertyTypeByLanguageCommand propertyTypeByLanguageCommand, PropertyType propertyType) {
		if (propertyType == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}
		PropertyTypeByLanguage ptbl=null;
		boolean saveme=false;
		propertyTypeByLanguageCommand.items.each{
			if(null!=it.name && null!=it.plural && null!=it.nameForWeb){
				ptbl=PropertyTypeByLanguage.findByPropertyTypeAndLanguage(propertyType, it.language);
				saveme=false;
				if(ptbl){//if it exists and it can be edited
					if(!(ptbl.name.equals(it.name) && ptbl.plural.equals(it.plural)/*&& ptbl.nameForWeb.equals(it.nameForWeb)*/)){ //nameForWeb no debe poder ser editable xq se utiliza en la url a las paginas.
						ptbl.name=it.name;
						ptbl.plural=it.plural;
						saveme=true;
					}
				}else{
					ptbl=new PropertyTypeByLanguage(name: it.name, plural: it.plural, nameForWeb: it.nameForWeb, language: it.language, propertyType:propertyType);
					saveme=true;
				}
				if(saveme==true){
					if(!ptbl.save(flush:true)){
						GUtils.printErrors(ptbl, "Error Saving Property Type By Language '"+ptbl.name+"'");
						transactionStatus.setRollbackOnly()
						render(view:'/error', model:[message: message(code: 'propertyType.translations.save.error')]);
					}
				}
			}else{
				if(null==it.nameForWeb){//no se permite borrar xq nameForWeb utiliza en la url a las paginas.
					//ptbl=PropertyTypeByLanguage.getPropertyTypeByLanguageByPropertyTypeAndLanguage(propertyType, it.language);
					//if(ptbl!=null){//if it exists and it should be deleted
						//if(!ptbl.delete(flush:true)){
					//GUtils.printErrors(ptbl, message(code: 'propertyType.translations.delete.not.allowed.error'));
					//render(view:'/error', model:[message: message(code: 'propertyType.translations.delete.not.allowed.error')]);
						//}
					//}
					flash.message = message(code: 'propertyType.translations.delete.not.allowed.error');
				}
			}
		}

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'propertyTypeByLanguage.label', default: 'Building Feature Translation'), propertyType.id])
				redirect(id:propertyType.id, action:"show")
			}
			'*' { respond propertyType, view:"show", [status: CREATED] }
		}
	}
	
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'propertyType.label', default: 'PropertyType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
