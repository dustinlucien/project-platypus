package com.platypus

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import org.codehaus.groovy.grails.commons.ConfigurationHolder;

class UploadController {

	def imageService
	def userService
	def securityService
	
    def upload = {
	
		def expiration = new DateTime().plusMinutes(30);
		
		def formatter = DateTimeFormat.forPattern("dd/MMM/yyyy:HH:mm:ss Z");
		
		def s3Bucket = ConfigurationHolder.config.platypus.imageBucket;
		
		def successUrl = ConfigurationHolder.config.grails.serverURL + "/" + controller + "/success";
		
		log.info "successUrl = ${successUrl}"
		
		def policy = """\
		{"expiration": ${formatter.print(expiration)}",
		  "conditions": [ 
		    {"bucket": "${s3Bucket}"}, 
		    ["starts-with", "\$key", ""],
		    {"acl": "private"},
		    ["starts-with", "\$success_action_redirect", "${successUrl}"],
		    ["starts-with", "\$Content-Type", ""],
		    ["content-length-range", 0, 1048576]
		  ]
		};""";
		
		log.info "policy : ${policy}"
		
		def secretKey = ConfigurationHolder.config.amazonaws.secretKey;
		
		def results = securityService.sign(policy, secretKey)
		
		log.info "Base64 Policy : ${results['signed']}"
		log.info "Signature : ${results['signature']}"
	}
	
	def success = {
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
