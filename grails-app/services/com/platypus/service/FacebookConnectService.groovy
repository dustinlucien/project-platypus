package com.platypus.service

import org.springframework.beans.factory.InitializingBean
import java.security.MessageDigest
import javax.servlet.http.HttpServletRequest

import org.springframework.beans.factory.InitializingBean;
import org.codehaus.groovy.grails.commons.ConfigurationHolder;

import com.restfb.Connection;
import com.restfb.FacebookClient
import com.restfb.DefaultFacebookClient
import com.restfb.types.Album;
import com.restfb.types.Photo;

import java.lang.Long

class FacebookConnectService implements InitializingBean {
  
  boolean transactional = false
  
  private FacebookClient client
  private String appId
  
  private Long cachedUid
  private String cachedSecret
  private Long cachedExpiration
  private String cachedAccessToken
  private boolean loggedIn
  
  void afterPropertiesSet() {
    appId = ConfigurationHolder.config.facebook.appId
    assert appId != null
    
    this.cachedUid = -1
    this.cachedSecret = null
    this.cachedExpiration = -1
    this.cachedAccessToken = null
    
    this.client = null
    this.loggedIn = false
  }
  
  String getAppId() {
    return this.appId
  }
  
  
  def getUid(def request) {
    if (cachedUid == -1) {
      assert isLoggedIn(request)
    }
    
    return cachedUid
  }
  
  public void handleAuthEvent(def request) {
    log.debug "cookies:"
    
    request.cookies.each { log.debug "name : ${it.name} value: ${it.value}" }
    
    String fbCookie = request.cookies.find { it.name == "fbs_${this.appId}" }?.value
    
    if (fbCookie) {
      String[] splitCookie = fbCookie.replace('"', '').trim().split("&")
      
      Map fbParams = [:]   
      
      for (String param : splitCookie) {
        String name = param.split("=")[0];
        String value = param.split("=")[1];
        
        fbParams.put(name, value)
      }
      
      log.debug "uid from fbparams ${fbParams.get('uid')}"
      
      this.cachedUid = Long.decode(fbParams.get("uid"))
      
      log.debug "cachedUid ${this.cachedUid}"
      
      this.cachedSecret = fbParams.get("secret")
      
      log.debug "cachedSecret ${this.cachedSecret}"
      
      this.cachedAccessToken = fbParams.get("access_token")
      
      log.debug "cachedAccessToken ${this.cachedAccessToken}"
      
      this.client = new DefaultFacebookClient(this.cachedAccessToken)
      
      log.debug "setting facebookconnectservice.loggedIn to true"
      this.loggedIn = true
    } else {
      this.client = null
      this.cachedUid = -1
      this.cachedSecret = null
      this.cachedAccessToken = null
      log.debug "setting facebookconnectservice.loggedIn to false"
      this.loggedIn = false;
    }
  }
  
  public Long getUid() {
    assert this.loggedIn
    
    return this.cachedUid
  }
  
  public boolean isLoggedIn() {
    return this.loggedIn
  }
  
  def listPhotos(def offset=0, def limit=-1) {
    //assert this.loggedIn
    
    log.debug "loggedin to facebook " + this.loggedIn
    
    Connection<Photo> photos = client.fetchConnection("${this.cachedUid}/photos", Photo.class)
    Connection<Album> albums = client.fetchConnection("${this.cachedUid}/albums", Album.class)
    
    log.debug photos
    log.debug albums

  }
  
  def listPhotoAlbums(def offset=0, def limit=-1) {
    assert this.loggedIn
  }
}
