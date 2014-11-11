package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.ServerThread;
import model.drawCOR.ParserFacade;
import view.interfaces.DrawingAreaInt;

public class DrawingCtrl  {
	


	private DrawingAreaInt view;
	private ServerThread model;
	private ParserFacade parser;
	private ArrayList<String> formes;
	
	public DrawingCtrl(DrawingAreaInt d, ServerThread s){
		
		formes = new ArrayList<String>();
		view = d;
		model = s;
		model.setDrawingListener(this);
		parser = new ParserFacade();
	}
	
	/**
	 * Cette méthode est enclenchée quand une forme est envoyé par le client
	 */
	public void drawObject(String toParse){
		formes.add(toParse);
		repaint();
	}
	
	private void repaint(){
		for(String toParse: formes){
			parser.draw(toParse, view);
		}
		view.show();
	}

}
