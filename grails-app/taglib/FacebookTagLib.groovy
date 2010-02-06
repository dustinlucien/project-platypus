class FacebookTagLib {
	
	def facebookConnectService
	
	//Example: <g:facebookConnectJavascript base="http://example.com" secure="true"/>
	//Base attribute is optional.
	//Secure attribute is optional.  You should set it to true if your site uses ssl
	def facebookConnectJavascript = { attrs, body ->
		out << '''\n'''
		out << '''\n'''
		out << '''\n'''
		out << '''<!-- BEGIN: Javascript for facebook connect -->'''
		out << '''\n'''
		
		if(attrs['secure'])
			out << '''<script type="text/javascript" src="https://static.ak.connect.facebook.com/js/api_lib/v0.4/FeatureLoader.js.php"></script>'''
		else out << '''<script type="text/javascript" src="http://static.ak.connect.facebook.com/js/api_lib/v0.4/FeatureLoader.js.php"></script>'''
		
		out << '''\n'''
		out << "<script type=\"text/javascript\">"
		out << "FB.init(\"${facebookConnectService.getApiKey()}\", \"${attrs['base']?attrs['base']:""}"
		
		if(attrs['secure'])
			out << "${g.createLinkTo(dir:"${pluginContextPath}", file:"xd_receiver_ssl.htm")}\");"
		else out << "${g.createLinkTo(dir:"${pluginContextPath}", file:"xd_receiver.htm")}\");"
		
		out << "</script>"
		out << '''\n'''
		out << '''<!-- END: Javascript for facebook connect -->'''
		out << '''\n'''
		out << '''\n'''
		out << '''\n'''
	}
	
}