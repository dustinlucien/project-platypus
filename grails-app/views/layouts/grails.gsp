<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://www.facebook.com/2008/fbml">
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>
        <div id="grailsLogo" class="logo"><a href="http://grails.org"><img src="${resource(dir:'images',file:'grails_logo.png')}" alt="Grails" border="0" /></a></div>
		
		<fb:login-button autologoutlink="true"></fb:login-button>
		  <br/>
		<fb:name uid="loggedinuser" useyou="false"></fb:name>
		<fb:profile-pic uid="loggedinuser" size="normal" />
		
        <g:layoutBody />
		<g:facebookConnectJavascript  />
    </body>
</html>