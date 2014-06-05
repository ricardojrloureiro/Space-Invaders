package SpaceInvaders.Test;

import static org.junit.Assert.*;

import java.awt.Dimension;

import org.junit.Test;

import SpaceInvaders.GUI.SpaceInvaders;
import SpaceInvaders.Objects.SpaceShip;
import Sprite.Position;
import Sprite.SpriteSheet;

public class SpaceShipTest {
	/**
	 * Checks if the current position of the spaceShip changes.
	 */
	@Test
	public void checkMovement() {
		SpaceShip spaceShip = new SpaceShip(new Position(100,100),
				new SpriteSheet(SpaceShip.LOCATION, new Dimension(96/3, 40),1,3));
		
		Position currentPosition = new Position(spaceShip.getPosition().getX(),spaceShip.getPosition().getY());
		spaceShip.move(SpaceInvaders.LEFT);
		spaceShip.move(SpaceInvaders.LEFT);
		spaceShip.move(SpaceInvaders.UP);
		Position movedPosition = new Position(spaceShip.getPosition().getX(),spaceShip.getPosition().getY());
		boolean equal;
		if(currentPosition == movedPosition) {
			equal = true;
		} else {
			equal = false;
		}
		assertEquals(equal, false);
	}
	
	/**
	 * Checks if the current shot was added to the spaceShip;
	 * in the running game there is another option that is due to the frequency of the
	 * fire rate, allowing or not the user to fire and therefore not adding the shot to the
	 * space ship.
	 */
	@Test
	public void checkShots() {
		SpaceShip spaceShip = new SpaceShip(new Position(100,100),
				new SpriteSheet(SpaceShip.LOCATION, new Dimension(96/3, 40),1,3));
		spaceShip.addShot(1);
		spaceShip.addShot(1);
		assertEquals(2,spaceShip.getShots().size());
	}
	
	/**
	 * Check if the space ship dies and therefore if its possible to die upon an enemy attack
	 * two different dieing possibilities due to imediate dead (colisions between rocks & enemys)
	 * or the collision between shots fired by the enemies that only take a part of the current life
	 */
	@Test
	public void checkDead() {
		SpaceShip spaceShip = new SpaceShip(new Position(100,100),
				new SpriteSheet(SpaceShip.LOCATION, new Dimension(96/3, 40),1,3));
		spaceShip.damageShip(100);
		assertEquals(true,spaceShip.getDead());
		
		SpaceShip spaceShip2 = new SpaceShip(new Position(100,100),
				new SpriteSheet(SpaceShip.LOCATION, new Dimension(96/3, 40),1,3));
		spaceShip2.setDead(true);
		assertEquals(true,spaceShip2.getDead());
	}
	
	/** 
	 * Check if the points of the space ships are correctly increased when it's shots destroy an object
	 */
	@Test
	public void checkPoints() {
		SpaceShip spaceShip = new SpaceShip(new Position(100,100),
				new SpriteSheet(SpaceShip.LOCATION, new Dimension(96/3, 40),1,3));
		spaceShip.increasePoints(10);
		spaceShip.increasePoints(50);
		assertEquals(60,spaceShip.getPoints());
	}
}
