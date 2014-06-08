package SpaceInvaders.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import Sprite.Position;

/**
 * OptionMenu extends JPanel. Also implements KeyListener.
 * Used to display the option menu to screen.
 * @author Jose Miguel Melo & Ricardo Loureiro
 */
public class OptionMenu extends JPanel implements KeyListener{
	private static final long serialVersionUID = -5021379969150900668L;

	/** ArrayList of strings containing all menu options */
	private ArrayList<String> options;
	/** Index, in the array list, of selected option */
	private int optionSelected;
	/** Main menu background image*/
	private BufferedImage backgroundImage;

	/** Main menu character size */
	private int charSize = 32;
	/** Main menu title character size */
	private int titleCharSize = 56;
	/** Exit message character size */
	private int exitSize = 20;

	/** RGB code of selected option color */
	private int selectedOptionColor = 6579455;
	/** RGB code of not selected option color */
	private int notSelectedOptionColor = 16777215;
	/** RGB code of title color */
	private int titleColor = 16041984;

	/** represents JPanel visibility */
	private boolean visible;
	/** Thread which created this object */
	private Thread aboveThread;

	private String selectedKey;
	private boolean changeKey = false;
	private int lastKey;


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

	/**
	 * @param options ArrayList of strings with all menu options.
	 * @param backgroundImage Menu background image
	 * @param aboveThread Thread which created this object
	 */
	public OptionMenu(ArrayList<String> options, BufferedImage backgroundImage, Thread aboveThread){
		this.options = options;
		this.backgroundImage = backgroundImage;
		this.optionSelected = 0;
		this.setFocusable(false);
		visible = false;
		this.setVisible(visible);
		this.aboveThread=aboveThread;
		this.addKeyListener(this);
		setInvisible();
	}

	/**
	 * @param g Graphics to draw text
	 * @param text Text to be rendered
	 * @param pos Position in screen to render text
	 * @param rgb Integer with rgb color code. Set text color.
	 */
	public void drawText(Graphics g, String text, Position pos, int rgb, int charSize){
		g.setFont(new Font("mainFont", Font.BOLD, charSize));
		g.setColor(new Color(rgb));
		g.drawString(text, pos.getX(), pos.getY());
	}

	/**
	 * Paints the panel with all its component.
	 * First the background image. Then the text in the ArrayList of strings with all options.
	 * @param g Graphics to draw image
	 */
	public void paintComponent(Graphics g){
		g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);

		g.setFont(new Font("optionFont", Font.BOLD, titleCharSize));
		g.setColor(new Color(titleColor));
		g.drawString("Game Options", this.getHeight()/8, this.getWidth()/8 );

		for(int i = 0 ; i < options.size(); i++){
			if(options.get(i).equals("Move Up"))
				selectedKey = KeyEvent.getKeyText(SpaceInvadersListener.UP);
			if(options.get(i).equals("Move Down"))
				selectedKey = KeyEvent.getKeyText(SpaceInvadersListener.DOWN);
			if(options.get(i).equals("Move Right"))
				selectedKey = KeyEvent.getKeyText(SpaceInvadersListener.RIGHT);
			if(options.get(i).equals("Move Left"))
				selectedKey = KeyEvent.getKeyText(SpaceInvadersListener.LEFT);
			if(options.get(i).equals("Fire"))
				selectedKey = KeyEvent.getKeyText(SpaceInvadersListener.SHOT);
			if(options.get(i).equals("Special Attack"))
				selectedKey = KeyEvent.getKeyText(SpaceInvadersListener.LASER);

			if(optionSelected==i){
				drawText(g, "> "+options.get(i), new Position(this.getHeight()/8, this.getWidth()/8 + (i+1)*(charSize+15)), selectedOptionColor, charSize);
				drawText(g, selectedKey, new Position(this.getHeight()/8 + 300, this.getWidth()/8 + (i+1)*(charSize+15)), selectedOptionColor, charSize);
			}else{
				drawText(g, options.get(i), new Position(this.getHeight()/8, this.getWidth()/8 + (i+1)*(charSize+15)), notSelectedOptionColor, charSize);
				drawText(g, selectedKey, new Position(this.getHeight()/8 + 300, this.getWidth()/8 + (i+1)*(charSize+15)), notSelectedOptionColor, charSize);
			}
		}
		
		drawText(g, "To exit press ESC...", new Position(this.getHeight()/8, SpaceInvadersGame.HEIGHT - 40), notSelectedOptionColor, exitSize);
		
	}

	/**
	 * @return Integer with option selected by the user.
	 */
	public int getCurrentOption(){
		return this.optionSelected;
	}
	/**
	 * @param optionSelected Integer with option index in ArrayList of strings with all options.
	 */
	public void setCurrentOption(int optionSelected) {
		this.optionSelected=optionSelected;
	}



	public void keyTyped(KeyEvent e) {
	}


	public void keyPressed(KeyEvent e) {
	}

    /**
     * Allows the user to change his key bindings in a better way for him
     * @param e keyreleased event
     */

	public void keyReleased(KeyEvent e) {
		if(!changeKey){
			if(e.getKeyCode() == KeyEvent.VK_DOWN){
				optionSelected = (optionSelected+1) % options.size();
			}
			else if(e.getKeyCode() == KeyEvent.VK_UP){
				if(optionSelected==0)
					optionSelected=options.size()-1;
				else
					optionSelected = (optionSelected-1) % options.size();
			}

			else if(e.getKeyCode()== KeyEvent.VK_ESCAPE){
				this.setInvisible();
				((SpaceInvadersGame)aboveThread).getMainMenu().setVisible();
			}

			// start changing key
			else if(e.getKeyCode() == KeyEvent.VK_ENTER){
				if(changeKey){
					changeKey=false;
					return;
				}
				else
					changeKey=true;

				if(options.get(optionSelected).equals("Move Up")){
					lastKey=SpaceInvadersListener.UP;
					SpaceInvadersListener.UP = KeyEvent.VK_INSERT;
				}if(options.get(optionSelected).equals("Move Down")){
					lastKey=SpaceInvadersListener.DOWN;
					SpaceInvadersListener.DOWN = KeyEvent.VK_INSERT;
				}
				if(options.get(optionSelected).equals("Move Right")){
					lastKey=SpaceInvadersListener.RIGHT;
					SpaceInvadersListener.RIGHT = KeyEvent.VK_INSERT;
				}
				if(options.get(optionSelected).equals("Move Left")){
					lastKey=SpaceInvadersListener.LEFT;
					SpaceInvadersListener.LEFT = KeyEvent.VK_INSERT;
				}
				if(options.get(optionSelected).equals("Fire")){
					lastKey=SpaceInvadersListener.SHOT;
					SpaceInvadersListener.SHOT = KeyEvent.VK_INSERT;
				}
				if(options.get(optionSelected).equals("Special Attack")){
					lastKey=SpaceInvadersListener.LASER;
					SpaceInvadersListener.LASER = KeyEvent.VK_INSERT;
				}
			}
		}
		else{

			// cancel changing key
			if(e.getKeyCode()== KeyEvent.VK_ESCAPE){
				if(options.get(optionSelected).equals("Move Up")){
					SpaceInvadersListener.UP = lastKey;
				}if(options.get(optionSelected).equals("Move Down")){
					SpaceInvadersListener.DOWN = lastKey;
				}
				if(options.get(optionSelected).equals("Move Right")){
					SpaceInvadersListener.RIGHT = lastKey;
				}
				if(options.get(optionSelected).equals("Move Left")){
					SpaceInvadersListener.LEFT = lastKey;
				}
				if(options.get(optionSelected).equals("Fire")){
					SpaceInvadersListener.SHOT = lastKey;
				}
				if(options.get(optionSelected).equals("Special Attack")){
					SpaceInvadersListener.LASER = lastKey;
				}
			}

			// Change key
			else{
				if(options.get(optionSelected).equals("Move Up")){
					SpaceInvadersListener.UP = e.getKeyCode();
				}if(options.get(optionSelected).equals("Move Down")){
					SpaceInvadersListener.DOWN =  e.getKeyCode();
				}
				if(options.get(optionSelected).equals("Move Right")){
					SpaceInvadersListener.RIGHT =  e.getKeyCode();
				}
				if(options.get(optionSelected).equals("Move Left")){
					SpaceInvadersListener.LEFT =  e.getKeyCode();
				}
				if(options.get(optionSelected).equals("Fire")){
					SpaceInvadersListener.SHOT =  e.getKeyCode();
				}
				if(options.get(optionSelected).equals("Special Attack")){
					SpaceInvadersListener.LASER =  e.getKeyCode();
				}
				changeKey=false;
			}
		}
	}

}
