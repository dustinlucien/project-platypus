package com.platypus

import grails.test.*
import groovy.util.GroovyTestCase

class UploadControllerTests extends GroovyTestCase {
	
	def signatureService
		
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testUpload() {
	
		def controller = new UploadController()
		controller.signatureService = signatureService
		
		controller.upload()
		
		print controller.response.contentAsString;
    }
}
