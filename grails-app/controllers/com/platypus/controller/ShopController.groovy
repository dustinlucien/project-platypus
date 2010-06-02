package com.platypus.controller

class ShopController {
	def imageService
	def userService
	
	/*
		You've just created a new redneck image if you're coming here.  Already stored, will be the 
		most recent one
	*/
	def show = {
		def user = userService.getCurrentUser(request)
		
		def images = imageService.listMostRecentByOwner(user)
		
		if (log.isDebugEnabled()) {
			if (images) {
				images.each {
					log.debug "User : ${it.owner} Image Key : ${it.chiave}"
				}
			}
		}
		
		if (!images) {
			log.info "no images returned. redirecting to gallery"
			redirect(action:'list')
		}
		
		return [ images : images ]

	}
	
	def list = {
		[ images : imageService.listMostRecent() ]
	}
}
