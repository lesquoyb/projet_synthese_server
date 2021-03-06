package view;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import view.interfaces.ServerStatusInter;

/**
 * Affiche l'état du serveur, l'adresse ip, le port et le nombre de connexions.
 * @author baptiste
 *
 */
public class ServerStatus extends JFrame implements ServerStatusInter{

	private JLabel ip;
	private JLabel port;
	private JLabel noCon;
	
	

	public ServerStatus(){
		super();
		setTitle("Dessinator");
		setSize(700, 200);
		setResizable(true);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		ip = new JLabel();
		port = new JLabel();
		noCon = new JLabel();
		setLayout(new GridLayout(3, 3));
		add(new JLabel("adresse ip du serveur: "));
		add(ip);
		add(new JLabel("num�ro de port du serveur: "));
		add(port);
		add(new JLabel("nombre de connexion sur le serveur: "));
		add(noCon);
	}
	
	@Override
	public void setIP(String ip) {
		this.ip.setText(ip); 
		this.repaint();
	}

	@Override
	public void setPort(String port) {
		this.port.setText(port);
		this.repaint();
	}

	@Override
	public void displayError(String mess) {
		JOptionPane.showConfirmDialog(null, mess);		
	}

	@Override
	public void setNoConnexion(String no) {
		noCon.setText(no);
	}

}
