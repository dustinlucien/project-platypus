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
			<g:if test="${fbLoggedIn != true }">
				<fb:login-button v="2" size="medium" autologoutlink="true" onlogin="window.location.reload(true);" />
			</g:if>
			<g:else>
				<div class"span-8 last">
					<g:if test="${fbImages != null}">
						
					</g:if>
					<g:else>
						<p>No Facebook Images</p>
					</g:else>
				</div>
			</g:else>			
		</div>
	</div>

    <form action="http://${bucket}.s3.amazonaws.com" method="post" enctype="multipart/form-data">
      <input type="hidden" name="key" value="${key}">
      <input type="hidden" name="AWSAccessKeyId" value="${apiKey}"> 
      <input type="hidden" name="acl" value="public-read"> 
      <input type="hidden" name="success_action_redirect" value="${createLink(action:'success', absolute : true)}">
      <input type="hidden" name="policy" value="${policyBase64}">
      <input type="hidden" name="signature" value="${signature}">

      File to upload to S3: 
      <input name="file" type="file"> 
      <br> 
      <input type="submit" value="Upload File to S3"> 
    </form> 
  </body>
</html>