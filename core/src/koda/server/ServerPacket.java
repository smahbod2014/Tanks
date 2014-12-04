package koda.server;

import java.util.ArrayList;

import koda.entities.Entity;

public class ServerPacket extends Packet {

	public ArrayList<Entity> others;
	public ArrayList<String> other_usernames;
}
