package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.SpriteSheet;

/**
 * Bonus extends Object class.
 * @author Jose Miguel Melo & Ricardo Loureiro
 */
public class Bonus extends SpaceObject {
	/** Life bonus standard duration in seconds */
	public static final int LIFE_DURATION = 5;
	/** Protection bonus standard duration in seconds */
	public static final int PROTECTION_DURATION = 3;
	/** Life bonus type representation */
	public static final int LIFE_BONUS = 1;
	/** Protection bonus type representation */
	public static final int PROTECTION_BONUS = 2;
	

	public static final String LIFE_LOCATION = "/Sprites/lifeBonus.png";

	public static final String PROTECTION_LOCATION = "/Sprites/protectionBonus.png";
	
	/** Bonus type */
	protected int type;
	/** Bonus duration in seconds */
	protected int duration;
	
	protected boolean available;
	protected int initialTime;
	
	/**
	 * Bonus constructor
	 * @param position Position on screen.
	 * @param sprite Bonus sprite.
	 * @param duration Bonus duration
	 * @param type Bonus type
	 */
	public Bonus(Position position, SpriteSheet sprite, int duration, int type){
		super(position, sprite);
		this.duration=duration;
		this.type=type;
		this.available = true;
		this.initialTime = (int)System.currentTimeMillis();
	}
	
	/**
	 * Bonus default constructor. Duration set to life default duration (LIFE_DURATION).
	 * Type set to life bonus type (LIFE_BONUS).
	 * @param position Position on screen.
	 * @param sprite Bonus sprite.
	 */
	public Bonus(Position position, SpriteSheet sprite){
		super(position, sprite);
		this.duration = LIFE_DURATION;
		this.type = LIFE_BONUS;
	}
	
	/** Set bonus duration in seconds 
	 * @param duration Bonus duration in seconds.
	 */
	public void setDuration(int duration){this.duration=duration;}
	/**
	 * @return Bonus duration in seconds.
	 */
	public int getDuration(){return this.duration;}
	/**
	 * Set bonus type
	 * @param type Bonus type.
	 */
	public void setType(int type){this.type=type;}
	/**
	 * @return Bonus type.
	 */
	public int getType(){return this.type;}

    /**
     *
     * @return boolean available
     */
	public boolean isAvailable(){return this.available;}

    /**
     * Abstract function that is implemented by the classes that implement Bonus class
     */
	public void checkAvailable(){

	}

    /**
     * performs an action depending of the bonus
     * @param ship
     */
	public void performAction(SpaceShip ship){}
}
