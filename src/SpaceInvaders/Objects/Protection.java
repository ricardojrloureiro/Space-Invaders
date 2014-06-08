package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.SpriteSheet;

/**
 * Protection extends Bonus class. Represents protection bonus type.
 * @author Jose Miguel Melo & Ricardo Loureiro
 */
public class Protection extends Bonus {
	/**
	 * Protection default constructor. Protection bonus duration set to its default value -> PROTECTION_DURATION.
	 * @param position Position on screen.
	 * @param sprite Protection sprite representation.
	 */
	public Protection(Position position, SpriteSheet sprite) {
		super(position, sprite, Bonus.PROTECTION_DURATION, Bonus.PROTECTION_BONUS);
	}
	/**
	 * Protection constructor.
	 * @param position Position on screen.
	 * @param sprite Protection sprite representation.
	 * @param duration Protection bonus duration in seconds.
	 */
	public Protection(Position position, SpriteSheet sprite, int duration) {
		super(position, sprite, duration, Bonus.PROTECTION_BONUS);
	}

    /**
     * if the time has passed sets the availability to false
     */
	public void checkAvailable(){
		if((int)System.currentTimeMillis() - initialTime >= 1000*PROTECTION_DURATION){
			available=false;
		}
	}

    /**
     * sets a protection to the spaceship, immune to damage, and sets the availability to false
     * @param ship
     */
	public void performAction(SpaceShip ship){
		ship.setProtection(true);
		available=false;
	}
}
