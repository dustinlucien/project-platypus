<html>
  <head>
      <title>Put Yer Mark On It</title>
	    <meta name="layout" content="main" />
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  <body>
    <div id="header" class="span-23 prepend-1">
      <a href="${createLink(controller:'create')}" class="span-7" id="logoheader"><span class="hidden">Redneckify</span></a>
      <div id="mainNav">
        <a href="${createLink(controller:'create')}" class="span-5" id="redneskifyMeActive"><span class="hidden">Redneckify Me</span></a>
        <a href="${createLink(controller:'gallery')}" class="span-3" id="gallery"><span class="hidden">Redneckify Me</span></a>
        <a href="${createLink(controller:'shop')}" class="span-3" id="merch"><span class="hidden">Redneckify Me</span></a>
        <a href="${createLink(controller:'about')}" class="span-4 last" id="aboutUs"><span class="hidden">Redneckify Me</span></a>
      </div>
    </div>

    <div id="midNav" class="span-24">
      <a href="${createLink(controller:'create')}" class="span-10" id="subh1"></a>
      <a href="${createLink(controller:'create')}" class="span-7" id="subh2Active"></a>
      <a href="${createLink(controller:'share')}" class="span-6" id="subh3"></a>
    </div>

    <g:render template="/snippets/flashMessageTemplate" />

  	<div class="span-24" id="content">
  		<div class="span-7 prepend-1" id="leftContent" />
    	  <div class="span-14  prepend-1 last" id="rightContent">
    	    <img width="95%" src="${image.getImageUrl()}" />
    	    <g:form controller="create" action="redneckify" method="post" enctype="multipart/form-data">
            <label for="title">What's yer handle</label><g:textField class="title" name="title" value="My Redneck Self" />
            <g:submitButton name="submit" value="10-4 Good Buddy"></g:submitButton>
          </g:form>
    	  </div>
	    </div>
	  </div>
  </body>
</html>