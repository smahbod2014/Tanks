package koda.logic;

import java.util.ArrayList;

import koda.server.TanksClient;

public class GameLogic {

	private ArrayList<TanksClient> clients;
	
	public GameLogic() {
		clients = new ArrayList<TanksClient>();

	}
	
	public void addPlayer(TanksClient client) {
		synchronized (clients) {
			clients.add(client);
		}
	}
}
