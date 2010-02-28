package com.platypus.domain

class User {
	String firstname
	String lastname
	Long facebookUid
	
	
    static constraints = {
    	id visible:false
		firstname nullable:true
		lastname nullable:true
	}
}
