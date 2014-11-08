package model;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args)throws Exception{
		
		ServerSocket serveur = new ServerSocket(0); // place un serveur � l'�coute sur le port pass�, si 0, alors choix parmi les ports libre
	
		System.out.println("serveur d�marr�: "+serveur);
	
		InetAddress cetteMachine = InetAddress.getLocalHost();
	
		System.out.println("adresse IP du serveur: "+cetteMachine.getHostAddress());
		System.out.println("port du serveur : "+ serveur.getLocalPort());
	
		ThreadGroup groupe = new ThreadGroup("socketsClients");
		int noConnexion = 0;
		while(true){
		    
			Socket nouveauClientSocket = serveur.accept(); // attente de connexion de la part d'un nouveau client
		    ++noConnexion; // la connexion a eu lieu et un socket a �t� cr�� : nouveauClientSocket
		    System.out.println("Connexion r�ussie n�: "+noConnexion);
		    
		    /* � pr�sent cr�ation d'un thread pour g�rer les transactions avec le nouvau client en parall�le 
		     * avec les autres clients d�j� connect�s et avec l'attente perp�tuelle du servur*/
		    
		    Server nouveauClientThread = new Server(nouveauClientSocket, groupe, noConnexion); 
		    nouveauClientThread.start();
		}
	} 


}
