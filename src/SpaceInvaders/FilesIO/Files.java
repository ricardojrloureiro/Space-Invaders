package SpaceInvaders.FilesIO;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Loureiro on 06/06/2014.
 */
public class Files implements Serializable {
    private ArrayList<User> users;
    private int maxLeaderBoardSize = 8;

    public Files() {
        this.users = new ArrayList<User>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public boolean setUser(User user) {
        boolean join = false;
        for (int i = 0; i < users.size(); i++) {
            if (user.getPoints() > users.get(i).getPoints()) {
                users.add(i, user);
                join = true;
                break;
            }
        }
        // if cant add in the current check if there is spot for more users in the list
        if (users.size() < maxLeaderBoardSize && join == false) {
            users.add(user);
        } else if (users.size() >= maxLeaderBoardSize) {
            users.remove(maxLeaderBoardSize);
        }
        if (join == true)
            return true;
        else
            return false;
    }

    public void loadLeaderBoard() {
        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(new FileInputStream(getClass().getResource("/LeaderBoard/LeaderBoard.dat").getPath()));
            if(is==null)
                return;
        } catch (FileNotFoundException e) {
            saveLeaderBoard();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            users = (ArrayList<User>) is.readObject();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveLeaderBoard() {
        File saves = new File(getClass().getResource("/LeaderBoard/LeaderBoard.dat").getPath());
        if (!saves.exists()) {
            saves.mkdir();
        }
        ObjectOutputStream os = null;

        try {
            os = new ObjectOutputStream(
                    new FileOutputStream(getClass().getResource("/LeaderBoard/LeaderBoard.dat").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (os != null) {
            try {
                os.writeObject(users);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
