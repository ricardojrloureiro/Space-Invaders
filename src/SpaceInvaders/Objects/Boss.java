package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.Sprite;

/**
 * Boss extends Enemy class.
 * @author josemiguelmelo
 */
public class Boss extends Enemy{

	/**
	 * Boss constructor
	 * @param position Position on screen.
	 * @param sprite Boss sprite representation.
	 */
	public Boss(Position position, Sprite sprite) {
		super(position, sprite, Enemy.BOSS);
	}

}
