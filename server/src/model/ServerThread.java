package model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


/**
 * Serveur dont le but est d'afficher gr�ce � awt les formes g�om�triques transmisent par le client.
 * Il doit respecter le pattern chain of responsibility
 * @author baptiste
 *
 */
public class ServerThread extends Thread{
		
	Socket socket;  
	int noConnexion; // num�ro du client distant
	BufferedReader fluxEntrant;	
	PrintStream fluxSortant;
	private String ip;
	private String port;
	
	public String getIp() {
		return ip;
	}

	public String getPort() {
		return port;
	}

	/**
	* Suppose socket d�j� connect� vers le client n� noConnexion
	* @param noConnexion : n� du client
	**/
	public ServerThread(Socket socket, ThreadGroup groupe, int noConnexion) throws IOException{
			
		super(groupe,"ReceveurEnvoyeur");
		this.socket = socket;
		this.noConnexion = noConnexion;
		
		fluxEntrant = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		/* � pr�sent fluxEntrant est pr�t pour lire du texte provenant du client */
		
		fluxSortant = new PrintStream(this.socket.getOutputStream());
		/* � pr�sent fluxSortant est pr�t pour renvoyer des r�ponses textuelles au client */
	}
	
	public void run(){
			
		String ligne, r�ponse;
		
		try{
			 while ( ! isInterrupted() ){
				 
			     ligne = fluxEntrant.readLine(); 
			     if(ligne != null){
				     System.out.println(" le client n� "+this.noConnexion+" a envoy� : ");
				     System.out.println(ligne); 
				     ligne = ligne.trim();
				     r�ponse = ligne.toUpperCase();
				     
				     fluxSortant.print(r�ponse+'\n'); 
				     sleep(5);
			     }
			}
		}
		catch(InterruptedException erreur) { /* le thread s'arr�te */}
		catch(IOException erreur) { System.err.println(" on ne peut pas lire sur le socket provenant du client");}
		 
	}
} 