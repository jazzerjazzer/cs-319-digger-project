
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;


public class Coin extends GameObject{

	protected Image image;
	protected int score;
	
	public Coin(int x, int y){
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
	
	@Override
	public void paint(Graphics g){
		if(gameObjectState == State.notEaten){
			g.drawImage(image, x, y, null);
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
