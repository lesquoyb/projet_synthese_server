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

/**
 * 
 * @author baptiste
 *Controller de {@link DrawingArea}, il contient la liste des formes et se charge de les afficher.
 */
public class DrawingArea extends JFrame implements DrawingAreaInt {


	private BufferStrategy strategie;
	private Graphics graphics;
	private DrawingCtrl repaintCtrl;

	
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

		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		initGraphics();
	}
	
	private void initGraphics(){
		do{
			graphics = strategie.getDrawGraphics();	
		}while(strategie.contentsLost());
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
		graphics.drawOval(x - (radius/2), y - (radius/2), radius, radius); // on décale car les données envoyées sontcelles du centre du cercle alors que drawOval prend le point en haut à gauche
	}
	
	@Override
	public void showShapes(){
		graphics.dispose();
		strategie.show();
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


}

