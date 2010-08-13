package com.platypus.domain

class ImagePublishEvent {
  User owner
  Image target  
  
  Date dateCreated
  static constraints = {
    	id visible:false
    	target nullable:true
	}
}
