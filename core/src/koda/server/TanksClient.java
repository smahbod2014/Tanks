package koda.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import koda.entities.Entity;
import koda.handlers.C;

import com.badlogic.gdx.net.Socket;

public class TanksClient {

	public Socket socket;
	public ObjectOutputStream oos;
	public ObjectInputStream ois;
	
	public ArrayList<Entity> others;
	public ArrayList<String> other_usernames;
	
	public Entity player;
	public String username;
	
	public int state;
	
	public Runnable communicate = new Runnable() {
		@Override
		public void run() {
			while (true) {
				try {
					ClientPacket client_packet = (ClientPacket) ois.readObject();
					player = client_packet.entity;
					username = client_packet.username;
					
					ServerPacket server_packet = new ServerPacket();
					server_packet.others = others;
					server_packet.other_usernames = other_usernames;
					server_packet.state = state;
					oos.writeObject(server_packet);
					oos.reset();
					
					
					
					
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	public TanksClient(Socket socket) {
		this.socket = socket;
		try {
			this.oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.state = C.STATUS_CONNECTED;
	}
	
	public void start() {
		new Thread(communicate).start();
	}
}
