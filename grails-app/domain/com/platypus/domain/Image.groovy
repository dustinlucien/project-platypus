package com.platypus.domain

class Image {	
	String etag
	String key
	String bucket
	
    static constraints = {
    	id visible:false
	}
}
