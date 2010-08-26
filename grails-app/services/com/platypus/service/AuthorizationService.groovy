package com.platypus.service;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuth;
import oauth.signpost.OAuthProvider;

import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.signature.QueryStringSigningStrategy;
import oauth.signpost.signature.HmacSha1MessageSigner;

import org.apache.http.HttpRequest;

import org.springframework.beans.factory.InitializingBean;

import org.codehaus.groovy.grails.commons.ConfigurationHolder;

class AuthorizationService implements InitializingBean {

	static transactional = false
	
	def providers = [:]
    
    /**
     * Parses OAuth settings in Config.groovy and propagates providers and consumers
     * 
     * Example OAuth settings format in Config.groovy
     * 
     * e.g. Single consumer per provider
     * 
     * oauth{
     * 		provider_name{
     * 			requestTokenUrl='http://example.com/oauth/request_token'
     *			accessTokenUrl='http://example.com/oauth/access_token'
     *			authUrl='http://example.com/oauth/authorize'
     *			consumer.key='key'
     *			consumer.secret='secret'
     *		}
     * }
     * 
     */
    void afterPropertiesSet() {
      //initialize consumer list by reading config
      def serverURL = ConfigurationHolder.config.grails.serverURL.toString()
      if(!serverURL.endsWith('/')){
        serverURL += '/'
      }

      ConfigurationHolder.config.oauth.each{key, value->
        	//default single consumer
        	//if single consumer defined, will not go on to parse multiple consumers
          OAuthConsumer consumer = new CommonsHttpOAuthConsumer(value.consumerKey, value.consumerSecret);
          consumer.setMessageSigner(new HmacSha1MessageSigner())
          consumer.setSigningStrategy(new QueryStringSigningStrategy())
          
        	OAuthProvider provider = new CommonsHttpOAuthProvider(consumer, value.requestTokenURL, value.accessTokenURL, value.authURL);
        	providers[key] = provider
        }
      } else {
        println "Error initializaing OauthService: No consumers defined!"
      }
      
      log.debug "OAuth Initialized"
      providers.each { key, value ->
      	log.debug "Provider : " + key
      	log.debug "Request Token Endpoint     : " + value.getRequestTokenEndpointUrl()
      	log.debug "Access Token Endpoint      : " + value.getAccessTokenEndpointUrl()
      	log.debug "Authorization Website URL  : " + value.getAuthorizationWebsiteUrl()

      	def consumer = value.getConsumer();

      	log.debug "Consumer Key               : " + consumer.getConsumerKey();
      	log.debug "Consumer Secret            : " + consumer.getConsumerSecret();
      }
    }
    
    /**
     * Retrieves an unauthorized request token from the OAuth service
     * @return A map containing the token key and secret
     */
    def fetchRequestToken(String service, String callbackURL = OAuth.OUT_OF_BAND) {
     	def provider = providers[service];

     	if (!provider) {
    		log.debug "Unknown ServiceProvider requested: " + service;
    		throw new OAuthExpectationFailedException("Unknown Service Provider requested");
    	}

     	log.debug "Calling retrieveRequestToken with callbackURL : " + callbackURL;
     	
     	provider.retrieveRequestToken(callbackURL);

     	def consumer = provider.getConsumer();

     	log.debug "Return Consumer key ${consumer.getConsumerKey()} and secret ${consumer.getConsumerSecret()}"
     	return [key:consumer.getConsumerKey(), secret:consumer.getConsumerSecret()]
    }

    /*
     * Returns the authorization URL for the user
     */
    def fetchAuthorizationURL(String service, String callbackURL = OAuth.OUT_OF_BAND) {
     	def provider = providers[service];

     	if (!provider) {
    		log.debug "Unknown ServiceProvider requested: " + service;
    		throw new OAuthExpectationFailedException("Unknown Service Provider requested");
    	}

     	log.debug "The callback URL : " + callbackURL;

     	String authUrl = provider.retrieveRequestToken(callbackURL);
     	
     	log.debug "The URL we're going to use to authorize the user : " + authUrl;
     	
     	return authUrl;
    }
    
    /**
     * Exchanges the authorized request token for the access token
     * @return A map containing the access token and secret
     */
    def fetchAccessToken(String service, String verificationCode) {
    	log.debug "Going to exchange ${verificationCode} for access token"
    	def provider = providers[service];

    	if (!provider) {
    		log.debug "Unknown ServiceProvider requested: " + service;
    		throw new OAuthExpectationFailedException("Unknown Service Provider requested");
    	}

    	provider.retrieveAccessToken(verificationCode);

    	def consumer = provider.getConsumer();

    	log.debug "Return Consumer access token ${consumer.getToken()} and token secret ${consumer.getTokenSecret()}"

     	return [token:consumer.getToken(), secret:consumer.getTokenSecret()]
    }
	
  	/*
  	 * TODO: define a user and incorporate
  	def sign(String service, String request, User user) {
		
  	}
  	*/
	
    def sign(String service, String request, String token, String secret) {
    	assert service != null
    	assert request != null
    	assert token != null
    	assert secret != null
    		
    	def consumer = this.getConsumer(service)

  		consumer.setTokenWithSecret(token, secret)
    	return consumer.sign(request);
    }
    
    def sign(String service, HttpRequest request, String token, String secret) {
    	assert service != null
    	assert request != null
    	assert token != null
    	assert secret != null

    	def consumer = this.getConsumer(service)
    	
    	consumer.setTokenWithSecret(token, secret)
    	return consumer.sign(request);
    }
    
    private def getConsumer(String service) {
      def provider = providers[service];

    	if (!provider) {
    		log.debug "Unknown ServiceProvider requested: " + service;
    		throw new OAuthExpectationFailedException("Unknown Service Provider requested");   		
    	}
    	
    	return provider.getConsumer()
    }
}