package SpaceInvaders.Objects;

import Sprite.Position;
import Sprite.SpriteSheet;

public class Rock extends SpaceObject{
	
	public static final String LOCATION = "/Sprites/rock.png";
	public static final int SPRITE_DIMENSION = 40;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private boolean enabled;
	
	public Rock(Position position, SpriteSheet sprite) {
		super(position, sprite);
        enabled=true;
        this.velocityX = 0;
        this.velocityY = 2;
	}


	public void move() {
		moveDown();
	}

}
