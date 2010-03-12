package com.platypus.controller


class HomeController {

	def userService
	def imageService
	
	def index = {
		redirect(action:"list", params:params)
	}
	
	def list = {
		def user = userService.getCurrentUser()
		
		[ images : imageService.listMostRecent() ]
	}
	
}
