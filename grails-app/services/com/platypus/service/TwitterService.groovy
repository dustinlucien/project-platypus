package com.platypus.service;

import com.platypus.rest.RestClient;
import com.platypus.service.RestClientService;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.ResponseHandler;

import org.springframework.beans.factory.InitializingBean;

import org.codehaus.groovy.grails.commons.ConfigurationHolder;

class TwitterService extends RestClientService implements InitializingBean, RestClient {

	static transactional = false
	def authorizationService

	private String apiKey
	private String username
	private String apiVersion
	private String oAuthAccessToken
	private String oAuthAccessTokenSecret
	private String serviceUrl
	
  void afterPropertiesSet() {
    this.apiKey = ConfigurationHolder.config.twitter.apiKey
    this.username = ConfigurationHolder.config.twitter.username
    this.apiVersion = ConfigurationHolder.config.twitter.apiVersion
    this.oAuthAccessToken = ConfigurationHolder.config.twitter.oAuthAccessToken
    this.oAuthAccessTokenSecret = ConfigurationHolder.config.twitter.oAuthAccessTokenSecret
    this.serviceUrl = "http://api.twitter.com/${this.apiVersion}" 
    
    assert this.apiKey != null
    log.info "Twitter ApiKey ${this.apiKey}"
    
    assert this.username != null
    log.info "Twitter Username ${this.username}"
    
    assert this.apiVersion != null
    log.info "Twitter ApiVersion ${this.apiVersion}"
    
    assert this.oAuthAccessToken != null
    log.info "Twitter oAuth AccessToken ${this.oAuthAccessToken}"
    
    assert this.oAuthAccessTokenSecret != null
    log.info "Twitter oAuth AccessTokenSecret ${this.oAuthAccessTokenSecret}"
    
    assert this.serviceUrl != null
     log.info "Twitter Service URl ${this.serviceUrl}"
  }
  
  void statusUpdateForOurAccount(def message) {
    def params = ['status' : message]
    
    log.debug "updating twitter account @${this.username} with status ${message}"
    def response = this.post(this.serviceUrl + "/statuses/update.json", params)
    log.debug "reponse from twitter ${reponse}"
  }
  
	protected Map execute(HttpUriRequest method, ResponseHandler handler = null) throws IOException {
	  assert method != null
	  
	  method = authorizationService.sign("twitter", method, this.oAuthAccessToken, this.oAuthAccessTokenSecret)
	  
	  return super.execute(method, handler)
	}
}