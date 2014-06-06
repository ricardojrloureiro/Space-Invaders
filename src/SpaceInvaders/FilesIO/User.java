package SpaceInvaders.FilesIO;

import java.io.Serializable;

/**
 * Created by Loureiro on 06/06/2014.
 */
public class User implements Serializable {
    private int points;
    private String name;

    public User(int points,String name) {
        this.points = points;
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
