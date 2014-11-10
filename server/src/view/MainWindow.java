package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import view.interfaces.MainWindowInterface;
import view.interfaces.ServerStatusInter;

public class MainWindow extends JFrame implements MainWindowInterface {

	private ServerStatus serverStatus;
	
	public MainWindow(){

		
		serverStatus = new ServerStatus();
		
		setLayout(new BorderLayout());
		add(serverStatus,BorderLayout.CENTER);
	}

	@Override
	public ServerStatusInter getServerStatusInt() {
		return serverStatus;
	}
}
