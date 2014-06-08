package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.SpriteSheet;

/**
 * @author Jose Miguel Melo & Ricardo Loureiro
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
        this.velocityX = 1;
        this.velocityY = 1;
        
    }

    /**
     * Damage done to spaceShip from the Fire shooter.
     * @param spaceShip
     */
    @Override
	public void damageDone(SpaceShip spaceShip) {
        spaceShip.damageShip(10);
	}

    /**
     * Movement by the fire shooter, stars slowly going down while chasing the spaceship and firing at her.
     * @param spaceShip player space ship
     */
    public void move(SpaceShip spaceShip){
    	  if((int)System.currentTimeMillis() - times.get(1) > 800){
              if(!isDead()){
                  addShot(0);
                  times.set(1,(int)System.currentTimeMillis());
              }
          }
          if((int) System.currentTimeMillis() - times.get(2) > 50) {
              moveDown();
              times.set(2,(int) System.currentTimeMillis());
          }
          if(getPosition().getY()<spaceShip.getPosition().getY()) {
              int newTime = (int) System.currentTimeMillis();
              if(newTime-times.get(0) > 10){
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
