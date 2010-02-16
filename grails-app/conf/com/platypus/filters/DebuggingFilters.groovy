package com.platypus.filters

class DebuggingFilters {

    def filters = {
        notAppEngine(controller:'appEngineReload', action:'*', invert:true) {
            before = {
				log.debug "------------------------------------------------"
				log.debug "User agent: " + request.getHeader("User-Agent")
                log.debug "request params : ${params}"
				log.debug "entering controller : ${controllerName}"
				log.debug "calling action      : ${actionName}"				
				log.debug "------------------------------------------------"
            }
            after = {
                log.debug "leaving controller : ${controllerName}"
            }
            afterView = {
                
            }
        }
    }
    
}
