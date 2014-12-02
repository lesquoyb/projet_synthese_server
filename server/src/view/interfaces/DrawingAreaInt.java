package view.interfaces;

import controller.DrawingCtrl;

public interface DrawingAreaInt {
	
	/**
	 * Dessine une ligne sur le buffer.
	 * @param couleur
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void drawLine(String couleur,int x1, int y1,int x2, int y2);
	
	/**
	 * Dessine un polygone sur le buffer.
	 * @param couleur
	 * @param x
	 * @param y
	 * @param nbPoints
	 */
	public void drawPolygon(String couleur,int[] x,int[] y,int nbPoints);
	
	/**
	 * Dessine un cercle sur le buffer.
	 * @param couleur
	 * @param x
	 * @param y
	 * @param radius
	 */
	public void drawEllipse(String couleur,int x, int y, int radius);
	
	/**
	 * Affiche le buffer sur lequel on a écrit.
	 */
	public void showShapes();
	
	public void setRepaintCtrl(DrawingCtrl ctrl);
	

}
