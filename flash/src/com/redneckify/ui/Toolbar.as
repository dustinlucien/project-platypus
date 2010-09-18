package com.redneckify.ui
{
	import com.redneckify.GraphicsLib;
	import com.redneckify.RedneckifyStatics;
	
	import flash.display.DisplayObject;
	import flash.display.SimpleButton;
	import flash.display.SpreadMethod;
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.text.engine.RenderingMode;
	import flash.utils.Timer;
	

	
	
	public class Toolbar extends Sprite
	{
		
		private var _RotateCcw:SimpleButton;
		private var _RotateCw:SimpleButton;
		
		private var _MoveUp:SimpleButton;
		private var _MoveDown:SimpleButton;
		private var _MoveLeft:SimpleButton;
		private var _MoveRight:SimpleButton;
		
		private var _ZoomIn:SimpleButton;
		private var _ZoomOut:SimpleButton;
		
		private var _FlipLeft:SimpleButton;
		private var _FlipRight:SimpleButton;
		
		private var _Parent:DisplayObject;
		
		private var _HoldActionTimer : Timer;
		private var _HoldActionButton:SimpleButton;
		
		private var _DoneButton : SimpleButton;
		
		public function Toolbar(aParent:DisplayObject)
		{
			this._Parent = aParent;
			this._HoldActionTimer = new Timer( RedneckifyStatics.ToolbarRepeatActionDelay );
			this._HoldActionTimer.addEventListener(TimerEvent.TIMER,OnHoldActionTimer);
			this._HoldActionButton = null;
			this.addChild( com.redneckify.GraphicsLib.getToolbarBgBitmap() );
			this.CreateRotateButtons();
			this.CreateMoveButtons();
			this.CreateZoomButtons();
			this.CreateFlipButtons();
			this.CreateSliders();
			this.CreateDoneButton();
		}
		
		protected function AddHoldAction( aButton :SimpleButton ):void
		{
			aButton.addEventListener(MouseEvent.MOUSE_DOWN, _OnHoldActionDown );
			this._Parent. addEventListener(MouseEvent.MOUSE_UP, _OnHoldActionUp );
		}
		
		protected function OnHoldActionTimer( ev:Event ):void
		{
			if( _HoldActionButton != null )
			{
				_HoldActionButton.dispatchEvent( new MouseEvent(MouseEvent.CLICK) );
			}
		}
		
		protected function _OnHoldActionDown(ev:Event):void
		{
			_HoldActionButton =  ev.target as SimpleButton;
			_HoldActionTimer.start();
		}
		
		protected function _OnHoldActionUp(ev:Event):void
		{
		
			_HoldActionTimer.reset()
		}

		
		protected function CreateRotateButtons():void
		{
			// create rotate ccw button
			_RotateCcw = new SimpleButton( 
				com.redneckify.GraphicsLib.getToolbarButtonRotateCcwBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonRotateCcwBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonRotateCcwBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonRotateCcwBitmap());
			// rotate ccw events //			
			_RotateCcw.addEventListener(MouseEvent.CLICK,  OnRotateCcwClick );
			AddHoldAction(_RotateCcw);
			// rotate ccw position //
			_RotateCcw.x = 65;
			_RotateCcw.y = 40;
			this.addChild( _RotateCcw );
			// create rotate cv button
			_RotateCw = new SimpleButton( 
				com.redneckify.GraphicsLib.getToolbarButtonRotateCwBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonRotateCwBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonRotateCwBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonRotateCwBitmap());
			_RotateCw.x = 100;
			_RotateCw.y = 40;
			
			
			_RotateCw.addEventListener(MouseEvent.CLICK, OnRotateCwClick );
			AddHoldAction(_RotateCw);
			
			
			this.addChild( _RotateCw );

		}
		
		protected function OnRotateCwClick(ev:Event):void
		{
			this.dispatchEvent( new ToolbarEvent(ToolbarEvent.ROTATE_CW)  );
		}
		
		protected function OnRotateCcwClick(ev:Event):void
		{
			this.dispatchEvent( new ToolbarEvent(ToolbarEvent.ROTATE_CCW)  );
		}
		
		protected function OnMoveLeftClick(ev:Event):void
		{
			this.dispatchEvent( new ToolbarEvent(ToolbarEvent.MOVE_LEFT)  );
		}
		
		protected function OnMoveRightClick(ev:Event):void
		{
			this.dispatchEvent( new ToolbarEvent(ToolbarEvent.MOVE_RIGHT)  );
		}
		
		protected function OnMoveUpClick(ev:Event):void
		{
			this.dispatchEvent( new ToolbarEvent(ToolbarEvent.MOVE_UP)  );
		}
		
		protected function OnMoveDownClick(ev:Event):void
		{
			this.dispatchEvent( new ToolbarEvent(ToolbarEvent.MOVE_DOWN)  );
		}
		
		protected function OnZoomInClick(ev:Event):void
		{
			this.dispatchEvent( new ToolbarEvent(ToolbarEvent.ZOOM_IN)  );
		}
		
		protected function OnZoomOutClick(ev:Event):void
		{
			this.dispatchEvent( new ToolbarEvent(ToolbarEvent.ZOOM_OUT)  );
		}
		
		protected function OnFlipLeftClick(ev:Event):void
		{
			this.dispatchEvent( new ToolbarEvent(ToolbarEvent.FLIP_LEFT)  );
		}
		
		protected function OnFlipRightClick(ev:Event):void
		{
			this.dispatchEvent( new ToolbarEvent(ToolbarEvent.FLIP_RIGHT)  );
		}
		
		protected function CreateMoveButtons():void
		{
			var container: Sprite;			
			container = new Sprite();
			
			_MoveLeft = new SimpleButton(
				com.redneckify.GraphicsLib.getToolbarButtonMoveLeftBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonMoveLeftBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonMoveLeftBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonMoveLeftBitmap());
			
			_MoveLeft.addEventListener(MouseEvent.CLICK, OnMoveLeftClick );
			AddHoldAction(_MoveLeft);
			_MoveLeft.x = 0;
			_MoveLeft.y = 22;
			container.addChild(_MoveLeft);
			
			_MoveRight = new SimpleButton(
				com.redneckify.GraphicsLib.getToolbarButtonMoveRightBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonMoveRightBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonMoveRightBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonMoveRightBitmap());
			
			_MoveRight.addEventListener(MouseEvent.CLICK, OnMoveRightClick);
			AddHoldAction(_MoveRight);
			_MoveRight.x = 44;
			_MoveRight.y = 22;
			container.addChild(_MoveRight);
			
			
			_MoveUp = new SimpleButton(
				com.redneckify.GraphicsLib.getToolbarButtonMoveUpBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonMoveUpBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonMoveUpBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonMoveUpBitmap());
			
			_MoveUp.addEventListener(MouseEvent.CLICK, OnMoveUpClick );
			AddHoldAction(_MoveUp);
			_MoveUp.x = 22;
			_MoveUp.y = 0;
			container.addChild(_MoveUp);
			
			_MoveDown = new SimpleButton(
				com.redneckify.GraphicsLib.getToolbarButtonMoveDownBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonMoveDownBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonMoveDownBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonMoveDownBitmap());
			
			_MoveDown.addEventListener(MouseEvent.CLICK, OnMoveDownClick );
			AddHoldAction(_MoveDown);
			_MoveDown.x = 22;
			_MoveDown.y = 44;
			container.addChild(_MoveDown);
			
			container.x = 168;
			container.y = 40;
			
			this.addChild( container );
		}
		
		protected function CreateZoomButtons():void
		{
			var container: Sprite;			
			container = new Sprite();
			
			
			_ZoomIn = new SimpleButton(
				com.redneckify.GraphicsLib.getToolbarButtonZoomInBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonZoomInBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonZoomInBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonZoomInBitmap()
				);
			_ZoomIn.addEventListener(MouseEvent.CLICK, OnZoomInClick );
			AddHoldAction(_ZoomIn);
			container.addChild( _ZoomIn);
			
			_ZoomOut = new SimpleButton(
				com.redneckify.GraphicsLib.getToolbarButtonZoomOutBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonZoomOutBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonZoomOutBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonZoomOutBitmap()
			);
			_ZoomOut.x= 32;
			_ZoomOut.addEventListener(MouseEvent.CLICK, OnZoomOutClick );
			AddHoldAction(_ZoomOut);
			container.addChild(_ZoomOut);

			
			container.x = 260;
			container.y = 40;
			this.addChild( container );
		}
		
		protected function CreateFlipButtons():void
		{
			var container: Sprite;			
			container = new Sprite();
			
			_FlipLeft = new SimpleButton(
				com.redneckify.GraphicsLib.getToolbarButtonFlipLeftBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonFlipLeftBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonFlipLeftBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonFlipLeftBitmap()
			);
			_FlipLeft.addEventListener(MouseEvent.CLICK, OnFlipLeftClick );
			container.addChild( _FlipLeft);
			
			_FlipRight = new SimpleButton(
				com.redneckify.GraphicsLib.getToolbarButtonFlipRightBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonFlipRightBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonFlipRightBitmap(),
				com.redneckify.GraphicsLib.getToolbarButtonFlipRightBitmap()
			);
			_FlipRight.x= 36;
			_FlipRight.addEventListener(MouseEvent.CLICK, OnFlipRightClick );
			container.addChild(_FlipRight);

			
			container.x = 360;
			container.y = 40;
			this.addChild( container );
		}
		
		
		
		protected var brightness:Number =0;
		protected var contrast :Number = 0;
		
		protected var baseBrightness:Number =50;
		protected var baseContrast :Number = 50;
		
		protected var scaleBrightness:Number =100;
		protected var scaleContrast :Number = 100;
		
		
		public function getBrightness():Number
		{
			return (this.brightness - this.baseBrightness);
		}
		
		public function getContrast():Number
		{
			return (this.contrast - this.baseContrast);
		}
		
		protected function OnContrastSliderUpdate(ev:SliderEvent):void
		{
			contrast = ev.data;
			dispatchEvent(new ToolbarEvent( ToolbarEvent.CONTRAST ));
		}
		
		protected function OnBrightnessSliderUpdate(ev:SliderEvent):void
		{
			brightness = ev.data;
			dispatchEvent(new ToolbarEvent( ToolbarEvent.BRIGHTNESS ));
		}
		
		protected function CreateSliders():void
		{
			var container: Sprite;
			container = new Sprite();
			
			var brightness:Sprite = new Sprite();
			var brightnessBar:Sprite = new Sprite();
			var brightnessDot: Sprite = new Sprite();
			brightnessBar.addChild( com.redneckify.GraphicsLib.getToolbarSliderBgBitmap() );
			brightnessDot.addChild( com.redneckify.GraphicsLib.getToolbarSliderDotBitmap() );
			brightness.addChild( brightnessBar );
			brightness.addChild( brightnessDot);
			container.addChild( brightness );
			var brightnessSlider: com.redneckify.ui.SimpleSlider = new com.redneckify.ui.SimpleSlider();
			brightnessSlider.setupSlider( this, brightnessBar, brightnessDot,scaleBrightness,this.baseBrightness );
			brightnessSlider.addEventListener(SliderEvent.SLIDER_UPDATED, OnBrightnessSliderUpdate );
			
			var contrast:Sprite = new Sprite();
			var contrastBar:Sprite = new Sprite();
			var contrastDot: Sprite = new Sprite();
			contrastBar.addChild( com.redneckify.GraphicsLib.getToolbarSliderBgBitmap() );
			contrastDot.addChild( com.redneckify.GraphicsLib.getToolbarSliderDotBitmap() );
			contrast.addChild( contrastBar );
			contrast.addChild( contrastDot);
			contrast.y = 33;
			container.addChild( contrast );
			var contrastSlider: com.redneckify.ui.SimpleSlider = new com.redneckify.ui.SimpleSlider();
			contrastSlider.setupSlider( this, contrastBar, contrastDot,scaleContrast,this.baseContrast );
			contrastSlider.addEventListener(SliderEvent.SLIDER_UPDATED, OnContrastSliderUpdate );
			
			
			
			container.x = 155;
			container.y = 147;
			this.addChild( container );
		}
		
		protected function CreateDoneButton():void
		{
			_DoneButton = new SimpleButton(
				GraphicsLib.getDoneButtonBitmap(),
				GraphicsLib.getDoneButtonOnBitmap(),
				GraphicsLib.getDoneButtonOnBitmap(),
				GraphicsLib.getDoneButtonBitmap()
				);
			
			_DoneButton.addEventListener(MouseEvent.CLICK, OnDoneClick );
			
			_DoneButton.x = 50;
			_DoneButton.y = 250;
			
			
			
			this.addChild(_DoneButton);
		}
		
		protected function OnDoneClick(ev:Event):void
		{
			this.dispatchEvent( new ToolbarEvent(ToolbarEvent.DONE)  );
		}
	}
}