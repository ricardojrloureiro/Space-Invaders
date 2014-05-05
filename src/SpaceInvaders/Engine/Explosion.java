package SpaceInvaders.Engine;

import java.awt.Graphics;

import SpaceInvaders.Objects.Drawable;
import Sprite.Position;
import Sprite.Sprite;

public class Explosion implements Drawable{
	public static final int DEFAULT_DURATION = 2;
	
	private Sprite sprite;
	private int duration;
	private Position position;
	/**
	 * This constructor sets the explosion duration with its default value. 
	 * The duration default value is define by the constant DEFAUL_DURATION.
	 * @param position Explosion position in screen
	 * @param sprite Explosion sprite
	 */
	public Explosion(Position position, Sprite sprite){
		this.setSprite(sprite);
		this.position=position;
		this.setDuration(DEFAULT_DURATION);
	}
	/**
	 * @param position Explosion position in screen
	 * @param sprite Explosion sprite
	 * @param duration Explosion duration in seconds
	 */
	public Explosion(Position position, Sprite sprite, int duration){
		this.setSprite(sprite);
		this.position=position;
		this.setDuration(duration);
	}

	/**
	 * @return Explosion sprite
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * @param sprite Explosion sprite
	 */
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * @return Explosion duration in seconds
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration Explosion duration in seconds
	 */
	public void setDuration(int duration) {
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
	
	@Override
	public void draw(Graphics g) {
		sprite.paint(g, position);
	}

	
}
