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
		view.setRepaintCtrl(this);
		model.setDrawingListener(this);
		parser = new ParserFacade();
	}
	
	/**
	 * Cette méthode est enclenchée quand une forme est envoyée par le client
	 */
	public void drawObject(String toParse){
		formes.add(toParse);
		repaint();
	}
	
	/**
	 * Redessine la vue à l'aide de la liste de formes.
	 */
	public void repaint(){
		for(String toParse: formes){
			parser.draw(toParse, view);
		}
		view.showShapes();
	}

}
