package com.platypus.domain

import java.util.Date

class Image {	
	User owner
	
	String etag
	String key
	String bucket
		
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
    	id visible:false
	}
}
