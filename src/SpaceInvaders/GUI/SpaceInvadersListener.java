package SpaceInvaders.GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import SpaceInvaders.Objects.Shot;

/**
 * Created by ricardo on 05-06-2014.
 */
public class SpaceInvadersListener implements KeyListener {
	private SpaceInvaders spaceInvaders;


	public static int LEFT = KeyEvent.VK_LEFT;
	public static int RIGHT = KeyEvent.VK_RIGHT;
	public static int UP = KeyEvent.VK_UP;
	public static int DOWN = KeyEvent.VK_DOWN;
	public static int SHOT = KeyEvent.VK_SPACE;
	public static int LASER = KeyEvent.VK_X;


	public SpaceInvadersListener(SpaceInvaders spaceInvaders){
		this.spaceInvaders = spaceInvaders;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
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

	@Override
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
