

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Bonus extends GameObject{

	protected Image image;

	public Bonus(int x, int y){
		super();
		gameObjectState = State.notEaten;		

		height = 40;
		width = 40;

		this.x = x;
		this.y = y;

		rectangle.x = getX();
		rectangle.y = getY();

		rectangle.height = getHeight();
		rectangle.width = getWidth();

		image = new ImageIcon("bonus3.png").getImage();

	}

	public State getBonusState() {
		return gameObjectState;
	}

	@Override
	public void paint(Graphics g){
		if(gameObjectState != State.eaten)
			g.drawImage(image, getX(), getY(), null);
	}


	
	public char toChar(){
		return 'B';
	}

}
