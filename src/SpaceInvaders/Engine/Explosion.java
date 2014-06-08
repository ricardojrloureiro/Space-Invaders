package SpaceInvaders.Engine;

import java.awt.Graphics;

import SpaceInvaders.Objects.Drawable;
import Sprite.Position;
import Sprite.SpriteSheet;

/**
 * Class Explosion that implements Drawable
 * @author Jose Miguel Melo & Ricardo Loureiro
 */

public class Explosion implements Drawable{
	public static final double DEFAULT_DURATION = 0.5;
	public static final String LOCATION = "/Sprites/explosion.png";
	public static final int SPRITE_WIDTH = 557;
	public static final int SPRITE_HEIGTH = 69;
	public static final int SPRITE_COLS = 6;

	private SpriteSheet sprite;
	private double duration;
	private Position position;
	private int spritesheetPosition;
	private boolean enabled;
	
	
	
	private int lastTime = (int)System.currentTimeMillis();
	
	/**
	 * This constructor sets the explosion duration with its default value. 
	 * The duration default value is define by the constant DEFAUL_DURATION.
	 * @param position Explosion position in screen
	 * @param sprite Explosion sprite
	 */
	public Explosion(Position position, SpriteSheet sprite){
		this.setSprite(sprite);
		this.position=position;
		this.setDuration(DEFAULT_DURATION * 1000);
		this.spritesheetPosition=0;
		this.enabled = true;
		sprite.loadSprite();
		Player player = new Player("/Sounds/explosion.wav");
		player.run();
	}
	/**
	 * @param position Explosion position in screen
	 * @param sprite Explosion sprite
	 * @param duration Explosion duration in seconds
	 */
	public Explosion(Position position, SpriteSheet sprite, int duration){
		this.setSprite(sprite);
		this.position=position;
		this.setDuration(duration*1000);
		this.spritesheetPosition = 0;
		this.enabled = true;
		sprite.loadSprite();
	}

	/**
	 * @return Explosion sprite
	 */
	public SpriteSheet getSprite() {
		return sprite;
	}

	/**
	 * @param sprite Explosion sprite
	 */
	public void setSprite(SpriteSheet sprite) {
		this.sprite = sprite;
	}

	/**
	 * @return Explosion duration in seconds
	 */
	public double getDuration() {
		return duration;
	}

	/**
	 * @param duration Explosion duration in seconds
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}
	/**
	 * @return Explosion position in screen
	 */
	public Position getPosition() {
		return position;
	}
	/**
	 * @param position Explosion position in screen
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

    /**
     * returns true if is enabled or false it it isn't
     * @return enabled
     */
	public boolean isEnabled(){
		return this.enabled;
	}

    /**
     * sets the current explosion to disabled
     */
    public void disable(){
		this.enabled = false;
	}

    /**
     * draws the explosion
     * @param g Graphics to draw image
     */
	public void draw(Graphics g) {
		if(spritesheetPosition < sprite.getSprite()[0].length){
			sprite.getSpriteAt(spritesheetPosition, 0).paint(g, position);
			if((int) System.currentTimeMillis() - lastTime >= ((int)(duration/SPRITE_COLS))){
				spritesheetPosition++;
				lastTime = (int) System.currentTimeMillis();
			}
		}else{
			disable();
		}
	}
}
