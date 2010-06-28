<html>
    <head>
        <title>Redneckify!</title>
		<meta name="layout" content="main" />
    </head>
  <body>
	<g:render template="/navigation/createHeaderTemplate" />
	

	<div id="midNav" class="span-24">
	  <a href="${createLink(controller:'upload')}" class="span-10" id="subh1"></a>
	  <a href="${createLink(controller:'create')}" class="span-7" id="subh2"></a>
	  <a href="${createLink(controller:'share')}" class="span-6" id="subh3"></a>
	</div>
	
		
	<div class="span-24" id="content">
	</div>
  </body>
</html>