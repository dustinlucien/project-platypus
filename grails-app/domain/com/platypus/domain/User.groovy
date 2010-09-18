package com.platypus.domain

class User implements Serializable {
	String firstname
	String lastname

  String email
  String location
  String gender
  
	/*
	I think I need my own user id with a decent length for this class
	*/
	Long facebookUid
	String facebookPermissions
	
	Date dateCreated
	Date lastUpdated
	
  static constraints = {
    id visible:false
		firstname nullable:true
		lastname nullable:true
		email nullable:true
		location nullable:true
		gender nullable:true
		facebookUid nullable:true
		facebookPermissions nullable:true
	}
	
  transient def beforeInsert() {
		if (this.facebookUid == null) {
			this.facebookUid = -1
		}
	}
	
	transient def getDisplayName() {
	  def displayName = "Anonymous"
	  
	  if (firstname != null)
	    displayName = firstname
	  
	  if (lastname != null)
	    displayName += ' ' + lastname
	  
	  return displayName  
	}
	
}
