package com.platypus.controller


class HomeController {
  
  def imageService
  
  def index = {
    [ images : imageService.listMostRecent([limit:3]) ]
  }
  
}
