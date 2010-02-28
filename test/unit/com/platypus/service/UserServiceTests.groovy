package com.platypus.service

import grails.test.*

import com.platypus.service.UserService
import com.platypus.service.FacebookConnectService

import com.platypus.domain.User
import com.platypus.domain.Image

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.mock.web.MockHttpSession

class UserServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

	void testCreateUser() {
		mockDomain(User)
		mockLogging(User)
		
		mockLogging(UserService)
		mockLogging(FacebookConnectService)
		
		def userService = new UserService()
		userService.facebookConnectService = new FacebookConnectService()
		
		def user = userService.createUser([firstname:"dustin", lastname:"lucien"])
		
		assert user.firstname == "dustin"
		assert user.lastname == "lucien"
	}
	
    void testGetCurrentUser() {
		mockDomain(User)
		
		HttpServletRequest mockRequest = new MockHttpServletRequest()

		def userService = new UserService()
		userService.facebookConnectService = new FacebookConnectService()
		
		def user = userService.getCurrentUser(mockRequest)
    }
}
