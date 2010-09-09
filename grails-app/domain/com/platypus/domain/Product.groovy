package com.platypus.domain

class Product implements Serializable {
  String title
  Integer templateId
  Float price
  Float weight
  
  static constraints = {
    id visible:false
		title nullable:false
		templateId nullable:false
		price nullable:true
		weight nullable:false
	}
}