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
		mockDomain(Image)
		mockLogging(Image)
		
		mockDomain(User)
		mockLogging(User)
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
		def user = new User(firstname:"dustin", lastname:"lucien")
		
		new Image(owner:user, 
				  etag:"701885fad9809d3247a865049e05f46b", 
				  key:"2010/02/28/9b56d4ab-9635-4e5d-829a-adc656abe741",
				  bucket:"project-playtpus-development").save(flush:true)
							
		new Image(owner:user, 
				  etag:"701885fad9809d3247a865049e05f46c", 
				  key:"2010/02/28/9b56d4ab-9635-4e5d-829a-adc656abe742",
				  bucket:"project-playtpus-development").save(flush:true)
					
		new Image(owner:user, 
				  etag:"701885fad9809d3247a865049e05f46d", 
				  key:"2010/02/28/9b56d4ab-9635-4e5d-829a-adc656abe743",
				  bucket:"project-playtpus-development").save(flush:true)
																																
		def list = imageService.listMostRecent()
		
		assert list
		assert list.size() > 0
		assert list.size() >= 3
		assert list[0].etag == "701885fad9809d3247a865049e05f46d"
		assert list[1].etag == "701885fad9809d3247a865049e05f46c"
		assert list[2].etag == "701885fad9809d3247a865049e05f46b"
	}
	
	void testListMostRecentWithLimit() {
		def user = new User(firstname:"dustin", lastname:"lucien")
		
		new Image(owner:user, 
				  etag:"701885fad9809d3247a865049e05f46e", 
				  key:"2010/02/28/9b56d4ab-9635-4e5d-829a-adc656abe741",
				  bucket:"project-playtpus-development").save(flush:true)
							
		new Image(owner:user, 
				  etag:"701885fad9809d3247a865049e05f46f", 
				  key:"2010/02/28/9b56d4ab-9635-4e5d-829a-adc656abe742",
				  bucket:"project-playtpus-development").save(flush:true)
					
		new Image(owner:user, 
				  etag:"701885fad9809d3247a865049e05f46g", 
				  key:"2010/02/28/9b56d4ab-9635-4e5d-829a-adc656abe743",
				  bucket:"project-playtpus-development").save(flush:true)
																																
		def list = imageService.listMostRecent(limit:2)
		
		assert list
		assert list.size() > 0
		assert list.size() == 2
		assert list[0].etag == "701885fad9809d3247a865049e05f46g"
		assert list[1].etag == "701885fad9809d3247a865049e05f46f"
	}
}
