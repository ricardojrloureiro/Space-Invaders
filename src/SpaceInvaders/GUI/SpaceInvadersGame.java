package SpaceInvaders.GUI;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import SpaceInvaders.FilesIO.Files;
import SpaceInvaders.Objects.Enemy;

/**
 * Space Invaders game thread. Implements Runnable interface.
 *
 * @author Jose Miguel Melo & Ricardo Loureiro
 */
public class SpaceInvadersGame extends Thread implements Runnable {

    /**
     * Preferred Window Width
     */
    public static final int WIDTH = 1000;
    /**
     * Preferred Window Height
     */
    public static final int HEIGHT = 750;
    /**
     * Option menu integer representation
     */
    public static final int OPTION_MENU = 1;
    /**
     * Leaderboard menu integer representation
     */
    public static final int LEADERBOARD_MENU = 2;
    /**
     * Exit menu integer representation
     */
    public static final int EXIT_MENU = 3;
    /**
     * Represents running state
     */
    private boolean running;
    /**
     * Window JFrame
     */
    private Window window;
    /**
     * Main Menu JPanel
     */
    private MainMenu mainMenu;
    /**
     * Option Menu JPanel
     */
    private OptionMenu optionMenu;

    /**
     * LeaderBoardMenu Menu JPanel
     */
    private LeaderBoardMenu leaderBoardMenu;
    /**
     * Game JPanel
     */
    private SpaceInvaders spaceInvadersPanel;

    /**
     * @return Main Menu Panel
     */
    public MainMenu getMainMenu() {
        return mainMenu;
    }

    /**
     * @return Option Menu Panel
     */
    public OptionMenu getOptionMenu() {
        return optionMenu;
    }

    public LeaderBoardMenu getLeaderBoardMenu() {
        return leaderBoardMenu;
    }

    public SpaceInvaders getSpaceInvadersPanel() {
        return spaceInvadersPanel;
    }

    static protected boolean finish;
    static protected Files file;

    /**
     * Run thread
     */
    public void run() {
        int lastTimeEnemy = (int) System.currentTimeMillis();
        int lastTimeRock = (int) System.currentTimeMillis();
        int lastTime = (int) System.currentTimeMillis();
        int initialTime = (int) System.currentTimeMillis();

        while (running) {
            int timeToBoss;
            if (spaceInvadersPanel.getLevel() == 1)
                timeToBoss = 30000;
            else
                timeToBoss = 80000;

            if ((int) System.currentTimeMillis() - initialTime >= timeToBoss) {
                if (!spaceInvadersPanel.isAtBoss()) {
                    spaceInvadersPanel.startBoss();
                    initialTime = (int) System.currentTimeMillis();
                }
            }

            if (spaceInvadersPanel.isAtBoss() && !spaceInvadersPanel.isShowingBossMessage() && !spaceInvadersPanel.isShowingWinMessage()) {
                initialTime = (int) System.currentTimeMillis();
            }
            if (spaceInvadersPanel.isShowingBossMessage())
                if ((int) System.currentTimeMillis() - initialTime >= 1500)
                    spaceInvadersPanel.setShowingBossMessage(false);

            if (spaceInvadersPanel.isShowingWinMessage()) {
                if ((int) System.currentTimeMillis() - initialTime >= 2000) {
                    spaceInvadersPanel.setShowingWinMessage(false);
                    spaceInvadersPanel.levelUp();

                    lastTimeEnemy = (int) System.currentTimeMillis();
                    lastTimeRock = (int) System.currentTimeMillis();
                    lastTime = (int) System.currentTimeMillis();
                    initialTime = (int) System.currentTimeMillis();
                }
            } else {
                // Rocks and Enemies
                if (spaceInvadersPanel.isVisible() && !spaceInvadersPanel.getSpaceShip().getDead()) {
                    if (!spaceInvadersPanel.getSpaceShip().getDead()) {
                        if ((int) System.currentTimeMillis() - lastTimeRock >= 1000) {
                            lastTimeRock = (int) System.currentTimeMillis();
                            spaceInvadersPanel.addRock();
                            spaceInvadersPanel.addBonus();

                        }

                        if ((int) System.currentTimeMillis() - lastTimeEnemy >= 1000) {
                            Random rand = new Random();
                            lastTimeEnemy = (int) System.currentTimeMillis();
                            if (rand.nextInt(100) <= 60)
                                spaceInvadersPanel.addEnemy(Enemy.DESTROYER);
                            if (rand.nextInt(100) > 60)
                                spaceInvadersPanel.addEnemy(Enemy.SUICIDAL);
                            if (rand.nextInt(100) < 30)
                                spaceInvadersPanel.addEnemy(Enemy.FIRESHOOTER);
                        }
                    }
                }
            }
            // Paint
            if (((int) System.currentTimeMillis() - lastTime) >= 1000 / 60) {
                if (this.mainMenu.isVisible()) {
                    this.mainMenu.repaint();
                } else {
                    if (this.optionMenu.isVisible()) {
                        this.optionMenu.repaint();
                    } else {
                        this.spaceInvadersPanel.repaint();
                        if (!spaceInvadersPanel.isLevelEnded()) {
                            if (!spaceInvadersPanel.getSpaceShip().getDead()) {
                                spaceInvadersPanel.moveObjects();
                            }
                        }
                    }
                }
                lastTime = (int) System.currentTimeMillis();
            }
        }
        window.dispose();
    }

    /**
     * Stop thread
     */
    public void stopThread() {
        this.running = false;
    }

    /**
     * Thread constructor.
     * Creates window, main menu panel and option menu
     */
    public SpaceInvadersGame() throws FileNotFoundException {
        this.running = true;
        finish = false;

        window = new Window("Space Invaders");

        window.getContentPane().setLayout(new CardLayout());

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(WIDTH, HEIGHT));


        ArrayList<String> optionOptions = new ArrayList<String>();
        optionOptions.add("Move Up");
        optionOptions.add("Move Down");
        optionOptions.add("Move Right");
        optionOptions.add("Move Left");
        optionOptions.add("Fire");
        optionOptions.add("Special Attack");


        ArrayList<String> menuOptions = new ArrayList<String>();
        menuOptions.add("Play Game");
        menuOptions.add("Options");
        menuOptions.add("LeaderBoard");
        menuOptions.add("Exit");

        ArrayList<String> leaderBoardOptions = new ArrayList<String>();
        leaderBoardOptions.add("press ESC to exit");

        BufferedImage mainBackground = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

        try {
            /*
      Menu background location
     */
            String mainBackgroundLocation = "/Sprites/menuBackground.jpg";
            mainBackground = ImageIO.read(getClass().getResourceAsStream(mainBackgroundLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mainMenu = new MainMenu(menuOptions, mainBackground, this);
        this.optionMenu = new OptionMenu(optionOptions, mainBackground, this);
        this.leaderBoardMenu = new LeaderBoardMenu(leaderBoardOptions, mainBackground, this);
        this.spaceInvadersPanel = new SpaceInvaders(this);

        mainMenu.setVisible();

        window.getContentPane().add(mainMenu);
        window.getContentPane().add(optionMenu);
        window.getContentPane().add(leaderBoardMenu);
        window.getContentPane().add(spaceInvadersPanel);

        window.setSize(WIDTH, HEIGHT);
        window.setResizable(false);
        window.setVisible(true);
        file = new Files();
        file.loadLeaderBoard();
    }
}