package com.platypus.controller

class UserController {
  static layout = 'admin'
	static scaffold = com.platypus.domain.User
	
	def userService
	
	def sajaxsessionevent = {
	  userService.handleAuthEvent(request, params?.fbperms)
	  response.status = 200
	  render ""
	  return
	}
	
	def sajaxedgeevent = {
	  log.debug "handling a Like event"
	  userService.handleLikeEvent(request, params?.pkey)
	  response.status = 200
	  render ""
	  return
	}
}
