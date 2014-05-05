package SpaceInvaders.Engine;

public class Collision {

	public static final int RECTANGLE_DETECTION = 1;
	public static final int ROUNDED_DETECTION = 2;
	public static final int PIXEL_DETECTION = 3;

	private Object firstObject;
	private Object secondObject;
	private int type;

	/**
	 * In this constructor, the type is set to its default value. The default value is defined by the constant RECTANGLE_DETECTION.
	 * @param obj1 Object to detect if has collision with obj2
	 * @param obj2 Object to detect if has collision with obj1
	 */
	public Collision(Object obj1, Object obj2){
		this.setFirstObject(obj1);
		this.setSecondObject(obj2);
		this.type=RECTANGLE_DETECTION;
	}

	/**
	 * @return the firstObject
	 */
	public Object getFirstObject() {return firstObject;	}
	/**
	 * @param firstObject the firstObject to set
	 */
	public void setFirstObject(Object firstObject) {this.firstObject = firstObject;}
	/**
	 * @return the secondObject
	 */
	public Object getSecondObject() {return secondObject;}
	/**
	 * @param secondObject the secondObject to set
	 */
	public void setSecondObject(Object secondObject) {this.secondObject = secondObject;}

	/**
	 * Detects a collision using rectangle collision detection algorithm
	 * @return False if no collision. True if collision has been detected
	 */
	private boolean rectangleDetection(){

		return false;
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
