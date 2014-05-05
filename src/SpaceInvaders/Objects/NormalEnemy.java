package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.Sprite;

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
	public NormalEnemy(Position position, Sprite sprite) {
		super(position, sprite, Enemy.NORMAL);
	}
	
}
