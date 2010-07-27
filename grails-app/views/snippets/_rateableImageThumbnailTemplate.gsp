<div class="rate clear"> 
    <a href="${createLink(controller:'share', action:'show', id:image.pkey)}"><img width=100 height=100 src="${image.getImageUrl()}" /> 
    <p><strong>${image.getDisplayName()}</strong><br />by ${image.owner.getDisplayName()}</p>
    <ul class="rating"> 
      <li><a class="red" href="#"><span class="hidden">Rate 1</span></a></li> 
      <li><a class="red" href="#"><span class="hidden">Rate 2</span></a></li> 
      <li><a class="red" href="#"><span class="hidden">Rate 3</span></a></li> 
      <li><a href="#"><span class="hidden">Rate 4</span></a></li> 
      <li><a href="#"><span class="hidden">Rate 5</span></a></li>
    </ul>
    <br/>
    <fb:like href="${createLink(controller:'share', action:'show', id:image.pkey, absolute:true)}" layout="button_count" show_faces="false" />
</div>

