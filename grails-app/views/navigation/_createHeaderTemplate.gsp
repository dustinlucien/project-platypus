<div id="header" class="span-23 prepend-1">
  <a href="${createLink(controller:'home')}" class="span-7" id="logoheader"><span class="hidden">Redneckify</span></a>

  <div id="mainNav">
    <a href="${createLink(controller:'create')}" class="span-5" id="redneskifyMeActive"><span class="hidden">Redneckify Me</span></a>
    <a href="${createLink(controller:'gallery')}" class="span-3" id="gallery"><span class="hidden">Redneckify Me</span></a>
    <a href="${createLink(controller:'shop')}" class="span-3" id="merch"><span class="hidden">Redneckify Me</span></a>
    <a href="${resource(uri:'/aboutus')}" class="span-4 last" id="aboutUs"><span class="hidden">Redneckify Me</span></a>
  </div>
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
