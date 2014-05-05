package SpaceInvaders.GUI;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class SpaceInvadersGame extends Thread implements Runnable{

	private static int WIDTH = 1020;
	private static int HEIGHT = 600;

	private boolean running;

	private Window window;
	private MainMenu mainMenu;
	private OptionMenu optionMenu;

	private String mainBackgroundLocation = "/Sprites/menuBackground.jpg";

	public static final int OPTION_MENU = 1;
	public static final int EXIT_MENU = 2;

	
	public Window getWindow(){ return window;}
	public MainMenu getMainMenu(){ return mainMenu;}
	public OptionMenu getOptionMenu(){ return optionMenu;}
	
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

	public void stopThread(){
		this.running = false;
	}

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
