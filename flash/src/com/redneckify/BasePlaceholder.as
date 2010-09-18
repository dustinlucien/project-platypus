package com.redneckify
{
	import flash.display.Bitmap;
	import flash.display.BitmapData;
	import flash.display.BlendMode;
	import flash.display.Loader;
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.geom.Point;
	import flash.net.URLRequest;
	
	public class BasePlaceholder extends Sprite
	{
		public function BasePlaceholder()
		{
			super();
			this.blendMode = BlendMode.LAYER;
			this.loaded = false;
			this.myImage = null;
		}
		
		protected var ptRotationPoint:Point;
		
		protected var  myImageLoader:Loader;
		protected var myBitmapData:BitmapData;
		protected var myImage:Bitmap;
		protected var loaded: Boolean;
		
		public function isLoaded():Boolean
		{
			return loaded;
		}
		
		public function LoadExternal( url:String ):void
		{
			
			myImageLoader = new Loader();
			var imageURLRequest:URLRequest = new URLRequest(url);
			
			myImageLoader.load(imageURLRequest);
			myImageLoader.contentLoaderInfo.addEventListener(Event.COMPLETE, _imageLoaded);
			
			
		}
		
		protected function _imageLoaded(e:Event):void {
			
			if( myImage != null ) {
				this.removeChild( myImage );
				myImage = null;
			} // clear old image in placeholder //
			
			myBitmapData = new BitmapData(myImageLoader.width, myImageLoader.height,true, 0x000000);

			myBitmapData.draw(myImageLoader);
			myImage = new Bitmap(myBitmapData);
			myImage.smoothing = true;
			this.centerImage();
			addChild(myImage);
			ptRotationPoint = new Point(myImage.x + myImage.width/2, myImage.y+ myImage.height/2);
			
			this.loaded = true;
			
			this.dispatchEvent(new PlaceholderEvent(PlaceholderEvent.LOADED));
		}
		
		public function centerImage():void
		{
			myImage.x =  (this.parent.width/2) - myImage.width /2;
			myImage.y = (this.parent.height/2)-myImage.height/2;
		}
		
		public function scaleImage(scale:Number,outWidth:Number,outHeight:Number):void
		{
			myImage.scaleX = scale;
			myImage.scaleY = scale;
			myImage.x =  (outWidth/2) - myImage.width /2;
			myImage.y = (outHeight/2)-myImage.height/2;
		}
	}
}