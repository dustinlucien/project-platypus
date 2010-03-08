<html>
    <head>
        <title>Redneckify</title>
		<meta name="layout" content="main" />
    </head>
  <body>
	<div class="span-24 last">
		<div class="span-8">
			<h1>Redneckify</h1>
		</div>
		<div class="span-4">
			<h3><a href="${createLink(uri:'/')}">Redneckify Me!</a></h3>
		</div>
		<div class="span-4">
			<h3><a href="${createLink(controller:'image')}">Gallery</a></h3>
		</div>
		<div class="span-4">
			<h3><a href="${createLink(controller:'shop')}">Merchandise</a></h3>
		</div>
		<div class="span-4 last">
			<h3><a href="${createLink(uri:'/aboutus')}">About Us</a></h3>
		</div>
	</div>
	<div class="span-24 last">
		<h2>This will be the L2 nav</h2>
		<fb:login-button v="2" size="large" onlogin="window.location.reload(true);">Connect with Facebook</fb:login-button>
	</div>
	<div class="span-8">
		<h2>Left nav</h2>
		<div class="span-8 last">
			<h3>box 1</h3>
		</div>
		<div class="span-8 last">
			<h3>box 2</h3>
		</div>
		<div class="span-8 last ">
			<h3>box 3</h3>
		</div>
	</div>
	<div class="span-16 last">
		<h1>
			Main content section
		</h1>
		<h2>
			Get started <a href="${createLink(controller:'upload', action:'upload')}">here</a>
		</h2>
	</div>
  </body>
</html>