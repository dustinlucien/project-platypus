import com.platypus.service.FacebookConnectService

class FacebookTagLib {
	
	def facebookConnectService
	
	//Example: <g:facebookConnectJavascript base="http://example.com" secure="true"/>
	//Base attribute is optional.
	//Secure attribute is optional.  You should set it to true if your site uses ssl
	def facebookConnectJavascript = { attrs, body ->
		out << '''\n'''
		out << '''\n'''
		out << '''\n'''
		out << '''<!-- BEGIN: Javascript for facebook graph api -->'''
		out << '''\n'''
		
		out << '''<div id="fb-root"></div>'''
		out << '''\n'''
		
		out << """<script>
		      window.fbAsyncInit = function() {
		          FB.init({appId: '${facebookConnectService.getAppId()}', status: true, cookie: true, xfbml: true});
		      };
		      (function() {
		        var e = document.createElement('script');
		        e.type = 'text/javascript';
		        e.src = document.location.protocol +
		          '//connect.facebook.net/en_US/all.js';
		        e.async = true;
		        document.getElementById('fb-root').appendChild(e);
		      }());
		    </script>"""
		
		out << '''\n'''
		out << '''<!-- END: Javascript for facebook connect -->'''
		out << '''\n'''
		out << '''\n'''
		out << '''\n'''
	}
}