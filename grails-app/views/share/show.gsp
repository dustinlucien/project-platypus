<html>
<head>
  <title>Redneckify!</title>
  <meta name="layout" content="main" />
</head>
<body>
  <div id="header" class="span-23 prepend-1">
    <a href="${createLink(controller:'create')}" class="span-7" id="logoheader"><span class="hidden">Redneckify</span></a>
    <div id="mainNav">
      <a href="${createLink(controller:'create')}" class="span-5" id="redneskifyMeActive"><span class="hidden">Redneckify Me</span></a>
      <a href="${createLink(controller:'gallery')}" class="span-3" id="gallery"><span class="hidden">Redneckify Me</span></a>
      <a href="${createLink(controller:'shop')}" class="span-3" id="merch"><span class="hidden">Redneckify Me</span></a>
      <a href="${createLink(controller:'about')}" class="span-4 last" id="aboutUs"><span class="hidden">Redneckify Me</span></a>
    </div>
  </div>

  <div id="midNav" class="span-24">
    <a href="${createLink(controller:'create')}" class="span-10" id="subh1"></a>
    <a href="${createLink(controller:'create')}" class="span-7" id="subh2"></a>
    <a href="${createLink(controller:'share')}" class="span-6" id="subh3Active"></a>
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
    <div class="span-10 prepend-1">
      <img width="80%" src="${image.getImageUrl()}" />
      <fb:like href="${longUrl}" show_faces="false" />
    </div>

    <div class="span-11 prepend-1 last" id="rightContent">
      <p>Already been redneckkified? Need to git at yer pic?<a href="#"> Sign on in right here. Billy Bob</a></p>
      <h5>Ya happy now? Does it look good? Go and tell yer huntin' buddies!</h5>
      <ul id="icons">
        <li><fb:like href="${shareUrl}" show_faces="false" /></li>
        <li><a target="_blank" href="http://twitter.com/home?status=Check this Redneck I found!  Made with @redneckify.  ${shortUrl}" id="tw">Share on Twitter!</a></li>
      </ul>
      <div id="sl1" class="clear"><span class="hidden">Mugs, shirts, mouse pads - get yer redneck self on anything!</span></div>
      <div id="get">
        <a target="_blank" href="http://www.zazzle.com/api/create/at-238983239304996873?rf=238983239304996873&ax=Linkover&pd=168040314455684141&fwd=ProductPage&ed=true&image1=${image.getImageUrl()}"><img width="25%" src="${image.getImageUrl()}" /></a>
        <a target="_blank" href="http://www.zazzle.com/api/create/at-238983239304996873?rf=238983239304996873&ax=Linkover&pd=168040314455684141&fwd=ProductPage&ed=true&image1=${image.getImageUrl()}"><img width="25%" src="${image.getImageUrl()}" /></a>
        <a target="_blank" href="http://www.zazzle.com/api/create/at-238983239304996873?rf=238983239304996873&ax=Linkover&pd=168040314455684141&fwd=ProductPage&ed=true&image1=${image.getImageUrl()}"><img width="25%" class="last" src="${image.getImageUrl()}" /></a>
        <p>Mens Hoodie<br /><strong>$29</strong> | <a target="_blank" href="http://www.zazzle.com/api/create/at-238983239304996873?rf=238983239304996873&ax=Linkover&pd=168040314455684141&fwd=ProductPage&ed=true&image1=${image.getImageUrl()}">Git it now</a></p>
        <p>Mens Hoodie<br /><strong>$29</strong> | <a target="_blank" href="http://www.zazzle.com/api/create/at-238983239304996873?rf=238983239304996873&ax=Linkover&pd=168040314455684141&fwd=ProductPage&ed=true&image1=${image.getImageUrl()}">Git it now</a></p>
        <p>Mens Hoodie<br /><strong>$29</strong> | <a target="_blank" href="http://www.zazzle.com/api/create/at-238983239304996873?rf=238983239304996873&ax=Linkover&pd=168040314455684141&fwd=ProductPage&ed=true&image1=${image.getImageUrl()}">Git it now</a></p>
      </div>
    </div>
  </div>
</body>
</html>