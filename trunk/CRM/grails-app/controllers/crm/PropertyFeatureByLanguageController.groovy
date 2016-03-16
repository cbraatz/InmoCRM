package crm

import static org.springframework.http.HttpStatus.*
import crm.commands.PropertyFeatureByLanguageCommand;
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PropertyFeatureByLanguageController {
    def show(PropertyFeatureByLanguage propertyFeatureByLanguage) {//accesible a través de la tabla en el show de PropertyFeature
        redirect(controller:"propertyFeature", action:"translate", params:[fid:propertyFeatureByLanguage.propertyFeature.id])
    }
}
