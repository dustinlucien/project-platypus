<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en" xmlns:fb="http://www.facebook.com/2008/fbml" xmlns:og="http://opengraphprotocol.org/schema/">
    <head>
		  <meta http-equiv="Content-Type" content="text/html; charset=windows-1251" />
      <title><g:layoutTitle default="Redneckify Me!" /></title>
      <link rel="stylesheet" href="${resource(dir:'css',file:'style.css')}" media="screen" type="text/css"/>
      <link rel="stylesheet" href="${resource(dir:'css/blueprint',file:'screen.css')}" media="screen, projection" type="text/css"/>
      <link rel="stylesheet" href="${resource(dir:'css/blueprint/src',file:'print.css')}" type="text/css" media="print">	
      <!--[if lt IE 8]><link rel="stylesheet" href="${resource(dir:'css/blueprint',file:'ie.css')}" type="text/css" media="screen, projection"><![endif]-->
      <g:layoutHead />
      <g:googleAnalytics />
    </head>
    <body>
      <div class="container">
        
        <g:layoutBody />

        <div class="clear"></div>

        <div id="footer">
          <a href="#" class="span-4" id="logofooter"><span class="hidden">Redneckify</span></a>
          <ul class="span-3">
            <li><a href="${createLink(controller:'create')}">Redneckify Me</a></li>
            <li><a href="${createLink(controller:'gallery')}">Gallery</a></li>
            <li><a href="${createLink(controller:'shop')}">Merch</a></li>
            <li><a href="${createLink(controller:'about')}">About us</a></li>
          </ul>
          <ul class="span-3">
            <li><a href="http://twitter.com/redneckify">Twitter</a></li>
            <li><a href="http://www.facebook.com/apps/application.php?id=314398985906">Facebook</a></li>
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
      <g:facebookConnectJavascript stoken="${flash.stoken}" />      
    </body>
</html>
