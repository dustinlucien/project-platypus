class TwitterAnywhereTagLib {
  def twitterAnywhereResources = {attrs, body ->
    out << """<script type=\"text/javascript\" src=\"http://platform.twitter.com/anywhere.js?id=${grailsApplication.config.twitter.apiKey}&v=1\" ></script>"""
  }
  
  def twitterTweetButtonResources = {attrs, body ->
    out << """<script type=\"text/javascript\" src=\"http://platform.twitter.com/widgets.js\"></script>"""
  }
}