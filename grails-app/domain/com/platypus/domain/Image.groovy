package com.platypus.domain



import javax.persistence.*;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.blobstore.BlobKey;

@Entity
class Image implements Serializable {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Key id

	@Basic
	Key owner
	
	@Basic
	BlobKey data
	
	@Basic 
	String title
	
    static constraints = {
    	id visible:false
	}
}
