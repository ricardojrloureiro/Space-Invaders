package SpaceInvaders.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import SpaceInvaders.FilesIO.User;
import SpaceInvaders.Objects.Drawable;
/**
 * EndGameDialog extends JPanel class and implements Drawable and KeyListener.
 * @author Jose Miguel Melo & Ricardo Loureiro
 */
public class EndGameDialog extends JPanel implements Drawable, KeyListener {


    private static final long serialVersionUID = -5557147814372408993L;
    private int points;
    private boolean visible;
    private String MESSAGE;
    private String name = "";

    /**
     * Constructor
     * @param points to be displayed to the user
     * @param message to be displayed to the user
     */
    public EndGameDialog(int points, String message) {
        this.points = points;
        visible = true;
        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
        MESSAGE = message;
    }

    /**
     * gets the visibility of the JPanel
     * @return boolean visible
     */
    public boolean isVisible() {
        return this.visible;
    }

    /**
     * Sets the visibility of the JPanel
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * draws the current JPanel into the frame
     * @param g Graphics to draw image
     */
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        int textSize = 40;
        g.setFont(new Font("lifeFont", Font.BOLD, textSize));
        g.drawString(MESSAGE + points, SpaceInvadersGame.WIDTH / 2 - 240, SpaceInvadersGame.HEIGHT / 2);

        g.setFont(new Font("lifeFont", Font.BOLD, 20));
        g.drawString("Record Name: " + name, SpaceInvadersGame.WIDTH / 2 - 240, SpaceInvadersGame.HEIGHT / 2 + 30);

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {

    }

    /**
     * Allows the user to insert his name after the game is over
     * @param e keyevent
     */
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            visible = false;
            SpaceInvadersGame.file.setUser(new User(points, name));
            SpaceInvadersGame.file.saveLeaderBoard();
            
        }

        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (name.length() > 0)
                name = name.substring(0, name.length() - 1);
        } else
            name = name + String.valueOf(e.getKeyChar());
    }
}
