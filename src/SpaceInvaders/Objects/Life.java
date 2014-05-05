package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.Sprite;

public class Life extends Bonus{
	
	public Life(Position position, Sprite sprite, int duration) {
		super(position, sprite, duration, Bonus.LIFE_BONUS);
	}
	
	public Life(Position position, Sprite sprite) {
		super(position, sprite, Bonus.LIFE_DURATION, Bonus.LIFE_BONUS);
	}

}
