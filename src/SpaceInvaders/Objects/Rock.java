package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.SpriteSheet;
/**
 * Rock extends SpaceObject class.
 * @author Jose Miguel Melo & Ricardo Loureiro
 */
public class Rock extends SpaceObject{
	
	public static final String LOCATION = "/Sprites/rock.png";
	public static final int SPRITE_DIMENSION = 40;

    /**
     * velocity of the rocks.
     * @param position
     * @param sprite
     */
	public Rock(Position position, SpriteSheet sprite) {
		super(position, sprite);
        enabled=true;
        this.velocityX = 0;
        this.velocityY = 2;
	}

    /**
     * movement of the rocks
     */
	public void move() {
		moveDown();
	}

}
