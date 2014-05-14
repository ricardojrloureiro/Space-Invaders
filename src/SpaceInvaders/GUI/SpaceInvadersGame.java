package SpaceInvaders.GUI;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import SpaceInvaders.Objects.Enemy;

/**
 * Space Invaders game thread. Implements Runnable interface.
 * @author josemiguelmelo
 */
public class SpaceInvadersGame extends Thread implements Runnable{

	/** Preferred Window Width */
	public static final int WIDTH = 1020;
	/** Preferred Window Height */
	public static final int HEIGHT = 600;
	/** Option menu integer representation */
	public static final int OPTION_MENU = 1;
	/** Exit menu integer representation */
	public static final int EXIT_MENU = 2;

	/** Represents running state */
	private boolean running;

	/** Window JFrame */
	private Window window;
	/** Main Menu JPanel*/
	private MainMenu mainMenu;
	/** Option Menu JPanel */
	private OptionMenu optionMenu;

	/** Game JPanel */
	private SpaceInvaders spaceInvadersPanel;

	/** Menu background location */
	private String mainBackgroundLocation = "/Sprites/menuBackground.jpg";

	/**
	 * @return Window Frame
	 */
	public Window getWindow(){ return window;}
	/**
	 * @return Main Menu Panel
	 */
	public MainMenu getMainMenu(){ return mainMenu;}
	/**
	 * @return Option Menu Panel
	 */
	public OptionMenu getOptionMenu(){ return optionMenu;}


	private int lastTime;
	private int lastTimeEnemy;

	/**
	 * Run thread
	 */
	public void run(){
		lastTimeEnemy = (int) System.currentTimeMillis();
		lastTime = (int) System.currentTimeMillis();
		while(running){
			if((int) System.currentTimeMillis() - lastTime >=1000 ){
				lastTime = (int) System.currentTimeMillis();
				spaceInvadersPanel.addRock();
			}
			
			if((int) System.currentTimeMillis() - lastTimeEnemy >= 5000) {
				lastTimeEnemy = (int) System.currentTimeMillis();
				spaceInvadersPanel.addDestroyer(Enemy.DESTROYER);
			}
			
			if((int) System.currentTimeMillis() - lastTime >= 1000/60) {
				if(this.mainMenu.isVisible()){
					this.mainMenu.repaint();
				}else if(this.optionMenu.isVisible()){
					this.optionMenu.repaint();
				}else{
					this.spaceInvadersPanel.repaint();
				}
			}
		}
		window.dispose();
	}

	/**
	 * Stop thread
	 */
	public void stopThread(){
		this.running = false;
	}

	/**
	 * Thread constructor.
	 * Creates window, main menu panel and option menu
	 */
	public SpaceInvadersGame(){
		this.running = true;

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
		optionOptions.add("Special Atack");


		ArrayList<String> menuOptions = new ArrayList<String>();
		menuOptions.add("Play Game");
		menuOptions.add("Options");
		menuOptions.add("Exit");

		BufferedImage mainBackground = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

		try{
			mainBackground = ImageIO.read(getClass().getResourceAsStream(mainBackgroundLocation));
		}catch(IOException e){
			e.printStackTrace();
		}
		this.mainMenu = new MainMenu(menuOptions, mainBackground, this);
		this.optionMenu = new OptionMenu(optionOptions, mainBackground, this);
		this.spaceInvadersPanel = new SpaceInvaders(this);

		//mainMenu.setVisible();

		window.getContentPane().add(mainMenu);
		window.getContentPane().add(optionMenu);
		window.getContentPane().add(spaceInvadersPanel);

		spaceInvadersPanel.setVisible();

		window.setSize(Toolkit.getDefaultToolkit().getScreenSize());

		window.setResizable(false);
		window.setVisible(true);
	}




}
