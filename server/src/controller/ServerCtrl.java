package controller;

import view.interfaces.ServerStatusInter;
import model.ServerThread;

public class ServerCtrl {

	private ServerThread server;
	private ServerStatusInter view;
	
	public ServerCtrl(ServerThread s,ServerStatusInter v){
		server = s;
		view = v;
	}
}
