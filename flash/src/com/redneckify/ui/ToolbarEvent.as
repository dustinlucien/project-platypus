package com.redneckify.ui
{
	import flash.events.Event;
	
	public class ToolbarEvent extends Event
	{
		public static const ROTATE_CW:String = "ROTATE_CW";
		public static const ROTATE_CCW:String = "ROTATE_CCW";
		
		public static const MOVE_LEFT:String = "MOVE_LEFT";
		public static const MOVE_RIGHT:String = "MOVE_RIGHT";
		public static const MOVE_UP:String = "MOVE_UP";
		public static const MOVE_DOWN:String = "MOVE_DOWN";
		
		public static const ZOOM_IN:String = "ZOOM_IN";
		public static const ZOOM_OUT:String = "ZOOM_OUT";
		
		public static const FLIP_LEFT:String = "FLIP_LEFT";
		public static const FLIP_RIGHT:String = "FLIP_RIGHT";
		
		
		public static const BRIGHTNESS:String = "BRIGHTNESS";
		public static const CONTRAST:String = "CONTRAST";
		
		public static const DONE:String = "DONE";
		
		public function ToolbarEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
	}
}