package model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import controller.DrawingCtrl;


/**
 * Serveur dont le but est d'afficher gr�ce � awt les formes g�om�triques transmisent par le client.
 * Il doit respecter le pattern chain of responsibility
 * @author baptiste
 *
 */
public class ServerThread extends Thread{
		
	private int noConnexion; // numéro du client distant
	private BufferedReader fluxEntrant;	
	private PrintStream fluxSortant;
	private String ip;
	private String port;
	private DrawingCtrl drawingListener;
	private Socket socket;
	
	
	public String getIp() {
		return ip;
	}

	public String getPort() {
		return port;
	}

	/**
	* Suppose socket déjà connecté vers le client n° noConnexion
	* @param noConnexion : n° du client
	**/
	public ServerThread(Socket socket, ThreadGroup groupe, int noConnexion) throws IOException{
			
		super(groupe,"ReceveurEnvoyeur");
		this.socket = socket;
		this.noConnexion = noConnexion;
		
		fluxEntrant = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		
		fluxSortant = new PrintStream(this.socket.getOutputStream());
	}
	
	public void run(){
			
		String ligne, reponse;
		
		try{
			 while ( ! isInterrupted() ){
				 
			     ligne = fluxEntrant.readLine(); 
			     if(ligne != null){
			    	 drawingListener.actionPerformed(null);
				     System.out.println(" le client n° "+this.noConnexion+" a envoyé : ");
				     System.out.println(ligne); 
				     ligne = ligne.trim();
				     reponse = ligne.toUpperCase();
				     
				     fluxSortant.print(reponse+'\n'); 
				     sleep(5);
			     }
			}
		}
		catch(InterruptedException erreur) { }
		catch(IOException erreur) { System.err.println(" on ne peut pas lire sur le socket provenant du client");}
		 
	}
	
	public void setDrawingListener(DrawingCtrl cont){
		drawingListener = cont;
	}
	
} 