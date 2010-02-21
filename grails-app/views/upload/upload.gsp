<html>
    <head>
        <title>Upload Test</title>
		<meta name="layout" content="main" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
  <body> 
    <form action="${bucket}" method="post" enctype="multipart/form-data">
      <input type="hidden" name="key" value="${filekey}-${filename}">
      <input type="hidden" name="AWSAccessKeyId" value="${awsApiKey}"> 
      <input type="hidden" name="acl" value="private"> 
      <input type="hidden" name="success_action_redirect" value="${createLink(action:'success')}">
      <input type="hidden" name="policy" value="YOUR_POLICY_DOCUMENT_BASE64_ENCODED">
      <input type="hidden" name="signature" value="${signature}">

      File to upload to S3: 
      <input name="file" type="file"> 
      <br> 
      <input type="submit" value="Upload File to S3"> 
    </form> 
  </body>
</html>