package com.platypus.service

import org.springframework.beans.factory.InitializingBean
import java.security.MessageDigest
import javax.servlet.http.HttpServletRequest

import com.google.code.facebookapi.FacebookJsonRestClient

import org.springframework.beans.factory.InitializingBean;
import org.codehaus.groovy.grails.commons.ConfigurationHolder;

class FacebookConnectService implements InitializingBean {

    boolean transactional = false
    
    private FacebookJsonRestClient client
    private String sessionId
    private String apiKey
	private String secretKey
	
	
	void afterPropertiesSet() {
		apiKey = ConfigurationHolder.config.facebookConnect.apiKey
		secretKey = ConfigurationHolder.config.facebookConnect.secretKey
		
		if (!apiKey || !secretKey) {
			log.error "FacebookConnect won't work.  No application setup included in config"
			throw new RuntimeException("Unable to continue.  No FacebookConnect config")
		}
	}
	
    FacebookJsonRestClient getFacebookClient(HttpServletRequest request = null) {
		
		if (!client) {
			if (request && isLoggedIn(request)) {
	    		client = new FacebookJsonRestClient(apiKey, secretKey, sessionId)
			} else {
				log.error "user has not logged in to facebook.  no session"
				client = null
			}
		}
		
    	return client
    }

	String getApiKey() {
		return apiKey
	}
	
	String getSecretKey() {
		return secretKey
	}
	
    //Sample usage: facebookConnectService.isLoggedIn(request)
	def isLoggedIn(HttpServletRequest request) {
    	boolean isCorrectFacebookSignature = validateSignature(request)
    	
    	if(isCorrectFacebookSignature) {
    		def facebookUserId = request.cookies.find{it.name == "${apiKey}_user"}.value
    		sessionId = request.cookies.find{it.name == "${secretKey}_session_key"}.value
    		log.info("facebook user login.  Facebook user id: ${facebookUserId} sessionId: ${sessionId}")
    	}

    	return isCorrectFacebookSignature
    }
    
    //Sample usage:  facebookConnectService.isValidForSignup(params)
    def isValidForSignup(Map params) {
    	boolean isCorrectFacebookSignature = validateSignupParams(params)
    	
    	if(isCorrectFacebookSignature) {
    		def facebookUserId = params.fb_sig_user
    		sessionId = params.fb_sig_session_key
    		log.info("facebook user sign up.  Facebook user id: ${facebookUserId} sessionId: ${sessionId}")
    	}

    	return isCorrectFacebookSignature
    }
	
    private boolean validateSignupParams(Map params) {
    	def paramValues = getFacebookParamValues(params)+"${grailsApplication.config.facebookConnect.secret}"
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
	
    private boolean validateSignature(HttpServletRequest request) {
    	
    	def cookieValues = getFacebookCookieValues(request, facebookConnectConfig.facebookConnect.APIKey)+"${facebookConnectConfig.facebookConnect.SecretKey}"
    	
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

    	
    	def signature = request.cookies.find {it.name == facebookConnectConfig.facebookConnect.APIKey}?.value

    	if(signature == hexString.toString())
    		return true
    	else false
    }
    
    private String getFacebookCookieValues(HttpServletRequest request, String cookieName){
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
