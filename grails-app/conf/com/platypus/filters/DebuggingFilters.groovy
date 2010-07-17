package com.platypus.filters

class DebuggingFilters {

  def facebookConnectService
  
  def filters = {
    notAppEngine(controller:'appEngineReload', action:'*', invert:true) {
      before = {
        log.debug "-------------------ENTERING-----------------------------"
        log.debug "User agent: " + request.getHeader("User-Agent")
        log.debug "request params : ${params}"
        log.debug "entering controller : ${controllerName}"
        log.debug "calling action      : ${actionName}"
        log.debug "facebook login status : ${facebookConnectService.isLoggedIn()}"
        log.debug "------------------------------------------------"
      }
      after = {
        log.debug "leaving controller : ${controllerName}"
        log.debug "leaving action     : ${actionName}"
        log.debug "-------------------LEAVING-----------------------------"        
      }
      afterView = {

      }
    }
  }

}
