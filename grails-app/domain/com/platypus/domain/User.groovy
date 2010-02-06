package com.platypus.domain



import javax.persistence.*;
// import com.google.appengine.api.datastore.Key;

@Entity
class User implements Serializable {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id

	String firstname
	String lastname
	
    static constraints = {
    	id visible:false
	}
}
