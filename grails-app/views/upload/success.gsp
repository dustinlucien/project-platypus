<html>
    <head>
        <title>Image Upload Success</title>
		<meta name="layout" content="main" />
    </head>
  <body>
	Successfully upload an image to Amazon S3
	<a href="http://${image.bucket}.s3.amazonaws.com/${image.key}">http://${image.bucket}.s3.amazonaws.com/${image.key}</a>
	<img src="http://${image.bucket}.s3.amazonaws.com/${image.key}" />
  </body>
</html>