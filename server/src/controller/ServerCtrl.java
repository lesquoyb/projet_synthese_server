package controller;

import java.io.IOException;

import model.Server;
import model.DedicatedThread;
import view.DrawingArea;
import view.interfaces.ServerStatusInter;


/**
 * Controlleur gérant la connexion d'un nouveau client. 
 * A chaque nouveau client, une zone de dessin est créée ainsi qu'un {@link DedicatedThread} et un {@link DrawingCtrl} associé.
 * @author baptiste
 *
 */
public class ServerCtrl {

	

	private Server server;
	private ServerStatusInter view;
	
	/**
	 * Quand on a un nouveau client qui se connecte, on crée un nouveau ServerThread et son controlleur associé
	 */
	public void nouveauClient(){
	    DedicatedThread nouveauClientThread;
		try {
			int noConnexion = Integer.parseInt(server.getNoConnexion());
			nouveauClientThread = new DedicatedThread(server.getNouveauClient(), server.getGroup(), noConnexion );
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
