<html>
    <head>
	<meta name="layout" content="main" />
        
        <g:javascript library="jquery" plugin="jquery"/>
        <jqui:resources/>
        <g:twitterWidgetResources />
        
		    <script type="text/javascript">
  		    function loginToFacebook() {
            FB.login(function(response) {
              if (response.session) {
                  getAlbumsToDisplay(15, 0)
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
                getAlbumsToDisplay(15,0)
              } else {
                loginToFacebook()
              }
            });
          }
          
          function buildSelectable(imgUrl, liId, liText) {                        
            var li = document.createElement('li')
            li.className = 'ui-state-default'
            
            if (liId != null) {
              li.id = liId
            }
            
            if (imgUrl != null) {
              li.style.background = "url(" + imgUrl + ")";
              li.style.backgroundRepeat = "no-repeat"
              li.style.backgroundPosition = "center"
              li.style.backgroundColor = '#FFFFFF'
            }
            
            if (liText != null) {
              li.innerHTML = liText
              li.style.fontSize = "1em"
            }
            return li
          }

          function replaceAndEnableAlbumSelectable(parent) {
            $('#facebook-photos').replaceWith(parent);
            $('#browse').remove()
            
            $(function() {
          		$('#selectable').selectable({
          		  selecting: function(event, ui) {
         		      ui.selecting.style.backgroundColor = '#FECA40';
         		    },
                selected: function(event, ui) {
                  ui.selected.style.backgroundColor = '#F39814';
                  $('#select-btn').attr('disabled', false).attr('value', ui.selected.id);
                },
                unselecting: function(event, ui) {
                  ui.unselecting.style.backgroundColor = '#FECA40';
                },
                unselected: function(event, ui) {
                  ui.unselected.style.backgroundColor = '#FFFFFF'
                }                
              });
          	});
          }
          
          function replaceAndEnableImageSelectable(parent) {
            $('#facebook-photos').replaceWith(parent);

            $(function() {
          		$('#selectable').selectable({
          		   selecting: function(event, ui) {
          		     ui.selecting.style.backgroundColor = '#FECA40';
          		   },
                 selected: function(event, ui) {
                   ui.selected.style.backgroundColor = '#F39814';
                   $('#externalfile').attr('name', 'facebookfile').attr('value', ui.selected.id);
                 },
                 unselecting: function(event, ui) {
                   ui.unselecting.style.backgroundColor = '#FECA40';
                 },
                 unselected: function(event, ui) {
                   ui.unselected.style.backgroundColor = '#FFFFFF'
                 }
              });
          	});
          }          

          function buildClosureForPhotosResponse(selectableList, album) {
              return function(photos) {
                  if (!photos || photos.error) {
                      alert('Problem with Facebook API request' + photos.error)
                  }
                  
                  if (photos.data.length == 0) {
                    return
                  }
                  
                  selectableList.appendChild(buildSelectable(photos.data[0].picture, album.id, album.name))
              };
          }


          function buildSelectablePagingContainer(fbLimit, fbOffset, enableNextButton, selectableList, getSelectableClosure) {
              var parent = document.createElement('div')
              parent.id ="facebook-photos"
              parent.className = "span-12 last"
              
              tempDiv = document.createElement('div')
              tempDiv.className = 'span-12 last'
              tempDiv.id = 'controls'         
              var prevButton = document.createElement('button')
              if ((fbOffset - fbLimit) >= 0) {
               prevButton.onclick = function(){getSelectableClosure(fbLimit, fbOffset - fbLimit);};
              } else {
                prevButton.disabled = true
              }
              prevButton.id = 'prev-btn'
              tempDiv.appendChild(prevButton)
              
              var nextButton = document.createElement('button')
              if (enableNextButton) {
                nextButton.disabled = true
              } else {
                nextButton.onclick = function(){getSelectableClosure(fbLimit, fbLimit + fbOffset);};                
              }
              nextButton.id = 'next-btn'
              tempDiv.appendChild(nextButton)
              parent.appendChild(tempDiv)
              
              tempDiv = document.createElement('div')
              tempDiv.className = 'photos'
              tempDiv.appendChild(selectableList)
              parent.appendChild(tempDiv)
              
              return parent;            
          }

          function buildAlbumSelectButton() {
            var selectButton = document.createElement('button')
            selectButton.id = 'select-btn'
            selectButton.disabled = true
            selectButton.onclick = function() {
              getImagesToDisplay(this.value, 15, 0)
            }
            
            return selectButton
          }
          
          function buildGetAlbumsToDisplayClosure() {
              return function(limit, offset) {
                  return getAlbumsToDisplay(limit, offset)
              }
          }

          function buildGetImagesToDisplayClosure(albumId) {
              return function(limit, offset) {
                  return getImagesToDisplay(albumId, limit, offset)
              }
          }
             
          function getAlbumsToDisplay(fbLimit, fbOffset) {
            FB.api('/me/albums', { limit: fbLimit, offset: fbOffset }, function(albums) {
              if (!albums || albums.error) {
                alert ("Problem with Facebook API request: " + response.error)
              }

              var selectableList = document.createElement('ul')
              selectableList.id = 'selectable'
              
              /*
              if (fbOffset == 0) {
                var taggedAlbum = new Object()
                taggedAlbum.id = 'tagged'
                taggedAlbum.name = 'Tagged Photos'
                
                FB.api('/me/photos', { limit:1, offset:0 }, buildClosureForPhotosResponse(selectableList, taggedAlbum));
              }
              */
              
              for (var i=0, l=albums.data.length; i<l; i++) {
                var album = albums.data[i]
                FB.api(album.id + '/photos', { limit:1, offset:0 }, buildClosureForPhotosResponse(selectableList, album));
              }

              var parent = buildSelectablePagingContainer(fbLimit, fbOffset, (albums.data.length < fbLimit), 
                                                          selectableList, buildGetAlbumsToDisplayClosure())
                                                          
              jQuery('#controls', parent).append(buildAlbumSelectButton())

              replaceAndEnableAlbumSelectable(parent);
            });
          }
          

          function getImagesToDisplay(albumId, fbLimit, fbOffset) {

            var apiMethod = '/' + albumId + '/photos'
            FB.api(apiMethod, { limit: fbLimit, offset: fbOffset }, function(photos) {
              if (!photos || photos.error) {
                alert ("Problem with Facebook API request: " + photos.error)
              }
              
              var selectableList = document.createElement('ul')
              selectableList.id = 'selectable'
              
              for (var i=0, l=photos.data.length; i<l; i++) {
                var photo = photos.data[i];
                
                selectableList.appendChild(buildSelectable(photo.picture, photo.id, null))
              }
              
              var parent = buildSelectablePagingContainer(fbLimit, fbOffset, (photos.data.length < fbLimit),
                                                          selectableList, buildGetImagesToDisplayClosure(albumId));
              
            	replaceAndEnableImageSelectable(parent)
            });
          }

          function submitForm() {
              document.uploadForm.submit();
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
	  
	<div class="span-24" id="content">
    <g:render template="/snippets/flashMessageTemplate" bean="${flash}"/>
		<div class="span-7 prepend-1" id="leftContent">
		  <div class="span-7 pull-1" id="latestR">
		    <span class="hidden">Latest Rednecks</span>
		  </div>  
		  <g:render template="/snippets/redneckThumbnailTemplate" var="image" collection="${images}" />
      <div class="span-7 last">
        <p><a href="${createLink(controller:'gallery')}" class="blue" >See all them there rednecks</a></p>
      </div>		  
		</div>
		
	  <div class="span-14 prepend-1 last" id="rightContent">
	    <p>
	      Already been redneckified? Need to git at yer pic? <a href="${createLink(controller:'shop')}"
        class="blue">Head on over to the shop!</a>
	    </p>

	    <h2>
	      To git started redneckifyin' yer picture, upload it to the site by pickin' a photo from the
        Facebook or yer hard drive, then push the big brown button down yonder.
	    </h2> 
      
      <div class="span-12 last">
         <div id="facebook-photos" class="span-3"><button id="facebook" onclick="getLoginStatus()"></button></div>
         <g:form controller="create" action="redneckify" name="uploadForm" method="post" enctype="multipart/form-data">
           <div class="span-8 last inline" id="browse">
             <div class="span-1" style="font-size: 2em; margin-left: 20px; margin-right:20px;">or</div>
             <div class="span-6 last" id="file-input">
               <input id="file" type="file" name="file"/>
             </div>
           </div>
           <input type="hidden" id="externalfile" name="facebookfile" />
	         <div class="span-12 last">
	           <div id="yepBubba">
	             <span class="hidden">Yep, that one, Bubba!</span>
	           </div>
	           <g:submitButton id="goOnbtn" name="submit" value=""></g:submitButton>
	         </div>           
         </g:form>
     	</div>
	   </div>	  
	 </div>
  </body>
</html>
