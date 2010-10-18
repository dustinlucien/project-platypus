package com.platypus.service

class ZazzleProductService {
  static transactional = false
  
  def associateId = "238983239304996873"
  //def zazzleServer = "http://rlv.zcache.com/img/imt-prd/"
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
    products.add(new Product("146190001601409777", "Keychain", 15.0))
    products.add(new Product("155281159257729079", "Dog Coat", 15.0))
    products.add(new Product("128124261718836322", "Bumper Sticker", 15.0))
    products.add(new Product("235807312128788259", "T-Shirts", 19.0))
    products.add(new Product("235729169986527554", "T-Shirts", 15.0))
    products.add(new Product("148639800638635303", "Trucker Hat", 15.0))
    products.add(new Product("235827913634227605", "Tank Top", 15.0))
    products.add(new Product("168040314455684141", "Coffee Mug", 15.0))
    products.add(new Product("144022018299821725", "Mouse Pad", 10.95))
  }
  
  def getAssociateId() {
    return associateId;
  }
  
  def getZazzleServerUrl() {
    return zazzleServer;
  }
  
  def getRandomProduct() {
    def random = new Random()
    int numProducts = this.products.size()
    
    return products.get(random.nextInt(numProducts))
  }
  
  def getRandomUniqueProductList(def limit = 3) {
    
    def random = new Random()
    def indices = []
    
    int numProducts = this.products.size()
    
    def shortCircuit = false
    if (numProducts <= limit) {
      log.error "can't enforce uniqueness.  you're asking for more product variation than you have"
      shortCircuit = true
    }
    
    for (int i = 0; i < limit; i++) {
      log.debug "looking for another random product"
      def next = random.nextInt(numProducts)
      def unique = true
      for (int j = 0; j < indices.size(); j++) {
        if (indices[j] == this.products[next]) {
          unique = false
          break
        }
      }
      
      if (unique || shortCircuit) {
        indices.add(this.products[next])
      } else {
        //try again
        i--
      }
    }
    
    return indices

  }
  
  def getRandomProductList(def limit = 3) {
    
    def random = new Random()
    def indices = []
    
    int numProducts = this.products.size()
    
    for (int i = 0; i < limit; i++) {
      log.debug "looking for a random product"
      def next = random.nextInt(numProducts)
      indices.add(this.products[next])
    }
    
    return indices

  }
}