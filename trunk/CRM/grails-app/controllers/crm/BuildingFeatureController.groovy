package crm

import static org.springframework.http.HttpStatus.*
import crm.commands.BuildingFeatureByLanguageCommand;
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BuildingFeatureController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BuildingFeature.list(params), model:[buildingFeatureCount: BuildingFeature.count()]
    }

    def show(BuildingFeature buildingFeature) {
        respond buildingFeature
    }

    def create() {
        respond new BuildingFeature(params)
    }

    @Transactional
    def save(BuildingFeature buildingFeature) {
        if (buildingFeature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (buildingFeature.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond buildingFeature.errors, view:'create'
            return
        }

        buildingFeature.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'buildingFeature.label', default: 'BuildingFeature'), buildingFeature.id])
                redirect buildingFeature
            }
            '*' { respond buildingFeature, [status: CREATED] }
        }
    }
	
	def translate() {
		BuildingFeature buildingFeature=BuildingFeature.get(params.fid);
		respond buildingFeature
	}
	
	@Transactional
	def saveTranslations(BuildingFeatureByLanguageCommand buildingFeatureByLanguageCommand, BuildingFeature buildingFeature) {
		if (buildingFeature == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}
		BuildingFeatureByLanguage pfbl=null;
		boolean saveme=false;
		buildingFeatureByLanguageCommand.items.each{
			if(null!=it.name && null!=it.plural){
				pfbl=BuildingFeatureByLanguage.findByBuildingFeatureAndLanguage(buildingFeature, it.language);
				saveme=false;
				if(pfbl){//if it exists and it can be edited
					if(!(pfbl.name.equals(it.name) && pfbl.plural.equals(it.plural))){
						pfbl.name=it.name;
						pfbl.plural=it.plural;
						saveme=true;
					}
				}else{
					pfbl=new BuildingFeatureByLanguage(name: it.name, plural: it.plural, language: it.language, buildingFeature:buildingFeature);
					saveme=true;
				}
				if(saveme==true){
					if(!pfbl.save(flush:true)){
						GUtils.printErrors(pfbl, "Error Saving Building Feature By Language '"+pfbl.name+"'");
						transactionStatus.setRollbackOnly()
						render(view:'/error', model:[message: message(code: 'buildingFeature.translations.save.error')]);
					}
				}
			}else{
				if(null==it.name && null==it.plural){
					pfbl=BuildingFeatureByLanguage.getBuildingFeatureByLanguageByBuildingFeatureAndLanguage(buildingFeature, it.language);
					if(pfbl!=null){//if it exists and it should be deleted
						if(!pfbl.delete(flush:true)){
							GUtils.printErrors(pfbl, "Error Deleting Building Feature By Language '"+pfbl.name+"'");
						}
					}
				}//else { validate if name or plural is empty }
			}
		}

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'buildingFeatureByLanguage.label', default: 'Building Feature Translation'), buildingFeature.id])
				redirect(id:buildingFeature.id, action:"show")
			}
			'*' { respond buildingFeature, view:"show", [status: CREATED] }
		}
	}
	
    def edit(BuildingFeature buildingFeature) {
        respond buildingFeature
    }

    @Transactional
    def update(BuildingFeature buildingFeature) {
        if (buildingFeature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (buildingFeature.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond buildingFeature.errors, view:'edit'
            return
        }

        buildingFeature.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'buildingFeature.label', default: 'BuildingFeature'), buildingFeature.id])
                redirect buildingFeature
            }
            '*'{ respond buildingFeature, [status: OK] }
        }
    }

    @Transactional
    def delete(BuildingFeature buildingFeature) {

        if (buildingFeature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        buildingFeature.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'buildingFeature.label', default: 'BuildingFeature'), buildingFeature.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'buildingFeature.label', default: 'BuildingFeature'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
