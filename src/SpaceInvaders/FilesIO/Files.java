package SpaceInvaders.FilesIO;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class Files that implements Serializable
 * @author Jose Miguel Melo & Ricardo Loureiro
 */
public class Files implements Serializable {
    private ArrayList<User> users;
    private int maxLeaderBoardSize = 8;

    /**
     * Default Constructor
     */
    public Files() {
        this.users = new ArrayList<User>();
    }

    /**
     * returns the array list of the current users in the leaderboard
     * @return
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * sets a specific into the leader board if the requirements are filled
     * @param user that just finished the game, containing points and his name
     * @return true if was successfully added or false if it wasn't
     */
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
        } else if (users.size() == maxLeaderBoardSize+1) {
            users.remove(maxLeaderBoardSize);
        }
        if (join == true)
            return true;
        else
            return false;
    }

    /**
     * Loads LeaderBoard from the File LeaderBoard.dat
     * setting the users to the ones who were saved in that file
     */
    public void loadLeaderBoard() {
        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(new FileInputStream(getClass().getResource("/LeaderBoard/LeaderBoard.dat").getPath()));
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

    /**
     * saves the current leader board into the LeaderBoard.dat files
     */
    public void saveLeaderBoard() {
        File saves = new File(getClass().getResource("/LeaderBoard/").getPath());
        if (!saves.exists()) {
            saves.mkdir();
        }
        ObjectOutputStream os = null;

        try {
            os = new ObjectOutputStream(
                    new FileOutputStream(getClass().getResource("/LeaderBoard/LeaderBoard.dat").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {

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
