package SpaceInvaders.GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import SpaceInvaders.Objects.Shot;

/**
 * SpaceInvadersListener class implements KeyListener
 * @author Jose Miguel Melo & Ricardo Loureiro
 */
public class SpaceInvadersListener implements KeyListener {
	private SpaceInvaders spaceInvaders;


	public static int LEFT = KeyEvent.VK_LEFT;
	public static int RIGHT = KeyEvent.VK_RIGHT;
	public static int UP = KeyEvent.VK_UP;
	public static int DOWN = KeyEvent.VK_DOWN;
	public static int SHOT = KeyEvent.VK_SPACE;
	public static int LASER = KeyEvent.VK_X;

    /**
     * Constructor
     * @param spaceInvaders
     */
	public SpaceInvadersListener(SpaceInvaders spaceInvaders){
		this.spaceInvaders = spaceInvaders;
	}


	public void keyTyped(KeyEvent e) {
	}

    /**
     * allows the user to navegate in diagonals across the map by activating each time a key is pressed.
     * also allows the user to fire shots or lasers depending of each key.
     * @param e key pressed event
     */

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == LEFT)
			SpaceInvaders.keysPressed.set(0, true);
		if(e.getKeyCode() == RIGHT)
			SpaceInvaders.keysPressed.set(1, true);
		if(e.getKeyCode() == DOWN)
			SpaceInvaders.keysPressed.set(2, true);
		if(e.getKeyCode() == UP)
			SpaceInvaders.keysPressed.set(3, true);

		if(e.getKeyCode() == SHOT){
			if (((int) System.currentTimeMillis() - spaceInvaders.getLastShotTime()) >= 200) {
				spaceInvaders.setLastShotTime((int) System.currentTimeMillis());
				spaceInvaders.getSpaceShip().addShot(Shot.TYPE_NORMAL);
			}
		}
		if(e.getKeyCode() == LASER){
			if (((int) System.currentTimeMillis() - spaceInvaders.getLastLaserFire()) >= 4000) {
				spaceInvaders.setLastLaserFire((int) System.currentTimeMillis());
                spaceInvaders.setLastShotTime((int) System.currentTimeMillis());
				spaceInvaders.getSpaceShip().addShot(Shot.TYPE_LASER);
			}
		}
	}

    /**
     * after the key is released the boolean is set to false
     * @param e key released event
     */
	public void keyReleased(KeyEvent e) {

		if(e.getKeyCode() == LEFT)
			SpaceInvaders.keysPressed.set(0, false);
		if(e.getKeyCode() == RIGHT)
			SpaceInvaders.keysPressed.set(1, false);
		if(e.getKeyCode() == DOWN)
			SpaceInvaders.keysPressed.set(2, false);
		if(e.getKeyCode() == UP)
			SpaceInvaders.keysPressed.set(3, false);
	}
}
