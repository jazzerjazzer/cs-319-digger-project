import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Tile extends GameObject{

	protected Image image;
	
	public Tile(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		gameObjectState = State.notEaten;
		
		height = 10;
		width = 10;
		
		rectangle.x = getX();
		rectangle.y = getY();
		
		rectangle.height = getHeight();
		rectangle.width = getWidth();
		
		image = new ImageIcon("earth_tile.jpg").getImage();
	}
	
	@Override
	public void paint(Graphics g){
		if(gameObjectState != State.eaten)
			g.drawImage(image, getX(), getY(), null);
	}
	
	public char toChar(){
		return 'T';
	}
}
