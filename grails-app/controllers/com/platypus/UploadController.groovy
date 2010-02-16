package com.platypus

class UploadController {

	def blobstoreService
	def imageService
	def userService
	
    def upload = { 
		def uploadUrl = blobstoreService.createUploadUrl("/upload/success")
		
		log.debug "${uploadUrl}"
		
		[uploadUrl:uploadUrl]
	}
	
	def success = {
		log.debug "we're in success"
		
		def blobs = blobstoreService.getUploadedBlobs(request)
		
		log.debug "blobs returned from blobstore service : ${blobs}"
		
		redirect(uri:"/")
	}
	
	/*
	def success = {
        def blobs = blobstoreService.getUploadedBlobs(request)
        
		def blobKey = blobs["imagefile"]

        if (blobKey == null) {
			log.error "successfully uploaded an image to blobstore, but no blobkey found"
            redirect('/');
        } else {
			def user = userService.getCurrentUser(request)
			
			if (user) {
				def image = imageService.createImage(blobKey, user)

	            redirect(controller:"images", action:"show", params:[image:image.id])
			} else {
				log.error "couldn't find the current user.  probably just orphaned that image"
				//redirect(controller:"user", action:"login")
			}
        }
    }
	*/
}
