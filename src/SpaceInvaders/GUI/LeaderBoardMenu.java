package SpaceInvaders.GUI;

import SpaceInvaders.FilesIO.User;
import Sprite.Position;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * LeaderBoardMenu class that extends JPanel and implements KeyListener
 * @author Jose Miguel Melo & Ricardo Loureiro
 */
public class LeaderBoardMenu extends JPanel implements KeyListener {

    /**
     * ArrayList of strings containing all Leader Board menu options
     */
    private ArrayList<String> options;
    /**
     * Index, in the array list, of selected option
     */
    private int optionSelected;
    /**
     * Leader Board menu background image
     */
    private BufferedImage backgroundImage;

    /**
     * Main menu character size
     */
    private int charSize = 48;
    /**
     * Main menu title character size
     */
    private int titleCharSize = 56;

    /**
     * RGB code of selected option color
     */
    private int selectedOptionColor = 6579455;
    /**
     * RGB code of not selected option color
     */
    private int notSelectedOptionColor = 16777215;
    /**
     * RGB code of title color
     */
    private int titleColor = 16041984;

    /**
     * represents JPanel visibility
     */
    private boolean visible;
    /**
     * Thread which created this object
     */
    private Thread aboveThread;

    protected BufferedImage image;

    public LeaderBoardMenu(ArrayList<String> options, BufferedImage backgroundImage, Thread aboveThread) {
        this.options = options;
        this.backgroundImage = backgroundImage;
        this.optionSelected = 0;
        this.setFocusable(false);
        visible = false;
        this.setVisible(visible);
        this.aboveThread = aboveThread;
        this.addKeyListener(this);
        setInvisible();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/LeaderBoard/leaderboard.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInvisible() {
        this.setFocusable(false);
        this.visible = false;
        this.setVisible(visible);
    }

    public void setVisible() {
        visible = true;
        this.setVisible(visible);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void drawText(Graphics g, String text, Position pos, int rgb, int charWidth) {
        g.setFont(new Font("mainFont", Font.BOLD, charWidth));
        g.setColor(new Color(rgb));
        g.drawString(text, pos.getX(), pos.getY());
    }


    public void paintComponent(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
        g.drawImage(image,20,120,70,170,null);
        g.setFont(new Font("optionFont", Font.BOLD, titleCharSize));
        g.setColor(new Color(titleColor));
        g.drawString("Space Invaders", this.getHeight() / 8, this.getWidth() / 8);

        for (int i = 0; i < SpaceInvadersGame.file.getUsers().size(); i++) {
            drawText(g, SpaceInvadersGame.file.getUsers().get(i).getName() + " - " + SpaceInvadersGame.file.getUsers().get(i).getPoints()
                    , new Position(this.getHeight() / 8, (i + 1) * charSize + this.getWidth() / 8), notSelectedOptionColor, charSize - 10);
        }
        drawText(g, options.get(0), new Position(this.getHeight() / 8, this.getWidth() / 2 + (charSize + 10)), selectedOptionColor, charSize);

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.setInvisible();
            ((SpaceInvadersGame) aboveThread).getMainMenu().setVisible();
        }
    }
}
