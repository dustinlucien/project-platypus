package com.redneckify
{
	import com.redneckify.utils.ColorMatrix;
	
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.filters.ColorMatrixFilter;
	import flash.geom.ColorTransform;
	import flash.geom.Matrix;
	import flash.geom.Point;
	import flash.geom.Transform;

	
	
	public class ImagePlaceholder extends BasePlaceholder
	{
		public function ImagePlaceholder()
		{
			super();
		}
		
		
		
		
		
		private function rotateAroundCenter (ob:*, angleDegrees:Number, ptRotationPoint:Point):void {
			var m:Matrix=ob.transform.matrix;
			m.tx -= ptRotationPoint.x;
			m.ty -= ptRotationPoint.y;
			m.rotate (angleDegrees*(Math.PI/180));
			m.tx += ptRotationPoint.x;
			m.ty += ptRotationPoint.y;
			ob.transform.matrix=m;
		}
		
		private function moveOb (ob:*,dx: Number, dy: Number):void {
			var m:Matrix=ob.transform.matrix;
			m.tx += dx;
			m.ty += dy;
			
			ob.transform.matrix=m;
		}
		
		private function zoomOb(ob:*,a: Number):void {
			var m:Matrix=ob.transform.matrix;
			m.tx -= ptRotationPoint.x;
			m.ty -= ptRotationPoint.y;
			m.scale(  a, a );			
			m.tx += ptRotationPoint.x;
			m.ty += ptRotationPoint.y;
			ob.transform.matrix=m;
		}
		
		
		public function flipHorizontal(ob:*):void
		{
			var imageCorrectionTx:Number = 0;
			var matrix:Matrix = ob.transform.matrix;
			if( matrix.a != -1 )
			{
			matrix.a=-1;
			matrix.tx+=  RedneckifyStatics.OutputWidth  ;
			imageCorrectionTx = RedneckifyStatics.OutputWidth-myImage.transform.matrix.tx - myImage.width; //correct image position due to mirroring //
			}else
			{
				matrix.a*=-1;
				matrix.tx-=RedneckifyStatics.OutputWidth  ;
				imageCorrectionTx = RedneckifyStatics.OutputWidth-myImage.transform.matrix.tx - myImage.width;
				
			}
			
			//myImage.transform.matrix.translate( myImage.transform.matrix.tx - imageCorrectionTx,0);
			moveOb(myImage, imageCorrectionTx-myImage.transform.matrix.tx ,0);
			ob.transform.matrix=matrix;
			
		}
		
		
		
		public function DoRotate(angl:Number):void
		{
			rotateAroundCenter(myImage, angl*this.transform.matrix.a, this.ptRotationPoint);
		}
		
		public function DoMove( dx: Number, dy: Number ):void
		{
			ptRotationPoint.x+= dx*this.transform.matrix.a; // *this.transform.matrix.a needed to support right side after flip
			ptRotationPoint.y+= dy;
			moveOb( myImage,dx*this.transform.matrix.a,dy);
		}
		
		public function DoZoom( a:Number ):void
		{
			zoomOb( myImage , a );	
		}
		public function DoFlip():void
		{
			flipHorizontal(this);
		}
		
		public function DoSetBrightness(v:Number):void
		{
			brightnessValue = v;
			updateColorMatrix();
		}
		public function DoSetContrast(v:Number):void
		{
			contrastValue = v;
			updateColorMatrix();
		}
		
		private var brightnessValue:Number;
		private var contrastValue:Number;
		
		protected function updateColorMatrix():void 
		{
			var cm:com.redneckify.utils.ColorMatrix = new ColorMatrix();
			cm.adjustColor(brightnessValue, contrastValue, 0,0);
			myImage.filters = [new flash.filters.ColorMatrixFilter(cm)];
		}
		
		
	}
}