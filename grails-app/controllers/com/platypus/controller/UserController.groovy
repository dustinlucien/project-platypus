package com.platypus.controller

class UserController {
	def userService
	
	def sajaxsessionevent = {
	  userService.handleAuthEvent(request, params?.fbperms)
	  response.status = 200
	  render ""
	  return
	}
	
	def sajaxedgeevent = {
	  log.debug "handling a Like event with params ${params}"
	  if (params.pkey) {
	    userService.handleLikeEvent(request, params.pkey)
	  } else {
	    userService.handleLikeEvent(request)
	  }
	  
	  response.status = 200
	  render ""
	  return
	}
}
