package com.platypus.filters

import org.apache.commons.lang.RandomStringUtils

class SecurityFilters {
  
  def filters = {
    sessionSecurity(controller:'*', action:'*') {
      before = {
        if (!session?.stoken) {
          session.stoken = RandomStringUtils.randomAlphanumeric(16)
        }
        if (log.isDebugEnabled()) {
          log.debug "security token in session : ${session.stoken}"
        }
      }
      
      after = { model ->
        flash.stoken = session.stoken
        if (log.isDebugEnabled()) {
          log.debug "flash after after ${flash}"
        }
      }
    }
    
    ajaxSecurity(controller:'*', action:'sajax*') {
      before = {
        if (log.isDebugEnabled()) {
          log.debug "checking to make sure the secure ajax method ${actionName} is protected"
        }
        if (!params?.st || (params.st != session.stoken)) {
          if (log.isDebugEnabled()) {
            log.debug "Incorrect or missing security token at /${controllerName}/${actionName}"
          }
          response.status = 403
          render "Incorrect security token"
          return false
        }
        if (log.isDebugEnabled()) {
          log.debug "Secure token verified for /${controllerName}/${actionName}"
        }
        return true
      }
    }
  }
  
}
