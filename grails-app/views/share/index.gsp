<html>
<head>
  <title>Redneckify!</title>
  <meta name="layout" content="main" />
</head>
<body>
  <div id="header" class="span-23 prepend-1">
    <a href="${createLink(controller:'home')}" class="span-7" id="logoheader"><span class="hidden">Redneckify</span></a>
    <div id="mainNav">
      <a href="${createLink(controller:'create')}" class="span-5" id="redneskifyMeActive"><span class="hidden">Redneckify Me</span></a>
      <a href="${createLink(controller:'gallery')}" class="span-3" id="gallery"><span class="hidden">Redneckify Me</span></a>
      <a href="${createLink(controller:'shop')}" class="span-3" id="merch"><span class="hidden">Redneckify Me</span></a>
      <a href="${resource(uri:'/aboutus')}" class="span-4 last" id="aboutUs"><span class="hidden">Redneckify Me</span></a>
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
      <img width=90% height=90% src="${image.getImageUrl()}" />
      <p><a href="${createLink(controller:'create')}">Redneckify another pic.</a></p>
      <p><strong>Go on. Do it.</strong> You know you want to!</p>
    </div>

    <div class="span-11 prepend-1 last" id="rightContent">
      <p>Already been redneckkified? Need to git at yer pic?<a href="#"> Sign on in right here. Billy Bob</a></p>
      <h5>Ya happy now? Does it look good? Go and tell yer huntin' buddies!</h5>
      <ul id="icons">
        <li><a href="#" id="fb"><span class="hidden">FaceBook</span></a></li>
        <li><a href="#" id="tw"></a></li>
        <li><a href="#" id="gt"></a></li>
        <li><a href="#" id="ym"></a></li>
      </ul>
      <div id="sl1" class="clear"><span class="hidden">Mugs, shirts, mouse pads - get yer redneck self on anything!</span></div>
      <div id="get">
        <img width=30% height=30% src="${image.getImageUrl()}" />
        <img width=30% height=30% src="${image.getImageUrl()}" />
        <img width=30% height=30% class="last" src="${image.getImageUrl()}" />
        <p>Mens Hoodie<br /><strong>$29</strong> | <a href="#">Git it now</a></p>
        <p>Mens Hoodie<br /><strong>$29</strong> | <a href="#">Git it now</a></p>
        <p>Mens Hoodie<br /><strong>$29</strong> | <a href="#">Git it now</a></p>
      </div>
      <a href="#" class="span-10 savebtn"><span class="hidden">Save your redneck self in the gallery</span></a>
    </div>
  </div>
</body>
</html>