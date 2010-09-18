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
                 });    
		        });
		        FB.Event.subscribe('edge.create', function(response) {
		           var ajaxurl = '${createLink(controller:'user', action:'sajaxedgeevent', params : [st : attrs.stoken])}'
		           
		           var lastIndex = response.indexOf('?')
		           if (lastIndex <= 0) {
		             lastIndex = response.length
		           }
		           
		           var pkey = response.slice(response.lastIndexOf('/') + 1, lastIndex)
		           
		           if (ajaxurl.indexOf('?') >= 0) {
		             ajaxurl += '&pkey=' + pkey
		           } else {
		             ajaxurl += '?pkey=' + pkey
		           }
		           
		           \$.get(ajaxurl, function(data){
                  console.log('response from sajaxedgeevent' + data)
                });
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