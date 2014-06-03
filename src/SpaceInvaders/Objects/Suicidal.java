package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.SpriteSheet;

/**
 * Suicidal extends Enemy class. Represents suicidal type of enemies.
 * @author josemiguelmelo
 */
public class Suicidal extends Enemy {

	public static final String LOCATION = "/SpaceShips/suicidalShip.png";
	public static final int SPRITE_DIMENSION = 64;

	
	/**
	 * Suicidal class constructor.
	 * @param position Position on screen.
	 * @param sprite Suicidal sprite representation.
	 */
	public Suicidal(Position position, SpriteSheet sprite) {
		super(position, sprite, Enemy.SUICIDAL);
		this.velocityX = 2;
		this.velocityY = 2;
	}
	
	@Override
	public void move(SpaceShip spaceShip){
		moveDown();
        if(getPosition().getY() < spaceShip.getPosition().getY()) {
            int newTime = (int) System.currentTimeMillis();
            if(newTime-times.get(0) > 5){
                if(spaceShip.getPosition().getX() > getPosition().getX()){
                    moveRight();
                    times.set(0,(int) System.currentTimeMillis());
                }
                else if(spaceShip.getPosition().getX() < getPosition().getX()) {
                    moveLeft();
                    times.set(0,(int) System.currentTimeMillis());
                }
            }
        }
	}

}
