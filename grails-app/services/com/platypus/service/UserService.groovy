package com.platypus.service

import javax.servlet.http.HttpServletRequest

import com.platypus.domain.User
import com.platypus.domain.LikeEvent
import com.platypus.domain.Image

class UserService {

  static transactional = false

  def imageService
	def facebookConnectService
	
	def createUser(def params = null) {
		def user = new User(params);

		if (!user.save(flush:true)) {
		  user.errors.allErrors.each {
				log.error "${it}"
			}
			return null;
		} else {
		  return user
	  }
	}
	
	def handleLikeEvent(def request, def pkey) {
	  def user = this.getCurrentUser(request)
	  
	  if (!user) {
	    log.error "could not create a LikeEvent because user : ${user}"
	    return false
	  }
	  
	  def image = null
	  if (pkey) {
	    image = Image.findByPKey(pkey)
	  }
    
    def event = null
    
    if (image) {
      event = new LikeEvent([owner : user, target : image])
    } else {
      event = new LikeEvent([owner : user])
    }
    
	  if (!event.save()) {
	    event.errors.allErrors.each {
	      log.error "${it}"
	    }
	  }
	}
	
	def handleAuthEvent(def request, def perms = null) {
	  //right now, and auth event is only through facebook
	  def user = this.getCurrentUser(request)

	  if (perms) {
	    //add the facebook permissions to the user
	    log.debug "adding permissions ${perms} to user account"
	    user.facebookPermissions = perms
	    if (!user.save()) {
	      user.errors.allErrors.each {
  				log.error "${it}"
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
				if (user.facebookUid == -1) {
				  facebookConnectService.parseCookies(request)
				  if (facebookConnectService.isLoggedIn()) {
  					/*
  						Update existing user with facebookUid
  					*/
  					def existingUser = User.findByFacebookUid(facebookConnectService.getUid())
					
  					if (existingUser && (existingUser.id != user.id)) {
  					  log.warn "merging existing users data since we got ahold of the facebook information"
  						imageService.mergeOwners(existingUser, user)
  						user.delete(flush:true)
  						user = existingUser
  					} else {
  					  
  					  facebookConnectService.populateUserWithFacebookProfile(user)
  					  
  						if (!user.save()) {
  							user.errors.allErrors.each {
  								log.error "${it}"
  							}
  						}
  					}
  				}
				}
			}
		}
		
		if (!user) {
		  facebookConnectService.parseCookies(request)
			if (facebookConnectService.isLoggedIn()) {
				user = User.findByFacebookUid(facebookConnectService.getUid())

				if (!user) {
				  def fbParams = facebookConnectService.getFacebookProfileParams()
					log.info "creating a new user with facebookUid : ${fbParams}"
					user = this.createUser(fbParams)
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
			log.debug "USER after getCurrentUser(): ${user}"
		}		
		
		return user
	}
}
