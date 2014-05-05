package Game.Engine;

import Sprite.Position;
import Sprite.Sprite;

public class Protection extends Bonus {

	public Protection(Position position, Sprite sprite) {
		super(position, sprite, Bonus.PROTECTION_DURATION, Bonus.PROTECTION_BONUS);
	}

	public Protection(Position position, Sprite sprite, int duration) {
		super(position, sprite, duration, Bonus.PROTECTION_BONUS);
	}

}
