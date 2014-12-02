package model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import controller.DrawingCtrl;


/**
 * Thread dont le but est d'afficher gr�ce � awt les formes g�om�triques transmisent par le client.
 * Il doit y en avoir un par client.
 * Il doit respecter le pattern chain of responsibility
 * @author baptiste
 *
 */
public class DedicatedThread extends Thread{
		
	private int noConnexion; // numéro du client distant
	private BufferedReader fluxEntrant;	
	private PrintStream fluxSortant;
	private String ip;
	private String port;
	private DrawingCtrl drawingController;
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
	public DedicatedThread(Socket socket, ThreadGroup groupe, int noConnexion) throws IOException{
			
		super(groupe,"ReceveurEnvoyeur");
		this.socket = socket;
		this.noConnexion = noConnexion;
		
		fluxEntrant = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		
		fluxSortant = new PrintStream(this.socket.getOutputStream());
	}
	
	public void run(){
			
		String messageRecu, reponse;
		
		try{
			 while ( ! isInterrupted() ){
				 
			     messageRecu = fluxEntrant.readLine(); 
			     if(messageRecu != null){
			    	 drawingController.drawObject(messageRecu);
				     System.out.println(" le client n° "+this.noConnexion+" a envoyé : ");
				     System.out.println(messageRecu); 
				     
				     fluxSortant.print(1+'\n'); 
				     sleep(5);
			     }
			}
		}
		catch(InterruptedException erreur) { }
		catch(IOException erreur) { System.err.println(" on ne peut pas lire sur le socket provenant du client");		}
		 
	}
	
	public void setDrawingListener(DrawingCtrl cont){
		drawingController = cont;
	}
	
} 