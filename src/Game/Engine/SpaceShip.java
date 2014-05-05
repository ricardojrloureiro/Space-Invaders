package Game.Engine;

import Sprite.Position;
import Sprite.Sprite;

public class SpaceShip extends Object{

	public static final int INITIAL_LIFE = 100;
	public static final int INITIAL_POINTS = 0;

	private int life;
	private boolean dead;
	private int points;

	public SpaceShip(Position position){
		super(position, null);
		this.life=INITIAL_LIFE;
		this.dead = false;
		this.points=INITIAL_POINTS;
	}
	
	public SpaceShip(Position position, Sprite sprite) {
		super(position, sprite);
		this.life=INITIAL_LIFE;
		this.dead = false;
		this.points=INITIAL_POINTS;
	}

	public void setLife(int life){this.life=life;}
	public int getLife(){return this.life;}
	public void setDead(boolean dead){this.dead=dead;}
	public boolean getDead(){return this.dead;}
	public void setPoints(int points){this.points=points;}
	public int getPoints(){return this.points;}

}
