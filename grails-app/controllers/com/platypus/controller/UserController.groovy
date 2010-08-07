package com.platypus.controller

class UserController {
  static layout = 'admin'
	static scaffold = com.platypus.domain.User
	
	def facebookConnectService
	
	def sajaxsessionevent = {
	  facebookConnectService.handleAuthEvent(request)
	  response.status = 200
	  render ""
	  return
	}
	
	def sajaxedgeevent = {
	  //TODO
	}
}
