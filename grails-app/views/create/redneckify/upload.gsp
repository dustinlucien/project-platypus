<html>
    <head>
        <title>Git yer pitcher on in there</title>
		<meta name="layout" content="main" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
  <body>
	<g:render template="/navigation/createHeaderTemplate" />

	<div id="midNav" class="span-24">
	  <a href="${createLink(controller:'create')}" class="span-10" id="subh1"></a>
	  <a href="${createLink(controller:'create')}" class="span-7" id="subh2"></a>
	  <a href="${createLink(controller:'share')}" class="span-6" id="subh3"></a>
	</div>

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