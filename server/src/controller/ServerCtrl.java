package controller;

import view.interfaces.ServerStatusInter;
import model.Server;

public class ServerCtrl {

	private Server server;
	private ServerStatusInter view;
	
	public ServerCtrl(Server s,ServerStatusInter v){
		server = s;
		view = v;
	}
}
