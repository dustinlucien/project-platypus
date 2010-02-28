package com.platypus.domain

class Image {	
	User owner
	
	String etag
	String key
	String bucket
		
	Date createTime
	Date updateTime
	
	def beforeInsert() {
       createTime = new Date()
	}

	def beforeUpdate() {
       updateTime = new Date()
	}
	
	static constraints = {
    	id visible:false
	}
}
