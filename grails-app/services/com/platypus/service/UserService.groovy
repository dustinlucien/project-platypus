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
			
		} else {
			//populate from current params
			user = new User(params);
			user.save()
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
			def facebookApi = facebookConnectService.getFacebookRestClient((HttpServletRequest)request)

			if (!facebookApi) {
				user = this.createUser()
			} else {
				def facebookUid = facebookApi.user_getLoggedInUser()
				//get some more information about the user once this works
				
				user = this.createUser([facebookUid:facebookUid])
			}
		}
	}
}
