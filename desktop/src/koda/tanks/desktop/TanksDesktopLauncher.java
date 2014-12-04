package koda.tanks.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import koda.Tanks;

public class TanksDesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Tanks.WIDTH;
		config.height = Tanks.HEIGHT;
		config.title = Tanks.TITLE;
		config.resizable = false;
		new LwjglApplication(new Tanks(), config);
	}
}
