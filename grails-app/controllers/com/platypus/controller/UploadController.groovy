package com.platypus.controller

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.DateTimeZone

import com.platypus.domain.Image
import com.platypus.domain.User

class UploadController {

	/*
	Why do i have to do this?
	*/
	def securityService
	def imageService
	def userService
	
    def upload = {
	
		log.info "services available : signature -> ${securityService}, image -> ${imageService}, user -> ${userService}"
				
		def timestamp = new DateTime(DateTimeZone.UTC).plusMinutes(30);
		
		def apiKey = grailsApplication.config.amazonaws.apiKey
		def secretKey = grailsApplication.config.amazonaws.secretKey
		def bucket = grailsApplication.config.platypus.imageBucket
		
		def successUrl = grailsApplication.config.grails.serverURL + "/upload/success";
		
		log.info "successUrl = ${successUrl}"
		
		def key = imageService.buildUniqueImageKey();
		
		def policy = """\
		{"expiration": "${timestamp.toString()}",
		  "conditions": [ 
		    {"bucket": "${bucket}"},
		    ["starts-with", "\$key", ""],
		    {"acl": "public-read"},
		    {"success_action_redirect": "${successUrl}"}
		  ]
		}"""
		
		log.info "policy : ${policy}"
				
		def results = securityService.sign(policy, secretKey)
		
		if (log.isDebugEnabled()) {
			log.debug "Base64 Policy : ${results['signed']}"
			log.debug "Signature : ${results['signature']}"
		}
		
		return [bucket : bucket, key : key, apiKey : apiKey, policyBase64 : results['signed'], signature : results['signature']]
	}
	
	def success = {
		def image = new Image(params)
		
		def user = userService.getCurrentUser(request)
		
		if (user == null) {
			log.error "NULL User returned from ggetCurrentUser()"
			throw new RuntimeException()
		}
		
		image.owner = user;
		
		image.save(flush:true)
		
		log.debug "${image.errors}"
		
		return [image : image]
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
