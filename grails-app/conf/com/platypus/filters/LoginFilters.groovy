package com.platypus.filters

class LoginFilters {
  def userService  
  def facebookConnectService
  
  def filters = {
    checkFacebookStatus(controller:'*', action:'*') {
      before = {
        log.debug "handling facebook login event"
        facebookConnectService.handleAuthEvent(request)
      }
      after = {
        
      }
      afterView = {
        
      }
    }
  }
  
}
