<html>
<head>
  <title>Redneckify!</title>
  <meta name="layout" content="main" />
  <title>${image.getDisplayName()}</title>
  <meta property="og:title" content="${image.getDisplayName()}"/>
  <meta property="og:type" content="article"/>
  <meta property="og:url" content="${longUrl}"/>
  <meta property="og:image" content="${image.getImageUrl()}"/>
  <meta property="og:site_name" content="Redneckify Me!"/>
  <meta property="fb:app_id" content="${facebookAppId}"/>
  <g:javascript library="jquery" plugin="jquery"/>
  <g:twitterAnywhereResources />  
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
  <div class="span-24" id="content">
    <g:render template="/snippets/flashMessageTemplate" />
    <div class="span-10 prepend-1">
      <div>
        <img width="400" src="${image.getImageUrl()}" />
      </div>
      <div>
          <fb:like href="${longUrl}" show_faces="false" />
      </div>
    </div>

    <div class="span-11 prepend-1 last" id="rightContent">
      <h5>Let the whole trailer park know you found a picture of your long lost cousin!</h5>
      <ul id="icons">
        <li><span id="tweet-box"></span></li>
        <li><span id="follow-placeholder"></span></li>
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
  <script type="text/javascript">
    twttr.anywhere(function (T) {
      T("#tweet-box").tweetBox({
         height: 100,
         width: 350,
         defaultContent: "Y'all need to see this picture on @redneckify.  ${shortUrl}",
         label: "Git it on with the lil' birdie"
       });
      
      T("#follow-placeholder").followButton("${grailsApplication.config.twitter.username}");
    });
    
  </script>
</body>
</html>