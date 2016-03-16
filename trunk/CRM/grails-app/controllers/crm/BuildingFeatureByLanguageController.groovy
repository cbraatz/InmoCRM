package crm

import static org.springframework.http.HttpStatus.*
import crm.commands.BuildingFeatureByLanguageCommand;
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BuildingFeatureByLanguageController {
	def show(BuildingFeatureByLanguage buildingFeatureByLanguage) {//accesible a trav�s de la tabla en el show de BuildingFeature
		redirect(controller:"buildingFeature", action:"translate", params:[fid:buildingFeatureByLanguage.buildingFeature.id])
	}
}