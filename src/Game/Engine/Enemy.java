package Game.Engine;

import Sprite.Position;
import Sprite.Sprite;

public class Enemy extends Object {

	public static final int INITIAL_LIFE = 100;
	public static final int NORMAL = 1;
	public static final int SUICIDAL = 2;
	public static final int BOSS = 3;
	public static final int DESTROYER = 4;
	
	protected int life;
	protected boolean dead;
	protected int type;
	

	public Enemy(Position position, Sprite sprite) {
		super(position, sprite);
		this.life = INITIAL_LIFE;
		this.dead = false;
		this.type = NORMAL;
	}
	
	public Enemy(Position position, Sprite sprite, int type) {
		super(position, sprite);
		this.life = INITIAL_LIFE;
		this.dead = false;
		this.type = type;
	}
	
	
	public void setLife(int life){this.life = life;}
	public int getLife(){return this.life;}
	public void setDead(boolean dead){ this.dead= dead;}
	public boolean getDead(){ return this.dead;}
	public void setType(int type){this.type = type;}
	public int getType(){ return this.type;}
	
	
	

}
