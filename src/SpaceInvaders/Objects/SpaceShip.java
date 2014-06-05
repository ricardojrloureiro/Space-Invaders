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
	
	private boolean protection;
	
	public SpriteSheet protectionSprite;
	private int protectionIniTime;
    protected int Ydimension;

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
		this.velocityX = 5;
		this.velocityY = 5;
		this.protection = false;
		
		this.protectionSprite = new SpriteSheet("/Sprites/protectionLayer.png", new Dimension(36,44), 1, 1);
		protectionSprite.loadSprite();
	}
	
	public boolean isProtected(){ return protection;}
	public void setProtection(boolean protection){ 
		this.protection = protection;
		if(this.protection)
			protectionIniTime = (int)System.currentTimeMillis();
	}
	
	public void checkProtection(){
		if((int)System.currentTimeMillis() - protectionIniTime >= Bonus.PROTECTION_DURATION*1000)
			this.protection=false;
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

	public void addShot(int type){
        if(type == 1){
            shots.add(new Shot(new Position(position.getX(), position.getY()),
                    new SpriteSheet( "/Sprites/shot.png", new Dimension(Shot.SPRITE_DIMENSION, Shot.SPRITE_DIMENSION),1,1),
                    10));
        } else if(type==2){
            if(getPosition().getY() < 600){
                Ydimension =getPosition().getY();
            } else {
                Ydimension=600;
            }

            shots.add(new Shot(new Position(position.getX(),position.getY()),
                    new SpriteSheet("/Sprites/leUltimatumLazer.png", new Dimension(20,Ydimension),1,1),
                    10,Shot.TYPE_LASER,false,"",1));
        }
	}

	/**
	 * @param g Graphics to draw image
	 */
	@Override
	public void draw(Graphics g) {
		if(isProtected())
			protectionSprite.getSpriteAt(0, 0).paint(g, position);
		super.draw(g);
		for(int i = 0; i< shots.size(); i++){
			if(shots.get(i).getPosition().getY() < 0) {
				shots.get(i).setEnable(false);
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
			moveLeft();
			break;
		case SpaceInvaders.RIGHT:
			moveRight();
			break;
		case SpaceInvaders.DOWN:
			moveDown();
			break;
		case SpaceInvaders.UP:
			moveUp();
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
	
	public void reset(){
		this.life=INITIAL_LIFE;
		this.dead = false;
		this.points=INITIAL_POINTS;
		this.spritePosition = new Position(1,0);
		this.shots.clear();
		this.velocityX = 5;
		this.velocityY = 5;
	}
}
