package SpaceInvaders.Objects;

import java.awt.*;

import Sprite.Position;
import Sprite.SpriteSheet;

/**
 * Object class represents all objects in game.
 * @author Jose Miguel Melo & Ricardo Loureiro
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

    /**
     * Sets enable the boolean enabled depending of the parameter.
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * returns true if the boolean enable is true and false if not.
     * @return boolean
     */
    public boolean isEnabled() {
        return enabled;
    }

	
	/**
	 * @param g Graphics to draw image
	 */

	public void draw(Graphics g) {
       	sprite.getSpriteAt(spritePosition.getX(), spritePosition.getY()).paint(g, position);
	}

    /**
     * moves left depending of the current object velocity
     */

	public void moveLeft(){
		this.position.setX(this.position.getX() - velocityX);
	}

    /**
     * moves right depending of the current object velocity
     */

	public void moveRight() {
		this.position.setX(this.position.getX() + velocityX);
	}

    /**
     * moves up depending of the current object velocity
     */

	public void moveUp() {
		this.position.setY(this.position.getY() - velocityY);
	}

    /**
     * moves down depending of the current object velocity
     */

	public void moveDown() {
		this.position.setY(this.position.getY() + velocityY);
	}
}
