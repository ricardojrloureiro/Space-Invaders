package Sprite;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String location;

	private int numRows;
	private int numCols;

	private Dimension dimension;
	private Sprite[][] sprites;
	private BufferedImage image;
	
	
	public SpriteSheet(String location, Dimension dimension, int numRows, int numCols){
		this.dimension = dimension;
		this.location = location;
		this.numCols = numCols;
		this.numRows = numRows;
		sprites = new Sprite[numRows][numCols];
	}

	public void setRows(int numRows){ this.numRows = numRows; }
	public int getRows(){ return this.numRows; }
	public void setCols(int numCols){ this.numCols = numCols; }
	public int getCols(){ return this.numCols;}
	public void setDimension(Dimension dimension){ this.dimension = dimension;}
	public Dimension getDimension(){ return this.dimension;}
	public Sprite[][] getSprite(){ return this.sprites; }
	public BufferedImage getImage(){ return this.image;}
	public void setImage(BufferedImage image){ this.image = image;}

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
