package koda.server;

import koda.logic.GameLogic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.Socket;

public class TanksServer {

	private static final int PORT = 2406;
	
	private static GameLogic logic = new GameLogic();
	
	public static void main(String[] args) {
		ServerSocket server = Gdx.net.newServerSocket(Protocol.TCP, PORT, null);
		
		//accept connections
		try {
			while (true) {
				Socket socket = server.accept(null);
				TanksClient client = new TanksClient(socket);
				logic.addPlayer(client);
				client.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			server.dispose();
		}
	}
}
