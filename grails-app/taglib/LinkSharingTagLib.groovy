class LinkSharingTagLib {
  def createLinkToShare = { attrs, body ->
    def serverUrl = grailsApplication.config.grails.serverURL
    
    out << serverUrl + '/share/show/' + attrs.pkey
  }
}
