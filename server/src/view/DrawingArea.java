package view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawingArea extends JPanel {

	private Canvas canvas;
	private BufferStrategy strategie;
	private Graphics graphics;

	
	public DrawingArea(){
		super();
		setVisible(true);
		setBackground(Color.white);
		canvas = new Canvas();
		canvas.setIgnoreRepaint(true);
		canvas.createBufferStrategy(1); //nb de buffer
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while(graphics == null) {
			strategie = canvas.getBufferStrategy();
			if(strategie != null){
				graphics = strategie.getDrawGraphics();				
			}
		}
		add(canvas);

	}
	
	public void draw(){
		graphics.drawLine(0, 1, 600, 600);
		strategie.show();
	}
}
