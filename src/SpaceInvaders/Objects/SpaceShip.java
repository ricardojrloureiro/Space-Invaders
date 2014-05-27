package SpaceInvaders.Objects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import SpaceInvaders.GUI.SpaceInvaders;
import Sprite.Position;
import Sprite.SpriteSheet;

/**
 * SpaceShip extends Object class. Represents the space ship, controlled by the user.
 * @author josemiguelmelo
 */
public class SpaceShip extends SpaceObject{
	/** Initial default life */
	public static final int INITIAL_LIFE = 100;
	/** Initial default points */
	public static final int INITIAL_POINTS = 0;

	public static final String LOCATION = "/Sprites/spaceShip.png";
	public static final int SPRITE_DIMENSION = 18;

	/** Space ship current life */
	private int life;
	/** Space ship current status (dead or alive) */
	private boolean dead;
	/** Space ship current points */
	private int points;
	/** Array list with all shots fired */
	private ArrayList<Shot> shots;

	/**
	 * SpaceShip class constructor. Space ship life and points set to its default values (INITIAL_LIFE and INITIAL_POINTS).
	 * @param position Position on screen.
	 * @param sprite SpaceShip sprite representation.
	 */
	public SpaceShip(Position position, SpriteSheet sprite) {
		super(position, sprite);
		this.life=INITIAL_LIFE;
		this.dead = false;
		this.points=INITIAL_POINTS;
		this.spritePosition = new Position(1,0);
		this.shots = new ArrayList<Shot>();
	}
	
	/**
	 * @param life Space ship life.
	 */
	public void setLife(int life){
		this.life=life;
		if(this.life <= 0) {
			this.dead = true;
		}
	}	
	/**
	 * @param damage of the attack.
	 */
	public void damageShip(int damage){
		this.life = life - damage;
		if(this.life<=0) {
			this.dead = true;
		}
	}
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
	/**
	 * @param shots Array List with all shots
	 */
	public void setShots(ArrayList<Shot> shots){ this.shots = shots; }
	/**
	 * @return Array list with all shots fired
	 */
	public ArrayList<Shot> getShots(){ return this.shots; }

	public void addShot(){
		shots.add(new Shot(new Position(position.getX(), position.getY()), 
				new SpriteSheet(Shot.LOCATION, new Dimension(Shot.SPRITE_DIMENSION, Shot.SPRITE_DIMENSION),1,1), 
				10));	
	}

	/**
	 * @param g Graphics to draw image
	 */
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		for(int i = 0; i< shots.size(); i++){
			if(shots.get(i).getPosition().getY() < 0) {
				shots.remove(i);
			} else
                shots.get(i).draw(g);
		}
	}

	/**
	 * @parameter integer DIRECTION that is the direction to take.
	 * Spaceship's move function that allows the user to navigate throughout the map.
	 */
	public void move(int DIRECTION) {
		switch (DIRECTION) {
		case SpaceInvaders.LEFT:
			getPosition().setX(getPosition().getX() - SpaceInvaders.SPACESHIP_MOVE_SPEED);
			break;
		case SpaceInvaders.RIGHT:
			getPosition().setX(getPosition().getX() + SpaceInvaders.SPACESHIP_MOVE_SPEED);
			break;
		case SpaceInvaders.DOWN:
			getPosition().setY(getPosition().getY() + SpaceInvaders.SPACESHIP_MOVE_SPEED);
			break;
		case SpaceInvaders.UP:
			getPosition().setY(getPosition().getY() - SpaceInvaders.SPACESHIP_MOVE_SPEED);
			break;
		}
	}
	
	/**
	 * @parameter INTEGER type of enemy destroyed
	 * increases the points of the current space ship.
	 */
	public void increasePoints(int points){
		this.points += points;
	}
}
