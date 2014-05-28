package SpaceInvaders.Map;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class BackgroundMap {

	/** Map x position */
	private int x;
	/** Map y position */
	private int y;

	/** Map image */
	private BufferedImage mapImage;

	/** Background map tiles */
	private Tile[][] tileArray;
	/** Tile dimension */
	private Dimension tileDimension;
	/** Map image location */
	private String mapImageLocation;

	/** Dimension shown */
	private Dimension shownDimension;


	BackgroundMap(int x, int y, Dimension shownDimension, BufferedImage mapImage, Dimension tileDimension){
		this.setShownDimension(shownDimension);
		this.setX(x);
		this.setY(y);
		this.setMapImage(mapImage);
		this.mapImageLocation=null;
		this.setTileDimension(tileDimension);
		
		tileArray = new Tile[(int) (tileDimension.getHeight()*mapImage.getHeight())][ (int) (tileDimension.getWidth() * mapImage.getWidth())];
	}
	BackgroundMap(int x, int y, Dimension shownDimension, String mapImageLocation, Dimension tileDimension){
		this.setShownDimension(shownDimension);
		this.setX(x);
		this.setY(y);
		this.setMapImageLocation(mapImageLocation);
		this.setTileDimension(tileDimension);


		try {
			mapImage = ImageIO.read(
					getClass().getResourceAsStream(mapImageLocation)
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		tileArray = new Tile[(int) (tileDimension.getHeight()*mapImage.getHeight())][ (int) (tileDimension.getWidth() * mapImage.getWidth())];

	}


	/**
	 * @return Map x position
	 */
	public int getX() {	return x; }
	/**
	 * @param x Map x position
	 */
	public void setX(int x) { this.x = x; }
	/**
	 * @return Map y position
	 */
	public int getY() {	return y; }
	/**
	 * @param y Map y position
	 */
	public void setY(int y) { this.y = y; }
	/**
	 * @return Tile image
	 */
	public BufferedImage getMapImage() { return mapImage;	}
	/**
	 * @param tileImage Tile image
	 */
	public void setMapImage(BufferedImage mapImage) { this.mapImage = mapImage; }
	/**
	 * @return Map shown dimension
	 */
	public Dimension getShownDimension() { return shownDimension; }
	/**
	 * @param shownDimension Map shown dimension
	 */
	public void setShownDimension(Dimension shownDimension) { this.shownDimension = shownDimension;	}
	/**
	 * @return the tileDimension
	 */
	public Dimension getTileDimension() { return tileDimension; }
	/**
	 * @param tileDimension the tileDimension to set
	 */
	public void setTileDimension(Dimension tileDimension) {	this.tileDimension = tileDimension;	}
	/**
	 * @return the tile
	 */
	public Tile[][] getTileArray() { return tileArray; }
	/**
	 * @param tile the tile to set
	 */
	public void setTileArray(Tile[][] tileArray) { this.tileArray = tileArray; }
	/**
	 * @return Map image location
	 */
	public String getMapImageLocation() { return mapImageLocation;	}
	/**
	 * @param mapImageLocation the mapImageLocation to set
	 */
	public void setMapImageLocation(String mapImageLocation) { this.mapImageLocation = mapImageLocation; }


	public void loadTiles(){
		try{
			BufferedImage subImage;
			
			for(int i = 0; i < tileDimension.getHeight(); i++){
				for(int j=0; j < tileDimension.getWidth(); j++){
					subImage = mapImage.getSubimage((int)(j*tileDimension.getWidth()), (int) (i*tileDimension.getHeight()),
							(int)tileDimension.getWidth(), (int)tileDimension.getHeight());
					Tile tile = new Tile(subImage, (int) tileDimension.getHeight());
					tileArray[i][j]=tile;

				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g){
		int counterx = 0;
		int countery = 0;
		for(int i = y; i < (shownDimension.getHeight()/tileDimension.getHeight()); i++){
			for(int j = x; j <  (shownDimension.getWidth()/tileDimension.getWidth()); j++){
				g.drawImage(tileArray[i][j].getTile(), counterx*(int)tileDimension.getWidth(),
						countery *(int)tileDimension.getHeight(), (int)tileDimension.getWidth(),
						(int)tileDimension.getHeight(), null);
				counterx++;
			}
			countery++;
			counterx=0;
		}
	}

}
