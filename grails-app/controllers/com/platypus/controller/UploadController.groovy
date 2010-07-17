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
  
  def index = {
        
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
    
    def results = securityService.signPolicy(policy, secretKey)
    
    if (log.isDebugEnabled()) {
      log.debug "Base64 Policy : ${results['signed']}"
      log.debug "Signature : ${results['signature']}"
    }
    
    def fbPhotos = null
    def fbProfile = null
    
    def photoData = null
       
    if (facebookConnectService.isLoggedIn()) {
      fbPhotos = facebookConnectService.listPhotos()
      fbProfile = facebookConnectService.getProfilePicture()
      
      if (fbPhotos != null) {
        
        log.debug "found  some photos from facebook"
        log.debug "${fbPhotos}"
        
        photoData = fbPhotos.getData()
        
        log.debug "${photoData}"
        log.debug "found ${photoData.size()} photos"
        
        if (log.isDebugEnabled()) {
          photoData.each {
            log.debug "${it}"
          }
        }
      } else {
        log.debug "photos are null coming from facebook"
      }
      
      if (fbProfile != null) {
        log.debug "found  some photo albums on facebook"
        log.debug "${fbProfile}"
      }
      
    }
    
    return [fbLoggedIn : facebookConnectService.isLoggedIn(), fbPhotos : photoData, bucket : bucket, chiave : chiave, apiKey : apiKey, policyBase64 : results['signed'], signature : results['signature']]
  }
  
  def success = {
    def user = userService.getCurrentUser(request)
    def image = imageService.saveNewImage(params, user)
    redirect(controller:'share')
  }
  
  
}
