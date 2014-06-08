package SpaceInvaders.Objects;

import java.awt.Dimension;

import SpaceInvaders.GUI.SpaceInvadersGame;
import Sprite.Position;
import Sprite.SpriteSheet;

/**
 * Boss extends Enemy class.
 * @author Jose Miguel Melo & Ricardo Loureiro
 */
public class Boss extends Enemy {
    public static final String BOSS1_LOCATION = "/BossImages/boss1.png";
    public static final String BOSS2_LOCATION = "/BossImages/boss2.png";

    /**
     * Boss constructor
     *
     * @param position Position on screen.
     * @param sprite   Boss sprite representation.
     */
    public Boss(Position position, SpriteSheet sprite) {
        super(position, sprite, Enemy.BOSS);
        velocityX = 2;
        velocityY = 2;
    }

    public void addShot(int type) {
        Shot shot = new Shot(new Position(position.getX() + (int) sprite.getDimension().getWidth() / 2, position.getY() + (int) sprite.getDimension().getHeight()),
                new SpriteSheet("/Sprites/BoosBullet1.png", new Dimension(Shot.SPRITE_DIMENSION, Shot.SPRITE_DIMENSION), 1, 1),
                10, type, true, "/Sprites/BoosBullet1.png", 18);
        shot.setBossFire(true);
        shots.add(shot);
    }

    @Override
    public void damageDone(SpaceShip spaceShip) {
        spaceShip.damageShip(13);
    }

    public void move(SpaceShip spaceShip) {
        if ((int) System.currentTimeMillis() - times.get(1) > 900) {
            if (!isDead()) {
                addShot(0);
                times.set(1, (int) System.currentTimeMillis());
            }
        }
        if (getPosition().getY() < spaceShip.getPosition().getY()) {
            int newTime = (int) System.currentTimeMillis();
            if (newTime - times.get(0) > 10) {
                if (spaceShip.getPosition().getX() > getPosition().getX() && getPosition().getX() > 0 &&
                        spaceShip.getPosition().getX() > (int) sprite.getDimension().getWidth()) {
                    moveLeft();
                    times.set(0, (int) System.currentTimeMillis());
                } else if (spaceShip.getPosition().getX() <= getPosition().getX() + (int) sprite.getDimension().getWidth() && getPosition().getX() < SpaceInvadersGame.WIDTH - sprite.getDimension().getWidth()) {
                    moveRight();
                    times.set(0, (int) System.currentTimeMillis());
                }
            }
        }
    }
}
