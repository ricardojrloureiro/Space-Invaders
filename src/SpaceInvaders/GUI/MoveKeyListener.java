package SpaceInvaders.GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import SpaceInvaders.Objects.SpaceShip;

public class MoveKeyListener implements KeyListener{

	private ArrayList<Boolean> keysPressed;
	private SpaceShip spaceShip;
	

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			spaceShip.getPosition().setX(spaceShip.getPosition().getX() - SpaceInvaders.SPACESHIP_MOVE_SPEED);
			break;
		case KeyEvent.VK_RIGHT:
			spaceShip.getPosition().setX(spaceShip.getPosition().getX() + SpaceInvaders.SPACESHIP_MOVE_SPEED);
			break;
		case KeyEvent.VK_DOWN:
			spaceShip.getPosition().setY(spaceShip.getPosition().getY() + SpaceInvaders.SPACESHIP_MOVE_SPEED);
			break;
		case KeyEvent.VK_UP:
			spaceShip.getPosition().setY(spaceShip.getPosition().getY() - SpaceInvaders.SPACESHIP_MOVE_SPEED);
			break;
		case KeyEvent.VK_SPACE:
			spaceShip.addShot();
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
