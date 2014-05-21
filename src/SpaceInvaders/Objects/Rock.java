package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.SpriteSheet;

public class Rock extends SpaceObject{
	
	public static final String LOCATION = "/Sprites/rock.png";
	public static final int SPRITE_DIMENSION = 40;
	
	
	public Rock(Position position, SpriteSheet sprite) {
		super(position, sprite);
	}


	public void move() {
		// TODO Auto-generated method stub
		getPosition().setY(getPosition().getY() + 1);
	}

}
