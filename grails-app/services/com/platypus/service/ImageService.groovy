package com.platypus.service

import com.platypus.domain.Image

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.DateTimeZone
import java.util.UUID

import com.platypus.util.BaseConverterUtil

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
		
		if (log.isDebugEnabled()) {
			log.debug "generated this unique key " + key;
		}
		
		return key;
	}
	
	def listMostRecent(def params = [limit:5,offset:0,page:1]) {
		assert params != null
		assert params.limit > 0
		
		params["sort"] = "dateCreated"
		params["order"] = "desc"
		
		return Image.list(params)
	}
	
	def listMostRecentByOwner(def user, def params = [limit:5,offset:0,page:1]) {
		assert user != null
		assert params != null
		assert params.limit > 0
		
		params["sort"] = "dateCreated"
		params["order"] = "desc"
		
		return Image.findAllByOwner(user, params)
	}
	
	def getMostRecentImage(def user) {
	  assert user != null
	  assert user.id != -1
	  
	  def params = [:]
	  params["sort"] = "dateCreated"
	  params["order"] = "desc"
	  
	  return Image.findByOwner(user, params)
	}
	
	def saveNewImage(def params, def user) {
		assert params != null
		assert user != null
		
		if (params?.key) {
			/*
				move the S3 key into the chiave field we're using to store the key
			*/
			params.chiave = params.key
		}
		
		def image = new Image(params)
				
		image.owner = user;
		if (image.save()) {
			return image;
		} else {
			image.errors.allErrors.each {
				println it
			}
			return null;
		}
	}
	
	def mergeOwners(def primary, def secondary) {
		assert primary != null
		assert secondary != null
		
		def images = Image.findAllByOwner(secondary)
		
		if (images) {
			images.each {
				it.owner = primary
				it.save()
			}
			return images.size()
		} else {
			return 0
		}
	}
	
	/*
	def listMostPopular(def params = [limit:5,offset:0,page:1]) {
		
	}
	
	def listHighestRated(def params = [limit:5,offset:0,page:1]) {
		
	}
	*/
}
