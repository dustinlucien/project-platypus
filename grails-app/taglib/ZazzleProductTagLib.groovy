import com.platypus.service.ImageService;

class ZazzleProductTagLib {

  def zazzleProductService
  
  def renderZazzleProductList = { attrs, body ->
    
    def serverUrl = zazzleProductService.getZazzleServerUrl()
    def associateId = zazzleProductService.getAssociateId()
    
    def products = zazzleProductService.getRandomProductList(attrs.count as int)

    products.each {
      def imageUrl =  serverUrl + "pd-" + it.getTemplateId() + "/isz-m" + "/at-" + associateId + "/tl-productImage.png?image1_url=" + attrs.image.getImageUrl().encodeAsURL()
      def linkUrl = "http://www.zazzle.com/api/create/at-" + associateId + "?rf=" + associateId + "&ax=Linkover&pd=" + it.getTemplateId() + "&fwd=ProductPage&ed=true&image1=" + attrs.image.getImageUrl().encodeAsURL()

      out << render(template:"/snippets/zazzleProductTemplate", model:['imageUrl' : imageUrl, 'linkUrl':linkUrl, 'title':it.getTitle()])
    }
  }
}