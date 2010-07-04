package com.platypus.controller

import grails.test.*
import groovy.util.GroovyTestCase

import com.platypus.service.SecurityService
import com.platypus.service.ImageService
import com.platypus.service.UserService

class UploadControllerTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testUpload() {
	
		def controller = new UploadController()
		
		controller.securityService = new SecurityService()
		controller.imageService = new ImageService()
		controller.userService = new UserService();
		
		controller.upload()
    }
}
