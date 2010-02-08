package com.platypus

class UploadController {

	def blobstoreService
	
    def upload = { 
		[uploadUrl:blobstoreService.createUploadUrl()]
	}
}
