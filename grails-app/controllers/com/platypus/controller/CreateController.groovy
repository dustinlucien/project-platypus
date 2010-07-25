package com.platypus.controller

class CreateController {

    def userService
    def imageService
    
    def index = {
      redirect(action:"redneckify")
    }
    
    def redneckifyFlow = {
      home {
        action {
          [ images : imageService.listMostRecent([limit:3]) ]
        }
        
        on("success").to("upload")
      }
      
      upload {
        on("submit").to("save")
      }
    
      save {
        action {
          def userImageParams = imageService.saveUploadedFile(request.getFile('file'))

          if (log.isDebugEnabled()) {
            log.debug "user image params returned from saveUploadedFile {$userImageParams}"
          }
          
          session.userImageFile = userImageParams.url
          session.userImageFileIsLocal = userImageParams.local
          session.userImageFileContentType = userImageParams.contentType
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
        on("finish").to("share")
      }
      
      share {
        redirect(controller:"share")
      }
    }
}
