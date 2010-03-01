package com.platypus.service

import grails.test.*

import com.platypus.domain.Image
import com.platypus.domain.User

class ImageServiceTests extends GrailsUnitTestCase {
	def imageService
	
    protected void setUp() {
        super.setUp()

		mockLogging(ImageService)
		
		imageService = new ImageService()
		
    }

    protected void tearDown() {
        super.tearDown()
		imageService = null
    }

    void testBuildUniqueImageKey() {
		def key = imageService.buildUniqueImageKey();
		def key2 = imageService.buildUniqueImageKey();
		
		assert key != key2
    }

	void testListMostRecent() {
		mockDomain(Image)
		mockLogging(Image)
		
		mockDomain(User)
		mockLogging(User)
		
		def user = new User(firstname:"dustin", lastname:"lucien")
		
		def image = new Image(owner:user, 
							  etag:"701885fad9809d3247a865049e05f46b", 
							  key:"2010/02/28/9b56d4ab-9635-4e5d-829a-adc656abe741",
							  bucket:"project-playtpus-development").save()
		def list = imageService.listMostRecent()
		
		assert list
		assert list.size() > 0
		assert list[0] == image
	}
}
