package com.redneckify.ui
{
	import com.redneckify.GraphicsLib;
	import com.redneckify.ImagePlaceholder;
	import com.redneckify.OverlayPlaceholder;
	import com.redneckify.PlaceholderEvent;
	import com.redneckify.RedneckifyStatics;
	
	import flash.display.Bitmap;
	import flash.display.ColorCorrection;
	import flash.display.DisplayObject;
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.geom.Matrix;
	import flash.geom.Point;
	import flash.geom.Rectangle;
	import flash.geom.Transform;
	import flash.ui.Mouse;
	import flash.utils.clearTimeout;
	import flash.utils.setTimeout;
	

	
	
	
	public class OutputArea extends Sprite
	{
		private var FrameOverlay:DisplayObject;
		private var photo : com.redneckify.ImagePlaceholder;
		private var overlay : com.redneckify.OverlayPlaceholder;
		private var result: Sprite;
		
		private var timerHandle:Number ;
		private var timerTask:String;
		private var delayBeforeOverlayMoveMode:Number;
		
		private const TIMERTASK_INTO_MOVEMODE:String = "TIMERTASK_INTO_MOVEMODE";
		private const TIMERTASK_FROM_MOVEMODE:String = "TIMERTASK_FROM_MOVEMODE";
		
		public const OutWidth:Number = RedneckifyStatics.OutputWidth;
		public const OutHeight:Number = RedneckifyStatics.OutputHeight;
		
		public function SetUserImage(url:String):void
		{
			photo.LoadExternal(url);
			
		}
		

		public function SetOverlayImage(url:String):void
		{
			overlay.LoadExternal(url);
			overlay.addEventListener(PlaceholderEvent.LOADED,_OverlayLoaded);
		}
		
		protected function _OverlayLoaded(ev:PlaceholderEvent):void
		{
			var m:Matrix = new Matrix();
			var scaleFactorY:Number = 1;
			var scaleFactorX:Number = 1;
			if( overlay.height> OutHeight ) 
			{
				scaleFactorY = OutHeight/overlay.height;	
			}
			
			if( overlay.width> OutWidth ) 
			{
				scaleFactorX = OutWidth/overlay.width;	
			}
			
			var scaleFactor:Number;
			scaleFactor = (scaleFactorX>scaleFactorY)?scaleFactorX:scaleFactorY; // using larger scale factor to fit box outside (no white padding )
			
			overlay.scaleImage(scaleFactor,OutWidth,OutHeight);
		}
		
		public function GetResult():Sprite
		{
			return result;
		}
		
		public function OutputArea()
		{
			// Intial varibles values 
			timerHandle = 0;
			delayBeforeOverlayMoveMode = RedneckifyStatics.DelayBeforeHandGrab;
			
			//
			 result = new Sprite();
			 
			 photo = new com.redneckify.ImagePlaceholder();
			 overlay = new OverlayPlaceholder();
			 
			 result.y = 70; // hardcode - bad //
			 result.x = 75;
			 
			 result.graphics.beginFill(0xffffff);
			  result.graphics.drawRect(0,0,OutWidth,OutHeight);
			  result.graphics.endFill();
			 
			 
			 result.addChild(photo);
			 result.addChild(overlay);
			 this.addChild(result);
			 FrameOverlay =  addChild( com.redneckify.GraphicsLib.getFrameOverlayBitmap() );
			 
			
			// FrameOverlay.alpha=0.5;//// DEBUG/////
							 
			 //
			 moveStep = 2;
			 zoomStep = 1.01;
			 rotateStep = 2;
			 
			 //
			 overlay.addEventListener(MouseEvent.MOUSE_OVER, this._OnOverlayMouseOver );
			 overlay.addEventListener(MouseEvent.MOUSE_OUT, this._OnOverlayMouseOut );
		}
		
		
		protected function _OnOverlayMouseOver(ev:MouseEvent):void
		{
			if( this.timerHandle  == 0 )
			{
				this.timerHandle = setTimeout( this._OverlayIntoMoveMode, delayBeforeOverlayMoveMode );
				this.timerTask = TIMERTASK_INTO_MOVEMODE;
			}
			_ClearTimerIfDifferent(TIMERTASK_INTO_MOVEMODE);
		}
		
		protected function _OnOverlayMouseOut(ev:MouseEvent):void
		{
			if( this.timerHandle  == 0 )
			{
				this.timerHandle = setTimeout( this._OverlayFromMoveMode, delayBeforeOverlayMoveMode );
				this.timerTask = TIMERTASK_FROM_MOVEMODE;
			}
			_ClearTimerIfDifferent(TIMERTASK_FROM_MOVEMODE);
		}
		
		private function _ClearTimerIfDifferent(desiredTask:String):void
		{
			if(( timerTask != desiredTask )&&( timerHandle!=0)) {
				clearTimeout( timerHandle );
				timerHandle = 0;
			}
		}
		
		
		private function _ClearTimer():void
		{
			this.timerHandle = 0;
			this.timerTask = "";
		}
		
		protected function _OverlayIntoMoveMode():void
		{
			_ClearTimer();	
			this.overlay.alpha = 0.75; // we should add animation here //
			flash.ui.Mouse.cursor = flash.ui.MouseCursor.HAND;
			this.addEventListener(MouseEvent.MOUSE_DOWN, _OverlayMouseDown );
			this.addEventListener(MouseEvent.MOUSE_UP, _OverlayMouseUp );
			
		}
		
		protected function _OverlayFromMoveMode():void
		{
			_ClearTimer();	
			this.overlay.alpha = 1; // we should add animation here //
			flash.ui.Mouse.cursor = flash.ui.MouseCursor.AUTO;
		}
		
		public function ResetMoveMode():void
		{
			if( timerHandle != 0 ) 
			{
				clearTimeout( timerHandle );
			}
			_OverlayFromMoveMode();
		}
		
		public function ReadyToSave():Boolean
		{
			return ( this.overlay.isLoaded() && this.photo.isLoaded() );
		}
		
		protected function _OverlayMouseDown(ev:Event):void
		{
			this.photo.startDrag();		
		}
		
		protected function _OverlayMouseUp(ev:Event):void
		{
			this.photo.stopDrag();		
		}
		
		public function DoRotateCw():void
		{
			photo.DoRotate(rotateStep);
		}
		
		public function DoRotateCcw():void
		{
			photo.DoRotate(-rotateStep);
		}
		
		
		protected var moveStep:Number;
		protected var zoomStep:Number;
		protected var rotateStep:Number;
		
		public function DoMoveLeft():void
		{
			photo.DoMove( -moveStep , 0 );
		}
		
		public function DoMoveRight():void
		{
			photo.DoMove( moveStep , 0 );	
		}
		
		public function DoMoveUp():void
		{
			photo.DoMove( 0 ,-moveStep  );	
		}
		
		public function DoMoveDown():void
		{
			photo.DoMove( 0,moveStep );	
		}
		
		public function DoZoomIn():void
		{
			photo.DoZoom( zoomStep );	
		}
		
		public function DoZoomOut():void
		{
			photo.DoZoom( 1/zoomStep );	
		}
		
		public function DoFlip():void
		{
			photo.DoFlip();
		
		}
		
		public function setBrightness(v:Number):void
		{
			photo.DoSetBrightness(v);	
		}
		
		public function setContrast(v:Number):void
		{
			photo.DoSetContrast(v);	
		}
	}
}