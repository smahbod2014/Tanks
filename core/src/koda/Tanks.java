package koda;

import koda.handlers.Resources;
import koda.states.GSM;
import koda.states.MenuState;
import koda.ui.FontFactory;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tanks extends ApplicationAdapter {
	
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	public static int TILE_SIZE = 32;
	public static String TITLE = "Tanks";
	
	public static FontFactory ff;
	public static Resources res;
	
	private GSM gsm;
	private SpriteBatch sb;
	
	@Override
	public void create () {
		sb = new SpriteBatch();
		loadResources();
		ff = new FontFactory(res.getTexture("font"));
		gsm = new GSM();
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.peek().handleInput();
		gsm.peek().update(Gdx.graphics.getDeltaTime());
		gsm.peek().render(sb);
	}
	
	private void loadResources() {
		res = new Resources();
		res.loadTexture("font", "fonts/font1_16.png");
		res.loadTexture("shell", "images/missile.png");
		res.loadTexture("tank_up", "images/tank1.png");
		res.loadTexture("tank_left", "images/tank2.png");
		res.loadTexture("tank_down", "images/tank3.png");
		res.loadTexture("tank_right", "images/tank4.png");
	}
}
