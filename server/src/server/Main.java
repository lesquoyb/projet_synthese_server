package server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args)throws Exception{
	ServerSocket serveur;
	serveur = new ServerSocket(9111); // place un serveur à l'écoute sur le port 9111
	                     //si le paramètre = 0 alors la JVM choisit un port libre

	System.out.println("serveur de majuscule démarré : "+serveur);

	InetAddress cetteMachine;
	int portLocal;
	ThreadGroup groupe;

	portLocal = serveur.getLocalPort();

	cetteMachine = InetAddress.getLocalHost();

	System.out.println("adresse IP du serveur de majuscule : "+cetteMachine.getHostAddress());
	System.out.println("port du serveur : "+portLocal);

	groupe = new ThreadGroup("socketsClients");
	int noConnexion = 0;
	while(true) //attente infinie du serveur sur le port
	    {
	    Socket nouveauClientSocket;
	    ReceveurEnvoyeur nouveauClientThread;
	    
	    nouveauClientSocket = serveur.accept(); // attente de connexion de la part d'un nouveau client
	    ++noConnexion; // la connexion a eu lieu et un socket a été créé : nouveauClientSocket
	    System.out.println("Connexion réussie n° : "+noConnexion);
	    
	    /* à présent création d'un thread pour gérer les transactions avec le nouvau client en parallèle 
	     * avec les autres clients déjà connectés et avec l'attente perpétuelle du servur*/
	    
	    nouveauClientThread = new ReceveurEnvoyeur(nouveauClientSocket, groupe, noConnexion); 
	    nouveauClientThread.start();
	    }
	} // main


}
