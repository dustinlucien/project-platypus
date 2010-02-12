package com.platypus.domain



import javax.persistence.*;
import com.google.appengine.api.datastore.Key;

@Entity
class User implements Serializable {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Key id

	@Basic
	String firstname
	
	@Basic
	String lastname
	
	@Basic 
	String username
	
	@Basic
	String password
	
	@Basic
	Long facebookUid = -1
		
    static constraints = {
    	id visible:false
		firstname nullable:true
		lastname nullable:true
		username nullable:true
		password nullable:true
	}
}
