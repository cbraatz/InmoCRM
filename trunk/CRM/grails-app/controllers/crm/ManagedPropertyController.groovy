package crm

import static org.springframework.http.HttpStatus.*
import crm.commands.FeatureByPropertyCommand;
import crm.exception.CRMException
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ManagedPropertyController {

    static allowedMethods = [save: "POST", update: "PUT"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ManagedProperty.list(params), model:[managedPropertyCount: ManagedProperty.count()]
    }

    def show(ManagedProperty managedProperty) {
		Concession conc=managedProperty.getActiveConcession();
		if (!conc.agent.partner.isAgent) {
			managedProperty.errors.rejectValue('',message(code:'concession.partner.agent.required.error').toString());
		}
		if (!managedProperty.hasImagesForWeb()) {
			managedProperty.errors.rejectValue('',message(code:'managedProperty.images.required.error').toString());
		}
        respond managedProperty
    }

    def create() {
		ManagedProperty mp=new ManagedProperty(params);
		mp.soldByUs=false;
        respond mp
    }
	def addEditImages(ManagedProperty managedProperty){
		redirect(controller:'upload', action:'images', params: [obj:'property', oid: managedProperty.id])
	}
    @Transactional
    def save(ManagedProperty managedProperty) {
        if (managedProperty == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (managedProperty.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond managedProperty.errors, view:'create'
            return
        }

        managedProperty.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'managedProperty.label', default: 'ManagedProperty'), managedProperty.id])
                redirect managedProperty
            }
            '*' { respond managedProperty, [status: CREATED] }
        }
    }
	
	
	
    def edit(ManagedProperty managedProperty) {
        respond managedProperty, model:[featureByPropertyCommand: new FeatureByPropertyCommand(FeatureByProperty.getStoredFeatureByPropertyListForEachPropertyFeature(managedProperty))]
    }

    @Transactional
    def update(ManagedProperty managedProperty, FeatureByPropertyCommand featureByPropertyCommand) {
        if (managedProperty == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (managedProperty.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond managedProperty.errors, view:'edit'
            return
        }

        
		if(managedProperty.save(flush:true)){
			FeatureByProperty fbp=null;
			featureByPropertyCommand.pfitems.each{
				if(it.value > 0){
					it.managedProperty=managedProperty;
					fbp=FeatureByProperty.findByManagedPropertyAndPropertyFeature(managedProperty,it.propertyFeature);
					if(null!=fbp){
						if(fbp.value != it.value || !fbp.description.equals(it.description)){
							fbp.value=it.value;
							fbp.description=it.description;
							if(!fbp.save(flush:true)){
								GUtils.printErrors(fbp,"featureByProperty save. PropertyFeature = "+fbp.propertyFeature?.name);
								transactionStatus.setRollbackOnly();
								throw new CRMException("featureByProperty save. PropertyFeature = "+fbp.propertyFeature?.name);
							}
						}
					}else{
						if(!it.save(flush:true)){
							GUtils.printErrors(it,"featureByProperty save. PropertyFeature = "+it.propertyFeature?.name);
							transactionStatus.setRollbackOnly();
							throw new CRMException("featureByProperty save. PropertyFeature = "+it.propertyFeature?.name);
						}
					}
				}
			}
		}else{
			throw new CRMException("Error saving managedProperty.");
		}
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'managedProperty.label', default: 'ManagedProperty'), managedProperty.id])
                redirect managedProperty
            }
            '*'{ respond managedProperty, [status: OK] }
        }
    }
	
    /*@Transactional
    def delete(ManagedProperty managedProperty) { //must add cascade delete for building and so on..

        if (managedProperty == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        managedProperty.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'managedProperty.label', default: 'ManagedProperty'), managedProperty.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }*/

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'managedProperty.label', default: 'ManagedProperty'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
