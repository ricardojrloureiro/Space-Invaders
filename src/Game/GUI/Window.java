package Game.GUI;

import javax.swing.JFrame;

public class Window extends JFrame{

	private static final long serialVersionUID = 9187970581037768248L;
	private String name;

	public Window(String name){
		this.name = name;
	}
	
	public String getName(){ return name; }
	public void setName(String name){ this.name = name;}
	
	
}
