package crm

import static org.springframework.http.HttpStatus.*
import crm.commands.PropertyFeatureByLanguageCommand;
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PropertyFeatureController {

    static allowedMethods = [save: "POST", saveTranslations: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PropertyFeature.list(params), model:[propertyFeatureCount: PropertyFeature.count()]
    }

    def show(PropertyFeature propertyFeature) {
        respond propertyFeature
    }

    def create() {
        respond new PropertyFeature(params)
    }

    @Transactional
    def save(PropertyFeature propertyFeature) {
        if (propertyFeature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (propertyFeature.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond propertyFeature.errors, view:'create'
            return
        }

        propertyFeature.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'propertyFeature.label', default: 'PropertyFeature'), propertyFeature.id])
                redirect propertyFeature
            }
            '*' { respond propertyFeature, [status: CREATED] }
        }
    }
	def translate() {
		PropertyFeature propertyFeature=PropertyFeature.get(params.fid);
		respond propertyFeature
	}
	@Transactional
	def saveTranslations(PropertyFeatureByLanguageCommand propertyFeatureByLanguageCommand, PropertyFeature propertyFeature) {
		if (propertyFeature == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}
		PropertyFeatureByLanguage pfbl=null;
		boolean saveme=false;
		propertyFeatureByLanguageCommand.items.each{
			if(null!=it.name && null!=it.plural){
				pfbl=PropertyFeatureByLanguage.findByPropertyFeatureAndLanguage(propertyFeature, it.language);
				saveme=false;
				if(pfbl){//if it exists and it can be edited
					if(!(pfbl.name.equals(it.name) && pfbl.plural.equals(it.plural))){
						pfbl.name=it.name;
						pfbl.plural=it.plural;
						saveme=true;
					}
				}else{
					pfbl=new PropertyFeatureByLanguage(name: it.name, plural: it.plural, language: it.language, propertyFeature:propertyFeature);
					saveme=true;
				}
				if(saveme==true){
					if(!pfbl.save(flush:true)){
						GUtils.printErrors(pfbl, "Error Saving Property Feature By Language '"+pfbl.name+"'");
						transactionStatus.setRollbackOnly()
						render(view:'/error', model:[message: message(code: 'propertyFeature.translations.save.error')]);
					}
				}
			}else{
				if(null==it.name && null==it.plural){
					pfbl=PropertyFeatureByLanguage.getPropertyFeatureByLanguageByPropertyFeatureAndLanguage(propertyFeature, it.language);
					if(pfbl!=null){//if it exists and it should be deleted
						if(!pfbl.delete(flush:true)){
							GUtils.printErrors(pfbl, "Error Deleting Property Feature By Language '"+pfbl.name+"'");
						}
					}
				}//else { validate if name or plural is empty }
			}
		}

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'propertyFeatureByLanguage.label', default: 'Property Feature Translation'), propertyFeature.id])
				redirect(id:propertyFeature.id, action:"show")
			}
			'*' { respond propertyFeature, view:"show", [status: CREATED] }
		}
	}
    def edit(PropertyFeature propertyFeature) {
        respond propertyFeature
    }

    @Transactional
    def update(PropertyFeature propertyFeature) {
        if (propertyFeature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (propertyFeature.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond propertyFeature.errors, view:'edit'
            return
        }

        propertyFeature.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'propertyFeature.label', default: 'PropertyFeature'), propertyFeature.id])
                redirect propertyFeature
            }
            '*'{ respond propertyFeature, [status: OK] }
        }
    }

    @Transactional
    def delete(PropertyFeature propertyFeature) {

        if (propertyFeature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
        propertyFeature.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'propertyFeature.label', default: 'PropertyFeature'), propertyFeature.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'propertyFeature.label', default: 'PropertyFeature'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
