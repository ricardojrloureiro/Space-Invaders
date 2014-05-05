package SpaceInvaders.Objects;

import java.awt.Graphics;

/**
 * Interface drawable. Used in objects that can be drawn to screen.
 * @author josemiguelmelo
 */
public interface Drawable {
	/**
	 * Draw object that implements this interface to screen/component with graphics represented by variable g
	 * @param g Graphics to draw image
	 */
	public void draw(Graphics g);
}
