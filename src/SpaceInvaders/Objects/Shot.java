package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.Sprite;

/**
 * Shot extends Object class.
 * @author josemiguelmelo
 */
public class Shot extends Object{
	/** Shot normal type */
	public static final int TYPE_NORMAL = 1;
	/** Shot laser type */
	public static final int TYPE_LASER = 2;
	
	/** Life it takes from the object that collides with it */
	private int lifeTake;
	/** Shot type */
	private int type;
	
	/**
	 * Shot class default constructor. Type set to its default value -> TYPE_NORMAL.
	 * @param position Position on screen
	 * @param sprite Shot sprite representation.
	 * @param lifeTake Life it takes from the object that collides with it.
	 */
	public Shot(Position position, Sprite sprite, int lifeTake){
		super(position, sprite);
		this.lifeTake = lifeTake;
		this.type = TYPE_NORMAL;
	}
	/**
	 * Shot class constructor.
	 * @param position Position on screen
	 * @param sprite Shot sprite representation.
	 * @param lifeTake Life it takes from the object that collides with it.
	 * @param type Shot type.
	 */
	public Shot(Position position, Sprite sprite, int lifeTake, int type) {
		super(position, sprite);
		this.lifeTake = lifeTake;
		this.type = type;
	}
	
	/**
	 * @param lifeTake Life it takes from the object that collides with it.
	 */
	public void setLifeTake(int lifeTake){this.lifeTake = lifeTake;}
	/**
	 * @return Life it takes from the object that collides with it.
	 */
	public int getLifeTake(){ return this.lifeTake;	}
	/**
	 * @param type Shot type.
	 */
	public void setType(int type){this.type=type;}
	/**
	 * @return Shot type.
	 */
	public int getType(){ return this.type;}
	

}
