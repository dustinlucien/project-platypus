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
    
    /*
      A hack to get ahold of the webflow variables.  should have these passed in with flash params or something
    */
    
    def successUrl = taglib.createLink(controller:'flash', action:'postfinish', params:[etoken:session.etoken], absolute:'true')

    if (log.isDebugEnabled()) {
      log.debug "the redirect URL to use following S3 upload : ${successUrl}"
    }
    
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

    def results = securityService.signPolicy(policy, secretKey)
    
    response.status = 200
    render "${imageKey}\n${results['signed']}\n${results['signature']}\n${apiKey}\nhttp://${bucket}.s3.amazonaws.com\n${successUrl}"
  }
	
	/*
		Requester must add their session token and the temporary etoken to the url or params
	*/
	def postfinish = {
		def etoken = params?.etoken
		def execution = session?.flowExecution
		
		if (etoken == null || (!(session?.etoken?.equals(etoken)))) {
		  log.error "incorrect ephemeral token in the params"
		  flash.message = "incorrect security token"
		} else if (execution == null) {
		  log.error "no flow execution state in session.  redirecting to home page"
		  flash.message = "There was an error in the upload flow.  Please try again"
		} else {
		  
  		def image = imageService.saveNewImage(params, userService.getCurrentUser(request))
  		
  		redirect(controller:'create',action:'redneckify',params:[_eventId:'finish', image:image.pkey, execution: (execution + "s2")])
	  }
	}
}