package model;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args)throws Exception{
		
		ServerSocket serveur = new ServerSocket(0); // place un serveur à l'écoute sur le port passé, si 0, alors choix parmi les ports libre
	
		System.out.println("serveur démarré: "+serveur);
	
		InetAddress cetteMachine = InetAddress.getLocalHost();
	
		System.out.println("adresse IP du serveur: "+cetteMachine.getHostAddress());
		System.out.println("port du serveur : "+ serveur.getLocalPort());
	
		ThreadGroup groupe = new ThreadGroup("socketsClients");
		int noConnexion = 0;
		while(true){
		    
			Socket nouveauClientSocket = serveur.accept(); // attente de connexion de la part d'un nouveau client
		    ++noConnexion; // la connexion a eu lieu et un socket a été créé : nouveauClientSocket
		    System.out.println("Connexion réussie n°: "+noConnexion);
		    
		    /* à présent création d'un thread pour gérer les transactions avec le nouvau client en parallèle 
		     * avec les autres clients déjà connectés et avec l'attente perpétuelle du servur*/
		    
		    Server nouveauClientThread = new Server(nouveauClientSocket, groupe, noConnexion); 
		    nouveauClientThread.start();
		}
	} 


}
