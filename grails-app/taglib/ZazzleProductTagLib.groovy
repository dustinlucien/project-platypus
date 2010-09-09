class ZazzleProductTagLib {

  def zazzleProductService
  
  def renderZazzleProductBanner = { attrs, body ->

    out << '''<div id="sl1" class="clear"><span class="hidden">Mugs, shirts, mouse pads - get yer redneck self on anything!</span></div>'''
    
    out << '''<div class="span-11 prepend-1 last" id="get">'''
    
    def serverUrl = zazzleProductService.getZazzleServerUrl()
    def associateId = zazzleProductService.getAssociateId()
    
    def products = zazzleProductService.getRandomProductList(3)
    
    products.each {
      def imageUrl =  serverUrl + "pd-" + it.getTemplateId() + "/isz-m" + "/at-" + associateId + "/tl-productImage.png?image1_url=" + attrs.image.getImageUrl().encodeAsURL()
      def linkUrl = "http://www.zazzle.com/api/create/at-" + associateId + "?rf=" + associateId + "&ax=Linkover&pd=" + it.getTemplateId() + "&fwd=ProductPage&ed=true&image1=" + attrs.image.getImageUrl().encodeAsURL()

      out << """<div class=\"span-3\" """
      out << """ <div><a target=\"_blank\" href=\"${linkUrl}\"><img width=\"90%\" src=\"${imageUrl}\" /></a></div> """
      out << """ <div style=\"text-align: center\">${it.getTitle()}<br /><a target=\"_blank\" href=\"${linkUrl}\">Git it now</a></div> """
      out << """</div> """
    }
    out << '''</div>'''
    
    out << '''<div class="span-11 prepend-1 last" id="zazzle">'''
    out << '''<p><a href="http://www.zazzle.com/dustinlucien" target="_blank">Check out more gear to Redneckify here!</a></p>'''
    out << '''</div>'''
  }
}