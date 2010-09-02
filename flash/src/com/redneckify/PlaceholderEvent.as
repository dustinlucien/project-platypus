package com.redneckify
{
	import flash.events.Event;
	
	public class PlaceholderEvent extends Event
	{
		
		public static const LOADED:String = "LOADED";
		
		public function PlaceholderEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}