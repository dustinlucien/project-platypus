package com.platypus

class UploadController {

	def blobstoreService
	def imageService
	def userService
	
    def upload = { 
		[uploadUrl:blobstoreService.createUploadUrl("/upload/success")]
	}
	
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
}
