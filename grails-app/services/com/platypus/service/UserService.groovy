package com.platypus.service

import javax.servlet.http.HttpServletRequest

import com.platypus.domain.User

class UserService {

  boolean transactional = false

  def imageService
	def facebookConnectService
	
	def createUser(def params = null) {
		def user = new User(params);

		user.save(flush:true)
		if (user.errors) {
			log.error "Errors saving new User : ${user.errors}"
		}
				
		return user
	}
	
	def updateFacebookLoginInformation(def request) {
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
					}
				}
			}
		}
	}
	
	def getCurrentUser(def request) {
		
		if (request == null) {
			log.error "passed in a null request"
			throw new RuntimeException("passed in a null request")
		}
		
		def session = request.getSession();
		
		def user = null

		if (session.user) {
			user = User.findById(session.user)
			
			if (!user) {
				session.user = null
				log.error "wtf? unkown user in session.  cleaning session."
			} else {
				if (user.facebookUid == -1 && facebookConnectService.isLoggedIn()) {
					/*
						Update existing user with facebookUid
					*/
					def existingUser = User.findByFacebookUid(facebookConnectService.getUid())
					
					if (existingUser && (existingUser.id != user.id)) {
						imageService.mergeOwners(existingUser, user)
						user.delete(flush:true)
						user = existingUser
					} else {
						user.facebookUid = facebookConnectService.getUid()
						if (!user.save()) {
							user.errors.allErrors.each {
								log.error "${it}"
							}
						}
					}
				}
			}
		}
		
		if (!user) {
			if (facebookConnectService.isLoggedIn()) {
				user = User.findByFacebookUid(facebookConnectService.getUid())

				if (!user) {
					log.info "creating a new user with facebookUid : ${facebookConnectService.getUid()}"
					user = this.createUser([facebookUid : facebookConnectService.getUid()])
					
					log.info "user ${user} with facebookUid of ${user.facebookUid} created"
				}
			} else {
				log.info "creating a new user with no facebook information"
				user = this.createUser()
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
