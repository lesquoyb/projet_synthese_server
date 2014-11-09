package model;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private int noConnexion;
	private ServerSocket serveur;
	private ThreadGroup groupe;
	
	public Server(){
		
		try {
			serveur = new ServerSocket(0); // place un serveur à l'écoute sur le port passé, si 0, alors choix parmi les ports libre
			
			System.out.println("serveur démarré: "+serveur);
		
			InetAddress cetteMachine = InetAddress.getLocalHost();
		
			System.out.println("adresse IP du serveur: "+cetteMachine.getHostAddress());
			System.out.println("port du serveur : "+ serveur.getLocalPort());
		
			groupe = new ThreadGroup("socketsClients");
			noConnexion = 0;
			mainLoop();
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void mainLoop() throws Exception {
		while(true){
		    
			Socket nouveauClientSocket = serveur.accept(); // attente de connexion de la part d'un nouveau client
		    ++noConnexion;
		    System.out.println("Connexion réussie n°: "+noConnexion);
		    ServerThread nouveauClientThread = new ServerThread(nouveauClientSocket, groupe, noConnexion); 
		    nouveauClientThread.start();
		}
	}


}
