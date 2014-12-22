import java.awt.Image;

import javax.swing.ImageIcon;


public class Road extends GameObject{

	protected Image image;
	public Road(){
		super();
		gameObjectState = State.notEaten;
		
		height = 40;
		width = 40;
		
		rectangle.x = getX();
		rectangle.y = getY();
		
		rectangle.height = getHeight();
		rectangle.width = getWidth();
	}
	public Road(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		gameObjectState = State.notEaten;
		
		height = 40;
		width = 40;
		
		rectangle.x = getX();
		rectangle.y = getY();
		
		rectangle.height = getHeight();
		rectangle.width = getWidth();
	}
	
	public char toChar(){
		return 'R';
	}
}