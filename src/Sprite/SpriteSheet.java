package Sprite;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * Sprite sheet class. Represents a sheet of sprites (images).
 * @author josemiguelmelo
 */
public class SpriteSheet {
	/** Sprite sheet location. */
	private String location;

	/** Sprite sheet number of rows. */
	private int numRows;
	/** Sprite sheet number of columns. */
	private int numCols;

	/** Sprite sheet dimension */
	private Dimension dimension;
	/** Bidimensional array of sprites. Contains all sprites loaded from sprite sheet. */
	private Sprite[][] sprites;
	/** Sprite sheet image */
	private BufferedImage image;
	
	/**
	 * SpriteSheet class constructor.
	 * @param location Sprite sheet location.
	 * @param dimension Sprite sheet dimension.
	 * @param numRows Sprite sheet number of rows.
	 * @param numCols Sprite sheet number of columns.
	 */
	public SpriteSheet(String location, Dimension dimension, int numRows, int numCols){
		this.dimension = dimension;
		this.location = location;
		this.numCols = numCols;
		this.numRows = numRows;
		sprites = new Sprite[numRows][numCols];
	}

	/**
	 * @param numRows Sprite sheet number of rows.
	 */
	public void setRows(int numRows){ this.numRows = numRows; }
	/**
	 * @return Sprite sheet number of rows.
	 */
	public int getRows(){ return this.numRows; }
	/**
	 * @param numCols Sprite sheet number of columns.
	 */
	public void setCols(int numCols){ this.numCols = numCols; }
	/**
	 * @return Sprite sheet number of columns.
	 */
	public int getCols(){ return this.numCols;}
	/**
	 * @param dimension Sprite sheet dimension.
	 */
	public void setDimension(Dimension dimension){ this.dimension = dimension;}
	/**
	 * @return Sprite sheet dimension.
	 */
	public Dimension getDimension(){ return this.dimension;}
	/**
	 * @return Bidimension array with all sprites loaded from spritesheet.
	 */
	public Sprite[][] getSprite(){ return this.sprites; }
	/**
	 * @return Spritesheet image.
	 */
	public BufferedImage getImage(){ return this.image;}
	/**
	 * @param image Spritesheet image.
	 */
	public void setImage(BufferedImage image){ this.image = image;}
	
	
	public Sprite getSpriteAt(int x, int y){ return sprites[y][x];}
	

	/**
	 * Loads all sprites from spritesheet file. 
	 * Sprites loaded are stored in a bidimensional array.
	 */
	public void loadSprite(){
		try{
			image = ImageIO.read(
					getClass().getResourceAsStream(location)
					);
			BufferedImage subImage;
			
			for(int i = 0; i < numRows; i++){
				for(int j = 0 ; j < numCols; j++){
					
					subImage = image.getSubimage(j * (int)dimension.getWidth(), i * (int)dimension.getHeight(),
							(int) dimension.getWidth(), (int) dimension.getHeight());
					sprites[i][j] = new Sprite(subImage, null);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
