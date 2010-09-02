class ZazzleProductTagLig {
  
  def renderZazzleProductBanner = { attrs, body ->
    def associateId = "238983239304996873"

    def templateIds = [ "217970687045164850", 
                        "146190001601409777",
                        "155281159257729079",
                        "128124261718836322",
                        "235729169986527554",
                        "148639800638635303",
                        "235827913634227605",
                        "168040314455684141"]
    
    def templateTitles = ["Stickers", "Keychains", "Dog Coat", "Bumper Sticker", "T-Shirts", "Trucker Hat", "Tank Top", "Coffee Mug"]
                            
    def zazzleServer = "http://rdr.zazzle.com/img/imt-prd/"
    
    def random = new Random()
    def indices = []
    
    for (int i = 0; i < 3; i++) {
      def next = random.nextInt(3)
      def unique = true
      for (j = 0; j < indices.size(); j++) {
        if (indices[j] == next) {
          unique = false
          break
        }
      }
      
      if (unique) {
        indices.add(next)
      } else {
        //try again
        i--
      }
    }
    
    out << '''<div id="sl1" class="clear"><span class="hidden">Mugs, shirts, mouse pads - get yer redneck self on anything!</span></div>'''
    
    out << '''<div id="get">'''
    
    indices.each {
      def imageUrl = this.zazzleServer + "pd-" + this.templateIds[it] + "/isz-m" + "/at-" + this.associateId + "/tl-productImage.png?image1=" + attrs.image.getImageUrl()
      def linkUrl = "http://www.zazzle.com/api/create/at-238983239304996873?rf=238983239304996873&ax=Linkover&pd=" + this.templateIds[it] + "&fwd=ProductPage&ed=true&image1=" + attrs.image.getImageUrl()
      def productName = templateTitles[it]
      
      out << """ <a target=\"_blank\" href=\"${linkUrl}\"><<img width=\"25%\" src=\"${imageUrl}\" /></a> """
      out << """ <p>${productName}<br /><a target=\"_blank\" href=\"${linkUrl}\">Git it now</a></p> """
    }
    
    out << '''</div>'''
    
  }
}