package view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JScrollBar;

import view.interfaces.DrawingAreaInt;
import view.interfaces.ServerStatusInter;
import controller.DrawingCtrl;

public class DrawingArea extends JFrame implements DrawingAreaInt {


	private BufferStrategy strategie;
	private Graphics graphics;
	private DrawingCtrl repaintCtrl;
	private JScrollBar scrollBar;

	
	public DrawingArea(ServerStatusInter parent,int noConnexion){
		super();
		setAlwaysOnTop(false);
		setTitle("Zone de dessin du client numéro: "+noConnexion);
		setSize(1000,500);
		setBackground(Color.white);
		setIgnoreRepaint(true);
		setLayout(new BorderLayout());
		
		setLocationRelativeTo(null);
		setVisible(true);
		createBufferStrategy(2); //nb de buffer
		
		try {				

			Thread.sleep(300);
			strategie = getBufferStrategy();
			graphics = strategie.getDrawGraphics();	

		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void initGraphics(){
			graphics = strategie.getDrawGraphics();	
			drawAxis();
	}

	
	private void drawAxis(){
		Graphics2D g2d = (Graphics2D)graphics;

		int larg = getWidth()/2;
		int hauteur = getHeight()/2;
		// change the center position and flip the y axis
		g2d.translate( getWidth()/2, getHeight()/2);
		g2d.scale(1f, -1f);
		
		//draw the background
		g2d.setBackground(Color.white);
		g2d.clearRect(-larg, hauteur, larg, -hauteur);

		//draw the axis
		g2d.setColor(Color.black);
		g2d.drawLine(-larg + 50,0,larg-50,0);
		g2d.drawLine(larg-50,0,larg-60,-10);
		g2d.drawLine(larg-50,0,larg-60,10);
		g2d.drawLine(0,hauteur - 50,0,-hauteur +50);
		g2d.drawLine(0,hauteur -50,-10,hauteur -60);
		g2d.drawLine(0,hauteur -50,10,hauteur -60);

	}
	
	@Override
	public void drawLine(String couleur, int x1, int y1, int x2, int y2) {
		graphics.setColor(Color.decode(couleur));
		graphics.drawLine(x1, y1, x2, y2);
		
	}

	@Override
	public void drawPolygon(String couleur, int[] x, int[] y,int nbPoints) {
		graphics.setColor(Color.decode(couleur));
		graphics.drawPolygon(x, y, nbPoints);
		
	}

	@Override
	public void drawEllipse(String couleur, int x, int y, int radius) {
		graphics.setColor(Color.decode(couleur));
		graphics.drawOval(x, y, radius, radius);
	}
	
	@Override
	public void showShapes(){
		if(graphics == null) {
			initGraphics(); //first time
		}
		strategie.show();
		graphics.dispose();
		initGraphics();
	}
	

	@Override
	public void validate() {
		super.validate();
		if(repaintCtrl != null){
			repaintCtrl.repaint();		
		}
	}

	@Override
	public void setRepaintCtrl(DrawingCtrl ctrl){
		repaintCtrl = ctrl;
	}

	@Override
	public void setCanvasSize(int width, int height) {
	//	canvas.setSize(width,height);
	}
	
	private void refreshExtent(){
		//TODO
	}
	
}

