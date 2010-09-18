package com.platypus.service

import com.platypus.domain.Image
import com.platypus.domain.LikeEvent

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.DateTimeZone
import java.util.UUID

import com.platypus.util.BaseConverterUtil
import org.springframework.web.context.request.RequestContextHolder

import java.net.URL;
import java.io.InputStream;

class ImageService {
  
  static final def mimeMap = ['image/png' : 'png', 'image/jpeg' : 'jpg', 'image/gif' : 'gif']
  
  static transactional = false
  
  def buildUniqueImageKey() {
    /*
     Build a key for an image 
     */
    def uuid = UUID.randomUUID()
    
    def date = new DateTime()
    def format = DateTimeFormat.forPattern("yyyy/MM/dd/");
    
    def key = format.print(date) + uuid + ".jpg"
    
    if (log.isDebugEnabled()) {
      log.debug "generated this unique key " + key;
    }
    
    return key;
  }
  
  def listMostRecent(def params = [max : 20]) {
    assert params != null
    
    params["sort"] = "dateCreated"
    params["order"] = "desc"
    
    if (log.isDebugEnabled()) {
      log.debug "params going into Image.list : ${params}"
    }
    
    return Image.list(params)
  }
  
  def listMostRecentByOwner(def user, def params = [max:5,offset:0,page:1]) {
    assert user != null
    assert params != null
    assert params.max > 0
    
    params["sort"] = "dateCreated"
    params["order"] = "desc"

    if (log.isDebugEnabled()) {
      log.debug "params going into Image.findAllByOwner : ${params}"
    }
        
    return Image.findAllByOwner(user, params)
  }

  def listMostLiked(def params = [max : 20]) {
    assert params != null
    
    params["sort"] = "liked"
    params["order"] = "desc"
    
    if (log.isDebugEnabled()) {
      log.debug "params going into Image.list : ${params}"
    }
    
    return Image.list(params)
  }
    
  def listForShop(def user = null, def max = 20) {
    def images = []
    if (user) {
      def userImages = this.listMostRecentByOwner(user, [max : max])
      if (userImages) {
        images.addAll(userImages)
      }
    }
    
    if (images.size() >= max) {
      return images
    }
    
    def recentImages = this.listMostRecent([max : (max - images.size())])
    recentImages.removeAll(images)
    
    images.addAll(recentImages)
    
    return images
  }
  
  def listForGallery(def max = 40) {
    def images = this.listMostLiked([max : max])
    
    log.debug "most liked images ${images}"
    if (images.size() >= max) {
      return images
    }
    
    def recentImages = this.listMostRecent([max : (max - images.size())])
    recentImages.removeAll(images)
    
    images.addAll(recentImages)
    
    return images
  }
  
  def getMostRecentImage(def user) {
    assert user != null
    assert user.id != -1
    
    def params = [:]
    params["sort"] = "dateCreated"
    params["order"] = "desc"
    
    return Image.findByOwner(user, params)
  }
  
  def saveNewImage(def params, def user) {
    assert params != null
    assert user != null
    
    if (params?.key) {
      /*
       move the S3 key into the chiave field we're using to store the key
       */
      params.chiave = params.key
    }
    
    def image = new Image(params)
    
    image.owner = user;
    if (image.save()) {
      return image;
    } else {
      log.error "ERROR : couldn't save new image"
      image.errors.allErrors.each { log.error "${it}" }
      return null;
    }
  }
  
  def mergeOwners(def primary, def secondary) {
    assert primary != null
    assert secondary != null
    
    def images = Image.findAllByOwner(secondary)
    
    if (images) {
      images.each {
        it.owner = primary
        it.save()
      }
      return images.size()
    } else {
      return 0
    }
  }
  
  def saveUploadedFile(def file) {
    assert file != null
    
    def contentType = file.getContentType()
    
    log.debug "content type of image file is ${contentType}"
    def suffix = this.mimeMap.get(contentType)
    if (suffix == null) {
      log.debug "mime type of ${contentType} not recognized.  assuming jpg"
      suffix = 'jpg'
      contentType = 'image/jpeg'
    }
    
    def session = RequestContextHolder.currentRequestAttributes().getSession()
    
    def localFile = session.createTempFile("platypus-upload", suffix)
    localFile.deleteOnExit()
    
    log.debug "the local file is : ${localFile.getPath()}"
    
    try {
      file.transferTo(localFile)
    } catch (Exception e) {
      log.error(e)
    }
    
    return [ 'path': localFile.getPath(), 'suffix' : suffix, 'contentType' : contentType]
  }
  
  InputStream openStream(def image) {
    return new URL(image.getImageUrl()).openStream()
  }
}
