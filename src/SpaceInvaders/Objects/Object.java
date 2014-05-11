package SpaceInvaders.Objects;

import java.awt.Graphics;

import Sprite.Position;
import Sprite.Sprite;
import Sprite.SpriteSheet;

/**
 * Object class represents all objects in game.
 * @author josemiguelmelo
 */
public class Object implements Drawable {
	/** Object position on screen. */
	protected Position position;
	/** Object sprite representation */
	protected SpriteSheet sprite;
	
	/**
	 * Object constructor.
	 * @param position Position on screen.
	 * @param sprite Object sprite representation.
	 */
	public Object(Position position, SpriteSheet sprite){
		this.position = position;
		this.sprite = sprite;
		this.sprite.loadSprite();
	}
	/**
	 * @return Object position on screen.
	 */
	public Position getPosition(){ return this.position;}
	/**
	 * @param position Position on screen.
	 */
	public void setPosition(Position position){ this.position = position;}
	/**
	 * @return Object sprite representation.
	 */
	public SpriteSheet getSprite(){ return this.sprite;}
	/**
	 * @param sprite Object sprite representation.
	 */
	public void setSprite(SpriteSheet sprite){this.sprite = sprite;}
	
	/**
	 * @param g Graphics to draw image
	 */
	@Override
	public void draw(Graphics g) {
		sprite.getSpriteAt(0, 0).paint(g, position);
	}
	
	
}
