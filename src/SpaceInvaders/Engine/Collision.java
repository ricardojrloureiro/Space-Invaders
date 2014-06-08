package SpaceInvaders.Engine;

import java.awt.Rectangle;

import SpaceInvaders.Objects.SpaceObject;

/**
 * Class Collision
 * @author Jose Miguel Melo & Ricardo Loureiro
 */
public class Collision {

	public static final int RECTANGLE_DETECTION = 1;
	public static final int ROUNDED_DETECTION = 2;
	public static final int PIXEL_DETECTION = 3;

	private SpaceObject firstObject;
	private SpaceObject secondObject;
	private int type;

	/**
	 * In this constructor, the type is set to its default value. The default value is defined by the constant RECTANGLE_DETECTION.
	 * @param obj1 Object to detect if has collision with obj2
	 * @param obj2 Object to detect if has collision with obj1
	 */
	public Collision(SpaceObject obj1, SpaceObject obj2){
		this.setFirstObject(obj1);
		this.setSecondObject(obj2);
		this.type=RECTANGLE_DETECTION;
	}
	
	/**
	 * In this constructor, the type is set to its default value. The default value is defined by the constant RECTANGLE_DETECTION.
	 * @param obj1 Object to detect if has collision with obj2
	 * @param obj2 Object to detect if has collision with obj1
	 * @param type define the type of algorithm that will evaluate the collision
	 */
	public Collision(SpaceObject obj1, SpaceObject obj2, int type){
		this.setFirstObject(obj1);
		this.setSecondObject(obj2);
		this.type=type;
	}

	/**
	 * @return the firstObject
	 */
	public SpaceObject getFirstObject() {return firstObject;	}
	/**
	 * @param firstObject the firstObject to set
	 */
	public void setFirstObject(SpaceObject firstObject) {this.firstObject = firstObject;}
	/**
	 * @return the secondObject
	 */
	public SpaceObject getSecondObject() {return secondObject;}
	/**
	 * @param secondObject the secondObject to set
	 */
	public void setSecondObject(SpaceObject secondObject) {this.secondObject = secondObject;}
	
	/**
	 * Detects a collision using rectangle collision detection algorithm
	 * @return False if no collision. True if collision has been detected
	 */
	
	private boolean rectangleDetection(){
        if(firstObject ==null || secondObject == null)
            return false;
		Rectangle first = new Rectangle(firstObject.getPosition().getX(),firstObject.getPosition().getY()
				,(int)firstObject.getSprite().getDimension().getWidth(),
				(int) firstObject.getSprite().getDimension().getHeight());
		Rectangle second = new Rectangle(secondObject.getPosition().getX(),secondObject.getPosition().getY()
				,(int)secondObject.getSprite().getDimension().getWidth(),
				(int) secondObject.getSprite().getDimension().getHeight());
	
		return first.intersects(second);
	}
	/**
	 * Detects a collision using rounded collision detection algorithm
	 * @return False if no collision. True if collision has been detected
	 */
	private boolean roundedDetection(){

		return false;
	}
	/**
	 * Detects a collision using pixel by pixel collision detection algorithm
	 * @return False if no collision. True if collision has been detected
	 */
	private boolean pixelDetection(){

		return false;
	}

	/**
	 * Detects a collision using the specified algorithm when creating this class object.
	 * @return False if no collision. True if collision has been detected
	 */
	public boolean detect(){
		switch(type){
		case RECTANGLE_DETECTION:
			return rectangleDetection();
		case ROUNDED_DETECTION:
			return roundedDetection();
		case PIXEL_DETECTION:
			return pixelDetection();
		default:
			break;
		}
		return false;
	}
}
