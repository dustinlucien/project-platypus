<html>
    <head>
        <title>Git yer pitcher on in there</title>
		<meta name="layout" content="main" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
  <body>
    <div id="header" class="span-23 prepend-1">
      <a href="${createLink(controller:'home')}" class="span-7" id="logoheader"><span class="hidden">Redneckify</span></a>
      <div id="mainNav">
        <a href="${createLink(controller:'create')}" class="span-5" id="redneskifyMeActive"><span class="hidden">Redneckify Me</span></a>
        <a href="${createLink(controller:'gallery')}" class="span-3" id="gallery"><span class="hidden">Redneckify Me</span></a>
        <a href="${createLink(controller:'shop')}" class="span-3" id="merch"><span class="hidden">Redneckify Me</span></a>
        <a href="${resource(uri:'/aboutus')}" class="span-4 last" id="aboutUs"><span class="hidden">Redneckify Me</span></a>
      </div>
    </div>

    <div id="midNav" class="span-24">
      <a href="${createLink(controller:'create')}" class="span-10" id="subh1Active"></a>
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

	<div class="span-24" id="content">
		<div class="span-12" id="upload">
      <g:form controller="create" action="redneckify" 
         method="post" enctype="multipart/form-data">
          <input type="file" name="file"/>
          <g:submitButton name="submit" value="Upload"></g:submitButton>
      </g:form>
		</div>
		
		<div class="span-12 last" id="facebook">
			<g:if test="${fbPhotos != null}">
				<g:set var="numPhotos" value="${fbPhotos.size()}" />
				<g:set var="num" value="${0}" />
				<g:while test="${num < numPhotos}">
					<img src="${fbPhotos.get(num++).getPicture()}" />
				</g:while>
			</g:if>
			<g:else>
				<g:if test="${fbLoggedIn != true}">
					<h3>You're not logged into Facebook</h3>
					<fb:login-button v="2" size="medium" autologoutlink="true" perms="user_photos,friends_photos,publish_stream">Login to use your photos</fb:login-button>
				</g:if>
			</g:else>
		</div>		
	</div>
  </body>
</html>