package com.platypus.domain

import java.util.Date

class Image {	
	User owner
	
	String etag
	String chiave
	String bucket
		
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
    	id visible:false
	}
	
	def getImageUrl() {
		return "http://${bucket}.s3.amazonaws.com/${chiave}"
	}
}
