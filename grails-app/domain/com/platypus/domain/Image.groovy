package com.platypus.domain

class Image {
	def belongsTo = [owner:User]
	
	String title
	
    static constraints = {
    	id visible:false
	}
}
