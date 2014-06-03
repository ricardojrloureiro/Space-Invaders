package SpaceInvaders.Map;

import java.awt.Dimension;
import java.awt.Graphics;
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


	public BackgroundMap(int x, int y, Dimension shownDimension, BufferedImage mapImage, Dimension tileDimension){
		this.setShownDimension(shownDimension);
		this.setX(x);
		this.setY(y);
		this.setMapImage(mapImage);
		this.mapImageLocation=null;
		this.setTileDimension(tileDimension);
		tileArray = new Tile[(int) (mapImage.getHeight()/tileDimension.getHeight())][ (int) ( mapImage.getWidth()/tileDimension.getWidth())];
		loadTiles();
	}
	
	public BackgroundMap(int x, int y, Dimension shownDimension, String mapImageLocation, Dimension tileDimension){
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
		loadTiles();
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
	 * @param mapImage Tile image
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
	 * @param tileArray the tile to set
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
			
			for(int i = 0; i < (mapImage.getHeight())/(tileDimension.getHeight()); i++){
				for(int j=0; j < (mapImage.getWidth())/(tileDimension.getWidth()); j++){
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

	public void draw(Graphics g){
		int counterx = 0;
		int countery = 0;
		int i = y;
		int j =x;
		
		if(i+(shownDimension.getHeight()/tileDimension.getHeight()) >= (mapImage.getHeight()/tileDimension.getHeight()))
			i=(int) ((mapImage.getHeight()/tileDimension.getHeight()) - (shownDimension.getHeight()/tileDimension.getHeight()));
		
		if(j+(shownDimension.getWidth()/tileDimension.getWidth()) >= (mapImage.getWidth()/tileDimension.getWidth()))
			j=(int) ((mapImage.getWidth()/tileDimension.getWidth()) - (shownDimension.getWidth()/tileDimension.getWidth()));
		
		for(; i < (shownDimension.getHeight()/tileDimension.getHeight()); i++){
			for(; j <  (shownDimension.getWidth()/tileDimension.getWidth()); j++){
				System.out.println(i);
				System.out.println(tileArray[i][j].getTile().getHeight());
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
