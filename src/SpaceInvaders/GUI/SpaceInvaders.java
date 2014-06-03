package SpaceInvaders.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import SpaceInvaders.Engine.Collision;
import SpaceInvaders.Map.BackgroundMap;
import SpaceInvaders.Objects.*;
import Sprite.Position;
import Sprite.SpriteSheet;

public class SpaceInvaders extends JPanel implements KeyListener{

	private static final long serialVersionUID = -5625571319865323101L;

	public static final int SPACESHIP_MOVE_SPEED = 2;
	
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

	private SpaceShip spaceShip;
	private ArrayList<Enemy> enemies;
	private Boss boss;
	private ArrayList<Rock> rocks;
	private ArrayList<Boolean> keysPressed;

	private int level;

	private static final int SPACESHIP_WIDTH = 96/3;
	private static final int SPACESHIP_HEIGHT = 40;

	private int lastTime;
	private int lastShotTime;
	private int current;
    private int lastCheckFire;

	private ArrayList<Position> starPosition = new ArrayList<Position>();


	/** represents JPanel visibility */
	private boolean visible;
	/** Thread which created this object */
	private Thread aboveThread;
    /** Timers */
    private ArrayList<Integer> times;

	public SpaceInvaders(Thread aboveThread){
		this.aboveThread = aboveThread;
		this.enemies = new ArrayList<Enemy>();
		this.rocks = new ArrayList<Rock>();
		spaceShip=new SpaceShip(new Position(100,100),
				new SpriteSheet(SpaceShip.LOCATION, new Dimension(SPACESHIP_WIDTH, SPACESHIP_HEIGHT),
						1,3));
        int time = (int) System.currentTimeMillis();
		lastShotTime=time;
		lastTime=time;
		current=time;
        lastCheckFire=time;
		keysPressed = new ArrayList<Boolean>();
		for(int i=0;i<4;i++){
			keysPressed.add(false);
		}
        times = new ArrayList<Integer> ();
		this.addKeyListener(this);
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
		if(current - lastTime >= 150){
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
		
		/*
		BackgroundMap bgMap = new BackgroundMap(0, 0, new Dimension(10*18, 10*18), "/Sprites/inverted_shot.png", new Dimension(18,18));
		bgMap.draw(g);
*/
		for(int i=0;i<enemies.size();i++) {
			    enemies.get(i).draw(g);
			if(current-lastTime>=20) {
				enemies.get(i).move(spaceShip);
			}
		}

		for(int i  = 0 ; i< rocks.size(); i++){
			rocks.get(i).draw(g);
			if(current - lastTime >= 50){
				rocks.get(i).move();
			}
		}
		spaceShip.draw(g);

		Collision collision = null;
		//checking collisions between rocks and the spaceShip.
		for(int i=0;i<rocks.size();i++) {
			collision = new Collision(spaceShip,rocks.get(i),Collision.RECTANGLE_DETECTION);
			if(collision.detect()){
				// meter a perder vida em vez de morrer logo
				spaceShip.setDead(true);
				((SpaceInvadersGame)aboveThread).stopThread();
			}
			if(rocks.get(i).getPosition().getY()>SpaceInvadersGame.HEIGHT) {
				rocks.remove(i);
			}
		}
        // collision beetween enemy fire shots and the space ship
		for(int i=0;i<enemies.size();i++){
            for(int x=0;x<enemies.get(i).getShots().size();x++) {
                collision = new Collision(spaceShip,enemies.get(i).getShots().get(x),Collision.RECTANGLE_DETECTION);
                if(collision.detect() && ((int) System.currentTimeMillis()-lastCheckFire > 500)) {
                    enemies.get(i).damageDone(spaceShip);
                    enemies.get(i).getShots().remove(x);
                    lastCheckFire=(int) System.currentTimeMillis();
                    if(spaceShip.getDead() == true){
                        ((SpaceInvadersGame)aboveThread).stopThread();
                    }
                }
            }
            if(!enemies.get(i).isDead()) {
                collision = new Collision(spaceShip,enemies.get(i),Collision.RECTANGLE_DETECTION);
                if(collision.detect()){
                    spaceShip.setDead(true);
                    if(spaceShip.getDead())
                        ((SpaceInvadersGame)aboveThread).stopThread();
                }
                if(enemies.get(i).getPosition().getY()>SpaceInvadersGame.HEIGHT) {
                    enemies.remove(i);
                }
            }
		}

        //checking shots & rocks
        for(int i=0;i<spaceShip.getShots().size();i++){
            for(int j=0;j<rocks.size();j++) {
                collision = new Collision(spaceShip.getShots().get(i),rocks.get(j),Collision.RECTANGLE_DETECTION);
                if(collision.detect()){
                    spaceShip.increasePoints(10);
                    rocks.remove(i); //TODO em vez de remover por disabled e remover so quando sair do ecra
                    spaceShip.getShots().get(j).setEnable(false);
						/*
						 * TODO Add image of the explosion
						 */
                }
                break;
            }
        }
        //checking shots from spaceship and enemies

       for(int i=0;i<spaceShip.getShots().size();i++){
           if(spaceShip.getShots().get(i).getEnabled()) {
               for(int j=0;j<enemies.size();j++) {
                   if(!enemies.get(j).isDead()){
                       collision = new Collision(spaceShip.getShots().get(i),enemies.get(j),Collision.RECTANGLE_DETECTION);
                       if(collision.detect()){
                           spaceShip.increasePoints(10);
                           enemies.get(j).setDead(true);
                           spaceShip.getShots().get(i).setEnable(false);
                           //    spaceShip.getShots().remove(i);
                            /*
                             * TODO Add image of the explosion
                            */
                           break; // para eliminar so um inimigo com cada tiro
                       }
                   }
               }
           }
       }
		updatePositions();
	} 

	/**
	 * Generates a random position to add a new rock to the current map.
	 * It adds on the ArrayList of rocks.
	 */
	public void addRock() {
		Random rand = new Random();

		rocks.add(new Rock(new Position(rand.nextInt(SpaceInvadersGame.WIDTH), 0), 
				new SpriteSheet(Rock.LOCATION, new Dimension(Rock.SPRITE_DIMENSION, Rock.SPRITE_DIMENSION), 1, 1)));
	}


	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			keysPressed.set(0, true);
			break;
		case KeyEvent.VK_RIGHT:
			keysPressed.set(1, true);
			break;
		case KeyEvent.VK_DOWN:
			keysPressed.set(2, true);
			break;
		case KeyEvent.VK_UP:
			keysPressed.set(3, true);
			break;
		case KeyEvent.VK_SPACE:
			if(((int) System.currentTimeMillis() - lastShotTime) > 500) {
				lastShotTime = (int) System.currentTimeMillis();
				spaceShip.addShot();
			}
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			keysPressed.set(0, false);
			break;
		case KeyEvent.VK_RIGHT:
			keysPressed.set(1, false);
			break;
		case KeyEvent.VK_DOWN:
			keysPressed.set(2, false);
			break;
		case KeyEvent.VK_UP:
			keysPressed.set(3, false);
			break;
		default:
			break;
		}
	}
	/**
	 * As the name says updates the Position according to the key events.
	 * Allows the user to use one more key at a time.
	 */
	private void updatePositions() {
		if(keysPressed.get(0) == true) { // left 
			if(spaceShip.getPosition().getX() > 1)
				spaceShip.move(LEFT);
		}
		if(keysPressed.get(1) == true) { // right
			if(spaceShip.getPosition().getX() < SpaceInvadersGame.WIDTH - 
					spaceShip.getSprite().getDimension().getWidth())
				spaceShip.move(RIGHT);
		}
		if(keysPressed.get(2) == true) { // down
			if(spaceShip.getPosition().getY() < SpaceInvadersGame.HEIGHT -
					spaceShip.getSprite().getDimension().getHeight())
				spaceShip.move(DOWN);
		}
		if(keysPressed.get(3) == true) { // up
			if(spaceShip.getPosition().getY() > 1)
				spaceShip.move(UP);	
		}
	}

	/**
	 * Adds to the enemies array list a enemy, depending of the type
	 */
	public void addEnemy(int type) {
		Random rand = new Random();
		switch (type) {
		case Enemy.DESTROYER:
			enemies.add(new Destroyer(new Position(rand.nextInt(SpaceInvadersGame.WIDTH), 0), 
					new SpriteSheet(Destroyer.LOCATION,
							new Dimension(Destroyer.SPRITE_DIMENSION, Destroyer.SPRITE_DIMENSION), 1, 1)));
			break;
		case Enemy.SUICIDAL:
			enemies.add(new Suicidal(new Position(rand.nextInt(SpaceInvadersGame.WIDTH), 0), 
					new SpriteSheet(Suicidal.LOCATION, 
							new Dimension(Suicidal.SPRITE_DIMENSION,Suicidal.SPRITE_DIMENSION), 1, 1)));
			break;
            case Enemy.FIRESHOOTER:
                enemies.add(new FireShooter(new Position(rand.nextInt(SpaceInvadersGame.WIDTH), 0),
                        new SpriteSheet(FireShooter.LOCATION,
                                new Dimension(FireShooter.SPRITE_DIMENSION,FireShooter.SPRITE_DIMENSION), 1, 1)));
            break;

		}
	}
}
