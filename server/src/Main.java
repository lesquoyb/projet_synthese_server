

import controller.ServerCtrl;
import model.Server;
import view.MainWindow;
import view.ServerStatus;

public class Main {

	public static void main(String[] args)throws Exception{
		
		ServerStatus m = new ServerStatus();
		Server s  = new Server();
    	ServerCtrl cont = new ServerCtrl(s, m);

	} 


}
