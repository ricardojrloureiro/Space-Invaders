package Game.Engine;

import Sprite.Position;
import Sprite.Sprite;

public class NormalEnemy extends Enemy{

	public NormalEnemy(Position position, Sprite sprite) {
		super(position, sprite, Enemy.NORMAL);
	}
	
}
