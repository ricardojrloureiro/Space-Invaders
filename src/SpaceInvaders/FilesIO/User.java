package SpaceInvaders.FilesIO;

import java.io.Serializable;

/**
 *@author Jose Miguel Melo & Ricardo Loureiro
 */
public class User implements Serializable {
    private int points;
    private String name;

    public User(int points, String name) {
        this.points = points;
        this.name = name;
    }

    /**
     * @return the user points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @return returns the user name
     */
    public String getName() {
        return name;
    }
}
