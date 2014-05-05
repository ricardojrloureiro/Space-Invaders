package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.Sprite;

public class Bonus extends Object {
	public static final int LIFE_DURATION = 5;
	public static final int PROTECTION_DURATION = 3;
	public static final int LIFE_BONUS = 1;
	public static final int PROTECTION_BONUS = 2;
	
	
	protected int type;
	protected int duration;
	
	public Bonus(Position position, Sprite sprite, int duration, int type){
		super(position, sprite);
		this.duration=duration;
		this.type=type;
	}
	
	public Bonus(Position position, Sprite sprite){
		super(position, sprite);
		this.duration = LIFE_DURATION;
		this.type = LIFE_BONUS;
	}
	
	public void setDuration(int duration){this.duration=duration;}
	public int getDuration(){return this.duration;}
	public void setType(int type){this.type=type;}
	public int getType(){return this.type;}
}
