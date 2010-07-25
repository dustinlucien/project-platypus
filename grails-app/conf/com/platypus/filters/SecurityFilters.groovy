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
          log.debug "security token in session : " + session.stoken
        }
      }
    }
  }
  
}
