package SpaceInvaders.Objects;

import java.awt.Graphics;

import Sprite.Position;
import Sprite.SpriteSheet;

/**
 * Shot extends Object class.
 * @author josemiguelmelo
 */
public class Shot extends SpaceObject{
	/** Shot normal type */
	public static final int TYPE_NORMAL = 1;
	/** Shot laser type */
	public static final int TYPE_LASER = 2;
	
	public static final String LOCATION = "/Sprites/shot.png";
	public static final int SPRITE_DIMENSION = 18;
	public boolean enemyFire;
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
	public Shot(Position position, SpriteSheet sprite, int lifeTake){
		super(position, sprite);
		this.lifeTake = lifeTake;
		this.type = TYPE_NORMAL;
        this.enemyFire = false;
	}
	/**
	 * Shot class constructor.
	 * @param position Position on screen
	 * @param sprite Shot sprite representation.
	 * @param lifeTake Life it takes from the object that collides with it.
	 * @param type Shot type.
	 */
	public Shot(Position position, SpriteSheet sprite, int lifeTake, int type) {
		super(position, sprite);
		this.lifeTake = lifeTake;
		this.type = type;
        this.enemyFire=false;
	}

    /**
     * Shot class constructor.
     * @param position Position on screen
     * @param sprite Shot sprite representation.
     * @param lifeTake Life it takes from the object that collides with it.
     * @param type Shot type.
     * @param enemyFire Enemy fire.
     */
    public Shot(Position position, SpriteSheet sprite, int lifeTake, int type,boolean enemyFire) {
        super(position, sprite);
        this.lifeTake = lifeTake;
        this.type = type;
        this.enemyFire=enemyFire;
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
	
	/**
	 * @param g Graphics to draw image
	 */
	@Override
	public void draw(Graphics g) {
        if(enemyFire==true)
            this.position.setY(this.position.getY()+1);
        else
         this.position.setY(this.position.getY()-1);
       	super.draw(g);
	}
}
