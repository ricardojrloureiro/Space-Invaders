package SpaceInvaders.GUI;

import javax.swing.JFrame;

/**
 * Window extends JFrame. Game main frame.
 * @author Jose Miguel Melo & Ricardo Loureiro
 */
public class Window extends JFrame{

	private static final long serialVersionUID = 9187970581037768248L;
	private String name;

    /**
     * Constructor
     * @param name of the current window
     */
	public Window(String name){
		this.name = name;
	}

    /**
     * returns the current window name
     * @return
     */
	public String getName(){ return name; }

    /**
     * sets the current window name
     * @param name
     */
	public void setName(String name){ this.name = name;}
	
	
}