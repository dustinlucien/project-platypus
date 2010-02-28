package com.platypus.service

import grails.test.*

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
		
		print key
    }
}
