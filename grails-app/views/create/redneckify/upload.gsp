<html>
    <head>
        <title>Git Yer Pitcher On In There</title>
		    <meta name="layout" content="main" />
		    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		    
		    <script type="text/javascript">
  		    function loginToFacebook() {
            FB.login(function(response) {
              if (response.session) {
                if (response.perms) {
                  getImagesToDisplay(15, 0)
                }
              }
            }, {perms:'user_photos, friends_photos, user_photo_video_tags'});
          }

          function logoutOfFacebook() {
             FB.logout(function(response) {
               window.location.reload()
             });
          }
          
          function getLoginStatus() {
            FB.getLoginStatus(function(response) {
              if (response.session) {
                //getImagesToDisplay(15, 0);
                getAlbumsToDisplay(15,0)
              } else {
                loginToFacebook()
              }
            });
          }
          
          function buildSelectable(imgUrl, liId, text) {
            //var e = document.createElement('img')
            //e.src = imgUrl
                        
            var li = document.createElement('li')
            li.className = 'ui-state-default'
            li.id = liId
            
            li.style.background = "url(" + imgUrl + ")";
            li.style.backgroundRepeat = "no-repeat"
            li.style.backgroundColor = "#FFFFFF"
            li.style.backgroundPosition = "center"
            li.style.fontSize = "1em"
            
            if (text != null) {
              li.innerHTML = text
            }
            
            //li.appendChild(e)
            
            return li
          }

          function buildClosureForPhotosResponse(selectableList, album) {
              return function(photos) {
                  if (!photos || photos.error) {
                      alert('Problem with Facebook API request' + photos.error)
                  }
                  selectableList.appendChild(buildSelectable(photos.data[0].picture, album.id, album.name))
              };
          }

          function buildImagesPagingContainer(fbLimit, fbOffset, selectableList, getSelectableClosure) {
              var parent = document.createElement('div')
              parent.id ="facebook-photos"
              parent.className = "span-12 last"
              
              var tempDiv = document.createElement('div')
              tempDiv.className = 'photos'
              
              tempDiv.appendChild(selectableList)

              parent.appendChild(tempDiv)
              
              var prevButton = document.createElement('button')
              if ((fbOffset - fbLimit) >= 0) {
               prevButton.onclick = function(){getSelectableClosure(fbLimit, fbOffset - fbLimit);};
              } else {
                prevButton.disabled = 'true'
              }
              
              prevButton.innerHTML = '<< Previous'
              
              tempDiv = document.createElement('div')
              tempDiv.className = 'span-12 last'
              tempDiv.id = 'buttons'
              
              tempDiv.appendChild(prevButton)
              
              var nextButton = document.createElement('button')
              if (response.data.length < fbLimit) {
                nextButton.disabled = 'true'
              } else {
                nextButton.onclick = function(){getSelectableClosure(fbLimit, fbLimit + fbOffset);};                
              }
              nextButton.innerHTML = 'Next >>'
              
              tempDiv.appendChild(nextButton)
              
              parent.appendChild(tempDiv)              
          }

          function buildAlbumsToDisplayClosure() {
              return function(limit, offset) {
                  return getAlbumsToDisplay(limit, offset)
              }
          }

          function buildImagesToDisplayClosure() {
              return function(limit, offset) {
                  return getImagesToDisplay(limit, offset)
              }
          }
             
          function getAlbumsToDisplay(fbLimit, fbOffset) {
            FB.api('/me/albums', { limit: fbLimit, offset: fbOffset }, function(albums) {
              if (!albums || albums.error) {
                alert ("Problem with Facebook API request: " + response.error)
              }

              var selectableList = document.createElement('ul')
              selectableList.id = 'selectable'
              
              //if (fbOffset == 0) {
                //buildSelectable()
              //}
              
              for (var i=0, l=albums.data.length; i<l; i++) {
                var album = albums.data[i];
                FB.api(album.id + '/photos', { limit:1, offset:0 }, buildClosureForPhotosResponse(selectableList, album));
              }
              
              var parent = buildImagesPagingContainer(fbLimit, fbOffset, selectableList, buildGetAlbumsToDisplayClosure())
              
              $('#facebook-photos').replaceWith(parent);
              
              $(function() {
            		$('#selectable').selectable({
                   selected: function(event, ui) {
                     $('#externalfile').attr('name', 'facebookfile').attr('value', ui.selected.id);
                   }
                });
            	});
            });
          }
          

          function getImagesToDisplay(fbLimit, fbOffset) {           
            FB.api('/me/photos', { limit: fbLimit, offset: fbOffset }, function(response) {
              if (!response || response.error) {
                alert ("Problem with Facebook API request: " + response.error)
              }
              
              var selectableList = document.createElement('ul')
              selectableList.id = 'selectable'
              
              for (var i=0, l=response.data.length; i<l; i++) {
                var photo = response.data[i];
                
                selectableList.appendChild(buildSelectable(photo.picture, photo.id, null))
              }
              
              var parent = buildImagesPagingContainer(fbLimit, fbOffset, selectableList, buildGetImagesToDisplayClosure());
              
              $('#facebook-photos').replaceWith(parent);
              
              $(function() {
            		$('#selectable').selectable({
                   selected: function(event, ui) {
                     $('#externalfile').attr('name', 'facebookfile').attr('value', ui.selected.id);
                   }
                });
            	});
            	
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
		  <div class="span-7 pull-1" id="latestR">
		    <span class="hidden">Latest Rednecks</span>
		  </div>  
		  <g:render template="/snippets/rateableImageThumbnailTemplate" var="image" collection="${images}" />
      <p><a href="${createLink(controller:'gallery')}"class="blue" >See all them there rednecks</a></p> 		  
		</div>
		
	  <div class="span-14  prepend-1 last" id="rightContent">
	     <!--
	     <p>Already been redneckkified? Need to git at yer pic?<a href="#" class="blue"> Sign on in right here, Billy Bob</a></p>
	     -->
	    <p class="bigP">To git started redneckifyin' yer picture, upload it to the site by pushin the big button down yonder.</p> 
         
      <div id="facebook-photos" class="span-12 last">
        <button onclick="getLoginStatus()">Find Images on Facebook</button>
      </div>
         
   		<div class="span-12 last" id="upload">
         <g:form controller="create" action="redneckify" method="post" enctype="multipart/form-data">
             <input type="file" name="file"/>
             <input type="hidden" id="externalfile" name="externalfile" />
      	     <div id="yepBubba"><span class="hidden">Yep, that one, Bubba!</span></div>             
             <g:submitButton id="goOnbtn" name="submit" value=""></g:submitButton>
         </g:form>
   		</div>
	   </div>	  
	</div>
  </body>
</html>