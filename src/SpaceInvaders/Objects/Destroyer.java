package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.SpriteSheet;

/**
 * Destroyer extends Enemy class
 * @author josemiguelmelo
 */
public class Destroyer extends Enemy {
	/**
	 * Destroyer constructor.
	 * @param position Position on screen.
	 * @param sprite Destroyer sprite representation.
	 */
	public Destroyer(Position position, SpriteSheet sprite) {
		super(position, sprite, Enemy.DESTROYER);
	}

}
