package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.SpriteSheet;

/**
 * NormalEnemy extends Enemy class.
 * @author josemiguelmelo
 */
public class NormalEnemy extends Enemy{
	/**
	 * NormalEnemy constructor.
	 * @param position Position on screen.
	 * @param sprite NormalEnemy sprite representation.
	 */
	public NormalEnemy(Position position, SpriteSheet sprite) {
		super(position, sprite, Enemy.NORMAL);
	}
	
}
