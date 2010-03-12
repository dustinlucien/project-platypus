package com.platypus.service

import grails.test.*

import com.platypus.service.UserService
import com.platypus.service.FacebookConnectService
import com.google.code.facebookapi.FacebookJsonRestClient

import com.platypus.domain.User
import com.platypus.domain.Image

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.mock.web.MockHttpSession

import org.gmock.*
import static org.hamcrest.Matchers.*

@WithGMock
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
	
    void testGetCurrentUserNotLoggedInToFacebook() {
		mockDomain(User)
		mockLogging(User)
		
		mockLogging(UserService)

		HttpServletRequest mockRequest = new MockHttpServletRequest()

		/*
			Use GMock for this since we need to mock the constructor
		*/
		def facebookRestMock = mock(FacebookJsonRestClient, constructor("apikey", "secretkey"))
		facebookRestMock.user_getLoggedInUser().returns("AXBN6754")
		
		def connectService = mock(FacebookConnectService)
		connectService.isLoggedIn(mockRequest).returns(false)
		connectService.getFacebookClient(mockRequest).returns(facebookRestMock)
		
		def userService = new UserService()
		userService.facebookConnectService = connectService

		play {
			def user = userService.getCurrentUser(mockRequest)

			print "${user}"		
			assert user != null
		}
    }
    
	void testGetCurrentUserLoggedInToFacebook() {
		mockDomain(User)
		mockLogging(User)
		
		mockLogging(UserService)

		HttpServletRequest mockRequest = new MockHttpServletRequest()

		/*
			Use GMock for this since we need to mock the constructor
		*/
		def facebookRestMock = mock(FacebookJsonRestClient, constructor("apikey", "secretkey"))
		facebookRestMock.user_getLoggedInUser().returns("AXBN6754")
		
		def connectService = mock(FacebookConnectService)
		connectService.isLoggedIn(mockRequest).returns(true)
		connectService.getFacebookClient(mockRequest).returns(facebookRestMock)
		
		def userService = new UserService()
		userService.facebookConnectService = connectService

		play {
			def user = userService.getCurrentUser(mockRequest)

			print "${user}"
			assert user != null
			assert user.facebookUid == "AXBN6754"		
		}
    }
}
