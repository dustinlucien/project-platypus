<html>
    <head>
        <title>Redneckify!</title>
		    <meta name="layout" content="main" />
        <g:javascript library="jquery" plugin="jquery"/>		    
		    <g:twitterWidgetResources />
    </head>
  <body>
    <div id="header" class="span-23 prepend-1">
      <a href="${createLink(controller:'create')}" class="span-7" id="logoheader"><span class="hidden">Redneckify</span></a>
      <div id="mainNav">
        <a href="${createLink(controller:'create')}" class="span-5" id="redneskifyMe"><span class="hidden">Redneckify Me</span></a>
        <a href="${createLink(controller:'gallery')}" class="span-3" id="galleryActive"><span class="hidden">Redneckify Me</span></a>
        <a href="${createLink(controller:'shop')}" class="span-3" id="merch"><span class="hidden">Redneckify Me</span></a>
        <a href="${createLink(controller:'about')}" class="span-4 last" id="aboutUs"><span class="hidden">Redneckify Me</span></a>
      </div>
    </div>

    <div id="midNav" class="span-24">
      <a href="${createLink(controller:'create')}" class="span-10" id="subh1"></a>
      <a href="${createLink(controller:'create')}" class="span-7" id="subh2"></a>
      <a href="${createLink(controller:'share')}" class="span-6" id="subh3"></a>
    </div>
	  <div class="span-24" id="content">
	      <g:render template="/snippets/flashMessageTemplate" />
    		<div class="span-23 prepend-2 inline">
          <g:render template="/snippets/redneckThumbnailTemplate" var="image" collection="${images}" />
        </div>
  	</div>
  </body>
</html>