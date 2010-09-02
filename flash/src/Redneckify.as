package
{
	import com.redneckify.GraphicsLib;
	import com.redneckify.MCUploader;
	import com.redneckify.ui.OutputArea;
	import com.redneckify.ui.Toolbar;
	import com.redneckify.ui.ToolbarEvent;
	
	import flash.display.BlendMode;
	import flash.display.DisplayObject;
	import flash.display.MovieClip;
	import flash.display.SimpleButton;
	import flash.display.Sprite;
	import flash.display.StageQuality;
	import flash.events.Event;
	import flash.events.MouseEvent;
	import flash.external.ExternalInterface;
	import flash.system.Security;
	import flash.system.SecurityDomain;
	import flash.system.System;
	
	
	[SWF(width='480', height='810', backgroundColor='#f9e4ca')]
	public class Redneckify extends Sprite
	{
		public function Redneckify()
		{
			
			stage.addEventListener( Event.ENTER_FRAME , Init );
			stage.quality = StageQuality.BEST;
		}
		
		protected var outputArea:com.redneckify.ui.OutputArea;
		protected var toolbar:com.redneckify.ui.Toolbar;		
		public function Init ( event:Event ):void
		{
			stage.removeEventListener( Event.ENTER_FRAME , Init );
			
			
			outputArea = new com.redneckify.ui.OutputArea();
			
			
			this.addChild( outputArea );
			toolbar =  new com.redneckify.ui.Toolbar(this) ;
			toolbar.y = outputArea.height;
			addChild( toolbar );
			
			toolbar.addEventListener(ToolbarEvent.ROTATE_CW, this.OnRotateCw);
			toolbar.addEventListener(ToolbarEvent.ROTATE_CCW, this.OnRotateCcw);
			
			toolbar.addEventListener(ToolbarEvent.MOVE_LEFT, this.OnMoveLeft);
			toolbar.addEventListener(ToolbarEvent.MOVE_RIGHT, this.OnMoveRight);
			toolbar.addEventListener(ToolbarEvent.MOVE_UP, this.OnMoveUp);
			toolbar.addEventListener(ToolbarEvent.MOVE_DOWN, this.OnMoveDown);
			
			toolbar.addEventListener(ToolbarEvent.ZOOM_IN, this.OnZoomIn);
			toolbar.addEventListener(ToolbarEvent.ZOOM_OUT, this.OnZoomOut);
			
			toolbar.addEventListener(ToolbarEvent.FLIP_LEFT, this.OnFlipLeft);
			toolbar.addEventListener(ToolbarEvent.FLIP_RIGHT, this.OnFlipRight);
			
		
			toolbar.addEventListener(ToolbarEvent.BRIGHTNESS, this.OnBrightness);
			toolbar.addEventListener(ToolbarEvent.CONTRAST, this.OnContrast);
			
			toolbar.addEventListener(ToolbarEvent.DONE, this.OnDone );

			this.InitExternalInterface();	
			this.LoadComplete();
			this.CheckFlashVarUserImage();
			
			
		}
		
		protected function CheckFlashVarUserImage():void
		{
			var paramList:Object = this.root.loaderInfo.parameters;
			if( paramList.userImage )
			{
				DoSetUserImage( paramList.userImage );
			}
			
		}
			
		protected function ShowSavingDlgModal():void
		{
			var greyBox:Sprite = new Sprite();
			greyBox.graphics.beginFill(0,0.75);
			greyBox.graphics.drawRect(0,0,this.width,810);
			greyBox.graphics.endFill();
			this.addChild(greyBox);
			var savingDlg:MovieClip = GraphicsLib.getSavingDialogMovieClip();
			savingDlg.x=0;
			savingDlg.y = 300;
			this.addChild(savingDlg);
				
		}
		
		protected function InitExternalInterface():void
		{
			ExternalInterface.addCallback("DoSetUserImage",DoSetUserImage);
			ExternalInterface.addCallback("DoSetOverlayImage",DoSetOverlayImage);
		}

		protected var uploader:MCUploader;
		
		// Done logics
		protected function OnDone(ev:Event):void
		{
			outputArea.ResetMoveMode(); // to avoid saving in semi-transparent mode //
			if( ! outputArea.ReadyToSave() ) {
				ExternalInterface.call("noOverlay");
				return;
			}
			this.ShowSavingDlgModal();
			uploader.CaptureAndUpload(outputArea.OutWidth,outputArea.OutHeight);
		}
		
		protected function LoadComplete():void
		{
			ExternalInterface.call("flashReady");
		}
		
		protected function DoSetUserImage(url:String):void
		{
			outputArea.SetUserImage(url);
		}
		
		protected function DoSetOverlayImage(url:String):void
		{
			outputArea.SetOverlayImage(url);
			uploader = new MCUploader(this.outputArea.GetResult() );
		}
		
		protected function OnBrightness(ev:Event):void
		{
			outputArea.setBrightness( toolbar.getBrightness() );
		}
		
		protected function OnContrast(ev:Event):void
		{
			outputArea.setContrast( toolbar.getContrast() );
		}
		
		
		protected function OnRotateCw(ev:Event):void 
		{
			outputArea.DoRotateCw();
		}
		
		protected function OnRotateCcw(ev:Event):void 
		{
			outputArea.DoRotateCcw();
		}
		
		protected function OnMoveLeft(ev:Event):void 
		{
			outputArea.DoMoveLeft();
		}
		
		protected function OnMoveRight(ev:Event):void 
		{
			outputArea.DoMoveRight();
		}
		
		protected function OnMoveUp(ev:Event):void 
		{
			outputArea.DoMoveUp();
		}
		
		protected function OnMoveDown(ev:Event):void 
		{
			outputArea.DoMoveDown();
		}
		
		protected function OnZoomIn(ev:Event):void 
		{
			outputArea.DoZoomIn();
		}
		
		protected function OnZoomOut(ev:Event):void 
		{
			outputArea.DoZoomOut();
		}
		
		protected function OnFlipLeft(ev:Event):void 
		{
			outputArea.DoFlip();
		}
		
		protected function OnFlipRight(ev:Event):void 
		{
			outputArea.DoFlip();
		}
	}
}