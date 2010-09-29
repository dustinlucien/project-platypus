package com.platypus.filters

class SessionFilter {

  def filters = {
    removeSessionId(url:".*;jsessionid=.*") {
      before = {
          def url = request.forwardURI
          log.trace("sessionFilter: URL is: ${url}")
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
