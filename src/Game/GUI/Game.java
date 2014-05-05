package Game.GUI;

public class Game {
	public static SpaceInvadersGame spaceInvaders;
	
	public static void main(String args[]){
		spaceInvaders = new SpaceInvadersGame();
		spaceInvaders.start();
		return;
	}

}
