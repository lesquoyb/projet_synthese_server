package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ServerThread;
import model.drawCOR.ParserFacade;
import view.interfaces.DrawingAreaInt;

public class DrawingCtrl  {
	


	private DrawingAreaInt view;
	private ServerThread model;
	private ParserFacade parser;
	
	public DrawingCtrl(DrawingAreaInt d, ServerThread s){
		view = d;
		model = s;
		model.setDrawingListener(this);
		parser = new ParserFacade();
	}
	
	/**
	 * Cette méthode est enclenchée quand une forme est envoyé par le client
	 */
	public void drawObject(String toParse){
		parser.draw(toParse, view);
	}

}
