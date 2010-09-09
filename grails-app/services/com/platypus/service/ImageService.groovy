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
  
  def listMostRecent(def params = [limit : 20]) {
    assert params != null
    
    params["sort"] = "dateCreated"
    params["order"] = "desc"
    
    return Image.list(params)
  }
  
  def listMostRecentByOwner(def user, def params = [limit:5,offset:0,page:1]) {
    assert user != null
    assert params != null
    assert params.limit > 0
    
    params["sort"] = "dateCreated"
    params["order"] = "desc"
    
    return Image.findAllByOwner(user, params)
  }
  
  def listForMerchandisePage(def user = null, def atLeast = 15, def atMost = 30) {
    if (user) {
      
    }
  }
  
  def listForGallery() {
    def c = LikeEvent.createCriteria();
    def images = c.list {
      projections {
        count 'id', 'myCount'
        groupProperty 'target'
      }
      order 'myCount'
    }
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
