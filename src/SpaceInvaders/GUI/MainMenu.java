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
 * MainMenu extends JPanel. Also Implements KeyListener.
 * Used to display the main menu to screen.
 * 
 * @author Jose Miguel Melo & Ricardo Loureiro
 */
public class MainMenu extends JPanel implements KeyListener{

	private static final long serialVersionUID = -2252184054024940979L;

	/** ArrayList of strings containing all menu options */
	private ArrayList<String> options;
	/** Index, in the array list, of selected option */
	private int optionSelected;
	/** Main menu background image*/
	private BufferedImage backgroundImage;

	/** Main menu character size */
	private int charSize = 48;
	/** Main menu title character size */
	private int titleCharSize = 56;

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
	public MainMenu(ArrayList<String> options, BufferedImage backgroundImage, Thread aboveThread){
		this.options = options;
		this.backgroundImage = backgroundImage;
		this.optionSelected = 0;
		this.setFocusable(false);
		visible=false;
		this.setVisible(visible);
		this.addKeyListener(this);
		this.aboveThread=aboveThread;
	}

	/**
	 * @param g Graphics to draw text
	 * @param text Text to be rendered
	 * @param pos Position in screen to render text
	 * @param rgb Integer with rgb color code. Set text color.
	 */
	public void drawText(Graphics g, String text, Position pos, int rgb){
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
		g.drawString("Space Invaders", this.getHeight()/8, this.getWidth()/8 );


		for(int i = 0 ; i < options.size(); i++){
			if(optionSelected==i){
				drawText(g, "> "+options.get(i), new Position(this.getHeight()/8, this.getWidth()/8 + (i+1)*(charSize+10)), selectedOptionColor);
			}else{
				drawText(g, options.get(i), new Position(this.getHeight()/8, this.getWidth()/8 + (i+1)*(charSize+10)), notSelectedOptionColor);
			}
		}
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


	public void keyTyped(KeyEvent e) {}



	public void keyPressed(KeyEvent e) {
	}

    /**
     * allows the user to change between Play game, Options, leaderboards or exit
     * @param e
     */

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
            optionSelected = (optionSelected+1) % options.size();
        }
		else if(e.getKeyCode() == KeyEvent.VK_UP){
			if(optionSelected==0)
				optionSelected=options.size()-1;
			else
				optionSelected = (optionSelected-1) % options.size();
		}
		else if(e.getKeyCode() == KeyEvent.VK_ENTER){
			switch(optionSelected){
			case 0:
				this.setInvisible();
				((SpaceInvadersGame)aboveThread).getSpaceInvadersPanel().reset();
				((SpaceInvadersGame)aboveThread).getSpaceInvadersPanel().setVisible();
				break;
			case SpaceInvadersGame.OPTION_MENU:
				((SpaceInvadersGame)aboveThread).getOptionMenu().setVisible();
				this.setInvisible();
				break;
			case SpaceInvadersGame.LEADERBOARD_MENU:
                ((SpaceInvadersGame)aboveThread).getLeaderBoardMenu().setVisible();
                this.setInvisible();
				break;
			case SpaceInvadersGame.EXIT_MENU:
				Game.spaceInvaders.stopThread();
				break;
			default:
				break;
			}
		}
	}
}
