package com.platypus.controller

class GalleryController {
  def imageService
  
  def index = {
    def images = imageService.listForGallery()

    if (!images) {
      log.info "no images returned. redirecting to gallery"
      flash.message = "There aren't any Rednecks yet.  Make one dammit!"
      redirect(controller:'create')
    }

    return images
  }
  
}
