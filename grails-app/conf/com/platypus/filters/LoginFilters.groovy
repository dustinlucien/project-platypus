package com.platypus.filters

class LoginFilters {

	def userService
	def facebookConnectService
	
    def filters = {
        all(controller:'*', action:'*') {
            before = {
	
            }
            after = {
                
            }
            afterView = {
                
            }
        }
    }
    
}
