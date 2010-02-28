package com.platypus.service

import com.platypus.domain.Image

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
	
	def listMostRecent(def params = [limit:5,offset:0,page:1]) {
		params["sort"] = "createTime"
		params["order"] = "desc"
		
		return Image.list(params)
	}
	
	/*
	def listMostPopular(def params = [limit:5,offset:0,page:1]) {
		
	}
	
	def listHighestRated(def params = [limit:5,offset:0,page:1]) {
		
	}
	*/
}
