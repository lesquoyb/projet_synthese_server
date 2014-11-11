

import model.Server;
import view.ServerStatus;
import controller.ServerCtrl;

public class Main {

	public static void main(String[] args)throws Exception{
		
		ServerStatus m = new ServerStatus();
		Server s  = new Server();
    	ServerCtrl cont = new ServerCtrl(s, m);

	} 


}
