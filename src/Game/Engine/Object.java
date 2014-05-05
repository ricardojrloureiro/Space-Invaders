package Game.Engine;

import java.awt.Graphics;

import Sprite.Position;
import Sprite.Sprite;

public class Object implements Drawable {
	protected Position position;
	protected Sprite sprite;
	
	public Object(Position position, Sprite sprite){
		this.position = position;
		this.sprite = sprite;
	}
	public Position getPosition(){ return this.position;}
	public void setPosition(Position position){ this.position = position;}
	public Sprite getSprite(){ return this.sprite;}
	public void setSprite(Sprite sprite){this.sprite = sprite;}
	
	@Override
	public void draw(Graphics g) {
		sprite.paint(g, position);
	}
	
	
}
