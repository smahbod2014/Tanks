package koda.entities;

import koda.Tanks;
import koda.handlers.C;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tank extends Entity {

	public Tank(float x, float y) {
		super(x, y);
		tr = Tanks.res.getTexture("tank_left");
	}
	
	public void setMotion(int dir) {
		switch (dir) {
		case C.DIRECTION_LEFT:
			directions[C.DIRECTION_LEFT] = true;
			directions[C.DIRECTION_UP] = false;
			directions[C.DIRECTION_DOWN] = false;
			tr = Tanks.res.getTexture("tank_left");
			break;
		case C.DIRECTION_DOWN:
			directions[C.DIRECTION_DOWN] = true;
			directions[C.DIRECTION_LEFT] = false;
			directions[C.DIRECTION_RIGHT] = false;
			tr = Tanks.res.getTexture("tank_down");
			break;
		case C.DIRECTION_RIGHT:
			directions[C.DIRECTION_RIGHT] = true;
			directions[C.DIRECTION_UP] = false;
			directions[C.DIRECTION_DOWN] = false;
			tr = Tanks.res.getTexture("tank_right");
			break;
		case C.DIRECTION_UP:
			directions[C.DIRECTION_UP] = true;
			directions[C.DIRECTION_LEFT] = false;
			directions[C.DIRECTION_RIGHT] = false;
			tr = Tanks.res.getTexture("tank_up");
			break;
		}
	}
	
	public void stopMotion(int dir) {
		directions[dir] = false;
	}

	@Override
	public void update(float dt) {
		if (directions[C.DIRECTION_LEFT]) {
			x -= dt * speed;
		}
		
		if (directions[C.DIRECTION_DOWN]) {
			y -= dt * speed;
		}
		
		if (directions[C.DIRECTION_RIGHT]) {
			x += dt * speed;
		}
		
		if (directions[C.DIRECTION_UP]) {
			y += dt * speed;
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(tr, x, y);
		sb.end();
	}
}
