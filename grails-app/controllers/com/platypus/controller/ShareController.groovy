package com.platypus.controller

import com.platypus.domain.Image

class ShareController {
  
    def userService
    def imageService
    def urlShortenerService
    
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
      
      return [ image : image, longUrl : longUrl, shortUrl : shortUrl]
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
              
      return [image : image, longUrl : longUrl, shortUrl : shortUrl]
    }
}
