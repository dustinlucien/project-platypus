<html>
    <head>
        <title>Redneckify</title>
		<meta name="layout" content="main" />
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
				<h3><a href="${createLink(uri:'/aboutus')}">About Us</a></h3>
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
	<div class="span-8" id="mini gallery">
		<h2>Recent Rednecks</h2>
		<div class="span-8 last">
			<h3>Image 1</h3>
		</div>
		<div class="span-8 last">
			<h3>Image 2</h3>
		</div>
		<div class="span-8 last ">
			<h3>Image 3</h3>
		</div>
	</div>
	<div class="span-16 last" id="main">
		<h1>
			Main content section
		</h1>
		<h2>
			Git 'er done <a href="${createLink(controller:'upload', action:'upload')}">here</a>
		</h2>
	</div>
  </body>
</html>