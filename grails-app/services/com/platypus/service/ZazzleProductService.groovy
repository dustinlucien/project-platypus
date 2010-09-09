package com.platypus.service

class ZazzleProductService {
  static transactional = false
  
  def associateId = "238983239304996873"
  def zazzleServer = "http://rlv.zazzle.com/img/imt-prd/"
  def products = []
  
  class Product {
    def templateId
    def title
    def price
    
    public Product(def templateId, def title, def price) {
      this.templateId = templateId
      this.title = title
      this.price = price
    }
    
    def getTemplateId() {
      return templateId;
    }
    
    def getTitle() {
      return title
    }
    
    def getPrice() {
      return price
    }
  }
  
  public ZazzleProductService() {
    products.add(new Product("217970687045164850", "Stickers", 15.0))
    products.add(new Product("146190001601409777", "Keychains", 15.0))
    products.add(new Product("155281159257729079", "Dog Coat", 15.0))
    products.add(new Product("128124261718836322", "Bumper Sticker", 15.0))
    products.add(new Product("235729169986527554", "T-Shirts", 15.0))
    products.add(new Product("148639800638635303", "Trucker Hat", 15.0))
    products.add(new Product("235827913634227605", "Tank Top", 15.0))
    products.add(new Product("168040314455684141", "Coffee Mug", 15.0))
    products.add(new Product("144022018299821725", "MousePad", 10.95))
  }
  
  def getAssociateId() {
    return associateId;
  }
  
  def getZazzleServerUrl() {
    return zazzleServer;
  }
  
  def getRandomProductList(int limit = 3) {
    
    def random = new Random()
    def indices = []
    
    int numProducts = this.products.size()
    
    for (int i = 0; i < limit; i++) {
      def next = random.nextInt(numProducts)
      log.debug "next int from random ${next}"
      def unique = true
      for (int j = 0; j < indices.size(); j++) {
        if (indices[j] == this.products[next]) {
          log.debug "not unique.  getting a new one"
          unique = false
          break
        }
      }
      
      if (unique) {
        indices.add(this.products[next])
      } else {
        //try again
        i--
      }
    }
    
    return indices

  }
}