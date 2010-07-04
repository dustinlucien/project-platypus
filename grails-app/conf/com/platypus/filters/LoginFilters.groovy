package com.platypus.filters

class LoginFilters {
  def userService  
  def facebookConnectService
  
  def filters = {
    checkFacebookStatus(controller:'*', action:'*') {
      before = {
        
        /*
        This all seems very badly written.  Need to find a more elegant way to know when 
        i need to do this checking, and when i don't
        */
        log.debug "handling facebook auth event"
        facebookConnectService.handleAuthEvent(request)
        
        log.debug "facebook login status before? " + facebookConnectService.isLoggedIn()
        
        log.debug "asking for the current user"
        userService.getCurrentUser(request)

        log.debug "facebook login status after? " + facebookConnectService.isLoggedIn()

      }
      after = {
        
      }
      afterView = {
        
      }
    }
  }
  
}
