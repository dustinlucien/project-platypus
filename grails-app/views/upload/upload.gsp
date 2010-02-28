<html>
    <head>
        <title>Upload Test</title>
		<meta name="layout" content="main" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
  <body> 
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