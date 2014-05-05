package SpaceInvaders.GUI;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * Space Invaders game thread. Implements Runnable interface.
 * @author josemiguelmelo
 */
public class SpaceInvadersGame extends Thread implements Runnable{

	/** Preferred Window Width */
	private static int WIDTH = 1020;
	/** Preferred Window Height */
	private static int HEIGHT = 600;
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
	
	
	/**
	 * Run thread
	 */
	public void run(){
		while(running){
			if(this.mainMenu.isVisible()){
				this.mainMenu.repaint();
			}else if(this.optionMenu.isVisible()){
				this.optionMenu.repaint();
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
		mainMenu.setVisible();

		window.getContentPane().add(mainMenu);
		window.getContentPane().add(optionMenu);


		window.setSize(Toolkit.getDefaultToolkit().getScreenSize());

		window.setResizable(false);
		window.setVisible(true);
	}

}
