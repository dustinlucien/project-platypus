package com.platypus.domain

class User {
	String firstname
	String lastname

	/*
	I think I need my own user id with a decent length for this class
	*/
	Long facebookUid
	
  static constraints = {
    id visible:false
		firstname nullable:true
		lastname nullable:true
		facebookUid nullable:true
	}
	
  def beforeInsert() {
		if (facebookUid == null) {
			facebookUid = -1
		}
	}
}
