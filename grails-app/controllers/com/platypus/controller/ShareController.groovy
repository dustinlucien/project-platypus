package com.platypus.controller

class ShareController {
  
    def userService
    def imageService
    
    def index = {
      def user = userService.getCurrentUser(request)
      
      assert user != null
      
      def image = imageService.getMostRecentImage(user)
      
      if (image == null) {
        redirect(controller:'gallery')
      } else {
        return [ image : imageService.getMostRecentImage(user) ]
      }
    }
}
