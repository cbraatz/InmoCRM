package crm

import static org.springframework.http.HttpStatus.*

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class CustomReportTemplateController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond CustomReportTemplate.list(params), model:[customReportTemplateCount: CustomReportTemplate.count()]
    }

    def show(CustomReportTemplate customReportTemplate) {
        respond customReportTemplate
    }

    def create() {
        respond new CustomReportTemplate(name:"initial name")
    }
	private CustomReportTemplate saveTemplateFile(CustomReportTemplate customReportTemplate){
		def f = request.getFile('templateUpload')
		String path=grailsApplication.config.getProperty('crm.upload.template.report');
		if(!f.empty || null != customReportTemplate.id) {
			if(!f.empty){
				File dire=new File(path);
				if(!dire.exists()){
					dire.mkdirs();
				}
				String templateName=f.getOriginalFilename();
				String ext=".jrxml";
				if(templateName.length() > ext.length() && true == GUtils.stringFinishesWith(templateName, ext)){
					templateName=GUtils.replaceIncorrectChars(templateName.substring(0,templateName.length()-ext.length()))+ext;
					File template= new File(dire.getAbsolutePath() + Utils.getLocalSeparatorChar() + templateName);//remplaza los caracteres invalidos como espacios,puntos y demas del nombre, excepto la extensión
					/*boolean exists=false;
					//verify if file is already loaded
					if(dire.exists()){
						dire.eachFile(){ ff->
						    if( !ff.isDirectory() ){
						   		if(ff.name.equals(template.name)){
									   exists=true;
								}
						    }
						}
					}
					if(!exists){*/
					if(GUtils.transferFile(f, template)){
					    customReportTemplate.name=template.name;
					}else{
					    customReportTemplate.errors.rejectValue('',message(code:'customReportTemplate.transfer.error').toString());
					}
				}else{
					customReportTemplate.errors.rejectValue('',message(code:'customReportTemplate.file.wrong.format.error').toString());
			    }
			}
			if (!customReportTemplate.hasErrors()) {
				if(!customReportTemplate.save(flush:true)){
					customReportTemplate.errors.rejectValue('',message(code:'customReportTemplate.save.error').toString());
				}
			}
		}else {
			customReportTemplate.errors.rejectValue('',message(code:'customReportTemplate.empty.file.error').toString());
		}
		return customReportTemplate;
		//redirect(controller:'upload', action:'edit', params: [obj:this.parentObject, oid:this.objectId]);
	}
    @Transactional
    def save(CustomReportTemplate customReportTemplate) {
        if (customReportTemplate == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (customReportTemplate.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond customReportTemplate.errors, view:'create'
            return
        }

        customReportTemplate=this.saveTemplateFile(customReportTemplate);
		
		if (customReportTemplate.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond customReportTemplate.errors, view:'create'
			return
		}
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'customReportTemplate.label', default: 'CustomReportTemplate'), customReportTemplate.id])
                redirect customReportTemplate
            }
            '*' { respond customReportTemplate, [status: CREATED] }
        }
    }

    def edit(CustomReportTemplate customReportTemplate) {
        respond customReportTemplate
    }

    @Transactional
    def update(CustomReportTemplate customReportTemplate) {
        if (customReportTemplate == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (customReportTemplate.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond customReportTemplate.errors, view:'edit'
            return
        }

        customReportTemplate.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'customReportTemplate.label', default: 'CustomReportTemplate'), customReportTemplate.id])
                redirect customReportTemplate
            }
            '*'{ respond customReportTemplate, [status: OK] }
        }
    }

    @Transactional
    def delete(CustomReportTemplate customReportTemplate) {

        if (customReportTemplate == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        customReportTemplate.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'customReportTemplate.label', default: 'CustomReportTemplate'), customReportTemplate.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'customReportTemplate.label', default: 'CustomReportTemplate'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
