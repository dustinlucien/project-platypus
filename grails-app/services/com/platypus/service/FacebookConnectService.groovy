package com.platypus.service

import org.springframework.beans.factory.InitializingBean;
import java.security.MessageDigest;
import javax.servlet.http.HttpServletRequest;

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
  private AtomicBoolean loggedIn
  
  void afterPropertiesSet() {
    appId = ConfigurationHolder.config.facebook.appId
    assert appId != null
    
    this.cachedUid = -1
    this.cachedSecret = null
    this.cachedExpiration = -1
    this.cachedAccessToken = null
    
    this.client = null
    this.loggedIn = new AtomicBoolean(false)
  }
  
  String getAppId() {
    return this.appId
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
      //this.client = new DefaultFacebookClient('2227470867|2.8UReIc1M3huqe0lt1f6xKA__.3600.1277791200-621241239|CKqQu6RdqDR87bQ8aeO22mtgHEA.')
      log.debug "setting facebookconnectservice.loggedIn to true"
      this.loggedIn.set(true)

    } else {
      this.client = null
      this.cachedUid = -1
      this.cachedSecret = null
      this.cachedAccessToken = null
      log.debug "setting facebookconnectservice.loggedIn to false"
      this.loggedIn.set(false)
    }
  }
  
  public Long getUid() {
    assert this.loggedIn.get()
    
    return this.cachedUid
  }
  
  public boolean isLoggedIn() {
    return this.loggedIn.get()
  }
  
  def listPhotos(def offset=0, def limit=-1) {
    assert this.loggedIn.get()
        
    Connection<Photo> photos = client.fetchConnection("${this.cachedUid}/photos", Photo.class)

    return photos
  }
  
  def listAlbums(def offset=0, def limit=-1) {
    assert this.loggedIn.get()
    
    Connection<Album> albums = client.fetchConnection("${this.cachedUid}/albums", Album.class)
    
    return albums
  }
  
  def getProfilePicture() {
    assert this.loggedIn.get()
    
    User user = client.fetchObject("${this.cachedUid}", User.class)
    
    return user.getPicture()
  }
  
  def getPhotoDetails(def objectId) {
    assert this.loggedIn.get()
    
    Photo photo = client.fetchObject("${objectId}", Photo.class)
    
    log.debug "returned a photo object + ${photo}"
    
    return ['url' : photo.getSource()]
  }
  
  def publishImage(def image, def message) {
    assert this.loggedIn.get()
    
    def imageStream = imageService.openStream(image)
    
    FacebookType publishPhotoResponse = facebookClient.publish("${this.cachedUid}/photos", 
                                                                FacebookType.class, imageStream, 
                                                                Parameter.with("message", message));

    log.debug("Published photo ID: " + publishPhotoResponse.getId());
    
    return true
  }
}
