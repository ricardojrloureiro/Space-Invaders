package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.SpriteSheet;

/**
 * Enemy extends Object class.
 * @author josemiguelmelo
 */
public class Enemy extends SpaceObject {

	/** Initial enemy life default value */
	public static final int INITIAL_LIFE = 100;
	/** Normal enemy type */
	public static final int NORMAL = 1;
	/** Suicidal enemy type */
	public static final int SUICIDAL = 2;
	/** Boss enemy type */
	public static final int BOSS = 3;
	/** Destroyer enemy type */
	public static final int DESTROYER = 4;
	/** Enemy current life */
	protected int life;
	/** Represent enemy status (dead or alive) */
	protected boolean dead;
	/** Enemy type */
	protected int type;
	
	/**
	 * Enemy default constructor. Enemy type set to its default value -> NORMAL
	 * @param position Enemy position on screen.
	 * @param sprite Enemy sprite representation.
	 */
	public Enemy(Position position, SpriteSheet sprite) {
		super(position, sprite);
		this.life = INITIAL_LIFE;
		this.dead = false;
		this.type = NORMAL;
	}
	/**
	 * Enemy constructor.
	 * @param position Enemy Position on screen.
	 * @param sprite Enemy sprite representation.
	 * @param type Enemy type.
	 */
	public Enemy(Position position, SpriteSheet sprite, int type) {
		super(position, sprite);
		this.life = INITIAL_LIFE;
		this.dead = false;
		this.type = type;
	}
	
	/**
	 * @param life Enemy life.
	 */
	public void setLife(int life){
		this.life = life;
		if(this.life<=0){
			this.dead = true;
		}
	}
	/**
	 * @return Enemy life.
	 */
	public int getLife(){return this.life;}
	/**
	 * @param dead Enemy status (dead or alive).
	 */
	public void setDead(boolean dead){ this.dead= dead;}
	/**
	 * @return Enemy status (True if dead. False if alive)
	 */
	public boolean isDead(){ return this.dead;}
	/**
	 * @param type Enemy type.
	 */
	public void setType(int type){this.type = type;}
	/**
	 * @return Enemy type.
	 */
	public int getType(){ return this.type;}
	/**
	 * @param damage done to the enemy by the ship
	 */
	public void damageEnemy(int damage) {
		this.life -= damage;
		if(this.life<=0){
			this.dead=true;
		}
	}
	/**
	 * Move the enemy depending of their type
	 */
	public void move() {
		// TODO Auto-generated method stub
		// TODO generate a switch that moves depending of the values.

		getPosition().setY(getPosition().getY()+1);
	}
	
	
	

}
