package SpaceInvaders.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import SpaceInvaders.Engine.Collision;
import SpaceInvaders.Engine.Explosion;
import SpaceInvaders.Objects.Bonus;
import SpaceInvaders.Objects.Boss;
import SpaceInvaders.Objects.Destroyer;
import SpaceInvaders.Objects.Enemy;
import SpaceInvaders.Objects.FireShooter;
import SpaceInvaders.Objects.Life;
import SpaceInvaders.Objects.Protection;
import SpaceInvaders.Objects.Rock;
import SpaceInvaders.Objects.Shot;
import SpaceInvaders.Objects.SpaceShip;
import SpaceInvaders.Objects.Suicidal;
import Sprite.Position;
import Sprite.SpriteSheet;

/**
 * SpaceInvaders class that extends form JPanel
 * @author Jose Miguel Melo & Ricardo Loureiro
 */
public class SpaceInvaders extends JPanel {

	private static final long serialVersionUID = -5625571319865323101L;

	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

	public static final int lifeTextSize = 24;
	public static final int specialAttackTextSize = 18;
	public static final int bossTextSize = 60;

	private SpaceShip spaceShip;
	private ArrayList<Enemy> enemies;
	private Boss boss;
	private ArrayList<Rock> rocks;
	private ArrayList<Bonus> bonus;

	static protected ArrayList<Boolean> keysPressed;
	private ArrayList<Explosion> explosions;


	private int level;
	private boolean atBoss;
	private boolean levelEnded;

	private static final int SPACESHIP_WIDTH = 96 / 3;
	private static final int SPACESHIP_HEIGHT = 40;

	private int lastTime;
	private int lastShotTime;
	private int current;
	private int lastCheckFire;
	private int lastLaserFire;
	private int lastLaserDamage;


	private Image mapImage;
	private String mapImageLocation = "/Sprites/background.jpg";

	/**
	 * represents JPanel visibility
	 */
	private boolean visible;
	/**
	 * Thread which created this object
	 */
	private Thread aboveThread;

	private final String WIN_MESSAGE = "LEVEL UP !";
	private boolean showBossMessage = false;
	private boolean showingWinMessage = false;

	private EndGameDialog endGameDialog;
	private SpaceInvadersListener spaceInvaderListener;

	public int getLevelsEnded() {
		return levelsEnded;
	}

	private int levelsEnded;

	/**
	 * constructor
	 * @param aboveThread
	 */
	public SpaceInvaders(Thread aboveThread) {
		this.levelsEnded = 0;
		this.aboveThread = aboveThread;
		this.enemies = new ArrayList<Enemy>();
		this.rocks = new ArrayList<Rock>();
		this.bonus = new ArrayList<Bonus>();
		spaceShip = new SpaceShip(new Position(100, 100),
				new SpriteSheet(SpaceShip.LOCATION, new Dimension(SPACESHIP_WIDTH, SPACESHIP_HEIGHT),
						1, 3));
		this.explosions = new ArrayList<Explosion>();

		int time = (int) System.currentTimeMillis();
		setLastShotTime(time);
		lastTime = time;
		current = time;
		lastCheckFire = time;
		lastLaserDamage = time;
		keysPressed = new ArrayList<Boolean>();
		for (int i = 0; i < 4; i++) {
			keysPressed.add(false);
		}
		spaceInvaderListener = new SpaceInvadersListener(this);
		this.addKeyListener(spaceInvaderListener);

		try {
			mapImage = ImageIO.read(
					getClass().getResourceAsStream(mapImageLocation)
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.lastLaserFire=(int)System.currentTimeMillis();
		this.atBoss = false;
		boss = null;
		this.level = 1;
		this.levelEnded = false;

		endGameDialog = null;
	}

	/**
	 * returns the boolean showBossMessage
	 * @return
	 */
	public boolean isShowingBossMessage() {
		return this.showBossMessage;
	}

	/**
	 * sets the showBossMessage depending of the parameter
	 * @param showBossMessage
	 */
	public void setShowingBossMessage(boolean showBossMessage) {
		this.showBossMessage = showBossMessage;
	}

	/**
	 * return if is showing win message to the user or not
	 * @return
	 */
	public boolean isShowingWinMessage() {
		return this.showingWinMessage;
	}

	/**
	 * Sets the showingWinMessage depending of the parameter
	 * @param showingWinMessage
	 */
	public void setShowingWinMessage(boolean showingWinMessage) {
		this.showingWinMessage = showingWinMessage;
	}

	/**
	 * Returns true if the level is over or false if it isn't
	 * @return
	 */
	public boolean isLevelEnded() {
		return this.levelEnded;
	}

	/**
	 * returns the current spaceShip from game
	 * @return
	 */
	public SpaceShip getSpaceShip() {
		return this.spaceShip;
	}

	/**
	 * @return Last time shot was fired
	 */
	public int getLastShotTime() {
		return lastShotTime;
	}


	/**
	 * @param lastShotTime Last time shot was fired
	 */
	public void setLastShotTime(int lastShotTime) {
		this.lastShotTime = lastShotTime;
	}


	/**
	 * @return Last time laser was fired
	 */
	public int getLastLaserFire() {
		return lastLaserFire;
	}


	/**
	 * @param lastLaserFire Last time laser was fired
	 */
	public void setLastLaserFire(int lastLaserFire) {
		this.lastLaserFire = lastLaserFire;
	}


	/**
	 * Set the panel as visible. Also request focus and set panel as focusable
	 */
	public void setVisible() {
		visible = true;
		this.setVisible(true);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	/**
	 * Set the panel as invisible. Set panel as not focusable.
	 */
	public void setInvisible() {
		this.setFocusable(false);
		this.visible = false;
		this.setVisible(false);
	}

	/**
	 * @return True if panel is visible in parent component
	 */
	public boolean isVisible() {
		return this.visible;
	}

	/**
	 * returns true if it is during a boss fight or false if it isn't
	 * @return
	 */
	public boolean isAtBoss() {
		return this.atBoss;
	}

	/**
	 * removes all the disabled objects
	 */
	public void removeDeadObjects() {
		//checks if protection is still available
		spaceShip.checkProtection();


		Iterator<Bonus> iterBonus = bonus.iterator();
		for (int i = 0; i < bonus.size(); i++) {
			bonus.get(i).checkAvailable();
		}
		while (iterBonus.hasNext()) {
			if (!iterBonus.next().isAvailable())
				iterBonus.remove();
			if (iterBonus.hasNext())
				iterBonus.next();
		}


		Iterator<Rock> iterRock = rocks.iterator();
		/* remove rocks */
		while (iterRock.hasNext()) {
			if (!iterRock.next().isEnabled())
				iterRock.remove();
			if (iterRock.hasNext())
				iterRock.next();
		}

		/* remove enemy shots */
		for (int i = 0; i < enemies.size(); i++) {
			Iterator<Shot> iterEnemyShot = enemies.get(i).getShots().iterator();
			while (iterEnemyShot.hasNext()) {
				if (!iterEnemyShot.next().getEnabled())
					iterEnemyShot.remove();
				if (iterEnemyShot.hasNext())
					iterEnemyShot.next();
			}
		}

		// boss
		if (boss != null) {
			Iterator<Shot> iterEnemyShot = boss.getShots().iterator();
			while (iterEnemyShot.hasNext()) {
				if (!iterEnemyShot.next().getEnabled())
					iterEnemyShot.remove();
				if (iterEnemyShot.hasNext())
					iterEnemyShot.next();
			}
		}
		/* remove player shots */
		Iterator<Shot> iterPlayerShot = spaceShip.getShots().iterator();
		while (iterPlayerShot.hasNext()) {
			if (!iterPlayerShot.next().getEnabled())
				iterPlayerShot.remove();
			if (iterPlayerShot.hasNext())
				iterPlayerShot.next();
		}

		/* remove enemies */
		Iterator<Enemy> iterEnemy = enemies.iterator();
		while (iterEnemy.hasNext()) {
			Enemy en = iterEnemy.next();
			if (en.isDead() && en.getShots().size() == 0)
				iterEnemy.remove();
			if (iterEnemy.hasNext())
				iterEnemy.next();
		}

		/* remove explosions */
		Iterator<Explosion> iterExplosions = explosions.iterator();
		while (iterEnemy.hasNext()) {
			Explosion ex = iterExplosions.next();
			if (!ex.isEnabled())
				iterExplosions.remove();
			if (iterExplosions.hasNext())
				iterExplosions.next();
		}


	}

	/**
	 * Moves all the current objects in the board, allowing to be displayed in different positions allong the time
	 */
	public void moveObjects() {
		if (levelsEnded == 2)
			return;
		current = (int) System.currentTimeMillis();



		/** MOVE COMPONENTS **/
		for (int i = 0; i < enemies.size(); i++) {
			if (current - lastTime >= 20) {
				enemies.get(i).move(spaceShip);
			}
		}

		for (int i = 0; i < rocks.size(); i++) {
			if (current - lastTime >= 50) {
				rocks.get(i).move();
			}
		}

		for (int i = 0; i < enemies.size(); i++) {
			for (int j = 0; j < enemies.get(i).getShots().size(); j++) {
				enemies.get(i).getShots().get(j).move(spaceShip);
			}
		}
		for (int i = 0; i < spaceShip.getShots().size(); i++) {
			if (current - lastTime >= 20) {
				spaceShip.getShots().get(i).move(spaceShip);
			}
		}
		/** BOSS **/

		try{
			if (boss != null) {
				boss.move(spaceShip);
				if (boss.getShots() != null)
					for (int j = 0; j < boss.getShots().size(); j++) {
						boss.getShots().get(j).move(spaceShip);
					}
			}

		}catch(Exception e){}

		/** **/

		/*** COLLISION DETECTION **/
		Collision collision = null;
		for (int i = 0; i < rocks.size(); i++) {
			if (rocks.get(i).isEnabled()) {
				collision = new Collision(spaceShip, rocks.get(i), Collision.RECTANGLE_DETECTION);
				if (collision.detect()) {
					// meter a perder vida em vez de morrer logo
					if (!spaceShip.isProtected()) {
						spaceShip.damageShip(14);
					}
					rocks.get(i).setEnabled(false);

					Explosion explosion = new Explosion(new Position(spaceShip.getPosition().getX(), spaceShip.getPosition().getY()),
							new SpriteSheet(Explosion.LOCATION,
									new Dimension(Explosion.SPRITE_WIDTH / Explosion.SPRITE_COLS, Explosion.SPRITE_HEIGTH),
									1, Explosion.SPRITE_COLS));

					explosions.add(explosion);
				}
			}
			if (rocks.get(i).getPosition().getY() > SpaceInvadersGame.HEIGHT) {
				rocks.remove(i);
			}
		}

		// collision beetween enemy fire shots and the space ship
		for (int i = 0; i < enemies.size(); i++) {
			for (int x = 0; x < enemies.get(i).getShots().size(); x++) {
				collision = new Collision(spaceShip, enemies.get(i).getShots().get(x), Collision.RECTANGLE_DETECTION);
				if (collision.detect() && ((int) System.currentTimeMillis() - lastCheckFire > 500)) {
					if (!spaceShip.isProtected()) {
						enemies.get(i).damageDone(spaceShip);
					}
					enemies.get(i).getShots().remove(x);
					lastCheckFire = (int) System.currentTimeMillis();


					Explosion explosion = new Explosion(new Position(spaceShip.getPosition().getX(), spaceShip.getPosition().getY()),
							new SpriteSheet(Explosion.LOCATION,
									new Dimension(Explosion.SPRITE_WIDTH / Explosion.SPRITE_COLS, Explosion.SPRITE_HEIGTH),
									1, Explosion.SPRITE_COLS));

					explosions.add(explosion);
				}
			}
			if (!enemies.get(i).isDead()) {
				collision = new Collision(spaceShip, enemies.get(i), Collision.RECTANGLE_DETECTION);
				if (collision.detect()) {
					if (!spaceShip.isProtected()) {
						spaceShip.damageShip(20);
					}
					enemies.get(i).setEnabled(false);
					enemies.get(i).setDead(true);

					Explosion explosion = new Explosion(new Position(spaceShip.getPosition().getX(), spaceShip.getPosition().getY()),
							new SpriteSheet(Explosion.LOCATION,
									new Dimension(Explosion.SPRITE_WIDTH / Explosion.SPRITE_COLS, Explosion.SPRITE_HEIGTH),
									1, Explosion.SPRITE_COLS));

					explosions.add(explosion);

				}
				if (enemies.get(i).getPosition().getY() > SpaceInvadersGame.HEIGHT) {
					enemies.remove(i);
				}
			}
		}
		//checking shots & rocks
		for (int i = 0; i < spaceShip.getShots().size(); i++) {
			if (spaceShip.getShots().get(i).getEnabled() == true)
				for (int j = 0; j < rocks.size(); j++) {
					if (rocks.get(j).isEnabled() == true) {
						collision = new Collision(spaceShip.getShots().get(i), rocks.get(j), Collision.RECTANGLE_DETECTION);
						if (collision.detect()) {
							spaceShip.increasePoints(10);
							rocks.get(j).setEnabled(false);
							if (spaceShip.getShots().get(i).getType() != Shot.TYPE_LASER)
								spaceShip.getShots().get(i).setEnable(false);

							Explosion explosion = new Explosion(new Position(rocks.get(j).getPosition().getX(), rocks.get(j).getPosition().getY()),
									new SpriteSheet(Explosion.LOCATION,
											new Dimension(Explosion.SPRITE_WIDTH / Explosion.SPRITE_COLS, Explosion.SPRITE_HEIGTH),
											1, Explosion.SPRITE_COLS));

							explosions.add(explosion);
						}
					}
				}
		}

		//checking shots from spaceship and enemies
		for (int i = 0; i < spaceShip.getShots().size(); i++) {
			if (spaceShip.getShots().get(i).getEnabled()) {
				for (int j = 0; j < enemies.size(); j++) {
					if (!enemies.get(j).isDead()) {
						collision = new Collision(spaceShip.getShots().get(i), enemies.get(j), Collision.RECTANGLE_DETECTION);
						if (collision.detect()) {
							spaceShip.increasePoints(10);
							enemies.get(j).setDead(true);
							if (spaceShip.getShots().get(i).getType() != Shot.TYPE_LASER)
								spaceShip.getShots().get(i).setEnable(false);


							Explosion explosion = new Explosion(new Position(enemies.get(j).getPosition().getX(), enemies.get(j).getPosition().getY()),
									new SpriteSheet(Explosion.LOCATION,
											new Dimension(Explosion.SPRITE_WIDTH / Explosion.SPRITE_COLS, Explosion.SPRITE_HEIGTH),
											1, Explosion.SPRITE_COLS));

							explosions.add(explosion);

							break; // para eliminar so um inimigo com cada tiro
						}
					}
				}
			}
		}


		for (int i = 0; i < bonus.size(); i++) {
			if (bonus.get(i).isAvailable()) {
				collision = new Collision(spaceShip, bonus.get(i), Collision.RECTANGLE_DETECTION);
				if (collision.detect()) {
					bonus.get(i).performAction(spaceShip);
				}
			}
		}


		/* BOOOOSSSSSSS ***/
		if (boss != null) {

			if (boss.isDead() && !levelEnded) {
				this.showingWinMessage = true;
				this.levelEnded = true;
				this.levelsEnded++;
			}


			// Check collision with boss
			for (int i = 0; i < spaceShip.getShots().size(); i++) {
				if (spaceShip.getShots().get(i).getEnabled() == true)
					if (!boss.isDead()) {
						collision = new Collision(spaceShip.getShots().get(i), boss, Collision.RECTANGLE_DETECTION);
						if (collision.detect()) {
							spaceShip.increasePoints(20);
							if (spaceShip.getShots().get(i).getType() == Shot.TYPE_LASER &&
									(int) System.currentTimeMillis() - lastLaserDamage >= 100) {
								lastLaserDamage = (int) System.currentTimeMillis();
								boss.damageTaken(60);
							}


							if (spaceShip.getShots().get(i).getType() == Shot.TYPE_NORMAL)
								boss.damageTaken(20);

							if (spaceShip.getShots().get(i).getType() != Shot.TYPE_LASER)
								spaceShip.getShots().get(i).setEnable(false);


							Explosion explosion = new Explosion(new Position(spaceShip.getShots().get(i).getPosition().getX(), spaceShip.getShots().get(i).getPosition().getY()),
									new SpriteSheet(Explosion.LOCATION,
											new Dimension(Explosion.SPRITE_WIDTH / Explosion.SPRITE_COLS, Explosion.SPRITE_HEIGTH),
											1, Explosion.SPRITE_COLS));

							explosions.add(explosion);
						}
					}
			}


			if (!spaceShip.getDead()) {
				collision = new Collision(spaceShip, boss, Collision.RECTANGLE_DETECTION);
				if (collision.detect()) {
					if (!spaceShip.isProtected()) {
						spaceShip.setLife(0);
						spaceShip.setDead(true);
					}
					boss.damageTaken(30);
					Explosion explosion = new Explosion(new Position(spaceShip.getPosition().getX(), spaceShip.getPosition().getY()),
							new SpriteSheet(Explosion.LOCATION,
									new Dimension(Explosion.SPRITE_WIDTH / Explosion.SPRITE_COLS, Explosion.SPRITE_HEIGTH),
									1, Explosion.SPRITE_COLS));

					explosions.add(explosion);
				}
			}
			if (boss != null)
				if (boss.getShots() != null)
					for (int x = 0; x < boss.getShots().size(); x++) {
						collision = new Collision(spaceShip, boss.getShots().get(x), Collision.RECTANGLE_DETECTION);
						if (collision.detect() && ((int) System.currentTimeMillis() - lastCheckFire > 500)) {
							if (!spaceShip.isProtected()) {
								boss.damageDone(spaceShip);
							}
							boss.getShots().remove(x);
							lastCheckFire = (int) System.currentTimeMillis();


							Explosion explosion = new Explosion(new Position(spaceShip.getPosition().getX(), spaceShip.getPosition().getY()),
									new SpriteSheet(Explosion.LOCATION,
											new Dimension(Explosion.SPRITE_WIDTH / Explosion.SPRITE_COLS, Explosion.SPRITE_HEIGTH),
											1, Explosion.SPRITE_COLS));

							explosions.add(explosion);
						}
					}


			for (int i = 0; i < spaceShip.getShots().size(); i++) {
				for (int x = 0; x < boss.getShots().size(); x++) {
					collision = new Collision(spaceShip.getShots().get(i), boss.getShots().get(x), Collision.RECTANGLE_DETECTION);
					if (collision.detect() && ((int) System.currentTimeMillis() - lastCheckFire > 500)) {
						boss.getShots().get(x).setEnable(false);

						if (spaceShip.getShots().get(i).getType() != Shot.TYPE_LASER)
							spaceShip.getShots().get(i).setEnable(false);

						Explosion explosion = new Explosion(new Position(spaceShip.getShots().get(i).getPosition().getX(), spaceShip.getShots().get(i).getPosition().getY()),
								new SpriteSheet(Explosion.LOCATION,
										new Dimension(Explosion.SPRITE_WIDTH / Explosion.SPRITE_COLS, Explosion.SPRITE_HEIGTH),
										1, Explosion.SPRITE_COLS));

						explosions.add(explosion);
					}
				}
			}
		}
		updatePositions();
		removeDeadObjects();




		if (spaceShip.getDead()) {
			endGameDialog = new EndGameDialog(spaceShip.getPoints(), "You lost, points: ");
			this.removeKeyListener(spaceInvaderListener);
			this.addKeyListener(endGameDialog);
		} else if (levelsEnded == 2) { // mudar esta condição
			endGameDialog = new EndGameDialog(spaceShip.getPoints(), "You WON, points: ");
			this.removeKeyListener(spaceInvaderListener);
			this.addKeyListener(endGameDialog);
			spaceShip.setVictory(true);
		}
	}

	/**
	 * Paints the panel with all its component.
	 * First the background image. Then the text in the ArrayList of strings with all options.
	 *
	 * @param g Graphics to draw image
	 */
	public void paintComponent(Graphics g) {
		/* BACKGROUND DRAW */
		current = (int) System.currentTimeMillis();

		g.drawImage(mapImage, 0, 0, SpaceInvadersGame.WIDTH, SpaceInvadersGame.HEIGHT, null);

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}

		for (int i = 0; i < rocks.size(); i++) {
			if (rocks.get(i).isEnabled() == true) {
				rocks.get(i).draw(g);
			}
		}

		for (int i = 0; i < explosions.size(); i++) {
			if (explosions.get(i).isEnabled())
				explosions.get(i).draw(g);
		}

		for (int i = 0; i < bonus.size(); i++) {
			if (bonus.get(i).isAvailable())
				bonus.get(i).draw(g);
		}

		if (isAtBoss()) {
			if (showBossMessage) {
				g.setColor(Color.RED);
				g.setFont(new Font("lifeFont", Font.BOLD, bossTextSize));
				String BOSS_MESSAGE = "BOSS FIGHT !";
				g.drawString(BOSS_MESSAGE, SpaceInvadersGame.WIDTH / 2 - 240, SpaceInvadersGame.HEIGHT / 2);
			}
			if (showingWinMessage) {
				g.setColor(Color.RED);
				g.setFont(new Font("lifeFont", Font.BOLD, bossTextSize));
				g.drawString(WIN_MESSAGE, SpaceInvadersGame.WIDTH / 2 - 240, SpaceInvadersGame.HEIGHT / 2);
			}
			if (boss != null) {
				boss.draw(g);
				g.setColor(Color.RED);
				g.setFont(new Font("lifeFont", Font.BOLD, lifeTextSize));
				g.drawString("Boss Life: " + boss.getLife(), getWidth() - 200, 120);
			}
		}

		g.setColor(Color.WHITE);
		g.setFont(new Font("lifeFont", Font.BOLD, lifeTextSize));
		g.drawString("Life: " + spaceShip.getLife(), getWidth() - 200, 40);
		g.setColor(Color.WHITE);
		g.setFont(new Font("pointsFont", Font.BOLD, lifeTextSize));
		g.drawString("Points: " + spaceShip.getPoints(), getWidth() - 200, 80);

		g.setColor(Color.ORANGE);
		g.setFont(new Font("specialAttackFont", Font.BOLD, specialAttackTextSize));
		g.drawString("Special Attack press " + KeyEvent.getKeyText(SpaceInvadersListener.LASER), 10, SpaceInvadersGame.HEIGHT - 30);

		spaceShip.draw(g);

		if (endGameDialog != null) {
			endGameDialog.draw(g);
			if (!endGameDialog.isVisible()) {
				setInvisible();
				((SpaceInvadersGame) aboveThread).getMainMenu().setVisible();
			}
		}
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

	/**
	 * As the name says updates the Position according to the key events.
	 * Allows the user to use one more key at a time.
	 */
	private void updatePositions() {
		if (keysPressed.get(0) == true) { // left
			if (spaceShip.getPosition().getX() > 1)
				spaceShip.move(LEFT);
		}
		if (keysPressed.get(1) == true) { // right
			if (spaceShip.getPosition().getX() < SpaceInvadersGame.WIDTH -
					spaceShip.getSprite().getDimension().getWidth())
				spaceShip.move(RIGHT);
		}
		if (keysPressed.get(2) == true) { // down
			if (spaceShip.getPosition().getY() < SpaceInvadersGame.HEIGHT -
					spaceShip.getSprite().getDimension().getHeight())
				spaceShip.move(DOWN);
		}
		if (keysPressed.get(3) == true) { // up
			if (spaceShip.getPosition().getY() > 1)
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
			Destroyer dest = new Destroyer(new Position(rand.nextInt(SpaceInvadersGame.WIDTH), 0),
					new SpriteSheet(Destroyer.LOCATION,
							new Dimension(Destroyer.SPRITE_DIMENSION, Destroyer.SPRITE_DIMENSION), 1, 1));
			if (level > 1)
				dest.setVelocity(3, 3);
			enemies.add(dest);
			break;
		case Enemy.SUICIDAL:
			Suicidal suic = new Suicidal(new Position(rand.nextInt(SpaceInvadersGame.WIDTH), 0),
					new SpriteSheet(Suicidal.LOCATION,
							new Dimension(Suicidal.SPRITE_DIMENSION, Suicidal.SPRITE_DIMENSION), 1, 1));
			if (level > 1)
				suic.setVelocity(3, 3);
			enemies.add(suic);
			break;
		case Enemy.FIRESHOOTER:
			FireShooter fs = new FireShooter(new Position(rand.nextInt(SpaceInvadersGame.WIDTH), 0),
					new SpriteSheet(FireShooter.LOCATION,
							new Dimension(FireShooter.SPRITE_DIMENSION, FireShooter.SPRITE_DIMENSION), 1, 1));
			if (level > 1)
				fs.setVelocity(2, 2);
			enemies.add(fs);
			break;

		}
	}

	/**
	 * Adds a bonus to the game
	 */
	public void addBonus() {
		Random rand = new Random();

		if (rand.nextInt(100) > 70) {
			Life lifeBonus = new Life(new Position(rand.nextInt(SpaceInvadersGame.WIDTH - 30), rand.nextInt(SpaceInvadersGame.HEIGHT - 30))
			, new SpriteSheet(Bonus.LIFE_LOCATION, new Dimension(24, 24), 1, 1));
			bonus.add(lifeBonus);
		} else {
			Protection protectionBonus = new Protection(new Position(rand.nextInt(SpaceInvadersGame.WIDTH - 30), rand.nextInt(SpaceInvadersGame.HEIGHT - 30))
			, new SpriteSheet(Bonus.PROTECTION_LOCATION, new Dimension(24, 24), 1, 1));
			bonus.add(protectionBonus);
		}
	}

	/**
	 * starts a boss fight
	 */
	public void startBoss() {
		atBoss = true;
		if (level == 1) {
			boss = new Boss(new Position(10, 10), new SpriteSheet(Boss.BOSS1_LOCATION,
					new Dimension(224, 128), 1, 1));

			boss.setLife(1000);
		} else {
			boss = new Boss(new Position(10, 10), new SpriteSheet(Boss.BOSS2_LOCATION,
					new Dimension(194, 103), 1, 1));
			boss.setLife(2000);
		}
		boss.setPosition(new Position(SpaceInvadersGame.WIDTH / 3, 10));
		showBossMessage = true;

	}

	/**
	 * returns the current level of the game
	 * @return level
	 */
	public int getLevel() {
		return this.level;
	}

	/**
	 * sets the level to a specific point
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * after the boss is dead the game gets a level up that increases the difficulty
	 */
	public void levelUp() {
		this.level++;
		this.enemies.clear();
		this.rocks.clear();
		this.atBoss = false;
		boss = null;
		this.levelEnded = false;
		int time = (int) System.currentTimeMillis();
		setLastShotTime(time);
		lastTime = time;
		current = time;
		lastCheckFire = time;
		this.explosions.clear();
		try {
			mapImage = ImageIO.read(
					getClass().getResourceAsStream(mapImageLocation)
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		spaceShip.setLife(SpaceShip.INITIAL_LIFE);
		spaceShip.getShots().clear();
	}

	/**
	 * resets all the things in the game
	 */
	public void reset() {
		this.levelsEnded=0;
		this.enemies.clear();
		this.rocks.clear();
		this.bonus.clear();
		this.explosions.clear();
		spaceShip.reset();

		int time = (int) System.currentTimeMillis();
		setLastShotTime(time);
		lastTime = time;
		current = time;
		lastCheckFire = time;
		lastLaserDamage = time;

		keysPressed.clear();
		for (int i = 0; i < 4; i++) {
			keysPressed.add(false);
		}

		spaceInvaderListener = new SpaceInvadersListener(this);
		this.removeKeyListener(spaceInvaderListener);
		this.removeKeyListener(endGameDialog);

		this.addKeyListener(spaceInvaderListener);

		try {
			mapImage = ImageIO.read(
					getClass().getResourceAsStream(mapImageLocation)
					);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.atBoss = false;
		boss = null;
		this.level = 1;
		this.levelEnded = false;
		endGameDialog = null;
	}
}
