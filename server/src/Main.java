

import javax.swing.JOptionPane;

import model.Server;
import view.ServerStatus;
import controller.ServerCtrl;

public class Main {

	public static void main(String[] args)throws Exception{
		
		ServerStatus m = new ServerStatus();
		boolean init = false;
		Server s = null;
		while(!init){
			try{
				s  = new Server();
				init = true;
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, e.getMessage());//e.printStackTrace();
				try {
					Thread.sleep(3000); // 3 secondes pour fermer la fenêtre avant le prochain essai.
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
    	ServerCtrl cont = new ServerCtrl(s, m);

	} 


}
