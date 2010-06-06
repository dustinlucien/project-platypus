<html>
    <head>
        <title>Upload Test</title>
		<meta name="layout" content="main" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
  <body>
    <div id="header" class="span-23 prepend-1">
      <a href="${createLink(controller:'home')}" class="span-7" id="logoheader"><span class="hidden">Redneckify</span></a>

      <div id="mainNav">
        <a href="${createLink(controller:'home')}" class="span-5" id="redneskifyMe"><span class="hidden">Redneckify Me</span></a>
        <a href="${createLink(controller:'gallery')}" class="span-3" id="gallery"><span class="hidden">Redneckify Me</span></a>
        <a href="${createLink(controller:'shop')}" class="span-3" id="merch"><span class="hidden">Redneckify Me</span></a>
        <a href="${resource(uri:'/aboutus')}" class="span-4 last" id="aboutUs"><span class="hidden">Redneckify Me</span></a>
      </div>

    </div>

    <div id="midNav" class="span-24">
      <a href="#" class="span-10" id="subh1"></a>
      <a href="#" class="span-7" id="subh2"></a>
      <a href="#" class="span-6" id="subh3"></a>
    </div>

	<div class="span-24" id="content">
		<div class="span-12" id="upload">
		    <form action="http://${bucket}.s3.amazonaws.com" method="post" enctype="multipart/form-data">
		      <input type="hidden" name="key" value="${chiave}">
		      <input type="hidden" name="AWSAccessKeyId" value="${apiKey}"> 
		      <input type="hidden" name="acl" value="public-read"> 
		      <input type="hidden" name="success_action_redirect" value="${createLink(action:'success', absolute : true)}">
		      <input type="hidden" name="policy" value="${policyBase64}">
		      <input type="hidden" name="signature" value="${signature}">

		      <h3>Upload a file from locally:</h3> 
		      <input name="file" type="file"> 
		      <br> 
		      <input type="submit" value="Upload File to S3"> 
		    </form>
		</div>
		<div class="span-12 last" id="facebook">
			<g:if test="${fbPhotos != null}">
				<g:set var="numPhotos" value="${fbPhotos.length()}" />
				<g:set var="num" value="${0}" />
				<g:while test="${num < numPhotos}">
					<img src="${fbPhotos.get(num++).src}" />
				</g:while>
			</g:if>
			<g:else>
				<g:if test="${!fbLoggedIn}">
					<h3>You're not logged into Facebook</h3>
					<fb:login-button v="2" size="medium" autologoutlink="true" perms="user_photos">Login to use your photos</fb:login-button>
				</g:if>
			</g:else>
		</div>
	</div>
  </body>
</html>