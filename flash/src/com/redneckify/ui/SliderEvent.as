package com.redneckify.ui
{
	import flash.events.Event;
	
	public class SliderEvent extends Event
	{
		public static const SLIDER_UPDATED:String = "SLIDER_UPDATED";
		
		public var data:*;
		public function SliderEvent( controlType:String, bubbles:Boolean = true, data:Object = null ) {
			super( controlType,bubbles);
			this.data = data;
		}
	}
}