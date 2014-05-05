package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.Sprite;

/**
 * SpaceShip extends Object class. Represents the space ship, controlled by the user.
 * @author josemiguelmelo
 */
public class SpaceShip extends Object{
	/** Initial default life */
	public static final int INITIAL_LIFE = 100;
	/** Initial default points */
	public static final int INITIAL_POINTS = 0;
	
	/** Space ship current life */
	private int life;
	/** Space ship current status (dead or alive) */
	private boolean dead;
	/** Space ship current points */
	private int points;
	
	/**
	 * SpaceShip class constructor. Space ship life and points set to its default values (INITIAL_LIFE and INITIAL_POINTS).
	 * @param position Position on screen.
	 * @param sprite SpaceShip sprite representation.
	 */
	public SpaceShip(Position position, Sprite sprite) {
		super(position, sprite);
		this.life=INITIAL_LIFE;
		this.dead = false;
		this.points=INITIAL_POINTS;
	}
	
	/**
	 * @param life Space ship life.
	 */
	public void setLife(int life){this.life=life;}
	/**
	 * @return Space ship life.
	 */
	public int getLife(){return this.life;}
	/**
	 * @param dead Space ship status (dead or alive).
	 */
	public void setDead(boolean dead){this.dead=dead;}
	/**
	 * @return Space ship status (dead or alive).
	 */
	public boolean getDead(){return this.dead;}
	/**
	 * @param points Space ship points.
	 */
	public void setPoints(int points){this.points=points;}
	/**
	 * @return Space ship points.
	 */
	public int getPoints(){return this.points;}

}
