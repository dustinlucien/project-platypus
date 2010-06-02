package com.platypus.controller

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.DateTimeZone

import com.platypus.domain.Image
import com.platypus.domain.User

class UploadController {

	def facebookConnectService
	def securityService
	def imageService
	def userService
	
    def upload = {
	
		def user = userService.getCurrentUser(request)
		
		log.info "services available : signature -> ${securityService}, image -> ${imageService}, user -> ${userService}"
				
		def timestamp = new DateTime(DateTimeZone.UTC).plusMinutes(30);
		
		def apiKey = grailsApplication.config.amazonaws.apiKey
		def secretKey = grailsApplication.config.amazonaws.secretKey
		def bucket = grailsApplication.config.platypus.imageBucket
		
		/*
		Why is it it so hard to generate a proper URL inside the controller?
		*/
		def taglib = new org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib()

		def successUrl = taglib.createLink(controller:'upload', action:'success', absolute:'true')
		
		/*
			def successUrl = grailsApplication.config.grails.serverURL + "/upload/success";
		*/
		
		log.info "successUrl = ${successUrl}"
		
		def chiave = imageService.buildUniqueImageKey();
		
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
		
		def fbLoggedIn = facebookConnectService.isLoggedIn(request)
		def fbPhotos = null
		
		if (fbLoggedIn) {
			fbPhotos = facebookConnectService.getPhotos(request)
		}
		
		return [fbLoggedIn : fbLoggedIn, fbPhotos : fbPhotos, bucket : bucket, chiave : chiave, apiKey : apiKey, policyBase64 : results['signed'], signature : results['signature']]
	}
	
	def success = {
		def user = userService.getCurrentUser(request)
		def image = imageService.saveNewImage(params, user)
		redirect(controller:'shop',action:'show')
	}
	

}
