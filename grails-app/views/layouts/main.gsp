<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://www.facebook.com/2008/fbml">
    <head>
        <title><g:layoutTitle default="Redneckify Me!" /></title>
		<link rel="stylesheet" href="${resource(dir:'css',file:'style.css')}" />
        <link rel="stylesheet" href="${resource(dir:'css/blueprint',file:'screen.css')}" />
        <link rel="stylesheet" href="${resource(dir:'css/blueprint',file:'print.css')}" type="text/css" media="print">	
        <!--[if lt IE 8]><link rel="stylesheet" href="${resource(dir:'css/blueprint',file:'ie.css')}" type="text/css" media="screen, projection"><![endif]-->
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body>
		<div class="container">
          <g:layoutBody />
	      
		  <div class="clear"></div>

	      <div id="footer">
	        <a href="#" class="span-4" id="logofooter"><span class="hidden">Redneckify</span></a>
	        <ul class="span-3">
	            <li><a href="#">Redneckify Me</a></li>
	            <li><a href="#">Gallery</a></li>
	            <li><a href="#">Merch</a></li>
	            <li><a href="#">About us</a></li>
	        </ul>
	        <ul class="span-3">
	            <li><a href="#">Twitter</a></li>
	            <li><a href="#">Facebook</a></li>
	            <li><a href="#">MySpace</a></li>
	        </ul>
	        <ul class="span-3">
	            <li>Terms of Service</li>
	            <li>Privacy Policy</li>
	        </ul>
	        <ul class="span-6">
	            <li>Copyright © 2010 BustedHumps Inc.</li>
	            <li>All Right Reserved.</li>
	        </ul>
	      </div>
	
		</div>
		<g:facebookConnectJavascript  />
    </body>
</html>