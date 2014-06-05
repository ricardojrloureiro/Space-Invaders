package SpaceInvaders.GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import SpaceInvaders.Objects.Shot;

/**
 * Created by ricardo on 05-06-2014.
 */
public class SpaceInvadersListener implements KeyListener {
	private SpaceInvaders spaceInvaders;
	
	
	public SpaceInvadersListener(SpaceInvaders spaceInvaders){
		this.spaceInvaders = spaceInvaders;
	}
	
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                SpaceInvaders.keysPressed.set(0, true);
                break;
            case KeyEvent.VK_RIGHT:
                SpaceInvaders.keysPressed.set(1, true);
                break;
            case KeyEvent.VK_DOWN:
                SpaceInvaders.keysPressed.set(2, true);
                break;
            case KeyEvent.VK_UP:
                SpaceInvaders. keysPressed.set(3, true);
                break;
            case KeyEvent.VK_SPACE: {
                if (((int) System.currentTimeMillis() - spaceInvaders.getLastShotTime()) >= 200) {
                    spaceInvaders.setLastShotTime((int) System.currentTimeMillis());
                    spaceInvaders.getSpaceShip().addShot(Shot.TYPE_NORMAL);
                }
            }
            break;
            case KeyEvent.VK_Z: {
                if (((int) System.currentTimeMillis() - spaceInvaders.getLastLaserFire()) >= 4000) {
                    spaceInvaders.setLastLaserFire((int) System.currentTimeMillis());
                    spaceInvaders.getSpaceShip().addShot(Shot.TYPE_LASER);
                }
            }
            break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                SpaceInvaders.keysPressed.set(0, false);
                break;
            case KeyEvent.VK_RIGHT:
                SpaceInvaders.keysPressed.set(1, false);
                break;
            case KeyEvent.VK_DOWN:
                SpaceInvaders.keysPressed.set(2, false);
                break;
            case KeyEvent.VK_UP:
                SpaceInvaders.keysPressed.set(3, false);
                break;
            default:
                break;
        }
    }
}
