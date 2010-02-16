package com.platypus

class ImagesController {	
	def show = {
		if (params?.image) {
			def image = Image.get(params.image);
			
			if (image) {
	     		//replace with s3
			} else {
				//show some standard image saying that we're missing this image
				throw new RuntimeException("not implemented yet")
			}
		}
	}
}
