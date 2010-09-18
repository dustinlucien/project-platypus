<div class="span-6 rate"> 
    <a href="${createLink(controller:'share', action:'show', id:image.pkey)}"><img width=100 height=100 src="${image.getImageUrl()}" /></a>
    <p><a href="${createLink(controller:'share', action:'show', id:image.pkey)}"><strong>${image.getDisplayName()}</strong></a><br />by ${image.owner.getDisplayName()}</p>
    <div class="span-3 social-btn">
      <div class="span-2 fb-like-btn">
        <fb:like href="${createLink(controller:'share', action:'show', id:image.pkey, absolute:true)}" layout="button_count" show_faces="false" />
      </div>
      <div class="span-1 tweet-btn">
        <a href="http://twitter.com/share" class="twitter-share-button" data-url="${createLink(controller:'share', action:'show', id:image.pkey, absolute:true)}" data-text="Git a load of this!" data-count="none" data-via="${grailsApplication.config.twitter.username}">Tweet</a>
      </div>
    </div>
</div>