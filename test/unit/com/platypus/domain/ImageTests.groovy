package com.platypus.domain

import grails.test.*

class ImageTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
		mockDomain(Image)
		mockLogging(Image)
		
		mockDomain(User)
		mockLogging(User)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testImageSave() {
		def user = new User(firstname:"dustin", lastname:"lucien")
		user.save(flush:true)
		
		def image = new Image(owner:user, 
							  etag:"701885fad9809d3247a865049e05f46b", 
							  key:"2010/02/28/9b56d4ab-9635-4e5d-829a-adc656abe741",
							  bucket:"project-playtpus-development").save(flush:true)
							
		assert image.owner == user
		assert image.etag == "701885fad9809d3247a865049e05f46b"
		assert image.key == "2010/02/28/9b56d4ab-9635-4e5d-829a-adc656abe741"
		assert image.bucket == "project-playtpus-development"
    }
}
