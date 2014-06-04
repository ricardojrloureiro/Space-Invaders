package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.SpriteSheet;

/**
 * Life extends Bonus class. Represents life bonus type.
 * @author josemiguelmelo
 */
public class Life extends Bonus{
	
	public int lifeBonus = 25;
	
	/**
	 * Life constructor.
	 * @param position Position on screen.
	 * @param sprite Life sprite representation.
	 * @param duration Life bonus duration in seconds.
	 */
	public Life(Position position, SpriteSheet sprite, int duration) {
		super(position, sprite, duration, Bonus.LIFE_BONUS);
	}
	/**
	 * Life default constructor. Life bonus duration set to its default value -> LIFE_DURATION
	 * @param position Position on screen.
	 * @param sprite Life sprite representation.
	 */
	public Life(Position position, SpriteSheet sprite) {
		super(position, sprite, Bonus.LIFE_DURATION, Bonus.LIFE_BONUS);
	}
	
	
	public void checkAvailable(){
		if((int)System.currentTimeMillis() - initialTime >= 1000*LIFE_DURATION){
			available=false;
		}
	}

	public void performAction(SpaceShip ship){
		ship.setLife(ship.getLife() + lifeBonus);
		available=false;
	}

}
