package SpaceInvaders.Objects;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import SpaceInvaders.GUI.SpaceInvaders;
import Sprite.Position;
import Sprite.SpriteSheet;

/**
 * SpaceShip extends Object class. Represents the space ship, controlled by the user.
 * @author Jose Miguel Melo & Ricardo Loureiro
 */
public class SpaceShip extends SpaceObject{
	/** Initial default life */
	public static final int INITIAL_LIFE = 100;
	/** Initial default points */
	public static final int INITIAL_POINTS = 0;

	public static final String LOCATION = "/Sprites/spaceShip.png";
	public static final int SPRITE_DIMENSION = 18;
    private final SpriteSheet normalShotSprite;

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

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

    protected boolean victory;
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
        this.victory = false;
		this.normalShotSprite =  new SpriteSheet( "/Sprites/shot.png", new Dimension(Shot.SPRITE_DIMENSION, Shot.SPRITE_DIMENSION),1,1);
		this.protectionSprite = new SpriteSheet("/Sprites/protectionLayer.png", new Dimension(36,44), 1, 1);
		protectionSprite.loadSprite();
	}

    /**
     * Returns true if the spaceship is under current protection or false if it isn't
     * @return protection boolean
     */
	public boolean isProtected(){ return protection;}

    /**
     * sets the protection of the spaceship and actualizes the time of the last protection
     * @param protection
     */
	public void setProtection(boolean protection){ 
		this.protection = protection;
		if(this.protection)
			protectionIniTime = (int)System.currentTimeMillis();
	}

    /**
     * Checks protection, if the time is above the protection max time, the protection will be disabled
     */
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
	 * @return Space ship points.
	 */
	public int getPoints(){return this.points;}

	/**
	 * @return Array list with all shots fired
	 */
	public ArrayList<Shot> getShots(){ return this.shots; }

    /**
     * adds a specific shot to the spaceship depending of the parameter.
     * @param type if the type is 1 is a normal fire shot if the type is 2 is a laser.
     */
	public void addShot(int type){
        if(type == 1){
            shots.add(new Shot(new Position(position.getX(), position.getY()),
                    normalShotSprite,
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
	 * @param  DIRECTION that is the direction to take.
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
	 * @param points type of enemy destroyed
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
        this.shots = new ArrayList<Shot>();
        this.velocityX = 5;
        this.velocityY = 5;
        this.protection = false;
        this.victory = false;
        this.position.setX(50);
        this.position.setY(50);
        this.protectionSprite = new SpriteSheet("/Sprites/protectionLayer.png", new Dimension(36,44), 1, 1);
        protectionSprite.loadSprite();
	}

}
