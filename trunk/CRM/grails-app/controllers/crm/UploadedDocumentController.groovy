package crm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional


@Transactional(readOnly = true)
class UploadedDocumentController {
	def index = { redirect(action:edit, params:params) }
	static transactional = true
	
	def allowedMethods = []
	String myPath=null;
	String parentObject=null;
	String objectId=null;
	
	//from https://fbflex.wordpress.com/2008/11/26/a-simple-grails-controller-for-file-management/
	@Transactional
	def edit(){
		this.parentObject=params.obj;
		this.objectId=params.oid;
		def documentFileInstanceList = []
		
		//validar params.obj y params.oid
		
		Concession concession;
		if(params.obj.equals("concession")){
			if(params.oid != null){
				concession=Concession.get(params.oid);
				this.myPath=grailsApplication.config.getProperty('crm.upload.document.concession')+File.separatorChar+params.oid;
				def dire = new File(this.myPath);
				dire.mkdirs();
				UploadedDocument uploadedDoc;
				if( dire.exists() ){
					dire.eachFile(){ file->
						if( !file.isDirectory() ){
							//si no existe en la base de datos hacer new UploadedDocument y save
							uploadedDoc=UploadedDocument.findByFileNameAndConcession(file.name, concession);
							if(!uploadedDoc){
								uploadedDoc=new UploadedDocument(description:"-", fileName:file.name, sizeInKB:file.length(), concession:concession, date:new Date());
								if(uploadedDoc.save(flush:true)){
									documentFileInstanceList.add(uploadedDoc);
								}else{
									transactionStatus.setRollbackOnly()
									GUtils.printErrors(uploadedDoc, "Saving document '"+file.name+"'");
									render(view:'/error', model:[message: message(code: 'uploadedDocument.preloaded.document.save.error')]);
								}
							}else{
								documentFileInstanceList.add(uploadedDoc);
							}
						}
					}
					boolean exists=false;
					def allDocs=UploadedDocument.findAll();
					allDocs.each(){
						exists=false;
						dire.eachFile(){ myDoc->
							if( !myDoc.isDirectory() ){
								if(it.fileName.equals(myDoc.name)){
									exists=true;
								}
							}
						}
						if(!exists){
							it.delete(flush:true);
							System.out.println("Borrando documento de la BD."+it.fileName);
						}
					}
				}else{
					render(view:'/error', model:[message: message(code: 'uploadedDocument.directory.error')]);
				}
				if(!concession?.getCurrentContract()?.uploadedDocument) {
					flash.message = message(code: 'uploadedDocument.required.message');
				}
			}else{
				render(view:'/error', model:[message: message(code: 'default.invalid.paramethers.error', args: ["oid = null"])]);
			}
		}else{
			render(view:'/error', model:[message: message(code: 'default.invalid.paramethers.error', args: ["obj = "+params.obj])]);
		}
		respond concession, model:[documentFileInstanceList:documentFileInstanceList]
	}
	@Transactional
	def delete(){
		UploadedDocument upDoc=UploadedDocument.get(params.id);
		def file=null;
		if(null != upDoc){
			file=new File(grailsApplication.config.getProperty('crm.upload.document.concession')+File.separatorChar+upDoc.concession.id+File.separatorChar+upDoc.fileName);
			upDoc.delete(flush:true);//deleting uploaded document
		}
		if(null == UploadedDocument.get(params.id) && null != file){//si ya se borró o el documento quedó cargada por error
			if(file.delete()){
				flash.message = message(code: 'uploadedDocument.removed.document.message', args: [upDoc.fileName]);
			}else{
				flash.message = message(code: 'uploadedDocument.remove.file.error', args: [upDoc.fileName]);
			}
		}else{
			GUtils.printErrors(upDoc, "Deleting document '"+file.name+"'");
			flash.message = message(code: 'upload.file.delete.error', args: [upDoc.fileName]);
		}
		redirect(controller:'uploadedDocument', action:'edit', params: [obj:this.parentObject, oid: this.objectId])
	}
	@Transactional
	def confirm(){//confirm document as current contract
		Concession concession;
		if(params.cid != null){
				concession=Concession.get(params.cid);
				if(concession != null){
					Contract contract=concession.getCurrentContract();
					if(contract != null){
						UploadedDocument ud=UploadedDocument.get(params.id);
						if(null != ud){
							contract.uploadedDocument=ud;
							if(contract.save(flush:true)){
								flash.message = message(code: 'uploadedDocument.current.contract.saved.message');
							}else{
								transactionStatus.setRollbackOnly()
								render(view:'/error', model:[message: message(code: 'uploadedDocument.current.contract.save.error')]);
							}
						}else{
							GUtils.printErrors(upDoc, "Deleting document '"+file.name+"'");
							flash.message = message(code: 'upload.file.delete.error', args: [upDoc.fileName]);
						}
					}else{
						render(view:'/error', model:[message: message(code: 'uploadedDocument.current.contract.not.found.error')]);
					}
				}else{
					render(view:'/error', model:[message: message(code: 'uploadedDocument.concession.not.found.error', args: [params.cid])]);
				}
		}else{
			render(view:'/error', model:[message: message(code: 'default.invalid.paramethers.error', args: ["cid = "+params.cid])]);
		}
		redirect(controller:'uploadedDocument', action:'edit', params: [obj:this.parentObject, oid: this.objectId])
	}
	@Transactional
	def update(String description){
		def f = request.getFile('fileUpload')
		if(!f.empty) {
		File dire=new File(this.myPath);
		dire.mkdirs()
		File file= new File(dire.getAbsolutePath() + File.separatorChar + f.getOriginalFilename());
		   if(file.length() <= Long.parseLong(grailsApplication.config.getProperty('grails.controllers.upload.internalMaxFileSize'))){
			  boolean exists=false;
			  //verify if file is already loaded
			  if(dire.exists()){
				  dire.eachFile(){ ff->
				  if( !ff.isDirectory() )
					  if(ff.name.equals(file.name)){
						  exists=true;
					  }
				  }
			  }
			  if(!exists){
				  //f.transferTo(file);
				  if(GUtils.transferFile(f, file)){
					  if(!UploadedDocument.findByFileNameAndConcession(file.name, Concession.get(this.objectId))){
						  UploadedDocument upDoc=new UploadedDocument(description:description, fileName:file.name, sizeInKB:file.length()/1024 , concession:Concession.get(this.objectId), date:new Date());
						  if(!upDoc.save(flush:true)){
							  transactionStatus.setRollbackOnly()
							  GUtils.printErrors(upDoc, "Error Saving new document '"+file.name+"'");
							  flash.message=message(code: 'uploadedDocument.document.save.error', args: [file.name]);
						  }
					  }
				  }else{
						GUtils.printErrors(upDoc, "Error transfering new document '"+file.name+"'");
						flash.message=message(code: 'upload.file.transfer.error', args: [file.name]);
				  }
			  }else{
				  flash.message=message(code: 'upload.file.already.uploaded.error', args: [file.name]);
			  }
		   }else {
			  flash.message = message(code: 'upload.file.size.error', args: [file.name, file.length(), Long.parseLong(grailsApplication.config.getProperty('grails.controllers.upload.internalMaxFileSize'))]);
		   }
		}else {
		   flash.message=message(code: 'upload.empty.file.error');
		}
		redirect(controller:'uploadedDocument', action:'edit', params: [obj:this.parentObject, oid:this.objectId]);
	}
    def index() { }
}
