class FacebookTagLib {
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
		        FB.init({appId: '${grailsApplication.config.facebook.appId}', status: true, cookie: true, xfbml: true});
	          FB.Event.subscribe('auth.sessionChange', function(response) {
		           console.log('session changed')
		           var ajaxurl = '${createLink(controller:'user', action:'sajaxsessionevent', params : [st : attrs.stoken])}'
		           
		           if (response.perms) {
		             ajaxurl += '&fbperms=' + response.perms
		           }
		           
		           \$.get(ajaxurl, function(data){
                   console.log('response from sajaxsessionevent' + data)
                   \$('#facebook-post-button').attr('disabled', 'true')
                 });    
		        });
		        FB.Event.subscribe('edge.create', function(response) {
		          console.log('liked something')
		          console.log(response)
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