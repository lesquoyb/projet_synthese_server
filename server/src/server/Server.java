package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
/**
 * exemple de serveur très simple : renvoie toute chaine de caractères envoyée par un client 
 * après l'avoir mise en majuscule
 *
 * */
public class Server extends Thread{
	
	Socket socket;  int noConnexion; // numéro du client distant

	BufferedReader fluxEntrant;	PrintStream fluxSortant;

	/**
	* Suppose socket déjà connecté vers le client n° noConnexion
	* @param noConnexion : n° du client
	**/
	public Server(Socket socket, ThreadGroup groupe, int noConnexion) throws IOException{
		super(groupe,"ReceveurEnvoyeur");
		this.socket = socket;
		this.noConnexion = noConnexion;
	
		fluxEntrant = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		/* à présent fluxEntrant est prêt pour lire du texte provenant du client */
	
		fluxSortant = new PrintStream(this.socket.getOutputStream());
		/* à présent fluxSortant est prêt pour renvoyer des réponses textuelles au client */
	}

	public void run(){
		
		String ligne;	String réponse;
	
		try{
			 while (!isInterrupted()){
				 
			     ligne = fluxEntrant.readLine(); // saisit le texte du client
			     System.out.println(" le client n° "+this.noConnexion+" a envoyé : ");
			     System.out.println(ligne); // écho de la question sur la console
			     ligne = ligne.trim();
			     réponse = ligne.toUpperCase(); // calcul de la réponse
			     
			     fluxSortant.println(réponse); // envoi de la reponse au client
			     sleep(5);
			} 
		}
		catch(InterruptedException erreur) {
			/* le thread s'arrête */
		}
		catch(IOException erreur) { 
			System.err.println(" on ne peut pas lire sur le socket provenant du client");
		}
		 
	}// run
} 