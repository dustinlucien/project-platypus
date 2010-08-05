<html>
<head>
  <title>Redneckify!</title>
  <meta name="layout" content="main" />
  <script type="text/javascript">
    function postTheImage() {
      FB.getLoginStatus(function(response) {
        if (response.session) {
          //AJAX request to post the image, then remove the button
          $.get("${createLink(controller:'share', action:'pubtofb', params : [image : image.pkey, st : stoken ])}",
            function(data){
              console.log("response form pubtofb" + data)
              $('#facebook-post-button').attr('disabled', 'true')
            });
        }
      }, {perms:'publish_stream, user_photos, friends_photos, user_photo_video_tags'});      
    }
  </script>
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

  <g:render template="/snippets/flashMessageTemplate" />

  <div class="span-24" id="content">
    <div class="span-10 prepend-1" id="leftContent">
      <img width="95%" src="${image.getImageUrl()}" />
      <fb:like href="${longUrl}" show_faces="false" />
      <p><a href="${createLink(controller:'create')}">Redneckify another pic.</a></p>
      <p><strong>Go on. Do it.</strong> You know you want to!</p>
    </div>

    <div class="span-11 prepend-1 last" id="rightContent">
      <h5>Ya happy now? Does it look good? Go and tell yer huntin' buddies!</h5>
      <ul id="icons">
        <li><button onclick="postTheImage()" id="facebook-post-button">Post this image to Facebook</button></li>
        <li><a target="_blank" href="http://twitter.com/home?status=Check out my Redneck self.  Made with @redneckify.  ${shortUrl}" id="tw">Share on Twitter!</a></li>
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
      
      <a href="">Check out more gear to Redneckify here!</a>
    </div>
  </div>
</body>
</html>