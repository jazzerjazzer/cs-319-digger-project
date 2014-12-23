
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Bag extends Coin{

	public Bag(int x, int y) {
		super(x, y);
		image = new ImageIcon("Bag3.png").getImage();
		score = 50;
	}
	@Override
	public void paint(Graphics g){
		if(gameObjectState != State.eaten)
			g.drawImage(image, getX(), getY(), null);
	}
}

