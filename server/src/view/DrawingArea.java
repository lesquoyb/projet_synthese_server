package view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;

import javax.swing.JDialog;
import javax.swing.JFrame;

import view.interfaces.DrawingAreaInt;
import view.interfaces.ServerStatusInter;
import controller.DrawingCtrl;

public class DrawingArea extends JDialog implements DrawingAreaInt {

	private Canvas canvas;
	private BufferStrategy strategie;
	private Graphics graphics;
	private DrawingCtrl repaintCtrl;

	
	public DrawingArea(ServerStatusInter parent,int noConnexion){
		super((JFrame)parent);
		setTitle("zone de dessin du client numéro: "+noConnexion);
		setSize(1000,500);
		setBackground(Color.white);
		canvas = new Canvas();
		canvas.setIgnoreRepaint(true);
		setLayout(new BorderLayout());
		add(canvas,BorderLayout.CENTER);
		setVisible(true);
	}
	
	private void initGraphics(){
		if(graphics == null){
			canvas.createBufferStrategy(1); //nb de buffer
			try {
				Thread.sleep(150);
				strategie = canvas.getBufferStrategy();
				if(strategie != null){
					graphics = strategie.getDrawGraphics();	
					graphics.setColor(Color.white);
					graphics.drawRect(0, 0, getWidth(), getHeight());	
				}
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			drawAxis();
		}

	}

//	@Override
	private void drawAxis(){
		Graphics2D g2d = (Graphics2D)graphics;
		
		// change the center position and flip the y axis
		g2d.translate( canvas.getWidth()/2, canvas.getHeight()/2);
		g2d.scale(1f, -1f);
		
		int larg = canvas.getWidth()/2;
		int hauteur = canvas.getHeight()/2;
		//draw the background
		g2d.setBackground(Color.white);
		g2d.clearRect(-larg, hauteur, larg, -hauteur);

		//draw the axis
		g2d.setColor(Color.black);
		Line2D xAxis = new Line2D.Float(-larg + 50f,0f,larg-50f,0f);
		Line2D arrowA = new Line2D.Float(larg-50f,0f,larg-60f,-10f);
		Line2D arrowB = new Line2D.Float(larg-50f,0f,larg-60f,10f);
		g2d.draw(xAxis);
		g2d.draw(arrowB);
		g2d.draw(arrowA);
		
		Line2D yAxis = new Line2D.Float(0f,hauteur - 50f,0f,-hauteur +50f);
		arrowA = new Line2D.Float(0f,hauteur -50f,-10f,hauteur -60f);
		arrowB = new Line2D.Float(0f,hauteur -50f,10f,hauteur -60f);
		g2d.draw(yAxis);
		g2d.draw(arrowB);
		g2d.draw(arrowA);
	}
	
	@Override
	public void drawLine(String couleur, int x1, int y1, int x2, int y2) {
		initGraphics();
		graphics.setColor(Color.decode(couleur));
		graphics.drawLine(x1, y1, x2, y2);
		
	}

	@Override
	public void drawPolygon(String couleur, int[] x, int[] y,int nbPoints) {
		initGraphics();
		graphics.setColor(Color.decode(couleur));
		graphics.drawPolygon(x, y, nbPoints);
		
	}

	@Override
	public void drawEllipse(String couleur, int x, int y, int radius) {
		initGraphics();
		graphics.setColor(Color.decode(couleur));
		graphics.drawOval(x, y, radius, radius);
	}
	
	@Override
	public void showShapes(){
		if(graphics == null) {
			initGraphics();
		}
		
		strategie.show();
		//load the new screen
		initGraphics();
	}
	
	@Override
	public void validate(){
		super.validate();
		if(repaintCtrl != null){
			repaintCtrl.repaint();		
		}
	}
	
	@Override
	public void setRepaintCtrl(DrawingCtrl ctrl){
		repaintCtrl = ctrl;
	}
}

