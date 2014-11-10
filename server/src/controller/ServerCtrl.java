package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.channels.NoConnectionPendingException;

import model.Server;
import model.ServerThread;
import view.DrawingArea;
import view.interfaces.DrawingAreaInt;
import view.interfaces.ServerStatusInter;

public class ServerCtrl implements ActionListener {

	

	private Server server;
	private ServerStatusInter view;
	
	/**
	 * Quand l'action est déclenchée, on crée un nouveau ServerThread et son controlleur associé
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	    ServerThread nouveauClientThread;
		try {
			int noConnexion = Integer.parseInt(server.getNoConnexion());
			nouveauClientThread = new ServerThread(server.getNouveauClient(), server.getGroup(), noConnexion );
		    view.setNoConnexion(String.valueOf(noConnexion));
		    DrawingArea draw = new DrawingArea(view,noConnexion);
		    DrawingCtrl control = new DrawingCtrl(draw, nouveauClientThread);
		    nouveauClientThread.start();    
		} 
		catch (NumberFormatException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
	}

	
	public ServerCtrl(Server s,ServerStatusInter v){
		server = s;
		view = v;
		init();
	}
	
	private void init(){
		view.setIP(server.getIp());
		view.setPort(server.getPort());
		view.setNoConnexion(server.getNoConnexion());
		server.setListenerNewThread(this);
		server.start();
	}
}
