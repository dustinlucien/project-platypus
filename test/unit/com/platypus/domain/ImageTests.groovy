package com.platypus.domain

import grails.test.*

class ImageTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testImageSave() {
		mockDomain(Image)
		mockLogging(Image)
		
		mockDomain(User)
		mockLogging(User)
		
		def user = new User(firstname:"dustin", lastname:"lucien")
		
		def image = new Image(owner:user, 
							  etag:"701885fad9809d3247a865049e05f46b", 
							  key:"2010/02/28/9b56d4ab-9635-4e5d-829a-adc656abe741",
							  bucket:"project-playtpus-development").save()
    }
}
