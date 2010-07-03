<html>
<head>
	<title>Git yer pitcher on in there</title>
	<meta name="layout" content="main" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<g:render template="/navigation/createHeaderTemplate" />

	<div id="midNav" class="span-24">
		<a href="${createLink(controller:'upload')}" class="span-10" id="subh1"></a>
		<a href="${createLink(controller:'create')}" class="span-7" id="subh2"></a>
		<a href="${createLink(controller:'share')}" class="span-6" id="subh3"></a>
	</div>


	<div class="span-24" id="content">
		<div class="span-7 prepend-1" id="leftContent">
			<div class="span-7 pull-1" id="pickYrR"><span class="hidden">Pick Your Redneck</span></div>
			<div class="span-7"><a class="sex" href="#">Male</a> | <a class="sex" href="#">Female</a></div>

			<div class="pics">
				<div class="span-3"><a href="#"><img src="${createLinkTo(dir:'images/rednecks', file: '01.png')}" /></a></div>
				<div class="span-3"><a href="#"><img src="${createLinkTo(dir:'images/rednecks', file: '02.png')}" /></a></div>
			</div>
			<div class="pics">
				<div class="span-3"><a href="#"><img src="${createLinkTo(dir:'images/rednecks', file: '03.png')}" /></a></div>
				<div class="span-3"><a href="#"><img src="${createLinkTo(dir:'images/rednecks', file: '04.png')}" /></a></div>
			</div>
			<div class="pics">
				<div class="span-3"><a href="#"><img src="${createLinkTo(dir:'images/rednecks', file: '05.png')}" /></a></div>
				<div class="span-3"><a href="#"><img src="${createLinkTo(dir:'images/rednecks', file: '06.png')}" /></a></div>
			</div>
			<div class="pics">
				<div class="span-3"><a href="#"><img src="${createLinkTo(dir:'images/rednecks', file: '07.png')}" /></a></div>
				<div class="span-3"><a href="#"><img src="${createLinkTo(dir:'images/rednecks', file: '08.png')}" /></a></div>
			</div>
			<div class="pics">
				<div class="span-3"><a href="#"><img src="${createLinkTo(dir:'images/rednecks', file: '09.png')}" /></a></div>
				<div class="span-3"><a href="#"><img src="${createLinkTo(dir:'images/rednecks', file: '10.png')}" /></a></div>
			</div>
			<div class="pics">
				<div class="span-3"><a href="#"><img src="${createLinkTo(dir:'images/rednecks', file: '11.png')}" /></a></div>
				<div class="span-3"><a href="#"><img src="${createLinkTo(dir:'images/rednecks', file: '12.png')}" /></a></div>
			</div>
			<div class="pics">
				<div class="span-3"><a href="#"><img src="${createLinkTo(dir:'images/rednecks', file: '13.png')}" /></a></div>
				<div class="span-3"><a href="#"><img src="${createLinkTo(dir:'images/rednecks', file: '14.png')}" /></a></div>
			</div>
			<div class="pics">
				<div class="span-3"><a href="#"><img src="${createLinkTo(dir:'images/rednecks', file: '15.png')}" /></a></div>
				<div class="span-3"><a href="#"><img src="${createLinkTo(dir:'images/rednecks', file: '16.png')}" /></a></div>
			</div>
			<div class="pics">
				<div class="span-3"><a href="#"><img src="${createLinkTo(dir:'images/rednecks', file: '17.png')}" /></a></div>
				<div class="span-3"><a href="#"><img src="${createLinkTo(dir:'images/rednecks', file: '18.png')}" /></a></div>
			</div>						
		</div>
		
        <div class="span-14  prepend-1 last" id="rightContent">
          <p>Already been redneckkified? Need to git at yer pic?<a href="#" class="blue"> Sign on in right here, Billy Bob</a></p>
          <img src="${createLinkTo(dir:'images', file: 'player.jpg')}" />
        </div>				
	</div>
</body>
</html>