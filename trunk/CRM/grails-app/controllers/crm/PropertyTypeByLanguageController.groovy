package crm

import static org.springframework.http.HttpStatus.*
import crm.commands.PropertyTypeByLanguageCommand;
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PropertyTypeByLanguageController {
	def show(PropertyTypeByLanguage propertyTypeByLanguage) {//accesible a través de la tabla en el show de PropertyType
		redirect(controller:"propertyType", action:"translate", params:[fid:propertyTypeByLanguage.propertyType.id])
	}
}
