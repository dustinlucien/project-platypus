<g:if test="${flash.error}">
  <div class="error">
    ${flash.error}
  </div>
</g:if>

<g:if test="${flash.message}">
  <div class="notice">
    ${flash.message}
  </div>
</g:if>
