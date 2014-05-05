package SpaceInvaders.GUI;

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
	public static void main(String args[]){
		spaceInvaders = new SpaceInvadersGame();
		spaceInvaders.start();
		return;
	}

}
