package server;
import java.net.*;
import java.io.*;
/**
 * thread et socket cot� serveur :  traite les requ�tes d'un client
 * ici le serveur, pour chaque cha�ne de caract�res re�ue d'un client, la transforme en majuscule puis la renvoie au client
* */
public class ReceveurEnvoyeur extends Thread
{
Socket socket;  int noConnexion; // num�ro du client distant

BufferedReader fluxEntrant;	PrintStream fluxSortant;

/**
* Suppose socket d�j� connect� vers le client n� noConnexion
 * @param noConnexion : n� du client
 * */
public ReceveurEnvoyeur(Socket socket, ThreadGroup groupe, int noConnexion) throws IOException
{
super(groupe,"ReceveurEnvoyeur");
this.socket = socket;
this.noConnexion = noConnexion;

fluxEntrant = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
/* � pr�sent fluxEntrant est pr�t pour lire du texte provenant du client */

fluxSortant = new PrintStream(this.socket.getOutputStream());
/* � pr�sent fluxSortant est pr�t pour renvoyer des r�ponses textuelles au client */
}

public void run()
{
String ligne;	String r�ponse;

try
{
 while (!isInterrupted())
     {
     ligne = fluxEntrant.readLine(); // saisit le texte du client
     System.out.println(" le client n� "+this.noConnexion+" a envoy� : ");
     System.out.println(ligne); // �cho de la question sur la console
     ligne = ligne.trim();
     r�ponse = ligne.toUpperCase(); // calcul de la r�ponse
     
     fluxSortant.print(r�ponse+'\n'); // envoi de la reponse au client
     sleep(5);
     } //while
}
catch(InterruptedException erreur) { /* le thread s'arr�te */}
catch(IOException erreur) { System.err.println(" on ne peut pas lire sur le socket provenant du client");}
 
}// run
} // ReceveurEnvoyeur