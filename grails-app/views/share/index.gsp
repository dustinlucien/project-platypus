<html>
<head>
  <title>Redneckify!</title>
  <meta name="layout" content="main" />
  <g:javascript library="jquery" plugin="jquery"/>
  <g:twitterAnywhereResources />
  <script type="text/javascript">
    function loginToFacebook() {
      FB.login(function(response) {
        if (response.session) {
          if (response.perms) {
            postTheImage()
          }
        }
      }, {perms:'publish_stream'});
    }
    
    function getLoginStatus() {
      FB.getLoginStatus(function(response) {
          if (response.session) {
            //checkExistingPerms()
            postTheImage()
          } else {
            loginToFacebook()
          }
        });
      }
    
    function postTheImage() {
      $.get("${createLink(controller:'share', action:'sajaxpubtofb', params : [image : image.pkey, st : flash.stoken])}",
        function(data){
          console.log("response form pubtofb" + data)
          $('#facebook-post-button').attr('disabled', 'true')
        });
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
  <div class="span-24" id="content">
    <g:render template="/snippets/flashMessageTemplate" />
    <div class="span-10 prepend-1" id="leftContent">
      <g:render template="/snippets/woodFrameImageTemplate" bean="${image}" />
      <div class="margin-top-10">
        <fb:like href="${longUrl}" show_faces="false" />
      </div>
      <div class="margin-top-10">
        <p><a href="${createLink(controller:'create')}">Redneckify another pic</a>
        <br/><strong>Go on. Do it.</strong> You know you want to!</p>
      </div>
    </div>

    <div class="span-10 prepend-2 last" id="rightContent">
      <h5>Ya happy now? Does it look good? Go and tell yer huntin' buddies!</h5>
      <ul id="icons">
        <li><h5><span>Save this image to </span><button onclick="postTheImage()" id="facebook" /></h5></li>
        <li><span id="tweet-box"></span></li>
        <li><span id="follow-placeholder"></span></li>
      </ul>
      
      <g:render template="/snippets/getYerRedneckOnAnythingTemplate" />
          
	    <div class="span-11 last" id="get">
	      <g:renderZazzleProductList image="${image}" />
	    </div>
      <g:render template="/snippets/zazzleStoreTemplate" />
      
    </div>
  </div>
  <script type="text/javascript">

    twttr.anywhere(function (T) {
      T("#tweet-box").tweetBox({
         height: 100,
         width: 400,
         defaultContent: "Check out my Redneck self.  Made with @redneckify.  ${shortUrl} ",
         label: "Shout out to all them tweeters"
       });
      
      T("#follow-placeholder").followButton("${grailsApplication.config.twitter.username}");
    });
    
  </script>
</body>
</html>