package com.platypus.controller

import com.platypus.domain.Image

class ShareController {
  
    def userService
    def imageService
    def urlShortenerService
    def facebookConnectService
    
    def index = {
      def user = userService.getCurrentUser(request)
      
      if (user == null) {
        log.debug "no user.  sending them to the gallery."
        redirect(controller:'gallery')
        return
      }
      
      def image = imageService.getMostRecentImage(user)
      
      if (image == null) {
        log.debug "no image(s) for this user.  sending them to the gallery."
        
        flash.message = "You haven't Redneckified anything yet.  Have a look at this here gallery for some insperatin'."
        
        redirect(controller:'gallery')
        return
      }
      
      def taglib = new org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib()

      def longUrl = taglib.createLink(controller:'share', action:'show', id:"${image.pkey}", absolute:'true')
      def shortUrl = urlShortenerService.shortenUrl(longUrl)
      
      log.debug "shortUrl back from shortening service : ${shortUrl}"
      
      return [ image : image, longUrl : longUrl, shortUrl : shortUrl, stoken : session.stoken]
    }
    
    def show = {
      def image = null
      
      if (!params?.id) {
        redirect(controller:'gallery')
        return
      }
      
      image = Image.findByPkey(params.id)
      if (image == null) {
        log.debug "unkown image id"
        
        flash.message = "That image doesn't exist.  Have a look at some of these, or create your own."
        
        redirect(controller:'gallery')
        return
      }
       
      def taglib = new org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib()

      def longUrl = taglib.createLink(controller:'share', action:'show', id:"${image.pkey}", absolute:'true')
      def shortUrl = urlShortenerService.shortenUrl(longUrl)

      log.debug "shortUrl back from shortening service : ${shortUrl}"
              
      return [image : image, longUrl : longUrl, shortUrl : shortUrl, facebookAppId : grailsApplication.config.facebook.appId]
    }
    
    def pubtofb = {
      if (!params?.st || (params.st != session.stoken)) {
        response.status = 403
        render "Incorrect security token"
        return
      }
      
      if (!params?.image) {
        response.status = 403
        render "Incorrect POST Url.  No image key specified"
        return
      }
      
      def image = Image.findByPkey(params.image)
      
      if (!image) {
        log.error "could not locate image for pkey ${params.image}"
        response.status = 404
        render "Image not found"
        return
      }
      
      def user = userService.getCurrentUser(request)
      
      if (user.id != image.owner.id) {
        log.error "Someone is trying to post an image to Facebook that they don't own!!!"
        log.error "Current user : ${user.id} Image.Owner : ${image.owner.id}"
        response.status = 403
        render "Permission denied"
        return
      }
      
      def taglib = new org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib()

      def longUrl = taglib.createLink(controller:'share', action:'show', id:"${image.pkey}", absolute:'true')

      def shortUrl = urlShortenerService.shortenUrl(longUrl)
      
      def message = "I just created an image with Redneckify!  Make one of your own here ${shortUrl}"
      
      if (!facebookConnectService.publishImage(image, message)) {
        log.error "Had trouble posting the image to Facebook"
      }
      
      response.status = 200
      render ""
      return;
    }
}
