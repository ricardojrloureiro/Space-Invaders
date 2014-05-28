package SpaceInvaders.Map;

import java.awt.image.BufferedImage;


/**
 * Part of full map
 * @author josemiguelmelo
 */
public class Tile {
	/** Tile image **/
	private BufferedImage tile;
	/** Tile size / image size **/
	private int tileSize;
	/** Tile type. Can collide or not */
	private int type;
	
	/** Tile Constructor 
	 * @param image Tile image
	 * @param tileSize Tile image size
	 **/
	public Tile(BufferedImage image, int tileSize){
		this.setTile(image);
		this.setTileSize(tileSize);
	}
	
	/** Set tile type.
	 * @param type Tile type
	 */
	public void setType(int type){ this.type = type; }
	/** Get tile type.
	 * @return Tile type
	 */
	public int getType(){ return this.type;}
	/** Get tile image
	 * @return Tile image
	 */
	public BufferedImage getTile() {return tile;}
	/** Set tile image
	 * @param tile Tile image
	 */
	public void setTile(BufferedImage tile) {this.tile = tile;}
	/** Get tile image size
	 * @return Tile image size
	 */
	public int getTileSize() {return tileSize;}
	/** Set tile image size
	 * @param tileSize Tile image size
	 */
	public void setTileSize(int tileSize) {	this.tileSize = tileSize;}
	
	
}
