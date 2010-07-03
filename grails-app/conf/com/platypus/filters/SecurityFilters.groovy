package com.platypus.filters

import org.apache.commons.lang.RandomStringUtils

class SecurityFilters {
  
  def filters = {
    sessionSecurity(controller:'*', action:'*') {
      before = {
        if (!session?.secureToken) {
          session.secureToken = RandomStringUtils.randomAlphanumeric(16)
        }
        if (log.isDebugEnabled()) {
          log.debug "security token in session : " + session.secureToken
        }
      }
    }
    secureFlash(controller:'flash', action:'*') {
      before = {
        def token = params?.stoken
        
        if (log.isDebugEnabled()) {
          log.debug "security token submitted as a param : ${token}"
        }
        
        log.debug "session securetoken : ${session.secureToken}"
        
        if (token == null || (!(session.secureToken.equals(token)))) {
          response.status = 403
          render "Incorrect access token"
          return false
        }
      }
    }
  }
  
}
