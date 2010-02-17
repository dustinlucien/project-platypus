<html>
    <head>
        <title>Upload Test</title>
		<meta name="layout" content="main" />
    </head>
    <body>
        <form action="${uploadUrl}" method="post" enctype="multipart/form-data">
            <input type="file" name="file">
            <input type="submit" value="Submit">
        <form>
    </body>
</html>