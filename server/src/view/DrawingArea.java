package view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JDialog;
import javax.swing.JFrame;

import controller.DrawingCtrl;
import view.interfaces.DrawingAreaInt;
import view.interfaces.ServerStatusInter;

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

		}
	}


	
	@Override
	public void drawLine(String couleur, int x1, int y1, int x2, int y2) {
		initGraphics();
		graphics.setColor(Color.decode(couleur));
		graphics.drawLine(x1, y1, x2, y2);
		strategie.show();
		
	}

	@Override
	public void drawPolygon(String couleur, int[] x, int[] y,int nbPoints) {
		initGraphics();
		graphics.setColor(Color.decode(couleur));
		graphics.drawPolygon(x, y, nbPoints);
		strategie.show();
		
	}

	@Override
	public void drawEllipse(String couleur, int x, int y, int radius) {
		initGraphics();
		graphics.setColor(Color.decode(couleur));
		graphics.drawOval(x, y, radius, radius);
		strategie.show();
	}
	

	
	
	
}

/*
 package view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JDialog;
import javax.swing.JFrame;

import view.interfaces.DrawingAreaInt;
import view.interfaces.ServerStatusInter;

public class DrawingArea extends JDialog implements DrawingAreaInt {

	private Canvas canvas;
	private BufferStrategy strategie;
	private Graphics graphics;

	
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
		canvas.createBufferStrategy(1); // 1 == nb de buffer

	}
	

	private void initGraphics(){
		try {
			Thread.sleep(150);
			strategie = canvas.getBufferStrategy();
			if(strategie != null){
				graphics = strategie.getDrawGraphics();	
			}
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}	

		clear();
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

	private void clear() {
		graphics.setColor(Color.white);
		graphics.drawRect(0, 0, getWidth(), getHeight());	
		
	}
	@Override
	public void show(){
		if(graphics == null) initGraphics();
		strategie.show();
		//load the new screen
		initGraphics();
	}
	
	
	
	
}
*/
