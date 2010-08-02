<html>
<head>
	<title>You Got A Purty Mouth</title>
	<meta name="layout" content="main" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
	<!-- Include CSS to eliminate any default margins/padding and set the height of the html element and 
	the body element to 100%, because Firefox, or any Gecko based browser, interprets percentage as 
	the percentage of the height of its parent container, which has to be set explicitly.  Initially, 
	don't display flashContent div so it won't show if JavaScript disabled.
	-->
	<style type="text/css" media="screen"> 
		html, body	{ height:100%; background-color:#ccc;}
		body { margin:0; padding:0; overflow:auto; text-align:center; 
		background-color: #fff; /*#f9e4ca;*/ }   
		#flashContent { display:none; }
	</style>

	<!-- Enable Browser History by replacing useBrowserHistory tokens with two hyphens -->
	<!-- BEGIN Browser History required section -->
	<link rel="stylesheet" type="text/css" href="history/history.css" />
	<script type="text/javascript" src="history/history.js"></script>
	<!-- END Browser History required section -->  

	<script type="text/javascript">

	function thisMovie(movieName) 
	{
		if (navigator.appName.indexOf("Microsoft") != -1) {
			return window[movieName];
		} else {
			return document[movieName];
		}
	}

	var flashReadyFlag = false;
	function flashReady()
	{
		flashReadyFlag = true;
	}

	function noOverlay()
	{
		alert("Your cool picture is not ready yet!");
	}

	function SetUserImage( url )
	{
		if( flashReadyFlag ) {
			thisMovie("Redneckify").DoSetUserImage(url);
		} else {
			alert("Error - flash is not ready yet");
		}
	}

	function SetOverlayImage( url )
	{
		if( flashReadyFlag ) {
			thisMovie("Redneckify").DoSetOverlayImage(url);
		} else {
			alert("Error - flash is not ready yet");
		}
	}
	</script>		    

	<script type="text/javascript" src="${resource(dir:'js', file: 'swfobject.js')}"></script>
	<script type="text/javascript">
		<!-- For version detection, set to min. required Flash Player version, or 0 (or 0.0.0), for no version detection. --> 
		var swfVersionStr = "10.0.0";
		<!-- To use express install, set to playerProductInstall.swf, otherwise the empty string. -->
		
		var xiSwfUrlStr = "${resource(dir:'swf', file: 'playerProductInstall.swf')}";
		var flashvars = { baseUrl : "${grailsApplication.config.grails.serverURL.encodeAsURL()}", userImage : "${userImageUrl.encodeAsURL()}" };  
		var params = {};
		params.quality = "high";
		params.bgcolor = "#f9e4ca";
		params.allowscriptaccess = "always";
		params.allowfullscreen = "true";
		var attributes = {};
		attributes.id = "Redneckify";
		attributes.name = "Redneckify";
		attributes.align = "middle";
		swfobject.embedSWF(
			"${resource(dir:'swf', file: 'Redneckify.swf')}", "flashContent", 
			"480", "810", 
			swfVersionStr, xiSwfUrlStr, 
			flashvars, params, attributes);
			<!-- JavaScript enabled so display the flashContent div in case it is not replaced with a swf object. -->
			swfobject.createCSS("#flashContent", "display:block;text-align:left;");
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
    <a href="${createLink(controller:'create')}" class="span-7" id="subh2Active"></a>
    <a href="${createLink(controller:'share')}" class="span-6" id="subh3"></a>
  </div>
  
  <g:render template="/snippets/flashMessageTemplate" />
  
	<div class="span-24" id="content">
		<div class="span-7 prepend-1" id="leftContent">
			<div class="span-7 pull-1" id="pickYrR"><span class="hidden">Pick Your Redneck</span></div>
			<div class="span-7"><a class="sex" href="#">Male</a> | <a class="sex" href="#">Female</a></div>
			<div class="pics">
				<div class="span-3"><a href="#" onclick='SetOverlayImage("${resource(dir:'images/rednecks', file: '01.png')}");return false;'><img width=100 height=100 src="${resource(dir:'images/rednecks', file: '01.png')}" /></a></div>
				<div class="span-3"><a href="#" onclick='SetOverlayImage("${resource(dir:'images/rednecks', file: '02.png')}");return false;'><img width=100 height=100 src="${resource(dir:'images/rednecks', file: '02.png')}" /></a></div>
			</div>
			<div class="pics">
				<div class="span-3"><a href="#" onclick='SetOverlayImage("${resource(dir:'images/rednecks', file: '03.png')}");return false;'><img width=100 height=100 src="${resource(dir:'images/rednecks', file: '03.png')}" /></a></div>
				<div class="span-3"><a href="#" onclick='SetOverlayImage("${resource(dir:'images/rednecks', file: '04.png')}");return false;'><img width=100 height=100 src="${resource(dir:'images/rednecks', file: '04.png')}" /></a></div>
			</div>
			<div class="pics">
				<div class="span-3"><a href="#" onclick='SetOverlayImage("${resource(dir:'images/rednecks', file: '05.png')}");return false;'><img width=100 height=100 src="${resource(dir:'images/rednecks', file: '05.png')}" /></a></div>
				<div class="span-3"><a href="#" onclick='SetOverlayImage("${resource(dir:'images/rednecks', file: '06.png')}");return false;'><img width=100 height=100 src="${resource(dir:'images/rednecks', file: '06.png')}" /></a></div>
			</div>
			<div class="pics">
				<div class="span-3"><a href="#" onclick='SetOverlayImage("${resource(dir:'images/rednecks', file: '07.png')}");return false;'><img width=100 height=100 src="${resource(dir:'images/rednecks', file: '07.png')}" /></a></div>
				<div class="span-3"><a href="#" onclick='SetOverlayImage("${resource(dir:'images/rednecks', file: '08.png')}");return false;'><img width=100 height=100 src="${resource(dir:'images/rednecks', file: '08.png')}" /></a></div>
			</div>
			<div class="pics">
				<div class="span-3"><a href="#" onclick='SetOverlayImage("${resource(dir:'images/rednecks', file: '09.png')}");return false;'><img width=100 height=100 src="${resource(dir:'images/rednecks', file: '09.png')}" /></a></div>
				<div class="span-3"><a href="#" onclick='SetOverlayImage("${resource(dir:'images/rednecks', file: '10.png')}");return false;'><img width=100 height=100 src="${resource(dir:'images/rednecks', file: '10.png')}" /></a></div>
			</div>
			<div class="pics">
				<div class="span-3"><a href="#" onclick='SetOverlayImage("${resource(dir:'images/rednecks', file: '11.png')}");return false;'><img width=100 height=100 src="${resource(dir:'images/rednecks', file: '11.png')}" /></a></div>
				<div class="span-3"><a href="#" onclick='SetOverlayImage("${resource(dir:'images/rednecks', file: '12.png')}");return false;'><img width=100 height=100 src="${resource(dir:'images/rednecks', file: '12.png')}" /></a></div>
			</div>
			<div class="pics">
				<div class="span-3"><a href="#" onclick='SetOverlayImage("${resource(dir:'images/rednecks', file: '13.png')}");return false;'><img width=100 height=100 src="${resource(dir:'images/rednecks', file: '13.png')}" /></a></div>
				<div class="span-3"><a href="#" onclick='SetOverlayImage("${resource(dir:'images/rednecks', file: '14.png')}");return false;'><img width=100 height=100 src="${resource(dir:'images/rednecks', file: '14.png')}" /></a></div>
			</div>
			<div class="pics">
				<div class="span-3"><a href="#" onclick='SetOverlayImage("${resource(dir:'images/rednecks', file: '15.png')}");return false;'><img width=100 height=100 src="${resource(dir:'images/rednecks', file: '15.png')}" /></a></div>
				<div class="span-3"><a href="#" onclick='SetOverlayImage("${resource(dir:'images/rednecks', file: '16.png')}");return false;'><img width=100 height=100 src="${resource(dir:'images/rednecks', file: '16.png')}" /></a></div>
			</div>
			<div class="pics">
				<div class="span-3"><a href="#" onclick='SetOverlayImage("${resource(dir:'images/rednecks', file: '17.png')}");return false;'><img width=100 height=100 src="${resource(dir:'images/rednecks', file: '17.png')}" /></a></div>
				<div class="span-3"><a href="#" onclick='SetOverlayImage("${resource(dir:'images/rednecks', file: '18.png')}");return false;'><img width=100 height=100 src="${resource(dir:'images/rednecks', file: '18.png')}" /></a></div>
			</div>						
		</div>
		
    <div class="span-14  prepend-1 last" id="rightContent">
      <p>Already been redneckkified? Need to git at yer pic?<a href="#" class="blue"> Sign on in right here, Billy Bob</a></p>

			<!-- SWFObject's dynamic embed method replaces this alternative HTML content with Flash content when enough 
			JavaScript and Flash plug-in support is available. The div is initially hidden so that it doesn't show
			when JavaScript is disabled.
			-->
			<div id="flashContent">
				<p>
					To view this page ensure that Adobe Flash Player version 
					10.0.0 or greater is installed. 
				</p>
				<script type="text/javascript"> 
				var pageHost = ((document.location.protocol == "https:") ? "https://" :	"http://"); 
				document.write("<a href='http://www.adobe.com/go/getflashplayer'><img src='" 
				+ pageHost + "www.adobe.com/images/shared/download_buttons/get_flash_player.gif' alt='Get Adobe Flash player' /></a>" ); 
				</script> 
			</div>

			<noscript>
				<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="480" height="810" id="Redneckify">
					<param name="movie" value="Redneckify.swf" />
					<param name="quality" value="high" />
					<param name="bgcolor" value="#f9e4ca" />
					<param name="allowScriptAccess" value="always" />
					<param name="allowFullScreen" value="true" />
					<!--[if !IE]>-->
					<object type="application/x-shockwave-flash" data="Redneckify.swf" width="480" height="810">
						<param name="quality" value="high" />
						<param name="bgcolor" value="#f9e4ca" />
						<param name="allowScriptAccess" value="sameDomain" />
						<param name="allowFullScreen" value="true" />
						<!--<![endif]-->
						<!--[if gte IE 6]>-->
						<p> 
							Either scripts or active content are not permitted to run or Adobe Flash Player version
							10.0.0 or greater is not installed.
						</p>
						<!--<![endif]-->
						<a href="http://www.adobe.com/go/getflashplayer">
							<img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash Player" />
						</a>
						<!--[if !IE]>-->
					</object>
					<!--<![endif]-->
				</object>
			</noscript>
    </div>
  </div>  
 </body>
</html>