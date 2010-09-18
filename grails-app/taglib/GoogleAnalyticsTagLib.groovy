import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil

class GoogleAnalyticsTagLib {
  def googleAnalytics = { attrs, body ->
    if (GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) {
      out << '''<!-- BEGIN: Javascript for Google Analytics -->'''
  		out << '''\n'''
		
      out << """
              <script type=\"text/javascript\">

                var _gaq = _gaq || [];
                _gaq.push(['_setAccount', '${grailsApplication.config.google.accountCode}']);
                _gaq.push(['_setDomainName', '${grailsApplication.config.google.domainName}']);
                _gaq.push(['_setAllowLinker', true]);
                _gaq.push(['_trackPageview']);

                (function() {
                  var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
                  ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
                  var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
                })();

              </script>
            """
    
      out << '''\n'''
  		out << '''<!-- END: Javascript for Google Analytics -->'''
	  }
    
  }
}