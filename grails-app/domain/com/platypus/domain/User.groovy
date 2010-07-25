package com.platypus.domain

class User implements Serializable {
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
	
  transient def beforeInsert() {
		if (this.facebookUid == null) {
			this.facebookUid = -1
		}
	}
	
	transient def displayName() {
	  def displayName = "Anonymous"
	  
	  if (firsname != null)
	    displayName = firstname
	  
	  if (lastname != null)
	    displayName += lastname
	  
	  return displayName  
	}
	
}
