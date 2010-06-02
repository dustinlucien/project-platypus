<html>
    <head>
        <title>Redneckify!</title>
		<meta name="layout" content="main" />
    </head>
  <body>
	
      <div id="header" class="span-23 prepend-1">
        <a href="#" class="span-7" id="logoheader"><span class="hidden">Redneckify</span></a>

        <div id="mainNav">
          <a href="${createLink(controller:'home')}" class="span-5" id="redneskifyMe"><span class="hidden">Redneckify Me</span></a>
          <a href="${createLink(controller:'gallery')}" class="span-3" id="gallery"><span class="hidden">Redneckify Me</span></a>
          <a href="${createLink(controller:'shop')}" class="span-3" id="merch"><span class="hidden">Redneckify Me</span></a>
          <a href="${resource(uri:'/aboutus')}" class="span-4 last" id="aboutUs"><span class="hidden">Redneckify Me</span></a>
        </div>

      </div>
		
      <div id="midNav" class="span-24">
        <a href="#" class="span-10" id="subh1"></a>
        <a href="#" class="span-7" id="subh2"></a>
        <a href="#" class="span-6" id="subh3"></a>
      </div>
		
	<div class="span-24 last" id="login banner">
		<fb:login-button v="2" size="medium" autologoutlink="true" perms="user_photos"/>
	</div>


	<div id="content" class="span-24">
		<div class="span-7 prepend-1" id="leftContent">
			<h2>Recent Rednecks</h2>
			<g:if test="${images != null && images.size() > 0}">
				<g:each in="${images}" var="image">
					<div class="pics">
						<div class="span-8 last">
							<img height="120" width="80" src="${image.getImageUrl()}" />
						</div>
					</div>
				</g:each>
			</g:if>
			<g:else>
				<div class"span-8 last">
					<p>No images uploaded yet</p>
				</div>
			</g:else>
		</div>

        <div class="span-14  prepend-1 last" id="rightContent">
          <p>Already been redneckkified? Need to git at yer pic?<a href="#" class="blue"> Sign on in right here, Billy Bob</a></p>
		  <p>Git 'er done <a href="${createLink(controller:'upload', action:'upload')}" class="blue">here</a></p>
        </div>
	</div>
  </body>
</html>