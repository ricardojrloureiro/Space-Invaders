package Game.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import Sprite.Position;

public class OptionMenu extends JPanel implements KeyListener{
	private static final long serialVersionUID = -5021379969150900668L;
	
	
	private ArrayList<String> options;
	private int optionSelected;
	private BufferedImage backgroundImage;

	private int charSize = 32;
	private int titleCharSize = 56;
	
	private int selectedOptionColor = 6579455;
	private int notSelectedOptionColor = 16777215;
	private int titleColor = 16041984;
	
	private boolean visible;
	
	private Thread aboveThread;
	
	public void setVisible(){
		visible=true;
		this.setVisible(visible);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	public void setInvisible(){
		this.setFocusable(false);
		this.visible = false;
		this.setVisible(visible);
	}
	public boolean isVisible(){
		return this.visible;
	}
	
	public OptionMenu(ArrayList<String> options, BufferedImage backgroundImage, Thread aboveThread){
		this.options = options;
		this.backgroundImage = backgroundImage;
		this.optionSelected = 0;
		this.setFocusable(false);
		this.addKeyListener(this);
		visible = false;
		this.setVisible(visible);
		this.aboveThread=aboveThread;
	}


	public void drawText(Graphics g, String text, Position pos, int rgb){
		g.setFont(new Font("mainFont", Font.BOLD, charSize));
		g.setColor(new Color(rgb));
		g.drawString(text, pos.getX(), pos.getY());
	}

	public void paintComponent(Graphics g){
		g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
		
		g.setFont(new Font("optionFont", Font.BOLD, titleCharSize));
		g.setColor(new Color(titleColor));
		g.drawString("Game Options", this.getHeight()/8, this.getWidth()/8 );
		
		for(int i = 0 ; i < options.size(); i++){
			if(optionSelected==i){
				drawText(g, "> "+options.get(i), new Position(this.getHeight()/8, this.getWidth()/8 + (i+1)*(charSize+15)), selectedOptionColor);
			}else{
				drawText(g, options.get(i), new Position(this.getHeight()/8, this.getWidth()/8 + (i+1)*(charSize+15)), notSelectedOptionColor);
			}
		}
	}

	public int getCurrentOption(){
		return this.optionSelected;
	}
	public void setCurrentOption(int optionSelected) {
		this.optionSelected=optionSelected;
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			optionSelected = (optionSelected+1) % options.size();
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP){
			if(optionSelected==0)
				optionSelected=2;
			else
				optionSelected = (optionSelected-1) % options.size();
		}
		else if(e.getKeyCode() == KeyEvent.VK_ENTER){
			
		}
		else if(e.getKeyCode()== KeyEvent.VK_ESCAPE){
			this.setInvisible();
			((SpaceInvadersGame)aboveThread).getMainMenu().setVisible();
		}
	}
	
	
}
