package com.platypus.domain

import java.util.Date
import java.util.Random
import com.platypus.util.BaseConverterUtil

class Image implements Serializable {	
	User owner
	
	String etag
	String chiave
	String bucket
	String pkey
	
	String title
	
	Date dateCreated
	Date lastUpdated
	
	static constraints = {
    	id visible:false
    	pkey nullable:true
    	title nullable:true
	}
	
	transient def beforeInsert() {
	  if (this.pkey == null) {
  	  def random = new Random()
      def randomInt = random.nextInt((int)Image.count() + 1) + 10000
    
      log.debug "randomInt generated beforeInsert : ${randomInt}"
    
      def pkey = BaseConverterUtil.toBase62(randomInt)
    
      log.debug "pkey generated beforeInsert : ${pkey}"
    
      this.pkey = pkey
    }
	}
	
	transient def getImageUrl() {
		return "http://${this.bucket}.s3.amazonaws.com/${this.chiave}"
	}
	
	transient def getDisplayName() {
    return (title == null) ? "Billy Bob Redneck" : title
	}
}
