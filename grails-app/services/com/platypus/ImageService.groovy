package com.platypus

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.DateTimeZone
import java.util.UUID

class ImageService {

    boolean transactional = false
	
	def buildUniqueImageKey() {
		/*
		 Build a key for an image 
		*/
		def uuid = UUID.randomUUID()
		
		def date = new DateTime()
		def format = DateTimeFormat.forPattern("yyyy/MM/dd/");
		
		def key = format.print(date) + uuid
		
		log.debug "generated this unique key " + key;
		
		return key;
	}
}
