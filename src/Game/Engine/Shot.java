package Game.Engine;

import Sprite.Position;
import Sprite.Sprite;

public class Shot extends Object{

	private int lifeTake;
	private int type;
	
	
	public Shot(Position position, int lifeTake, int type){
		super(position, null);
		this.lifeTake = lifeTake;
		this.type = type;
	}
	public Shot(Position position, Sprite sprite, int lifeTake, int type) {
		super(position, sprite);
		this.lifeTake = lifeTake;
		this.type = type;
	}
	
	public void setLifeTake(int lifeTake){this.lifeTake = lifeTake;}
	public int getLifeTake(){ return this.lifeTake;	}
	public void setType(int type){this.type=type;}
	public int getType(){ return this.type;}
	

}
