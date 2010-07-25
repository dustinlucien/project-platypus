<html>
    <head>
		<meta name="layout" content="main" />
		<!--<link rel="target_url" href=""/>-->
    </head>
  <body>
    <div id="header" class="span-23 prepend-1">
      <a href="/" class="span-7" id="logoheader"><span class="hidden">Redneckify</span></a>
      <div id="mainNav">
        <a href="${createLink(controller:'create')}" class="span-5" id="redneskifyMe"><span class="hidden">Redneckify Me</span></a>
        <a href="${createLink(controller:'gallery')}" class="span-3" id="gallery"><span class="hidden">Redneckify Me</span></a>
        <a href="${createLink(controller:'shop')}" class="span-3" id="merch"><span class="hidden">Redneckify Me</span></a>
        <a href="${createLink(controller:'about')}" class="span-4 last" id="aboutUsActive"><span class="hidden">Redneckify Me</span></a>
      </div>
    </div>

    <div id="midNav" class="span-24">
      <a href="${createLink(controller:'create')}" class="span-10" id="subh1"></a>
      <a href="${createLink(controller:'create')}" class="span-7" id="subh2"></a>
      <a href="${createLink(controller:'share')}" class="span-6" id="subh3"></a>
    </div>

    <g:if test="${flash.error}">
      <div class="error">
        ${flash.error}
      </div>
    </g:if>

    <g:if test="${flash.message}">
      <div class="message">
        ${flash.message}
      </div>
    </g:if>

  </body>
</html>