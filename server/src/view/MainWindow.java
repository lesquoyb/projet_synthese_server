package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import view.interfaces.MainWindowInterface;

public class MainWindow extends JFrame implements MainWindowInterface {

	private DrawingArea drawingArea;
	private ServerStatus serverStatus;
	
	public MainWindow(){
		
		setLocationRelativeTo(null);
		setTitle("Dessinator");
		setSize(500, 500);
		setResizable(true);
		setVisible(true);
		
		serverStatus = new ServerStatus();
		drawingArea = new DrawingArea();
		
		setLayout(new BorderLayout());
		add(drawingArea,BorderLayout.CENTER);
		add(serverStatus,BorderLayout.NORTH);
		
	}
}
