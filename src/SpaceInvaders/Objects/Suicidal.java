package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.Sprite;

/**
 * Suicidal extends Enemy class. Represents suicidal type of enemies.
 * @author josemiguelmelo
 */
public class Suicidal extends Enemy {
	/**
	 * Suicidal class constructor.
	 * @param position Position on screen.
	 * @param sprite Suicidal sprite representation.
	 */
	public Suicidal(Position position, Sprite sprite) {
		super(position, sprite, Enemy.SUICIDAL);
	}

}
