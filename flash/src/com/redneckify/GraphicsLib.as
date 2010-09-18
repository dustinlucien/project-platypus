package com.redneckify
{
	import flash.display.Bitmap;
	import flash.display.FrameLabel;
	import flash.display.MovieClip;

	public class GraphicsLib
	{
		public function GraphicsLib()
		{
		}
		
		[ Embed ( source = "images/loader.swf" , symbol = "loader" ) ] private static var savingDialogMovieClipClass:Class;
		private static var _SavingDialogMovieClip : MovieClip;
		
		public static function getSavingDialogMovieClip():MovieClip
		{
			if(_SavingDialogMovieClip == null ) _SavingDialogMovieClip = new savingDialogMovieClipClass();
			return _SavingDialogMovieClip;
		}
		
		[ Embed ( source = "images/frame-overlay.png" ) ] private static var frameOverlayGFXClass:Class;
		
		private static var  _FrameOverlayBitmap:Bitmap;
		
		public static function getFrameOverlayBitmap():Bitmap
		{
			if( _FrameOverlayBitmap == null ) _FrameOverlayBitmap = new frameOverlayGFXClass();
			return _FrameOverlayBitmap;
		}
		
		
		[ Embed ( source = "images/toolbar.png" ) ] private static var toolbarBgGFXClass:Class;
		
		private static var  _ToolbarBgBitmap:Bitmap;
		
		public static function getToolbarBgBitmap():Bitmap
		{
			if( _ToolbarBgBitmap == null ) _ToolbarBgBitmap = new toolbarBgGFXClass();
			return _ToolbarBgBitmap;
		}
		
		
		[ Embed ( source = "images/done-button.png" ) ] private static var doneButtonGFXClass:Class;
		
		private static var  _DoneButtonBitmap:Bitmap;
		
		public static function getDoneButtonBitmap():Bitmap
		{
			if( _DoneButtonBitmap == null ) _DoneButtonBitmap = new doneButtonGFXClass();
			return _DoneButtonBitmap;
		}
		
		[ Embed ( source = "images/done-button-on.png" ) ] private static var doneButtonOnGFXClass:Class;
		
		private static var  _DoneButtonOnBitmap:Bitmap;
		
		public static function getDoneButtonOnBitmap():Bitmap
		{
			if( _DoneButtonOnBitmap == null ) _DoneButtonOnBitmap = new doneButtonOnGFXClass();
			return _DoneButtonOnBitmap;
		}
		
		[ Embed ( source = "images/toolbar-buttons/rotate_ccw.png" ) ] private static var toolbarButtonRotateCcwGFXClass:Class;
		
		private static var  _ToolbarButtonRotateCcwBitmap:Bitmap;
		
		public static function getToolbarButtonRotateCcwBitmap():Bitmap
		{
			if( _ToolbarButtonRotateCcwBitmap == null ) _ToolbarButtonRotateCcwBitmap = new toolbarButtonRotateCcwGFXClass();
			return _ToolbarButtonRotateCcwBitmap;
		}
		
		
		[ Embed ( source = "images/toolbar-buttons/rotate_cw.png" ) ] private static var toolbarButtonRotateCwGFXClass:Class;
		
		private static var  _ToolbarButtonRotateCwBitmap:Bitmap;
		
		public static function getToolbarButtonRotateCwBitmap():Bitmap
		{
			if( _ToolbarButtonRotateCwBitmap == null ) _ToolbarButtonRotateCwBitmap = new toolbarButtonRotateCwGFXClass();
			return _ToolbarButtonRotateCwBitmap;
		}
		
		[ Embed ( source = "images/toolbar-buttons/move_left.png" ) ] private static var toolbarButtonMoveLeftGFXClass:Class;
		
		private static var  _toolbarButtonMoveLeftBitmap:Bitmap;
		
		public static function getToolbarButtonMoveLeftBitmap():Bitmap
		{
			if( _toolbarButtonMoveLeftBitmap == null ) _toolbarButtonMoveLeftBitmap = new toolbarButtonMoveLeftGFXClass();
			return _toolbarButtonMoveLeftBitmap;
		}
		
		[ Embed ( source = "images/toolbar-buttons/move_right.png" ) ] private static var toolbarButtonMoveRightGFXClass:Class;
		
		private static var  _toolbarButtonMoveRightBitmap:Bitmap;
		
		public static function getToolbarButtonMoveRightBitmap():Bitmap
		{
			if( _toolbarButtonMoveRightBitmap == null ) _toolbarButtonMoveRightBitmap = new toolbarButtonMoveRightGFXClass();
			return _toolbarButtonMoveRightBitmap;
		}
		
		[ Embed ( source = "images/toolbar-buttons/move_up.png" ) ] private static var toolbarButtonMoveUpGFXClass:Class;
		
		private static var  _toolbarButtonMoveUpBitmap:Bitmap;
		
		public static function getToolbarButtonMoveUpBitmap():Bitmap
		{
			if( _toolbarButtonMoveUpBitmap == null ) _toolbarButtonMoveUpBitmap = new toolbarButtonMoveUpGFXClass();
			return _toolbarButtonMoveUpBitmap;
		}
		
		[ Embed ( source = "images/toolbar-buttons/move_down.png" ) ] private static var toolbarButtonMoveDownGFXClass:Class;
		
		private static var  _toolbarButtonMoveDownBitmap:Bitmap;
		
		public static function getToolbarButtonMoveDownBitmap():Bitmap
		{
			if( _toolbarButtonMoveDownBitmap == null ) _toolbarButtonMoveDownBitmap = new toolbarButtonMoveDownGFXClass();
			return _toolbarButtonMoveDownBitmap;
		}
		
		[ Embed ( source = "images/toolbar-buttons/zoom_in.png" ) ] private static var toolbarButtonZoomInGFXClass:Class;
		
		private static var  _toolbarButtonZoomInBitmap:Bitmap;
		
		public static function getToolbarButtonZoomInBitmap():Bitmap
		{
			if( _toolbarButtonZoomInBitmap == null ) _toolbarButtonZoomInBitmap = new toolbarButtonZoomInGFXClass();
			return _toolbarButtonZoomInBitmap;
		}
		
		[ Embed ( source = "images/toolbar-buttons/zoom_out.png" ) ] private static var toolbarButtonZoomOutGFXClass:Class;
		
		private static var  _toolbarButtonZoomOutBitmap:Bitmap;
		
		public static function getToolbarButtonZoomOutBitmap():Bitmap
		{
			if( _toolbarButtonZoomOutBitmap == null ) _toolbarButtonZoomOutBitmap = new toolbarButtonZoomOutGFXClass();
			return _toolbarButtonZoomOutBitmap;
		}
		
		[ Embed ( source = "images/toolbar-buttons/flip_left.png" ) ] private static var toolbarButtonFlipLeftGFXClass:Class;
		
		private static var  _toolbarButtonFlipLeftBitmap:Bitmap;
		
		public static function getToolbarButtonFlipLeftBitmap():Bitmap
		{
			if( _toolbarButtonFlipLeftBitmap == null ) _toolbarButtonFlipLeftBitmap = new toolbarButtonFlipLeftGFXClass();
			return _toolbarButtonFlipLeftBitmap;
		}
		
		[ Embed ( source = "images/toolbar-buttons/flip_right.png" ) ] private static var toolbarButtonFlipRightGFXClass:Class;
		
		private static var  _toolbarButtonFlipRightBitmap:Bitmap;
		
		public static function getToolbarButtonFlipRightBitmap():Bitmap
		{
			if( _toolbarButtonFlipRightBitmap == null ) _toolbarButtonFlipRightBitmap = new toolbarButtonFlipRightGFXClass();
			return _toolbarButtonFlipRightBitmap;
		}
		
		[ Embed ( source = "images/slider/sliderBg.png" ) ] private static var toolbarSliderBgGFXClass:Class;
		
		
		public static function getToolbarSliderBgBitmap():Bitmap
		{
			return new toolbarSliderBgGFXClass();
		}
		
		[ Embed ( source = "images/slider/sliderDot.png" ) ] private static var toolbarSliderDotGFXClass:Class;
		
		
		public static function getToolbarSliderDotBitmap():Bitmap
		{
			return new toolbarSliderDotGFXClass();

		}
	}
}