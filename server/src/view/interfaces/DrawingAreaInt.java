package view.interfaces;

import controller.DrawingCtrl;

public interface DrawingAreaInt {
	
	public void drawLine(String couleur,int x1, int y1,int x2, int y2);
	
	public void drawPolygon(String couleur,int[] x,int[] y,int nbPoints);
	
	public void drawEllipse(String couleur,int x, int y, int radius);
	
	public void showShapes();
	
	public void setRepaintCtrl(DrawingCtrl ctrl);
	
	//public void drawAxis();
	

}
