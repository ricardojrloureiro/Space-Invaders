package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.Sprite;

/**
 * Protection extends Bonus class. Represents protection bonus type.
 * @author josemiguelmelo
 */
public class Protection extends Bonus {
	/**
	 * Protection default constructor. Protection bonus duration set to its default value -> PROTECTION_DURATION.
	 * @param position Position on screen.
	 * @param sprite Protection sprite representation.
	 */
	public Protection(Position position, Sprite sprite) {
		super(position, sprite, Bonus.PROTECTION_DURATION, Bonus.PROTECTION_BONUS);
	}
	/**
	 * Protection constructor.
	 * @param position Position on screen.
	 * @param sprite Protection sprite representation.
	 * @param duration Protection bonus duration in seconds.
	 */
	public Protection(Position position, Sprite sprite, int duration) {
		super(position, sprite, duration, Bonus.PROTECTION_BONUS);
	}

}
