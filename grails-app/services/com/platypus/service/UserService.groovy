package com.platypus.service

import javax.servlet.http.HttpServletRequest

import com.platypus.domain.User

class UserService {

    boolean transactional = false

	def facebookConnectService
	
	def createUser(def params = null) {
		def user = null
		if (!params) {
			//create a default user.
			user = new User();
			user.save()
			
			if (user.errors) {
				log.error "Errors saving new User : ${user.errors}"
			}
		} else {
			//populate from current params
			user = new User(params);
			user.save()
			
			if (user.errors) {
				log.error "Errors saving new User : ${user.errors}"
			}
		}
		
		return user
	}
	
	def getCurrentUser(def request) {
		
		def session = request.getSession();
		
		def user = null
		if (session.user) {
			user = User.findById(session.user)
		} else {
			//returns null if user not logged in or cannot determine user
			def facebookApi = facebookConnectService.getFacebookClient(request)
			
			if (!facebookApi) {
				user = this.createUser()
				log.debug "created a new user because facebook API returned NULL"
				log.debug "${user}"
				
			} else {
				def facebookUid = facebookApi.user_getLoggedInUser()
				//get some more information about the user once this works
				
				user = this.createUser([facebookUid:facebookUid])
				log.debug "created a new user with a facebookUID : ${facebookUid}"
				log.debug "${user}"
			}
			
			/*
			Be sure to put this user into the session
			*/
			session.user = user.id
		}
		
		return user
	}
}
