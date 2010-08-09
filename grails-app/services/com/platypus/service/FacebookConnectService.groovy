package com.platypus.service

import org.springframework.beans.factory.InitializingBean;
import java.security.MessageDigest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;

import org.springframework.beans.factory.InitializingBean;
import org.codehaus.groovy.grails.commons.ConfigurationHolder;

import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.DefaultFacebookClient;
import com.restfb.types.FacebookType;
import com.restfb.types.Album;
import com.restfb.types.Photo;
import com.restfb.types.User;
import com.restfb.Parameter;

import java.lang.Long;
import java.util.concurrent.atomic.AtomicBoolean;

import java.net.URL;

class FacebookConnectService implements InitializingBean {
  
  boolean transactional = false
  
  def imageService
  
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
  
  public void parseCookies(def request) {
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
      this.loggedIn = false
    }
  }
  
  public Long getUid() {
    assert this.isLoggedIn()
    
    return this.cachedUid
  }
  
  public boolean isLoggedIn() {
    if (!this.loggedIn) {
      //give it the old college try
      def request = RequestContextHolder.currentRequestAttributes().getRequest()
      this.parseCookies(request)
    }
    
    return this.loggedIn
  }
    
  def listPhotos(def offset=0, def limit=-1) {
    assert this.isLoggedIn()
        
    Connection<Photo> photos = client.fetchConnection("${this.cachedUid}/photos", Photo.class)

    return photos
  }
  
  def listAlbums(def offset=0, def limit=-1) {
    assert this.isLoggedIn()
    
    Connection<Album> albums = client.fetchConnection("${this.cachedUid}/albums", Album.class)
    
    return albums
  }
  
  def getProfilePicture() {
    assert this.isLoggedIn()
    
    User user = client.fetchObject("${this.cachedUid}", User.class)
    
    return user.getPicture()
  }
  
  def getPhotoDetails(def objectId) {
    assert this.isLoggedIn()
    
    Photo photo = client.fetchObject("${objectId}", Photo.class)
    
    log.debug "returned a photo object + ${photo}"
    
    return ['url' : photo.getSource()]
  }
  
  def publishImage(def image, def message) {
    assert this.isLoggedIn()
    
    def imageStream = imageService.openStream(image)
    
    FacebookType response = client.publish("${this.cachedUid}/photos", 
                                            FacebookType.class, imageStream, 
                                            Parameter.with("message", message));

    log.debug("Published photo ID: " + response.getId());
    
    return true
  }
  
  def publishMessageToFeed(def message) {
    assert this.isLoggedIn()
    
    FacebookType response = client.publish("${this.cachedUid}/feed", 
                                            FacebookType.class, 
                                            Parameter.with("message", message));
    
  }
  
  def populateUserWithFacebookProfile(def user) {
    assert this.isLoggedIn()
    
    User fbUser = client.fetchObject("${this.cachedUid}", User.class);
    
    if (fbUser) {
      user.facebookUid = this.cachedUid
      user.firstname = fbUser.getFirstName()
      user.lastname = fbUser.getLastName()
      user.email = fbUser.getEmail()
      user.location = fbUser.getLocation().getName()
      user.gender = fbUser.getGender()
    }
  }
  
  def getFacebookProfileParams() {
    assert this.isLoggedIn()
    
    User fbUser = client.fetchObject("${this.cachedUid}", User.class);
    
    def params = [:]
   
    if (fbUser) {
      params.facebookUid = this.cachedUid
      params.firstname = fbUser.getFirstName()
      params.lastname = fbUser.getLastName()
      params.email = fbUser.getEmail()
      params.location = fbUser.getLocation().getName()
      params.gender = fbUser.getGender()
    }
    
    return params
  }
}
