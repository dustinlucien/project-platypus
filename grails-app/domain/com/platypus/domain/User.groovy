package com.platypus.domain

class User {
	String firstname
	
	String lastname
	
	Long facebookUid = -1
		
    static constraints = {
    	id visible:false
		firstname nullable:true
		lastname nullable:true
	}
}
