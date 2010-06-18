package com.platypus.controller


class HomeController {
  
  def userService
  def imageService
  def facebookConnectService
  
  def index = {
               
    redirect(action:'list', params:params)
  }
  
  def list = {
              
    facebookConnectService.listPhotos()
    
    [ images : imageService.listMostRecent() ]
  }
  
}
