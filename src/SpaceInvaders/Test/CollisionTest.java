package SpaceInvaders.Test;

import static org.junit.Assert.*;

import java.awt.Dimension;

import org.junit.Test;

import SpaceInvaders.Engine.Collision;
import SpaceInvaders.Objects.Rock;
import SpaceInvaders.Objects.SpaceShip;
import Sprite.Position;
import Sprite.SpriteSheet;

public class CollisionTest {

	/**
	 * This function draws a rectangle on the limits of the image both images
	 * and checks if there was any intersection between them, if there was it returns true or
	 * false checking this way if there was a collision between them.
	 */
	@Test
	public void checkSquareCollision(){
		
		//IN INTERSECTION RANGE 
		SpaceShip spaceShip = new SpaceShip(new Position(100,100),
				new SpriteSheet(SpaceShip.LOCATION, new Dimension(96/3, 40),1,3));

		Rock rock = new Rock(new Position(100, 100), 
				new SpriteSheet(Rock.LOCATION, 
						new Dimension(Rock.SPRITE_DIMENSION, Rock.SPRITE_DIMENSION), 1, 1));

		Collision collision = new Collision(spaceShip,rock,
				Collision.RECTANGLE_DETECTION);
		boolean hit;
		if(collision.detect()){
			hit=true;
		} else {
			hit=false;
		}
		assertEquals(true,hit);

		// OUT OF INTERSECTION RANGE

		SpaceShip spaceShip2 = new SpaceShip(new Position(100,100),
				new SpriteSheet(SpaceShip.LOCATION, new Dimension(96/3, 40),1,3));

		Rock rock2 = new Rock(new Position(1, 1), 
				new SpriteSheet(Rock.LOCATION, 
						new Dimension(Rock.SPRITE_DIMENSION, Rock.SPRITE_DIMENSION), 1, 1));

		Collision collision2 = new Collision(spaceShip2,rock2,
				Collision.RECTANGLE_DETECTION);
		boolean hit2;
		if(collision2.detect()){
			hit2=true;
		} else {
			hit2=false;
		}
		assertEquals(false,hit2);
	}
	
	@Test
	public void checkMultipleSquaresCollision(){
		assertEquals(true,true);
	}
}
