package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
/**
 * exemple de serveur tr�s simple : renvoie toute chaine de caract�res envoy�e par un client 
 * apr�s l'avoir mise en majuscule
 *
 * */
public class Server extends Thread{
	
	Socket socket;  int noConnexion; // num�ro du client distant

	BufferedReader fluxEntrant;	PrintStream fluxSortant;

	/**
	* Suppose socket d�j� connect� vers le client n� noConnexion
	* @param noConnexion : n� du client
	**/
	public Server(Socket socket, ThreadGroup groupe, int noConnexion) throws IOException{
		super(groupe,"ReceveurEnvoyeur");
		this.socket = socket;
		this.noConnexion = noConnexion;
	
		fluxEntrant = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		/* � pr�sent fluxEntrant est pr�t pour lire du texte provenant du client */
	
		fluxSortant = new PrintStream(this.socket.getOutputStream());
		/* � pr�sent fluxSortant est pr�t pour renvoyer des r�ponses textuelles au client */
	}

	public void run(){
		
		String ligne;	String r�ponse;
	
		try{
			 while (!isInterrupted()){
				 
			     ligne = fluxEntrant.readLine(); // saisit le texte du client
			     System.out.println(" le client n� "+this.noConnexion+" a envoy� : ");
			     System.out.println(ligne); // �cho de la question sur la console
			     ligne = ligne.trim();
			     r�ponse = ligne.toUpperCase(); // calcul de la r�ponse
			     
			     fluxSortant.println(r�ponse); // envoi de la reponse au client
			     sleep(5);
			} 
		}
		catch(InterruptedException erreur) {
			/* le thread s'arr�te */
		}
		catch(IOException erreur) { 
			System.err.println(" on ne peut pas lire sur le socket provenant du client");
		}
		 
	}// run
} 