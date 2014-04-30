package Sprite;

public class Position {
	/** x position variable **/
	private int x;
	/** y position variable **/
	private int y;
	
	/**
	 * @param x x position
	 * @param y y position
	 */
	public Position(int x, int y){ this.setX(x); this.setY(y);}

	/**
	 * @return x x position
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return y position
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	

}
