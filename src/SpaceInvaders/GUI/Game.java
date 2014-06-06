package SpaceInvaders.GUI;

import java.io.FileNotFoundException;

/**
 * Game main programme class
 * @author josemiguelmelo
 */
public class Game {
	public static SpaceInvadersGame spaceInvaders;
	/**
	 * Main programme
	 * @param args Terminal arguments
	 */
	public static void main(String args[]) throws FileNotFoundException {
		spaceInvaders = new SpaceInvadersGame();
		spaceInvaders.start();
		return;
	}
}
