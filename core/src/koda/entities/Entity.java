package koda.entities;

import koda.Tanks;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {

	public final float speed = 100f;
	
	public float x;
	public float y;
	public float width = Tanks.TILE_SIZE;
	public float height = Tanks.TILE_SIZE;
	
	public boolean[] directions;
	public TextureRegion tr;
	
	public Entity(float x, float y) {
		this.x = x;
		this.y = y;
		this.directions = new boolean[4];
	}
	
	public boolean collidesWith(Entity e) {
		Rectangle a = new Rectangle(x, y, width, height);
		Rectangle b = new Rectangle(x, y, width, height);
		return a.overlaps(b);
	}
	
	public abstract void update(float dt);
	public abstract void render(SpriteBatch sb);
}
