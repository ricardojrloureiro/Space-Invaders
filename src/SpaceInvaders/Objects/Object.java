package SpaceInvaders.Objects;

import java.awt.Graphics;

import Sprite.Position;
import Sprite.Sprite;

/**
 * Object class represents all objects in game.
 * @author josemiguelmelo
 */
public class Object implements Drawable {
	/** Object position on screen. */
	protected Position position;
	/** Object sprite representation */
	protected Sprite sprite;
	
	/**
	 * Object constructor.
	 * @param position Position on screen.
	 * @param sprite Object sprite representation.
	 */
	public Object(Position position, Sprite sprite){
		this.position = position;
		this.sprite = sprite;
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
	public Sprite getSprite(){ return this.sprite;}
	/**
	 * @param sprite Object sprite representation.
	 */
	public void setSprite(Sprite sprite){this.sprite = sprite;}
	
	/**
	 * @param g Graphics to draw image
	 */
	@Override
	public void draw(Graphics g) {
		sprite.paint(g, position);
	}
	
	
}
