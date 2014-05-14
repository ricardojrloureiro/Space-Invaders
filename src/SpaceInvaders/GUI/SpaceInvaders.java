package SpaceInvaders.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import SpaceInvaders.Engine.Collision;
import SpaceInvaders.Objects.Boss;
import SpaceInvaders.Objects.Destroyer;
import SpaceInvaders.Objects.NormalEnemy;
import SpaceInvaders.Objects.Rock;
import SpaceInvaders.Objects.SpaceShip;
import SpaceInvaders.Objects.Suicidal;
import Sprite.Position;
import Sprite.SpriteSheet;

public class SpaceInvaders extends JPanel{

	private static final long serialVersionUID = -5625571319865323101L;
	
	public static final int SPACESHIP_MOVE_SPEED = 20;

	private SpaceShip spaceShip;
	private ArrayList<Destroyer> destroyers;
	private ArrayList<NormalEnemy> normalEnemies;
	private ArrayList<Suicidal> suicidals;
	private Boss boss;
	private ArrayList<Rock> rocks;
	
	private int level;

	private static final int SPACESHIP_WIDTH = 96/3;
	private static final int SPACESHIP_HEIGHT = 40;

	private int lastTime;
	private int current;

	private ArrayList<Position> starPosition = new ArrayList<Position>();


	/** represents JPanel visibility */
	private boolean visible;
	/** Thread which created this object */
	private Thread aboveThread;

	public SpaceInvaders(Thread aboveThread){
		this.aboveThread = aboveThread;
		this.destroyers = new ArrayList<Destroyer>();
		this.normalEnemies = new ArrayList<NormalEnemy>();
		this.suicidals = new ArrayList<Suicidal>();
		this.rocks = new ArrayList<Rock>();
		
		spaceShip=new SpaceShip(new Position(100,100),
				new SpriteSheet(SpaceShip.LOCATION, new Dimension(SPACESHIP_WIDTH, SPACESHIP_HEIGHT),
						1,3));	
		lastTime=(int) System.currentTimeMillis();
		current=(int) System.currentTimeMillis();
		//keysPressed = new ArrayList<Boolean>();
		//this.addKeyListener(this);
	}

	/** 
	 * Set the panel as visible. Also request focus and set panel as focusable
	 */
	public void setVisible(){
		visible=true;
		this.setVisible(visible);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	/**
	 * Set the panel as invisible. Set panel as not focusable.
	 */
	public void setInvisible(){
		this.setFocusable(false);
		this.visible = false;
		this.setVisible(visible);
	}
	/**
	 * @return True if panel is visible in parent component
	 */
	public boolean isVisible(){
		return this.visible;
	}

	private void drawStar(Graphics g){
		Random rand = new Random();
		g.setColor(Color.WHITE);
		if(current - lastTime >= 100){
			starPosition.clear();
			for(int i =0; i< (SpaceInvadersGame.WIDTH* SpaceInvadersGame.HEIGHT)/5000 ; i++){
				int randomPositionX = rand.nextInt(SpaceInvadersGame.WIDTH*2);
				int randomPositionY = rand.nextInt(SpaceInvadersGame.HEIGHT*2);
				starPosition.add(new Position(randomPositionX, randomPositionY));
				g.drawRoundRect(randomPositionX, randomPositionY, 3, 3, 3, 3);
			}
			lastTime=current;
		}else{
			for(int i =0; i< starPosition.size() ; i++){
				g.drawRoundRect(starPosition.get(i).getX(), starPosition.get(i).getY(), 3, 3, 3, 3);
			}
		}
	}

	/**
	 * Paints the panel with all its component.
	 * First the background image. Then the text in the ArrayList of strings with all options.
	 * @param g Graphics to draw image
	 */
	public void paintComponent(Graphics g){
		/* BACKGROUND DRAW */
		current = (int) System.currentTimeMillis();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, SpaceInvadersGame.WIDTH *2, SpaceInvadersGame.HEIGHT * 2);
		drawStar(g);
		for(int i  = 0 ; i< rocks.size(); i++){
			rocks.get(i).draw(g);
			if(current - lastTime >= 30){
				rocks.get(i).getPosition().setY(rocks.get(i).getPosition().getY() + 1);
			}
		}
		spaceShip.draw(g);
		Collision collision;
		//checking collisions between rocks and the spaceShip.
		for(int i=0;i<rocks.size();i++) {
			collision = new Collision(spaceShip,rocks.get(i),Collision.RECTANGLE_DETECTION);
			if(collision.detect()){
				((SpaceInvadersGame)aboveThread).stopThread();
			}
		}
	}


	public void addRock() {
		Random rand = new Random();
		
		rocks.add(new Rock(new Position(rand.nextInt(SpaceInvadersGame.WIDTH), 0), 
				new SpriteSheet(Rock.LOCATION, new Dimension(Rock.SPRITE_DIMENSION, Rock.SPRITE_DIMENSION), 1, 1)));
	}

}
