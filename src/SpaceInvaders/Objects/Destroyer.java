package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.SpriteSheet;

/**
 * Destroyer extends Enemy class
 * @author josemiguelmelo
 */
public class Destroyer extends Enemy {
	
	public static final String LOCATION = "/SpaceShips/battleShip3.png";
	public static final int SPRITE_DIMENSION = 64;
	/**
	 * Destroyer constructor.
	 * @param position Position on screen.
	 * @param sprite Destroyer sprite representation.
	 */
	public Destroyer(Position position, SpriteSheet sprite) {
		super(position, sprite, Enemy.DESTROYER);
		this.velocityX=1;
		this.velocityY=1;
	}
	
	@Override
	public void move(SpaceShip spaceShip){
		moveDown();
	}
}
