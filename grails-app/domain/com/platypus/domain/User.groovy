package com.platypus.domain

class User {
	String firstname
	
	String lastname
		
	String username
	
	String password
	
	Long facebookUid = -1
		
    static constraints = {
    	id visible:false
		firstname nullable:true
		lastname nullable:true
		username nullable:true
		password nullable:true
	}
}
