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

    def timestamp = new DateTime(DateTimeZone.UTC).plusMinutes(30);

    def taglib = new org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib()

    session.etoken = RandomStringUtils.randomAlphanumeric(8)
    
    def successUrl = taglib.createLink(controller:'flash', action:'postfinish', absolute:'true') + "?etoken=" + session.etoken

    def imageKey = imageService.buildUniqueImageKey();

    def policy = """\
    {"expiration": "${timestamp.toString()}",
      "conditions": [
        {"bucket": "${bucket}"},
        ["starts-with", "\$key", ""],
        {"acl": "public-read"},
        {"success_action_redirect": "${successUrl}"},
      ]
    }"""

    log.info "policy : ${policy}"

    def results = securityService.sign(policy, secretKey)
    
    response.status = 200
    render "${imageKey}\n${results['signed']}\n${results['signature']}\n${apiKey}\nhttp://${bucket}.s3.amazonaws.com\n${successUrl}"
  }
	
	/*
		Requester must add their session token and the temporary etoken to the url or params
	*/
	def postfinish = {
		def etoken = params?.etoken
		
		if (etoken == null || (!(session?.etoken?.equals(etoken)))) {
			response.status = 403
			render "Incorrect ephemeral token"
		} else {		
  		def user = userService.getCurrentUser(request)
  		def image = imageService.saveNewImage(params, user)
  		redirect(controller:'shop',action:'index')
	  }
	}
}
