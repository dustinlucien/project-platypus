<div class="span-6 rate"> 
    <a href="${createLink(controller:'share', action:'show', id:image.pkey)}"><img width=100 src="${image.getImageUrl()}" /></a>
    <p><a href="${createLink(controller:'share', action:'show', id:image.pkey)}"><strong>${image.getDisplayName()}</strong></a><br />by ${image.owner.getDisplayName()}</p>
    <div class="span-3 social-btn">
      <div class="span-2 fb-like-btn">
        <fb:like href="${createLinkToShare(pkey:image.pkey)}" layout="button_count" show_faces="false" />
      </div>
      <div class="span-2 tweet-btn">
        <a href="http://twitter.com/share" class="twitter-share-button" data-url="${createLinkToShare(pkey:image.pkey)}" data-text="Git a load of this!" data-count="horizontal" data-via="${grailsApplication.config.twitter.username}">Tweet</a>
      </div>
    </div>
</div>