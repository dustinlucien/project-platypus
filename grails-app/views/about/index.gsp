<html>
    <head>
		<meta name="layout" content="main" />
		<!--<link rel="target_url" href=""/>-->
		<script type="text/javascript" src="http://platform.twitter.com/anywhere.js?id=${grailsApplication.config.twitter.apiKey}&v=1" ></script>
    
    </head>
  <body>
    <div id="header" class="span-23 prepend-1">
      <a href="${createLink(controller:'create')}" class="span-7" id="logoheader"><span class="hidden">Redneckify</span></a>
      <div id="mainNav">
        <a href="${createLink(controller:'create')}" class="span-5" id="redneskifyMe"><span class="hidden">Redneckify Me</span></a>
        <a href="${createLink(controller:'gallery')}" class="span-3" id="gallery"><span class="hidden">Redneckify Me</span></a>
        <a href="${createLink(controller:'shop')}" class="span-3" id="merch"><span class="hidden">Redneckify Me</span></a>
        <a href="${createLink(controller:'about')}" class="span-4 last" id="aboutUsActive"><span class="hidden">Redneckify Me</span></a>
      </div>
    </div>

    <div id="midNav" class="span-24">
      <a href="${createLink(controller:'create')}" class="span-10" id="subh1"></a>
      <a href="${createLink(controller:'create')}" class="span-7" id="subh2"></a>
      <a href="${createLink(controller:'share')}" class="span-6" id="subh3"></a>
    </div>

    <g:if test="${flash.error}">
      <div class="error">
        ${flash.error}
      </div>
    </g:if>

    <g:if test="${flash.message}">
      <div class="message">
        ${flash.message}
      </div>
    </g:if>
    
  	<div class="span-24" id="content">
  		<div class="span-7 prepend-1" id="leftContent">
  		</div>

  	  <div class="span-14  prepend-1 last" id="rightContent">
  	    <p>Redneckify.me was put together by a trio of fun-lovin' country boys. The intent was always to have fun and never to offend. If ya got somethin' to say, good or bad, don't hold back. Send us one of them there emails and we'll be sure to get back to you faster than a '72 Ford burnin' through a gallon of gas.</p>

        <p><a href="mailto:redneckify@gmail.com">Git in touch on the email</a></p>
  	    <p><span id="follow-placeholder"></span></p>
  	    <p>Head on over to the Redneckify! Facebook Page</p>
  	  </div>
  	</div>
   <script type="text/javascript">

     twttr.anywhere(function (T) {
       T("#follow-placeholder").followButton("${grailsApplication.config.twitter.username}");
     });

   </script>     
  </body>
</html>