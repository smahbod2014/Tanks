package koda.ui;

import koda.Tanks;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextButton {

	public float x;
	public float y;
	public float scale;
	public float alpha;
	public int id;
	public Sprite[] text;
	
	public TextButton(float x, float y, float scale, String text, int id) {
		this.x = x;
		this.y = y;
		this.text = Tanks.ff.getFontArray(text);
		this.scale = scale;
		this.id = id;
		this.alpha = 1;
	}
	
	public TextButton(float x, float y, float scale, String text, int id, float alpha) {
		this.x = x;
		this.y = y;
		this.text = Tanks.ff.getFontArray(text);
		this.scale = scale;
		this.id = id;
		this.alpha = alpha;
	}
	
	public void setColor(float r, float g, float b, float a) {
		for (Sprite s : text) {
			s.setColor(r, g, b, a);
		}
	}
	
	public void setText(String text) {
		this.text = Tanks.ff.getFontArray(text);
	}
	
	public boolean contains(float x, float y){
		float width = Tanks.ff.width(text.length, scale);
		float height = Tanks.ff.width(1,scale);
		
		return (x >= this.x - width/2 && x <= this.x + width/2 && 
				y >= this.y - (height/2+5) && y <= this.y + (height/2-5));
	}
	
	public void render(SpriteBatch sb) {
		Tanks.ff.render(sb, this);
	}
}
