package com.platypus.controller

class ShopController {
	def imageService
	def userService
	
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
			redirect(action:'index')
		}
		
		return [ images : images ]

	}
	
	def index = {
		[ images : imageService.listMostRecent() ]
	}
}
