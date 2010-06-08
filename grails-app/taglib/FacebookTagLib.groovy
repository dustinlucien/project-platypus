import com.platypus.service.FacebookConnectService

class FacebookTagLib {
	
	def facebookConnectService
	
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
				  FB.Event.subscribe('auth.sessionChange', function(response) {
				    if (response.session) {
				      alert('logged in!')
				    } else {
					  alert('logged out!')
				    }
				  });
				  FB.Event.subscribe('auth.login', function(response) {
			        window.location.reload();
			      });
			    
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