package crm

import static org.springframework.http.HttpStatus.*

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class ClassicReportTemplateController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ClassicReportTemplate.list(params), model:[classicReportTemplateCount: ClassicReportTemplate.count()]
    }

    def show(ClassicReportTemplate classicReportTemplate) {
        respond classicReportTemplate
    }

    def create() {
        respond new ClassicReportTemplate(name:"initial name")
    }
	private ClassicReportTemplate saveTemplateFile(ClassicReportTemplate classicReportTemplate){
		def f = request.getFile('templateUpload')
		String path=grailsApplication.config.getProperty('crm.upload.template.report');
		if(!f.empty || null != classicReportTemplate.id) {
			if(!f.empty){
				File dire=new File(path);
				if(!dire.exists()){
					dire.mkdirs();
				}
				String templateName=f.getOriginalFilename();
				String ext=".jrxml";
				if(templateName.length() > ext.length() && true == GUtils.stringFinishesWith(templateName, ext)){
					templateName=GUtils.replaceIncorrectChars(templateName.substring(0,templateName.length()-ext.length()))+ext;
					File template= new File(dire.getAbsolutePath() + File.separatorChar + templateName);//remplaza los caracteres invalidos como espacios,puntos y demas del nombre, excepto la extensión
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
					    classicReportTemplate.name=template.name;
					}else{
					    classicReportTemplate.errors.rejectValue('',message(code:'classicReportTemplate.transfer.error').toString());
					}
				}else{
					classicReportTemplate.errors.rejectValue('',message(code:'classicReportTemplate.file.wrong.format.error').toString());
			    }
			}
			if (!classicReportTemplate.hasErrors()) {
				if(!classicReportTemplate.save(flush:true)){
					classicReportTemplate.errors.rejectValue('',message(code:'classicReportTemplate.save.error').toString());
				}
			}
		}else {
			classicReportTemplate.errors.rejectValue('',message(code:'classicReportTemplate.empty.file.error').toString());
		}
		return classicReportTemplate;
		//redirect(controller:'upload', action:'edit', params: [obj:this.parentObject, oid:this.objectId]);
	}
    @Transactional
    def save(ClassicReportTemplate classicReportTemplate) {
        if (classicReportTemplate == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (classicReportTemplate.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond classicReportTemplate.errors, view:'create'
            return
        }

        classicReportTemplate=this.saveTemplateFile(classicReportTemplate);
		
		if (classicReportTemplate.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond classicReportTemplate.errors, view:'create'
			return
		}
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'classicReportTemplate.label', default: 'ClassicReportTemplate'), classicReportTemplate.id])
                redirect classicReportTemplate
            }
            '*' { respond classicReportTemplate, [status: CREATED] }
        }
    }

    def edit(ClassicReportTemplate classicReportTemplate) {
        respond classicReportTemplate
    }

    @Transactional
    def update(ClassicReportTemplate classicReportTemplate) {
        if (classicReportTemplate == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (classicReportTemplate.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond classicReportTemplate.errors, view:'edit'
            return
        }

        classicReportTemplate.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'classicReportTemplate.label', default: 'ClassicReportTemplate'), classicReportTemplate.id])
                redirect classicReportTemplate
            }
            '*'{ respond classicReportTemplate, [status: OK] }
        }
    }

    @Transactional
    def delete(ClassicReportTemplate classicReportTemplate) {

        if (classicReportTemplate == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        classicReportTemplate.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'classicReportTemplate.label', default: 'ClassicReportTemplate'), classicReportTemplate.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'classicReportTemplate.label', default: 'ClassicReportTemplate'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
