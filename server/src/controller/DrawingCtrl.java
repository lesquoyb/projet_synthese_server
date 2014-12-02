package controller;

import java.util.ArrayList;

import model.DedicatedThread;
import view.DrawingArea;
import view.interfaces.DrawingAreaInt;
import controller.drawCOR.copy.ParserFacade;

/**
 * 
 * @author baptiste
 * Controller de la vue {@link DrawingArea}, s'occupe de receptionner les formes envoyées à {@link DedicatedServer}, 
 * et de les dessiner sur la vue à l'aide de la Chain Of Responsibility {@link ParserFacade}.
 */
public class DrawingCtrl  {
	


	private DrawingAreaInt view;
	private DedicatedThread model;
	private ParserFacade parser;
	private ArrayList<String> formes;
	
	public DrawingCtrl(DrawingAreaInt d, DedicatedThread s){
		
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
