<div class="rate clear"> 
    <a href="${createLink(controller:'share', action:'show', id:image.pkey)}"><img width=100 height=100 src="${image.getImageUrl()}" /> 
    <p><strong>${image.getDisplayName()}</strong><br />by ${image.owner.getDisplayName()}</p>
    <br/>
    <fb:like href="${createLink(controller:'share', action:'show', id:image.pkey, absolute:true)}" layout="button_count" show_faces="false" />
</div>