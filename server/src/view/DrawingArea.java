package view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JDialog;
import javax.swing.JFrame;

import view.interfaces.DrawingAreaInt;
import view.interfaces.MainWindowInterface;
import view.interfaces.ServerStatusInter;

public class DrawingArea extends JDialog implements DrawingAreaInt {

	private Canvas canvas;
	private BufferStrategy strategie;
	private Graphics graphics;

	
	public DrawingArea(ServerStatusInter parent,int noConnexion){
		super((JFrame)parent);
		setTitle("zone de dessin du client numéro: "+noConnexion);
		setSize(500,500);
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
				}
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}		
	}

	@Override
	public void drawLine(int x, int y) {
		initGraphics();
		
	}

	@Override
	public void drawPolygon(int[] x, int[] y) {
		initGraphics();
		
	}

	@Override
	public void drawEllipse(int x, int y, int radius) {
		initGraphics();
		graphics.drawOval(x, y, radius, radius);
		strategie.show();
	}
	
	
}
