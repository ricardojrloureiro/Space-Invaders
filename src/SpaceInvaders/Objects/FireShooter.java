package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.SpriteSheet;

/**
 * Created by ricardo on 27-05-2014.
 */
public class FireShooter extends Enemy{

    public static final String LOCATION = "/SpaceShips/battleShip2.png";
    public static final int SPRITE_DIMENSION = 85;

    /**
     * Suicidal class constructor.
     * @param position Position on screen.
     * @param sprite Suicidal sprite representation.
     */

    public FireShooter(Position position, SpriteSheet sprite) {
        super(position, sprite, Enemy.FIRESHOOTER);
    }
}
