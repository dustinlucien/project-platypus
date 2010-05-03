<html>
    <head>
        <title>Upload Test</title>
		<meta name="layout" content="main" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
  <body>
	<div class="span-24 last" id="navigation">
		<div class="span-24 last" id="l1 navigation">
			<div class="span-8">
				<h1>Redneckify</h1>
			</div>
			<div class="span-4">
				<h3><b>Redneckify Me!</b></h3>
			</div>
			<div class="span-4">
				<h3><a href="${createLink(controller:'gallery')}">Gallery</a></h3>
			</div>
			<div class="span-4">
				<h3><a href="${createLink(controller:'shop')}">Merchandise</a></h3>
			</div>
			<div class="span-4 last">
				<h3><a href="${resource(uri:'/aboutus')}">About Us</a></h3>
			</div>
		</div>
		<div class="span-24 last" id="l2 navigation">
			<div class="span-6">
				<h2>Empty Space</h2>
			</div>
			<div class="span-6">
				<h2><b>Upload Yer Picture</b></h2>
			</div>
			<div class="span-6">
				<h2>Redneckify It!</h2>
			</div>
			<div class="span-6 last">
				<h2>Share It!</h2>
			</div>
		</div>
		<div class="span-24 last" id="login banner">
				<fb:login-button v="2" size="medium" autologoutlink="true" onlogin="window.location.reload(true);" />
		</div>
	</div>

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
				<fb:login-button v="2" size="medium" autologoutlink="true" onlogin="window.location.reload(true);">Login to use your photos</fb:login-button>
			</g:if>
		</g:else>
	</div>
  </body>
</html>