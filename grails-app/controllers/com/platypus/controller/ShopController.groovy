package com.platypus.controller

class ShopController {
  def imageService
  def userService
  
  def show = {
    def user = userService.getCurrentUser(request)
    
    def images = imageService.listMostRecentByOwner(user)
    
    if (log.isDebugEnabled()) {
      if (images) {
        images.each {
          log.debug "User : ${it.owner} Image Key : ${it.chiave}"
        }
      }
    }
    
    if (!images) {
      log.info "no images returned. redirecting to gallery"
      flash.message = "There aren't any Rednecks yet.  Make one dammit!"
      redirect(controller:'create')
    }
    
    return [ images : images ]
    
  }
  
  def index = {
    def user = userService.getCurrentUser(request)
    
    def images = imageService.listForShop(user, 40);
    
    if (log.isDebugEnabled()) {
      if (images) {
        images.each {
          log.debug "User : ${it.owner} Image Key : ${it.chiave}"
        }
      }
    }
    
    if (!images) {
      log.info "no images returned. redirecting to gallery"
      flash.message = "There aren't any Rednecks yet.  Make one dammit!"
      redirect(controller:'create')
    }
        
    return [ images : images ]
  }
}
