<html>
    <head>
        <title>Redneckify!</title>
		<meta name="layout" content="main" />
    </head>
  <body>
	<g:render template="/navigation/mainHeaderTemplate" />

	 <div class="span-24 last" id="login banner">
	 	<fb:login-button v="2" size="medium" autologoutlink="true" perms="user_photos,friends_photos,publish_stream"/>
	</div>

	<div class="span-24" id="content">
		<div class="span-7 prepend-1" id="leftContent">
			<h2>Recent Rednecks</h2>
			<g:if test="${images != null && images.size() > 0}">
				<g:each in="${images}" var="image">
					<div class="pics">
						<div class="span-7 last">
							<img height="120" width="80" src="${image.getImageUrl()}" />
						</div>
					</div>
				</g:each>
			</g:if>
			<g:else>
				<div class"span-7 last">
					<p>No images uploaded yet</p>
				</div>
			</g:else>
		</div>

        <div class="span-14  prepend-1 last" id="rightContent">
          <p>Already been redneckkified? Need to git at yer pic?<a href="#" class="blue"> Sign on in right here, Billy Bob</a></p>
		  <p>Git 'er done <a href="${createLink(controller:'upload')}" class="blue">here</a></p>
        </div>
	</div>
  </body>
</html>