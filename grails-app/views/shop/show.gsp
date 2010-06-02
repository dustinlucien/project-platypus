<html>
    <head>
		<meta name="layout" content="main" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="title" content="title" />
		<meta name="description" content="Redneckify Me! image" />
		<meta name="medium" content="image" />
		<link rel="image_src" href="${images[0].getImageUrl()}" / >
		<!--<link rel="target_url" href=""/>-->
    </head>
  <body>
	<div class="span-24 last" id="navigation">
		<div class="span-24 last" id="l1 navigation">
			<div class="span-8">
				<h1>Redneckify</h1>
			</div>
			<div class="span-4">
				<h3><a href="${createLink(controller:'home')}">Redneckify Me!</a></h3>
			</div>
			<div class="span-4">
				<h3><a href="${createLink(controller:'gallery')}">Gallery</a></h3>
			</div>
			<div class="span-4">
				<h3><b>Merchandise</b></h3>
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
				<h2>Upload Yer Picture</h2>
			</div>
			<div class="span-6">
				<h2>Redneckify It!</h2>
			</div>
			<div class="span-6 last">
				<h2><b>Share It!</b></h2>
			</div>
		</div>
	</div>	
	<div class="span-24 last" id="wrapper">
		<div class"span-6">
			<img height="320" width="240" src="${images[0].getImageUrl()}" />
		</div>
		<div class="span-10" id="sharing">
			<fb:share-button class="url" type="icon_link"></fb:share-button>
			<a href="http://twitter.com/home?status=Just Redneckified myself here : http://redneckify.me" title="Click to share on Twitter" target="_blank">Share on Twitter</a>
		<div>
	</div>
  </body>
</html>