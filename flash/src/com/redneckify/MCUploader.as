package com.redneckify
{

	
	import com.adobe.images.JPGEncoder;
	
	import flash.display.*;
	import flash.events.Event;
	import flash.events.HTTPStatusEvent;
	import flash.external.ExternalInterface;
	import flash.geom.Matrix;
	import flash.geom.Transform;
	import flash.net.*;
	import flash.system.Capabilities;
	import flash.utils.ByteArray;
	
	
	
	public class MCUploader
	{
		
		protected var _sprite: Sprite;
		
		protected var _targetDpi:Number = 150;
		protected var _screenDpi:Number;
		
		protected var _postUrl: String;
		protected var _successUrl: String;
		protected var _key:String;
		protected var _policy :String;
		protected var _signature:String;
		protected var _apiKey:String;
		
		protected var _jpgStream:ByteArray ;
		
		protected var _ready: Boolean;
		
		// Uploads Sprite to S3
		public function MCUploader(sprite:Sprite)
		{
			_sprite = sprite;
			_screenDpi = Capabilities.screenDPI; // get screen dpi instead of hardcoding 72 or 96 - makes no sense in fact
			_ready = false;
			GetUploadConfig(); 
		}
		
		//captures, resizes and starts upload
		public function CaptureAndUpload(outWidth:Number,outHeight:Number):void
		{
			import com.adobe.images.JPGEncoder;
			var scaleFactor: Number;
			scaleFactor = _targetDpi / _screenDpi;
			
			var scaleMatrix:Matrix = new Matrix();
			scaleMatrix.scale( scaleFactor, scaleFactor );
			
			var targetWidth:Number = outWidth * scaleFactor;
			var targetHeight:Number = outHeight * scaleFactor;
			
			var jpgSource:BitmapData = new BitmapData ( targetWidth , targetHeight );
					
			jpgSource.draw(_sprite,scaleMatrix,null,null,null,true); // need last argument to be true in case we want things to be smooth
		
			var jpgEncoder:JPGEncoder = new JPGEncoder(85);
			_jpgStream = jpgEncoder.encode(jpgSource);
			
			StartUploadPost();
			
			// *** Uncomment following lines if you want to use direct upload to php script *** //
			//var header:URLRequestHeader = new URLRequestHeader("Content-type", "application/octet-stream");
			//var jpgURLRequest:URLRequest = new URLRequest("http://red/upload.php");   //  php test - direct download //
			//jpgURLRequest.requestHeaders.push(header);
			//jpgURLRequest.method = URLRequestMethod.POST;
			//jpgURLRequest.data = jpgStream;
		    // navigateToURL(jpgURLRequest, "_blank"); //  php test - direct download //
		}
		
		protected var infoLoader : URLLoader;
		
		protected function GetUploadConfig():void
		{
			var paramList:Object = this._sprite.root.loaderInfo.parameters;
			var baseUrl:String = paramList.baseUrl; // base url from flashvars (check source) - "http://localhost:8080/project-platypus" //
			var configActionUrl: String = paramList.configAction; // relative to site root config action URL 
			if( configActionUrl == null ) configActionUrl = "/flash/posturl";
			var infoRequest:URLRequest = new URLRequest( baseUrl +  configActionUrl );
			infoRequest.method = URLRequestMethod.GET;
			infoLoader = new URLLoader( infoRequest );
			infoLoader.addEventListener("complete", this.UploadConfigLoaded );
		}
		
		protected function UploadConfigLoaded(ev:Event):void
		{
			var configStr: String = infoLoader.data;
			var arr:Array = configStr.split("\n");
			if( arr.length<6 ) throw new Error("Invalid configuration from /flash/posturl");
			this._key = arr[0];
			this._policy = arr[1];
			this._signature = arr[2];
			this._apiKey = arr[3];
			this._postUrl = arr[4];
			this._successUrl = arr[5];
			this._ready  = true; // ready flag
		}
		
		

		protected function StartUploadPost():void
		{
			if( ! this._ready ) throw new Error("Application configuration is not ready");
			
			var s3UrlRequest:URLRequestWrapper = new URLRequestWrapper(
				_jpgStream,
				null	, 
				null, 
					{   key: this._key,
						AWSAccessKeyId:this._apiKey,
						acl:"public-read",
						success_action_redirect:this._successUrl,
						policy:this._policy,
						signature:this._signature } /* our post params */			
				);
			s3UrlRequest.url = this._postUrl;
			
			var jpgURLRequest:URLRequest =  s3UrlRequest.request;
					
			navigateToURL( jpgURLRequest , "_self");
		}
			

		
		
	}
}