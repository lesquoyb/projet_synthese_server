package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ServerThread;
import view.interfaces.DrawingAreaInt;

public class DrawingCtrl implements ActionListener {
	


	private DrawingAreaInt view;
	private ServerThread model;
	
	public DrawingCtrl(DrawingAreaInt d, ServerThread s){
		view = d;
		model = s;
		model.setDrawingListener(this);
	}
	
	/**
	 * Cette méthode est enclenchée quand une forme est envoyé par le client
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		view.drawEllipse(50, 50, 300);
		
	}

}
