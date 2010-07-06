<html>
    <head>
        <title>Redneckify!</title>
		<meta name="layout" content="main" />
    </head>
  <body>
	<g:render template="/navigation/mainHeaderTemplate" />

	 <div class="span-24 last" id="login banner">
	 	<fb:login-button v="2" size="medium" autologoutlink="true" perms="user_photos,friends_photos,publish_stream"/>
	</div>

	<div class="span-24" id="content">
		<div class="span-7 prepend-1" id="leftContent">
		  <div class="span-7 pull-1" id="latestR"><span class="hidden">Latest Redneck</span></div> 
            <div class="rate clear"> 
                <img src="images/img1.jpg" /> 
                <p><strong>"Redneck Jim"</strong><br />by Jim Johnson</p> 
                <ul class="rating"> 
                  <li><a class="red" class="red" href="#"><span class="hidden">Rate 1</span></a></li> 
                  <li><a class="red" href="#"><span class="hidden">Rate 2</span></a></li> 
                  <li><a class="red" href="#"><span class="hidden">Rate 3</span></a></li> 
                  <li><a href="#"><span class="hidden">Rate 4</span></a></li> 
                  <li><a href="#"><span class="hidden">Rate 5</span></a></li> 
                </ul> 
            </div> 
            <div class="rate"> 
                <img src="images/img2.jpg" /> 
                <p><strong>"Redneck Jim"</strong><br />by Jim Johnson</p> 
                                <ul class="rating"> 
                  <li><a class="red" class="red" href="#"><span class="hidden">Rate 1</span></a></li> 
                  <li><a class="red" href="#"><span class="hidden">Rate 2</span></a></li> 
                  <li><a class="red" href="#"><span class="hidden">Rate 3</span></a></li> 
                  <li><a href="#"><span class="hidden">Rate 4</span></a></li> 
                  <li><a href="#"><span class="hidden">Rate 5</span></a></li> 
                </ul> 
            </div> 
            <div class="rate"> 
                <img src="images/img3.jpg" /> 
                <p><strong>"Redneck Jim"</strong><br />by Jim Johnson</p> 
                <ul class="rating"> 
                  <li><a class="red" class="red" href="#"><span class="hidden">Rate 1</span></a></li> 
                  <li><a class="red" href="#"><span class="hidden">Rate 2</span></a></li> 
                  <li><a class="red" href="#"><span class="hidden">Rate 3</span></a></li> 
                  <li><a href="#"><span class="hidden">Rate 4</span></a></li> 
                  <li><a href="#"><span class="hidden">Rate 5</span></a></li> 
                </ul> 
            </div> 
 
 
          <p><a href="#"class="blue" >See all them there rednecks</a></p> 		  
		</div>

	    <div class="span-14  prepend-1 last" id="rightContent"> 
	     <p>Already been redneckkified? Need to git at yer pic?<a href="#" class="blue"> Sign on in right here, Billy Bob</a></p> 
	     <p class="bigP">To git started redneckifyin' yer picture, upload it to the site by pushin the big button down yonder.</p> 
	     <div id="yepBubba"><span class="hidden">Yep, that one, Bubba!</span></div> 
	     <a href="${createLink(controller:'upload')}" id="goOnbtn"><span class="hidden">Go on, git yer face on in there!</span></a> 
	     <p>If y'all got a <strong>Flickr</strong> account, you can <a href="#" class="blue">grab one of yer pictures from there</a>.</p> 
	
	   </div> 
	</div>
  </body>
</html>