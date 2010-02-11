package com.platypus

class ImagesController {

	def blobstoreService
	
	def show = {
		if (params?.image) {
			def image = Image.findById(params.image);
			
			if (image) {
	        	blobstoreService.serve(image.blobKey, response);
			} else {
				//show some standard image saying that we're missing this image
				throw new RuntimeException("not implemented yet")
			}
		}
	}
}
