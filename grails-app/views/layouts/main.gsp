<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://www.facebook.com/2008/fbml">
    <head>
        <title><g:layoutTitle default="Redneckify Me!" /></title>
        <link rel="stylesheet" href="${resource(dir:'css/blueprint',file:'screen.css')}" />
        <link rel="stylesheet" href="${resource(dir:'css/blueprint',file:'print.css')}" type="text/css" media="print">	
        <!--[if lt IE 8]><link rel="stylesheet" href="${resource(dir:'css/blueprint',file:'ie.css')}" type="text/css" media="screen, projection"><![endif]-->
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body>
		<div class="container">
            <g:layoutBody />
			<div class="span-24 last">
				<h2>This is the footer</h2>
			</div>
		</div>
		<g:facebookConnectJavascript  />
    </body>
</html>