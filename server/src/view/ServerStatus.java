package view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.interfaces.ServerStatusInter;

public class ServerStatus extends JPanel implements ServerStatusInter{

	private JLabel ip;
	private JLabel port;
	
	
	public ServerStatus(){
		ip = new JLabel();
		port = new JLabel();
		setLayout(new GridLayout(2, 2));
		add(new JLabel("adresse ip du serveur: "));
		add(ip);
		add(new JLabel("numéro de port du serveur: "));
	}
	
	@Override
	public void setIP(String ip) {
		this.ip.setText(ip); 		
	}

	@Override
	public void setPort(String port) {
		this.port.setText(port);		
	}

	@Override
	public void displayError(String mess) {
		JOptionPane.showConfirmDialog(null, mess);		
	}

}
