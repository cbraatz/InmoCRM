package crm


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UploadController {
	def index = { redirect(action:images, params:params) }
	static transactional = true
	
	def allowedMethods = []
	String myPath=null;
	String parentObject=null;
	String objectId=null;
	
	//from https://fbflex.wordpress.com/2008/11/26/a-simple-grails-controller-for-file-management/
	@Transactional
	def images(){
		this.parentObject=params.obj;
		this.objectId=params.oid;
		def fileResourceInstanceList = []
		
		//def f = new File(grailsApplication.config.images.location.toString())
		//validar params.obj y params.oid
		
		ManagedProperty managedProperty;
		if(params.obj.equals("property") && params.oid != null){
			managedProperty=ManagedProperty.get(params.oid);
			this.myPath=grailsApplication.config.getProperty('crm.upload.image.property')+File.separatorChar+params.oid;
			def dire = new File(this.myPath);
			dire.mkdirs();
			UploadedImage uploadedImage;
			if( dire.exists() ){
				dire.eachFile(){ file->
					if( !file.isDirectory() ){
						//si no existe en la base de datos hacer new uploadedImage y save
						uploadedImage=UploadedImage.findByFileNameAndManagedProperty(file.name, managedProperty);
						if(!uploadedImage){
							uploadedImage=new UploadedImage(description:"", fileName:file.name, sizeInKB:file.length(), managedProperty:managedProperty, isMainImage:false, addToWeb:true);
							if(uploadedImage.save(flush:true)){
								fileResourceInstanceList.add(uploadedImage);
							}else{
								GUtils.printErrors(uploadedImage, "Saving image '"+file.name+"'");
								render(view:'/error', model:[message: message(code: 'upload.preloaded.image.save.error')]);
							}
						}else{
							fileResourceInstanceList.add(uploadedImage);
						}
					}
				}
				boolean exists=false;
				def allImages=UploadedImage.findAll();
				allImages.each(){
					exists=false;
					dire.eachFile(){ hdImg->
						if( !hdImg.isDirectory() ){
							if(it.fileName.equals(hdImg.name)){
								exists=true;
							}
						}
					}
					if(!exists){
						it.delete(flush:true);
						System.out.println("Borrando imagen de la BD."+it.fileName);
					}
				}
			}else{
				render(view:'/error', model:[message: message(code: 'upload.image.directory.error')]);
			}
			
		}else{
			render(view:'/error', model:[message: message(code: 'default.invalid.paramethers.error')]);
		}
		respond managedProperty, model:[fileResourceInstanceList:fileResourceInstanceList]
	}
	@Transactional
	def deleteImage(){
		UploadedImage upIm=UploadedImage.get(params.id);
		def file=null;
		if(null != upIm){
			file=new File(grailsApplication.config.getProperty('crm.upload.image.property')+File.separatorChar+upIm.managedProperty.id+File.separatorChar+upIm.fileName);
			upIm.delete(flush:true);//deleting uploaded image
		}
		if(null == UploadedImage.get(params.id) && null != file){//si ya se borró o la imagen quedó cargada por error
			if(file.delete()){
				flash.message = message(code: 'upload.removed.image.message', args: [upIm.fileName]);
			}else{
				flash.message = message(code: 'upload.image.remove.file.error', args: [upIm.fileName]);
			}
		}else{
			GUtils.printErrors(upIm, "Deleting image '"+file.name+"'");
			flash.message = message(code: 'upload.file.delete.error', args: [upIm.fileName]);
		}
		redirect(controller:'upload', action:'images', params: [obj:this.parentObject, oid: this.objectId])
	}
	
	@Transactional
	def uploadImage(String description, boolean mainImage, boolean addToWebPage){
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
					  if(!UploadedImage.findByFileNameAndManagedProperty(file.name, ManagedProperty.get(this.objectId))){
						  UploadedImage upIm=new UploadedImage(description:description, fileName:file.name, isMainImage:mainImage, addToWeb:addToWebPage, sizeInKB:file.length()/1024 , managedProperty:ManagedProperty.get(this.objectId));
						  if(!upIm.save(flush:true)){
							  GUtils.printErrors(upIm, "Error Saving new image '"+file.name+"'");
							  flash.message=message(code: 'upload.image.save.error', args: [file.name]);
						  }
					  }
				  }else{
						GUtils.printErrors(upIm, "Error transfering new image '"+file.name+"'");
						flash.message=message(code: 'upload.image.transfer.error', args: [file.name]);
				  }
			  }else{
				  flash.message=message(code: 'upload.image.already.uploaded.error', args: [file.name]);
			  }
		   }else {
			  flash.message = message(code: 'upload.file.size.error', args: [file.name, file.length(), Long.parseLong(grailsApplication.config.getProperty('grails.controllers.upload.internalMaxFileSize'))]);
		   }
		}else {
		   flash.message=message(code: 'upload.image.empty.file.error');
		}
		redirect(controller:'upload', action:'images', params: [obj:this.parentObject, oid:this.objectId]);
	}
}
