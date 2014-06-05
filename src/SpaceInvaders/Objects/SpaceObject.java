package SpaceInvaders.Objects;

import java.awt.*;

import Sprite.Position;
import Sprite.SpriteSheet;

/**
 * Object class represents all objects in game.
 * @author josemiguelmelo
 */
public class SpaceObject implements Drawable, Movable {
	/** Object position on screen. */
	protected Position position;
	/** Object sprite representation */
	protected SpriteSheet sprite;
	/** Position in sprite to draw */
	protected Position spritePosition;
	
	protected int velocityX = 1;
	protected int velocityY = 1;
	
	protected boolean enabled;
    
	
	/**
	 * Object constructor.
	 * @param position Position on screen.
	 * @param sprite Object sprite representation.
	 */
	public SpaceObject(Position position, SpriteSheet sprite){
		this.position = position;
		this.sprite = sprite;
		this.sprite.loadSprite();
		this.spritePosition= new Position(0, 0);
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
	
	public void setVelocity(int x , int y){
		this.velocityX = x;
		this.velocityY = y;
	}
	

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    

    public boolean isEnabled() {
        return enabled;
    }

	
	/**
	 * @param g Graphics to draw image
	 */
	@Override
	public void draw(Graphics g) {
       	sprite.getSpriteAt(spritePosition.getX(), spritePosition.getY()).paint(g, position);
	}

	@Override
	public void moveLeft(){
		this.position.setX(this.position.getX() - velocityX);
	}
	@Override
	public void moveRight() {
		this.position.setX(this.position.getX() + velocityX);
	}
	@Override
	public void moveUp() {
		this.position.setY(this.position.getY() - velocityY);
	}
	@Override
	public void moveDown() {
		this.position.setY(this.position.getY() + velocityY);
	}
}
