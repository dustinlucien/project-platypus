package com.platypus.controller

class GalleryController {
  def imageService
  
  def index = {
    [ images : imageService.listMostRecent() ]
  }
}
