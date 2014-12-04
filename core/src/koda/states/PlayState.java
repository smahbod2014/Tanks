package koda.states;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import koda.entities.Entity;
import koda.entities.Tank;
import koda.handlers.C;
import koda.server.ClientPacket;
import koda.server.ServerPacket;
import koda.ui.TextButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.net.Socket;


public class PlayState extends State {

	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private String username;
	
	private ArrayList<Entity> others;
	private ArrayList<String> other_usernames;
	private boolean initialized = false;
	private Tank player;
	
	public PlayState(GSM gsm) {
		super(gsm);
		
		username = (String) JOptionPane.showInputDialog(null, "Username: ", "Info", JOptionPane.INFORMATION_MESSAGE, null, null, System.getProperty("user.name"));
		
		String ip = null;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			ip = "localhost";
			e.printStackTrace();
		}
		
		socket = Gdx.net.newClientSocket(Protocol.TCP, ip, 2406, null);
		
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		player = new Tank(0, 0);
		others = new ArrayList<Entity>();
		
		initialized = true;
	}
	
	private void communicate(float dt) throws ClassNotFoundException, IOException {
		ServerPacket server_packet = (ServerPacket) ois.readObject();
		this.others = server_packet.others;
		this.other_usernames = server_packet.other_usernames;
		
		switch (server_packet.state) {
		case C.STATUS_CONNECTED:
			//do nothing
			break;
		case C.STATUS_SERVER_DISCONNECT:
			System.out.println("Disconnected by server");
			socket.dispose();
			ois.close();
			oos.close();
			gsm.set(new MenuState(gsm));
			return;
		case C.STATUS_SERVER_SHUTTING_DOWN:
			System.out.println("Server shutting down");
			socket.dispose();
			ois.close();
			oos.close();
			gsm.set(new MenuState(gsm));
			return;
		}
		
		ClientPacket client_packet = new ClientPacket();
		client_packet.entity = player;
		client_packet.username = username;
		oos.writeObject(client_packet);
		oos.reset();
	}

	@Override
	public void handleInput() {
		if (!initialized) {
			return;
		}
		
		
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			player.setMotion(C.DIRECTION_LEFT);
		} else {
			player.stopMotion(C.DIRECTION_LEFT);
		}
		
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			player.setMotion(C.DIRECTION_DOWN);
		} else {
			player.stopMotion(C.DIRECTION_DOWN);
		}
		
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			player.setMotion(C.DIRECTION_RIGHT);
		} else {
			player.stopMotion(C.DIRECTION_RIGHT);
		}
		
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			player.setMotion(C.DIRECTION_UP);
		} else {
			player.stopMotion(C.DIRECTION_UP);
		}
	}

	@Override
	public void update(float dt) {
		if (!initialized) {
			return;
		}
		
		
		try {
			communicate(dt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		if (!initialized) {
			return;
		}
		
		for (int i = 0; i < others.size(); i++) {
			Entity e = others.get(i);
			String s = other_usernames.get(i);
			if (!s.equals(username)) {
				e.render(sb);
				
				TextButton tb = new TextButton(e.x, e.y + 16, 1, s, C.UNDEFINED);
				tb.render(sb);
			}
		}
		
		player.render(sb);
		TextButton tb = new TextButton(player.x, player.y + 16, 1, username, C.UNDEFINED);
		tb.render(sb);
	}
}
