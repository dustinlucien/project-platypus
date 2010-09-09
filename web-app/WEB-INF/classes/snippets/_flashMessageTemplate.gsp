<g:if test="${flash.error}">
  <div class="error">
    <h5>${flash.error}</h5>
  </div>
</g:if>

<g:if test="${flash.message}">
  <div class="notice">
    <h5>${flash.message}</h5>
  </div>
</g:if>
