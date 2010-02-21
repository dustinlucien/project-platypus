package com.platypus

import grails.test.*

class ImageServiceTests extends GrailsUnitTestCase {
	def imageService
	
    protected void setUp() {
        super.setUp()

		MockUtils.mockLogging(ImageService.class)
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
