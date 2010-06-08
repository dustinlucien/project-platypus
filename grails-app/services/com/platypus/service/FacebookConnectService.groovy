package com.platypus.service

import org.springframework.beans.factory.InitializingBean
import java.security.MessageDigest
import javax.servlet.http.HttpServletRequest

import org.springframework.beans.factory.InitializingBean;
import org.codehaus.groovy.grails.commons.ConfigurationHolder;

import com.restfb.FacebookClient
import com.restfb.DefaultFacebookClient

import java.lang.Long

class FacebookConnectService implements InitializingBean {

    boolean transactional = false
    
    private FacebookClient client
	private Long cachedUserId
    private String cachedSessionId
	private String appId
	
	void afterPropertiesSet() {
		appId = ConfigurationHolder.config.facebook.appId
		assert appId != null
	}

	String getAppId() {
		return appId
	}
	
    //Sample usage: facebookConnectService.isLoggedIn(request)
	def isLoggedIn(def request) {
    	boolean isCorrectFacebookSignature = validateSignature(request)
    	
    	if(isCorrectFacebookSignature) {
    		cachedUserId = Long.decode(request.cookies.find{it.name == "${apiKey}_user"}.value)
    		cachedSessionId = request.cookies.find{it.name == "${apiKey}_session_key"}.value
    		log.info("facebook user login.  Facebook user id: ${cachedUserId} sessionId: ${cachedSessionId}")
    	}

    	return isCorrectFacebookSignature
    }
    
    //Sample usage:  facebookConnectService.isValidForSignup(params)
    def isValidForSignup(Map params) {
    	boolean isCorrectFacebookSignature = validateSignupParams(params)
    	
    	if(isCorrectFacebookSignature) {
    		cachedUserId = Long.decode(params.fb_sig_user)
    		cachedSessionId = params.fb_sig_session_key
    		log.info("facebook user sign up.  Facebook user id: ${cachedUserId} sessionId: ${cachedSessionId}")
    	}

    	return isCorrectFacebookSignature
    }
	
	def getSessionId(def request) {
		if (cachedSessionId == NULL) {
			assert isLoggedIn(request)
			cachedSessionId = request.cookies.find{it.name == "${apiKey}_session_key"}.value
		}
		return cachedSessionId
	}
	
	def getUserId(def request) {
		if (cachedUserId == -1) {
			assert isLoggedIn(request)
			cachedUserId = Long.decode(request.cookies.find{it.name == "${apiKey}_user"}.value)
		}
		return cachedUserId
	}
	
	def getPhotos(def request) {
		def userId = getUserId(request)
		
		def fbClient = getFacebookClient(request)
		
		def fbPhotos = fbClient.photos_get(userId)
		
		if (log.isDebugEnabled()) {
			if (fbPhotos) {
				fbPhotos.each {
				  log.debug "fbPhoto : ${it}"
				}
			}
		}
		
		return fbPhotos
	}
	
    private boolean validateSignupParams(Map params) {
    	def paramValues = getFacebookParamValues(params)+"${secretKey}"
    	log.info("validating facebook signature from signature: ${paramValues}")
    	
    	def md = MessageDigest.getInstance("MD5")
		md.update(paramValues.getBytes())
		
		byte[] digestBytes =  md.digest()
		StringBuffer hexString = new StringBuffer();
    	for (int i = 0; i < digestBytes.length; i++) {
    	     String hex=Integer.toHexString(0xff & digestBytes[i]);
    	     if(hex.length()==1) hexString.append('0');
    	     hexString.append(hex);
    	}

    	
    	def signature = params.fb_sig

    	if(signature == hexString.toString())
    		return true
    	else false
    }
	
    private boolean validateSignature(def request) {
    	
    	def cookieValues = getFacebookCookieValues(request, apiKey)+"${secretKey}"
    	
    	log.info("validating facebook signature from cookie: ${cookieValues}")

    	def md = MessageDigest.getInstance("MD5")
		md.update(cookieValues.getBytes())
		
		byte[] digestBytes =  md.digest()
		StringBuffer hexString = new StringBuffer();
    	for (int i = 0; i < digestBytes.length; i++) {
    	     String hex=Integer.toHexString(0xff & digestBytes[i]);
    	     if(hex.length()==1) hexString.append('0');
    	     hexString.append(hex);
    	}

    	
    	def signature = request.cookies.find {it.name == apiKey}?.value

    	if(signature == hexString.toString())
    		return true
    	else false
    }
   
    private String getFacebookCookieValues(def request, String cookieName){
    	def cookies = new TreeMap()
    	request.cookies.findAll {
    		if(it.name.startsWith("${cookieName}_")) {
    			def key = it.name.replace("${cookieName}_", "")
    			def value = it.value
    			
    			cookies.put(key, value)
    		}
    	}

    	StringBuilder ret = new StringBuilder()
    	
    	for (Map.Entry<String,String> entry : cookies.entrySet()) {
    		ret.append("${entry.getKey()}=${entry.getValue()}")
		}
    	
    	return ret.toString()
    }
    
    private String getFacebookParamValues(Map params){
    	def parameters = new TreeMap()
    	Iterator it = params.keySet().iterator()
    	while(it.hasNext()) {	 
    		String key = it.next()
    		String val = params.get(key)
    		
    		if(key.startsWith("fb_sig_")) {
    			parameters.put(key.replace("fb_sig_", ""), val)
    		}
    	}

    	StringBuilder ret = new StringBuilder()
    	
    	for (Map.Entry<String,String> entry : parameters.entrySet()) {
    		ret.append("${entry.getKey()}=${entry.getValue()}")
		}
    	
    	return ret.toString()
    }
}
