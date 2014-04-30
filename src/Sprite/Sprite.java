package Sprite;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sprite {
	private BufferedImage image;
	private Dimension dimension;
	
	
	public Sprite(BufferedImage image, Dimension dimension){
		this.setImage(image);
		this.dimension = dimension;
	}
	
	public Sprite(Dimension dimension){
		this.dimension = dimension;
		this.setImage(new BufferedImage((int) dimension.getWidth(), (int) dimension.getHeight(), BufferedImage.TYPE_INT_ARGB ));
	}

	/**
	 * @return The sprite image
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @param image The image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	/**
	 * @param dimension The dimension to set
	 */
	public void setDimension(Dimension dimension){
		this.dimension = dimension;
	}
	
	/**
	 * @return The dimension
	 */
	public Dimension getDimension(){ return dimension;}
	
	/**
	 * Paint the sprite to screen
	 * @param g Graphics to draw image
	 * @param position Position in screen to draw image
	 * @param dimension Image dimension. null if default dimension
	 */
	public void paint(Graphics g, Position position){
		if(dimension==null)
			g.drawImage(image, position.getX(), position.getY(), null);
		else
			g.drawImage(image, position.getX(), position.getY(), (int)dimension.getWidth(), (int)dimension.getHeight(), null);
	}

	
	
	
}
