package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.Sprite;

public class Boss extends Enemy{

	public Boss(Position position, Sprite sprite) {
		super(position, sprite, Enemy.BOSS);
	}

}
