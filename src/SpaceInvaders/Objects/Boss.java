package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.SpriteSheet;

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
	public Boss(Position position, SpriteSheet sprite) {
		super(position, sprite, Enemy.BOSS);
	}

}
