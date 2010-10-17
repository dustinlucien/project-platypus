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
	
	def handleLikeEvent(def request, def pkey = null) {
	  if (log.isDebugEnabled()) {
	    log.debug "Handling a Like event for Image ${pkey}"
	    log.debug "Incoming request : ${request}"
	  }
	  
	  def user = this.getCurrentUser(request)
	  if (!user) {
	    log.error "didn't get a current user for like event.  aborting."
	    return false
	  }
	  
	  log.debug "current user for like event: ${user}"
	  def image = null
	  if (pkey) {
	    image = Image.findByPkey(pkey)
	  }
    
    def event = null
    if (image) {
      log.debug "like event was for an image"
      event = new LikeEvent([owner : user, target : image])
      
      image.liked++
      if (!image.save()) {
  	    image.errors.allErrors.each {
  	      log.error "${it}"
  	    }
  	  }
    } else {
      log.debug "like event was NOT for an image"
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
				  log.debug "our current user isn't connected to facebook"
				  facebookConnectService.parseCookies(request)
				  if (facebookConnectService.isLoggedIn()) {
				    log.debug "our current user isn't connected to facebook, but is logged in to facebook.  trying to link accounts"
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
  				    log.debug "linking facebook accounts.  user before -> ${user}" 					  
  					  facebookConnectService.populateUserWithFacebookProfile(user)
  				    log.debug "user after -> ${user}"
  				    
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
