package com.platypus.controller


class HomeController {

	def imageService
	
	def index = {
		redirect(action:"list", params:params)
	}
	
	def list = {
		[images : imageService.listMostRecent()]
	}
}
