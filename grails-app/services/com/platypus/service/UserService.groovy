package com.platypus.service

import javax.servlet.http.HttpServletRequest

import com.platypus.domain.User

class UserService {

    boolean transactional = false

	def facebookConnectService
		
	def createUser(def params = null) {
		def user = new User(params);
		user.save()
		
		if (user.errors) {
			log.error "Errors saving new User : ${user.errors}"
		}
				
		return user
	}
	
	def getCurrentUser(def request) {
		
		def session = request.getSession();
		
		def user = null

		if (session.user) {
			user = User.findById(session.user)
			
			if (!user) {
				session.user = null
				log.error "wtf? unkown user in session.  cleaning session."
			} else {
				if (user.facebookUid == -1 && facebookConnectService.isLoggedIn(request)) {
					/*
						Update existing user with facebookUid
					*/
					def facebookApi = facebookConnectService.getFacebookClient(request)
					user.facebookUid = facebookApi.user_getLoggedInUser()
					user.save()

					if (user.errors) {
						log.error "Errors updating User with FacebookUID : ${user.errors}"
					} else {		
						log.debug "created a new user with a facebookUID : ${facebookUid}"
						log.debug "${user}"
					}
				}
			}
		}
		
		if (!user) {
			if (facebookConnectService.isLoggedIn(request)) {
				def facebookApi = facebookConnectService.getFacebookClient(request)			
				def facebookUid = facebookApi.user_getLoggedInUser();
				
				user = User.findByFacebookUid(facebookUid)

				if (!user) {
					log.info "creating a new user with facebookUid : ${facebookUid}"
					user = this.createUser([facebookUid : facebookUid])
				}
			}
 		}
				

		/*
		Be sure to put this user into the session
		*/
		if (user) {
			session.user = user.id
		}

		if (log.isDebugEnabled()) {
			log.debug "USER : ${user}"
		}		
		
		return user
	}
}
