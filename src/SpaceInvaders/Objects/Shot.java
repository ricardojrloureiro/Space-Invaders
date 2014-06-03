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
	
	public static String LOCATION = "/Sprites/shot.png";

	public static int SPRITE_DIMENSION = 18;
	public boolean enemyFire;
	/** Life it takes from the object that collides with it */
	private int lifeTake;
	/** Shot type */
	private int type;
    private boolean enabled;
    private int shotFired;
	
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
        this.enabled=true;
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
        this.enabled=true;
	}

    /**
     * Shot class constructor.
     * @param position Position on screen
     * @param sprite Shot sprite representation.
     * @param lifeTake Life it takes from the object that collides with it.
     * @param type Shot type.
     * @param enemyFire Enemy fire.
     * @param location sprite enemy fire location
     * @param size size of the enemy sprite
     */
    public Shot(Position position, SpriteSheet sprite, int lifeTake, int type,boolean enemyFire,String location, int size) {
        super(position, sprite);
        this.lifeTake = lifeTake;
        this.type = type;
        this.enemyFire=enemyFire;
        this.enabled=true;
        this.LOCATION = location;
        this.SPRITE_DIMENSION = size;
        this.shotFired = (int) System.currentTimeMillis();
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
     * @param enable set enables the shot
     */
    public void setEnable(boolean enable){this.enabled = enable;}
    /**
     * @return true if the shot is enabled false otherways.
     */
    public boolean getEnabled(){ return this.enabled;	}
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
            this.position.setY(this.position.getY()+2);
        else{
            if(this.type == TYPE_LASER) {
                if((int) System.currentTimeMillis() - this.shotFired > 400 ) {
                        this.enabled = false;
                }
            } else {
                this.position.setY(this.position.getY()-2);
            }
        }
        if(enabled==true)
       	    super.draw(g);
	}
}
