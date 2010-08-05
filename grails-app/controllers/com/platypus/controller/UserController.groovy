package com.platypus.controller

class UserController {
  static layout = 'admin'
	static scaffold = com.platypus.domain.User
	
	def facebookConnectService
	
	def fblogin = {
	  facebookConnectService.handleAuthEvent(request)
	}
}
