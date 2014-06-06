package SpaceInvaders.GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import SpaceInvaders.Objects.Drawable;

public class LostGameDialog extends JPanel implements Drawable, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5557147814372408993L;
	private int points;
	private int bestScore;
	private boolean visible;
	private final int textSize = 40;
	private final String MESSAGE = "YOU LOST!! points: ";
	private String name = "";
	
	
	public LostGameDialog(int points, int bestScore){
		this.points = points;
		visible = true;
		this.bestScore = bestScore;
		this.setVisible(true);
		this.setFocusable(true);
		this.requestFocus();
	}
	
	public boolean isVisible(){
		return this.visible;
	}
	public void setVisible(boolean visible){
		this.visible = visible;
	}
	
	@Override
	public void draw(Graphics g) {
		 g.setColor(Color.RED);
		 g.setFont(new Font("lifeFont", Font.BOLD, textSize));
		 g.drawString(MESSAGE + points, SpaceInvadersGame.WIDTH / 2 - 240, SpaceInvadersGame.HEIGHT / 2);
		 if(points > bestScore){
			 g.setFont(new Font("lifeFont", Font.BOLD, 20));
			 g.drawString("Record Name: "+ name, SpaceInvadersGame.WIDTH / 2 - 240, SpaceInvadersGame.HEIGHT / 2 + 30);
		 }
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			visible = false;
		
		if(points > bestScore){
			name = name + String.valueOf(e.getKeyChar());
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
