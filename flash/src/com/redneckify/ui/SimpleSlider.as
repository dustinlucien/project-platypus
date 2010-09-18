package com.redneckify.ui
{
	import com.redneckify.ui.SliderEvent;
	
	import flash.display.*;
	import flash.events.*;
	import flash.geom.*;
	import flash.text.*;
	
	public class SimpleSlider extends Sprite
	{
		public function SimpleSlider()
		{
			super();
		}
		
		[Event(name=SliderEvent.SLIDER_UPDATED, type=com.redneckify.ui.SliderEvent ,  bubbles=true)]
		
		public function setupSlider (_holder:Sprite,  _sliderBar:Sprite,_sliderControl:Sprite,_snapping:int =25,defaultVal:int =0):void
		{
			holder = _holder;
			sliderBar = _sliderBar;
			sliderControl = _sliderControl;
			sliderControl.buttonMode = true;
			
			var actualWidth:int = _sliderBar.width - _sliderControl.width;
			
			snapping = _snapping;
			barSteps =	Math.round(actualWidth/snapping);
			if (defaultVal*barSteps>actualWidth){
				defaultVal = snapping;
			}
			
			dragRect.x = sliderBar.x;
			dragRect.y = sliderBar.y;
			dragRect.width = actualWidth;
			dragRect.height = 0;
			
			sliderControl.y = dragRect.y;
			sliderControl.addEventListener(MouseEvent.MOUSE_DOWN, mouseDownHandler);
			
			
			
			sliderControl.x = (barSteps * defaultVal) + dragRect.x;
			updateVal();
		}
		
		private function updateVal():void {
			this.sliderVal = Math.round((sliderControl.x - sliderBar.x) / barSteps);
		}
		
		private function mouseDownHandler(e:MouseEvent):void {
			sliderControl.startDrag(false, dragRect);
			holder.addEventListener(MouseEvent.MOUSE_MOVE,sliderMovesHandler);
			
			holder.addEventListener(MouseEvent.MOUSE_UP,mouseUpHandler);
		}
		private function sliderMovesHandler(e:MouseEvent):void {
			updateVal();
		}
		private function mouseUpHandler(e:MouseEvent):void {
			var snapX:int = Math.round((sliderControl.x - sliderBar.x) / barSteps);
			sliderControl.x = (snapX*barSteps) + dragRect.x;
			updateVal();
			this.dispatchEvent(new SliderEvent(SliderEvent.SLIDER_UPDATED,true,sliderVal));
			holder.removeEventListener(MouseEvent.MOUSE_MOVE,sliderMovesHandler);
			holder.removeEventListener(MouseEvent.MOUSE_MOVE,mouseUpHandler);
		
			holder.removeEventListener(MouseEvent.MOUSE_UP,mouseUpHandler);
			stopDrag();
		}
		
		private var dragRect:Rectangle = new Rectangle();
		private var holder:Sprite;
		private var sliderBar:Sprite;
		private var sliderControl:Sprite;
		private var snapping:int;
		private var barSteps:int;
		private var sliderVal:int;
	
	}
}