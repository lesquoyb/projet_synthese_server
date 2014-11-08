package view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.interfaces.ServerStatusInter;

public class ServerStatus extends JPanel implements ServerStatusInter{

	private String ip;
	private String port;
	
	
	public ServerStatus(){
		
	}
	
	@Override
	public void setIP(String ip) {
		this.ip = ip;		
	}

	@Override
	public void setPort(String port) {
		this.port = port;		
	}

	@Override
	public void displayError(String mess) {
		JOptionPane.showConfirmDialog(null, mess);		
	}

}
