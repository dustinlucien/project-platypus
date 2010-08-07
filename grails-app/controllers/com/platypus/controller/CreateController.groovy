package com.platypus.controller

import com.platypus.domain.Image

class CreateController {
    def imageService
    def facebookConnectService
    
    def index = {
      redirect(action:"redneckify")
    }
    
    def redneckifyFlow = {
      home {
        action {
          [ images : imageService.listMostRecent([limit:5]) ]
        }
        
        on("success").to("upload")
      }
      
      upload {
        on("submit").to("saveImage")
      }
    
      saveImage {
        action {
          def file = request.getFile('file')
          def oParams = [:]
          
          if (!file.empty) {
            def results = imageService.saveUploadedFile(request.getFile('file'))
            
            if (results) {
              session.imagePath = results.path
              session.imageContentType = results.contentType
              oParams['st'] = session.stoken
              oParams['ct'] = results.suffix
              oParams['t'] = 'l'
           } else {
             error()
           }
            
          } else if (params?.facebookfile){
            log.debug "got a facebook image with id ${params.facebookfile}";
            oParams['o'] = params.facebookfile
            oParams['st'] = session.stoken
            oParams['t'] = 'fb'            
          } else {
            error()
          }
        
          def taglib = new org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib()
          def userImageUrl = taglib.createLink(controller:'image', action:'serve', params : oParams)

          [ userImageUrl : userImageUrl ]
        }
        
        on("success") {
          /*
            hack! remove if flash can handle an execution param
          */
          session.flowExecution = params.execution.substring(0, params.execution.length() - 2)
        }.to("create")
        
        on("error") {
          log.error "error in save step of redneckify flow"
        }.to("upload")
      }
      
      create {
        on("finish").to("getImage")
      }
      
      getImage {
        action {
          if (!params?.image) {
            log.error "No image key was found in the request"
            error()
          }
          
          def image = Image.findByPkey(params.image)
          
          if (!image) {
            log.error "Could not find image referenced by Pkey ${params.image}"
            error()
          }
          
          flow.image = image
        }
        
        on("success").to("info")
      }
      
      info {
        on("submit").to("saveName")
      }
      
      saveName {
        action {
          def image = flow.image
          
          if (params?.title) {
            image.title = params.title
            
            if (!image.save()) {
              log.error "ERROR : couldn't save new image"
        			image.errors.allErrors.each {
        				log.error "${it}"
        			}
            }
          }
          //Post a status update to facebook and twitter here
        }
        on("success").to("share")
      }
      
      share {
        redirect(controller:"share")
      }
    }
}
