package com.platypus.filters

class SessionFilters {

  def filters = {
    removeSessionId(controller:'*', action:'*') {
      before = {
          def url = request.forwardURI
          log.debug("sessionFilter: URL is: ${url}")
          log.debug("params ${params}")
          // Double-check the url pattern, the filter matching doesn't seem to work
          if(url.matches(".*;jsessionid=.*")) {
              log.debug("URL ${url} has a jsessionid parameter")
              url = url.replaceFirst(";jsessionid=.*","")
              log.debug("sessionFilter: Redirect to: ${url}")
              redirect(url:url)
              
              return false;
          }
          
          return true;
      }
      after = {
               
      }
      afterView = {

      }
    }
  }

}
