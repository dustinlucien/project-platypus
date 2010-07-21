package com.platypus.controller

class ShareController {
  
    def userService
    def imageService
    
    def index = {
      def user = userService.getCurrentUser(request)
      
      assert user != null
      
      def image = imageService.getMostRecentImage(user)
      
      if (image == null) {
        log.debug "no image(s) for this user.  sending them to the gallery."
        
        flash.message = "You haven't Redneckified anything yet.  Have a look at this here gallery for some insperatin'."
        
        redirect(controller:'gallery')
      } else {
        return [ image : image ]
      }
    }
}
