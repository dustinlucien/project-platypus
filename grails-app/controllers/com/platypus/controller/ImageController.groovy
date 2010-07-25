package com.platypus.controller

import java.io.FileInputStream
import java.io.OutputStream
import java.io.InputStream

import org.codehaus.groovy.grails.commons.ConfigurationHolder

class ImageController {
    static layout = 'admin'
	  static scaffold = com.platypus.domain.Image
	  
    def serve = {
      
      if (params?.id == null)
      assert session?.userImageFile
      assert session?.userImageFileContentType
      assert session?.userImageFileIsLocal
      
      log.debug "serving image from the session that was uploaded locally"
      
      if (!session.userImageFileIsLocal) {
        log.error "can't handle remote image files yet"
      }
      
      def imageFile = new File(session.userImageFile)
      response.setContentType(session.userImageFileContentType)
      
      def imageFileSize = imageFile.length()
      response.setContentLength((int)imageFileSize)
      
      int bytesRead = -1
      int bufferSize = ConfigurationHolder.config.buffers.imageStreamingBufferSize
      
      byte[] buffer = new byte[bufferSize]
      
      InputStream input = new FileInputStream(imageFile)
      OutputStream out = response.getOutputStream()
      
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
      
      log.debug "image served?"
    }
}
