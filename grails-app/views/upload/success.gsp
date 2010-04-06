<html>
    <head>
        <title>Image Upload Success</title>
		<meta name="layout" content="main" />
    </head>
  <body>
	Successfully uploaded an image to Amazon S3
	<a href="${image.getImageUrl()}">${image.getImageUrl()}</a>
	<img src="${image.getImageUrl()}" />
  </body>
</html>