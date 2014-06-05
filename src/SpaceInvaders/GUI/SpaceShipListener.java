package SpaceInvaders.GUI;

import SpaceInvaders.Objects.Shot;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by ricardo on 05-06-2014.
 */
public class SpaceShipListener implements KeyListener {

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
                if (((int) System.currentTimeMillis() - SpaceInvaders.lastShotTime) >= 200) {
                    SpaceInvaders.lastShotTime = (int) System.currentTimeMillis();
                    SpaceInvaders.spaceShip.addShot(Shot.TYPE_NORMAL);
                }
            }
            break;
            case KeyEvent.VK_Z: {
                if (((int) System.currentTimeMillis() - SpaceInvaders.lastLaserFire) >= 4000) {
                    SpaceInvaders.lastLaserFire = (int) System.currentTimeMillis();
                    SpaceInvaders.spaceShip.addShot(Shot.TYPE_LASER);
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
