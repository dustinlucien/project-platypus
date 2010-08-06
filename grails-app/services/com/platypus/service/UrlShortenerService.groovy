package com.platypus.service

import com.platypus.json.helpers.JsonHttpResponseHandler

import org.springframework.beans.factory.InitializingBean;
import org.codehaus.groovy.grails.commons.ConfigurationHolder;

class UrlShortenerService implements InitializingBean {
  
  def restClientService
  
  def bitlyLogin
  def bitlyApiKey
  
  void afterPropertiesSet() {
    this.bitlyLogin = ConfigurationHolder.config.bitly.login
    this.bitlyApiKey = ConfigurationHolder.config.bitly.apiKey
    
    log.debug "URl shortening config information ${this.bitlyLogin}, ${this.bitlyApiKey}"
  }
  
  def shortenUrl(def url) {
    def params = ['login' : bitlyLogin,
                  'apiKey' : bitlyApiKey,
                  'format' : 'txt',
                  'longUrl' : url]
    
    def response = restClientService.get("http://api.bit.ly/v3/shorten", params)
    
    assert response != null
    assert response.code == 200
    
    return response.response.trim()
  }
}