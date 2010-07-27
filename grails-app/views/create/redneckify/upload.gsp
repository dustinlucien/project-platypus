<html>
    <head>
        <title>Git yer pitcher on in there</title>
		    <meta name="layout" content="main" />
		    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		    
		    <script type="text/javascript">
  		    function loginToFacebook() {
            FB.login(function(response) {
              if (response.session) {
                if (response.perms) {
                  // user is logged in and granted some permissions.
                  // perms is a comma separated list of granted permissions
                } else {
                  // user is logged in, but did not grant any permissions
                }
              } else {
                // user is not logged in
              }
            }, {perms:'user_photos, friends_photos, user_photo_video_tags'});
          }

          function logoutOfFacebook() {
             FB.logout(function(response) {
             });
          }
          
          function getLoginStatus() {
            FB.getLoginStatus(function(response) {
              if (response.session) {
                getImagesToDisplay();
              } else {
                FB.Event.subscribe('auth.login', getImagesToDisplay());
                loginToFacebook()
              }
            });
          }

          
          function getImagesToDisplay() {
            FB.api('/me/photos', { limit: 5 }, function(response) {
              for (var i=0, l=response.length; i<l; i++) {
                var photo = response[i];
                if (photo.picture) {
                  alert('Photo: ' + photo.picture);
                } else {
                  alert('No picture in the downloaded photo');
                }
              }
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
      <a href="${createLink(controller:'create')}" class="span-10" id="subh1Active"></a>
      <a href="${createLink(controller:'create')}" class="span-7" id="subh2"></a>
      <a href="${createLink(controller:'share')}" class="span-6" id="subh3"></a>
    </div>

    <g:render template="/snippets/flashMessageTemplate" />

	<div class="span-24" id="content">
		<div class="span-7 prepend-1" id="leftContent">
		  <div class="span-7 pull-1" id="latestR"><span class="hidden">Latest Rednecks</span></div>
	      
		      <g:render template="/snippets/rateableImageThumbnailTemplate" var="image" collection="${images}" />
	      
          <p><a href="${createLink(controller:'gallery')}"class="blue" >See all them there rednecks</a></p> 		  
		</div>

	  <div class="span-14  prepend-1 last" id="rightContent"> 

	     <!--
	     <p>Already been redneckkified? Need to git at yer pic?<a href="#" class="blue"> Sign on in right here, Billy Bob</a></p>
	     -->
	    <p class="bigP">To git started redneckifyin' yer picture, upload it to the site by pushin the big button down yonder.</p> 
   		<div class="span-12 last" id="upload">
   		  <button onClick="getLoginStatus()">Find Images on Facebook</button>
   		  <button onClick="logoutOfFacebook()">Logout</button>
         <g:form controller="create" action="redneckify" 
            method="post" enctype="multipart/form-data">
             <input type="file" name="file"/>
      	     <div id="yepBubba"><span class="hidden">Yep, that one, Bubba!</span></div>             
             <g:submitButton id="goOnbtn" name="submit" value=""></g:submitButton>
         </g:form>
   		</div>
	   </div>	  
	</div>
  </body>
</html>