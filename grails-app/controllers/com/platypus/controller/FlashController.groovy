package com.platypus.controller

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat

import com.platypus.domain.User
import com.platypus.domain.Image

import org.apache.commons.lang.RandomStringUtils

class FlashController {
	def securityService
	def imageService
	def userService

	def posturl = {

		def apiKey    = grailsApplication.config.amazonaws.apiKey
		def secretKey = grailsApplication.config.amazonaws.secretKey
		def bucket    = grailsApplication.config.platypus.imageBucket
		
		/*
		Why is it it so hard to generate a proper URL inside the controller?
		*/
		def taglib = new org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib()

		session.etoken = RandomStringUtils.randomAlphanumeric(8)
		def successUrl = taglib.createLink(controller:'flash', action:'postfinish', absolute:'true') + "?etoken=" + session.etoken
		
		def imageKey = imageService.buildUniqueImageKey();
		def timestamp = new DateTime(DateTimeZone.UTC).plusMinutes(20);
		
		long secondsTimestamp = timestamp.getMillis() / 1000;
		
		log.debug "timestamp : " + timestamp.toString()
		log.debug "timestamp epoch : " + secondsTimestamp
		log.debug "javas version : " + System.currentTimeMillis()
		
		def stringToSign = "POST\n\n\n" + secondsTimestamp + "\n" + "/" + bucket + "/" + imageKey;
		//def stringToSign = "PUT\n\n" + "application/x-www-form-urlencoded" + "\n" + secondsTimestamp + "\n" + "/" + bucket + "/" + imageKey;
    
		def signature = securityService.signHeaders(stringToSign, secretKey)
		
		def postUrl = "http://" + bucket + ".s3.amazonaws.com/" + imageKey + 
					  "?AWSAccessKeyId=" + apiKey + "&Signature=" + signature['signature'].encodeAsURL() + 
					  "&Expires=" + secondsTimestamp
		
		response.status = 200
		render "${postUrl}\n${successUrl}"
	}
	
	/*
		Requester must add their session token and the temporary etoken to the url or params
	*/
	def postfinish = {
		def etoken = params?.etoken
		
		if (etoken == null || (!(session?.etoken.equals(etoken)))) {
			response.status = 403
			render "Incorrect ephemeral token"
		}
		
		def user = userService.getCurrentUser(request)
		def image = imageService.saveNewImage(params, user)
		redirect(controller:'shop',action:'show')
	}
}
