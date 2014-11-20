package model;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import controller.ServerCtrl;


/**
 * Server représente un serveur web. À l'initialisation il choisi une adresse ip et un port et se met à écouter.
 * Il est chargé de créer un thread pour chaque nouveau client se connectant.
 * @author baptiste
 *
 */
public class Server extends Thread {

	private int noConnexion;
	private ServerSocket serveur;
	private ThreadGroup groupe;
	private String port;
	private String ip;
	private ServerCtrl listenerNewThread;
	private Socket nouveauClientSocket;

	
	public Server(){
		
		try {
			serveur = new ServerSocket(9111);//TODO change it to zero		
			InetAddress cetteMachine = InetAddress.getLocalHost();
			ip = cetteMachine.getHostAddress();
			port = String.valueOf(serveur.getLocalPort());	
			groupe = new ThreadGroup("socketsClients");
			noConnexion = 0;
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * boucle infinie, à chaque nouvelle connexion de client on crée un {@link ServerThread}
	 */
	public void run(){
		
		try{
			while(true){
			    
				nouveauClientSocket = serveur.accept(); // bloquant tant que pas de nouveau client
			    noConnexion++;
			    listenerNewThread.nouveauClient();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	
	/**
	 * Permet de passer le listener qui se déclenchera à la création de chaque nouveau thread.
	 * @param controller
	 */
	public void setListenerNewThread(ServerCtrl controller){
		listenerNewThread = controller;
	}
	
	/**
	 * 
	 * @return le groupe de thread auquel appartient le serveur
	 */
	public ThreadGroup getGroup(){
		return groupe;
	}
	
	/**
	 * 
	 * @return le numéro de port qui est écouté
	 */
	public String getPort(){
		return port;
	}
	/**
	 * 
	 * @return l'adresse ip du serveur
	 */
	public String getIp(){
		return ip;
	}
	
	/**
	 * 
	 * @return la socket correspondant au nouveau client venant de se connecter
	 */
	public Socket getNouveauClient(){
		return nouveauClientSocket;
	}
	
	/**
	 * 
	 * @return le nombre de client qui se sont connectés depuis le démarrage du serveur
	 */
	public String getNoConnexion(){
		return String.valueOf(noConnexion);
	}

}
