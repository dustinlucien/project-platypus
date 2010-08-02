package com.platypus.controller

import java.io.FileInputStream
import java.io.OutputStream
import java.io.InputStream
import java.net.URL

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class ImageController {
    static layout = 'admin'
	  static scaffold = com.platypus.domain.Image
	  
	  def facebookConnectService
	  
    def serve = {
      if (log.isDebugEnabled()) {
        log.debug "SERVING AN IMAGE WITH PARAMS ${params}"
      }
      
      if (!params?.st || (params.st != session.stoken)) {
        log.error "Incorrect security token in request"
        response.status = 403
        render "Permission denied"
        return
      }
            
      def type = params?.t
      
      InputStream input;
      
      if (type == 'l') {
        def imageFile = new File(session.imagePath)
        response.setContentType(session.imageContentType)

        def imageFileSize = imageFile.length()
        response.setContentLength((int)imageFileSize)

        input = new FileInputStream(imageFile)
      } else if (type == 'fb') {
        assert params?.o
        
        def results = facebookConnectService.getPhotoDetails(params.o)
        
        if (results) {
          //TODO: replace this with an HttpClientService call
          //all external calls should go through this service
          input = new URL(results.url).openStream()
        } else {
          log.error "Could not get URL to Facebook Image"
          response.status = 404
          render "Object not found"
          return
        }
        
      } else {
        log.error "Unrecognized or missing image type in params"
        response.status = 404
        render "Object not found"
        return
      }
      
      OutputStream out = response.getOutputStream()
      
      int bytesRead = -1
      int bufferSize = ConfigurationHolder.config.buffers.imageStreamingBufferSize

      byte[] buffer = new byte[bufferSize]
      
      try {
          while((bytesRead = input.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead)
          }
      } catch (IOException e) {
        log.error "error writing or reading image streams"
        log.error(e)
      } finally {
        input.close()
        out.close()        
      }
      
      if (log.isDebugEnabled()) {
        log.debug "DONE SERVING THE IMAGE"
      }
      
    }
}
