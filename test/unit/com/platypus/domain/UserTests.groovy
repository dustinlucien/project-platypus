package com.platypus.domain

import grails.test.*

class UserTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()

		mockDomain(User)
		mockLogging(User)		
    }

    protected void tearDown() {
        super.tearDown()
    }

	void testCreateBlankUser() {
		def user = new User().save(flush:true)
		
		assert user != null
	}
	
	void testCreateUser() {
		def user = new User(firstname:"dustin", lastname:"lucien", facebookUid:123456789).save(flush:true)
		
		assert user != null
		assert user.firstname == "dustin"
		assert user.lastname == "lucien"
		assert user.facebookUid == 123456789
	}
	
	void testCreateUserFromParams() {
		def params = [firstname:"dustin", lastname:"lucien",facebookUid:123456789]
		def user = new User(params).save(flush:true)
		
		assert user != null
		assert user.firstname == "dustin"
		assert user.lastname == "lucien"
		assert user.facebookUid == 123456789	
	}
}
