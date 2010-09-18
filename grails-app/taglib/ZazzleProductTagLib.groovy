import com.platypus.service.ImageService;

class ZazzleProductTagLib {
  
  def zazzleProductService
  
  def renderZazzleProductList = { attrs, body ->
    
    def serverUrl = zazzleProductService.getZazzleServerUrl()
    def associateId = zazzleProductService.getAssociateId()
    
    def products = zazzleProductService.getRandomProductList(3)

    products.each {
      def imageUrl =  serverUrl + "pd-" + it.getTemplateId() + "/isz-xs" + "/at-" + associateId + "/tl-productImage.png?image1_url=" + attrs.image.getImageUrl().encodeAsURL()
      def linkUrl = "http://www.zazzle.com/api/create/at-" + associateId + "?rf=" + associateId + "&ax=Linkover&pd=" + it.getTemplateId() + "&fwd=ProductPage&ed=true&image1=" + attrs.image.getImageUrl().encodeAsURL()

      out << render(template:"/snippets/zazzleProductTemplate", model:['spanClass': 'span-3', 'imageUrl' : imageUrl, 'linkUrl':linkUrl, 'title':it.getTitle()])
    }
  }
  
  def renderZazzleProductGallery = { attrs, body ->
    def serverUrl = zazzleProductService.getZazzleServerUrl()
    def associateId = zazzleProductService.getAssociateId()
    
    def products = zazzleProductService.getRandomProductList(attrs.images.size())
    
    for (int i = 0; i < products.size(); i++) {
      def imageUrl =  serverUrl + "pd-" + products[i].getTemplateId() + "/isz-s" + "/at-" + associateId + "/tl-productImage.png?image1_url=" + attrs.images[i].getImageUrl().encodeAsURL()
      def linkUrl = "http://www.zazzle.com/api/create/at-" + associateId + "?rf=" + associateId + "&ax=Linkover&pd=" + products[i].getTemplateId() + "&fwd=ProductPage&ed=true&image1=" + attrs.images[i].getImageUrl().encodeAsURL()

      out << render(template:"/snippets/zazzleProductTemplate", model:['spanClass': 'span-4', 'imageUrl' : imageUrl, 'linkUrl':linkUrl, 'title':products[i].getTitle()])
    }
  }
}