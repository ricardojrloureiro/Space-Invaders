package Sprite;

public class Position {
	/** x position variable **/
	private int x;
	/** y position variable **/
	private int y;
	
	/**
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public Position(int x, int y){ this.setX(x); this.setY(y);}

	/**
	 * @return x x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x The x coordinate to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y coordinate to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	

}
